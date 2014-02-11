package se2.day07.upload2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import se2.day06.download1.FileInfo;

public class UploadServer {
	private static final String DEST_PATH = "E:/java_test/dest/";

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("等待客户端连接、、、");
			while (true) {
				Socket socket = server.accept();
				pool.execute(new UploadTask(socket));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 定义上传任务类
	static class UploadTask implements Runnable {
		Socket socket;

		public UploadTask(Socket socket) {
			super();
			this.socket = socket;
		}

		public void run() {
			// 声明流引用变量
			RandomAccessFile raf = null;
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				FileInfo info = (FileInfo) ois.readObject();
				raf = new RandomAccessFile(DEST_PATH + info.getFileName(), "rw");
				byte[] buffer = new byte[1024];
				int len;
				// 定位至上次保存的位置
				raf.seek(info.getPosition());
				InputStream in = socket.getInputStream();
				while ((len = in.read(buffer)) != -1) {
					raf.write(buffer, 0, len);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (raf != null) {
						raf.close();
					}
					if (ois != null) {
						ois.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
