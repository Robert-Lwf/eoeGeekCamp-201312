package se2.day06.download2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import se2.day06.download1.FileInfo;

public class DownloadServer {
	private static final String SRC_PATH = "E:/java_test/src/";

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("等待客户端连接、、、");
			while (true) {
				Socket socket = server.accept();
				pool.execute(new Download(socket));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 定义下载任务类
	static class Download implements Runnable {
		Socket socket;// 从main方法中获取的客户端的socket

		public Download(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			// 声明相关的流引用变量
			ObjectInputStream ois = null;
			FileInputStream fis = null;
			try {
				OutputStream out = socket.getOutputStream();
				ois = new ObjectInputStream(socket.getInputStream());
				FileInfo info = (FileInfo) ois.readObject();
				fis = new FileInputStream(SRC_PATH + info.getFileName());
				byte[] buffer = new byte[1024 * 10];
				int len;
				System.out.println(info.getFileName() + "开始下载！");
				while ((len = fis.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				System.out.println(info.getFileName() + "下载完毕！");
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
					if (fis != null) {
						fis.close();
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
