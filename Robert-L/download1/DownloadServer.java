package se2.day07.download1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadServer {
	private static final String SRC_PATH = "E:/java_test/src/";
	private static final int PORT = 9999;

	public static void main(String[] args) {
     ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
     
     try {
		ServerSocket server=new ServerSocket(PORT);
		System.out.println("等待客户端发送下载的文件名");
		while(true){
			Socket socket=server.accept();
			Record record=responseClient(socket);
			//若record非空，即客户端发送的是下载请求
			if(record!=null){
				//创建一个任务，放入线程池执行
				pool.execute(new DownloadTask(socket, record));
			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
	}

	/**
	 * 响应客户端发送的请求，请求分两种 1.获取文件名。responseClient返回null
	 * 2.请求下载。responseClient返回客户端发送的record对象
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static Record responseClient(Socket socket) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = null;
		ois = new ObjectInputStream(socket.getInputStream());
		// 读取客户端发送的RecordInfo对象
		RecordInfo info = (RecordInfo) ois.readObject();
		if ("download".equals(info.getRequestType())) {
			FileInputStream fis = new FileInputStream(SRC_PATH
					+ info.getRecord().getFileName());
			// 获取文件的长度
			int fileSize = fis.available();
			// 保存在info对象中
			info.setFileSize(fileSize);
			// 创建对象输出流
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			// 将包含文件长度的info对象发送给客户端
			oos.writeObject(oos);
		}
		return null;
	}

	// 下载任务类
	static class DownloadTask implements Runnable {
		Socket socket;
		Record record;
		final int BUF_SIZE = 1024 * 10;

		public DownloadTask(Socket socket, Record record) {
			super();
			this.socket = socket;
			this.record = record;
		}

		@Override
		public void run() {
			RandomAccessFile raf = null;
			try {
				OutputStream out = socket.getOutputStream();
				raf = new RandomAccessFile(SRC_PATH + record.getFileName(), "r");
				// 获取本块下载的起始位置
				long start = record.getStartPos();
				// 获取本块 下载的结束位置
				long end = record.getEndPos();
				raf.seek(start);
				byte[] buffer = new byte[BUF_SIZE];
				int len;
				while ((len = raf.read(buffer)) != -1 && start < end) {
					out.write(buffer, 0, len);
					start += len;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (raf != null) {
					try {
						raf.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
}
