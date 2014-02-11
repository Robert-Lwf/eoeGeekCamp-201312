package se2.day06.socket1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDownload {
	static final String DEST_PATH = "E:/java_test/dest/";
	static final String FILENAME = "spjj.zip";

	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9999);
			FileInfo info = new FileInfo(FILENAME);
			oos = new ObjectOutputStream(socket.getOutputStream());
			InputStream in = socket.getInputStream();
			File file = new File(DEST_PATH + FILENAME);
			fos = new FileOutputStream(file);
			oos.writeObject(info);
			byte[] buffer = new byte[1024 * 10];
			int len;
			while ((len = in.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			System.out.println(FILENAME + "复制完毕！");
		} catch (UnknownHostException e) {
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
				if (socket != null) {
					socket.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
