package se2.day06.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTalk {
	static int i = 1;

	public static void main(String[] args) {
		ExecutorService pools = Executors.newCachedThreadPool();
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("等待客户端连接、、、");
			while (true) {
				Socket socket = server.accept();
				pools.execute(new Server(socket, "客服：" + i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class Server implements Runnable {
		Socket socket;
		String name;

		public Server(Socket socket, String name) {
			super();
			this.socket = socket;
			this.name = name;
		}

		@Override
		public void run() {
			BufferedReader reader = null;
			PrintWriter writer = null;
			try {
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream());
				Scanner scanner = new Scanner(System.in);
				writer.println("亲，要点啥？");
				writer.flush();
				while (true) {
					String question = reader.readLine();
					System.out.println(question);
					if (question.indexOf("88") >= 0) {
						writer.println("88");
						writer.flush();
						i--;
						break;
					}
					System.out.println(name + ":");
					String answer = scanner.next();
					writer.println(name + ":" + answer);
					writer.flush();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
					if (writer != null) {
						writer.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
