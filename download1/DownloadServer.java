package se2.day06.download1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DownloadServer {
	private static final String SRC_PATH = "E:/java_test/src/";

	public static void main(String[] args) {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		Socket socket = null;
		try {
			System.out.println("等待客户端连接、、、");
			ServerSocket server = new ServerSocket(9999);
			socket = server.accept();
			// 创建对象输入流
			ois = new ObjectInputStream(socket.getInputStream());
			// 获取客户端发送的下载文件名，该文件名封装在Info对象中
			FileInfo info = (FileInfo) ois.readObject();
			// 创建文件输入流
			fis = new FileInputStream(SRC_PATH + info.getFileName());
			OutputStream out = socket.getOutputStream();
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
		}

	}
}
