package se2.day06.socket1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDownload {
	static final String SRC_PATH = "E:/java_test/src";

	public static void main(String[] args) {
		Socket socket = null;
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("等待连接客户端、、、");
			socket = server.accept();
			ois = new ObjectInputStream(socket.getInputStream());
			OutputStream out = socket.getOutputStream();
			FileInfo info = (FileInfo) ois.readObject();
			fis = new FileInputStream(SRC_PATH + info.getFileName());
			byte[] buffer = new byte[1024 * 10];
			int len;
			while ((len = fis.read(buffer)) != -1) {
				out.write(buffer, 0, len);

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
				if (fis != null) {
					fis.close();
				}
				if (ois != null) {
					ois.close();
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
