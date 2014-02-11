package se2.day06.download2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import se2.day06.download1.FileInfo;

public class DownloadClient2 {
	private static final String DEST_PATH = "f:/java_test/dest/";
	private static final String FILENAME = "eclipse-cpp.zip";

	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9999);
			oos = new ObjectOutputStream(socket.getOutputStream());
			FileInfo info = new FileInfo(FILENAME, 0);
			oos.writeObject(info);// 向服务端发送info对象
			fos = new FileOutputStream(DEST_PATH + FILENAME);
			byte[] buffer = new byte[1024];
			int len;
			InputStream in = socket.getInputStream();
			while ((len = in.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
