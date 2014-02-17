package com.eoe.se2.day11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

public class Test07_Download2 {
	static final String BASE_URL = "http://127.0.0.1/httpcomponents-client-4.3.2-bin.zip";
	static final String FILENAME = "httpcomponents-client-4.3.2-bin.zip";
	static final String RECORD_FILENAME = "record.dat";
	static final String DEST_PATH = "E:/java_test/dest";
	static boolean isContinue = true;

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				System.out.println("按任意键停止下载");
				new Scanner(System.in).next();
				isContinue = false;
			};
		}.start();
		// 读取断点
		long position = readPosition();
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(BASE_URL);
		post.addHeader(new BasicHeader("Range", "bytes=" + position + "-"));
		RandomAccessFile raf = null;
		HttpResponse response;
		try {
			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (code != 200 & code != 206) {
				System.out.println("下载失败");
				return;
			}
			InputStream in = response.getEntity().getContent();
			raf = new RandomAccessFile(DEST_PATH + FILENAME, "rw");
			raf.seek(position);
			int len;
			byte[] buffer = new byte[1024 * 10];
			while ((len = in.read(buffer)) != -1 && isContinue) {
				raf.write(buffer, 0, len);
				position += len;
			}
			if (!isContinue) {
				savePosition(position);
			} else {
				System.out.println(FILENAME + "下载完成！");
				File file = new File(DEST_PATH + RECORD_FILENAME);
				if (file.exists()) {
					file.delete();
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}

	}

	static long readPosition() {
		DataInputStream dis = null;
		File file = new File(DEST_PATH + RECORD_FILENAME);
		if (!file.exists()) {
			return 0;
		}
		try {
			dis = new DataInputStream(new FileInputStream(file));
			long position = dis.readLong();
			return position;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	static void savePosition(long position) {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream(DEST_PATH
					+ RECORD_FILENAME));
			dos.writeLong(position);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
