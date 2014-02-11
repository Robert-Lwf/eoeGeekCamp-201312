package se2.day06.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTask2 {
	public static void main(String[] args) {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			Socket socket = new Socket("127.0.0.1", 9999);
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			Scanner scanner = new Scanner(System.in);
			while (true) {
				String answer = reader.readLine();
				System.out.println(answer);
				if (answer.indexOf("88") >= 0) {
					writer.println("88");
					writer.flush();
					break;
				}
				System.out.print("客户2:");
				String question = scanner.next();
				writer.println("客户2:" + question);
				writer.flush();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (writer != null) {
				writer.close();
			}
		}
	}
}
