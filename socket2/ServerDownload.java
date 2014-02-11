package se2.day06.socket2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import se2.day06.socket1.FileInfo;

public class ServerDownload {
	static final String SRC_PATH = "E:/java_test/src/";

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		try {
			ServerSocket server = new ServerSocket(9999);
			while (true) {
				Socket socket = server.accept();
				pool.execute(new DownloadTask(socket));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class DownloadTask implements Runnable {
		Socket socket;

		public DownloadTask(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			ObjectInputStream ois = null;
			FileInputStream fis = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				FileInfo info = (FileInfo) ois.readObject();
				fis = new FileInputStream(SRC_PATH + info.getFileName());
				OutputStream out = socket.getOutputStream();
				int len;
				byte[] buffer = new byte[1024 * 10];
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
					if (ois != null) {
						ois.close();
					}
					if (fis != null) {
						fis.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
