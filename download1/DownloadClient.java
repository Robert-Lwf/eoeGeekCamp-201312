package se2.day06.download1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DownloadClient {
	private static final String DEST_PATH = "E:/java_test/dest/";
	private static final String FILENAME = "spjj.zip";

	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9999);
			oos = new ObjectOutputStream(socket.getOutputStream());
			FileInfo info = new FileInfo(FILENAME, 0);
			// 向服务端发送封装了文件名的info对象
			oos.writeObject(info);
			fos = new FileOutputStream(DEST_PATH + FILENAME);
			byte[] buffer = new byte[1024 * 10];
			int len;
			InputStream in = socket.getInputStream();
			System.out.println(FILENAME + "开始下载");
			while ((len = in.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			System.out.println(FILENAME + "下载完成");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(oos!=null){
					oos.close();
				}
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
