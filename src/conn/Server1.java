package conn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;

public class Server1 {

	private static ArrayList<PrintWriter> streams;

	public static void main(String[] args) {
		go();
	}

	public static void go() {
		streams = new ArrayList<PrintWriter>();
		try {
			ServerSocket ss = new ServerSocket(28411);
			while (true) {
				Socket socket = ss.accept();
				System.out.println("got user");
				
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				streams.add(writer);

				Thread t = new Thread(new Listener(socket));
				t.start();
			}
		} catch (Exception e) {
		}
	}

	private static void tellEveryone(String msg) {
		java.util.Iterator<PrintWriter> it = streams.iterator();
		while (it.hasNext()) {
			try {
				PrintWriter writer = it.next();
				writer.println(msg);
				writer.flush();
				
			} catch (Exception e) {
			}
		}
	}

	private static class Listener implements Runnable {

		BufferedReader reader;

		Listener(Socket socket) throws IOException {
			InputStreamReader is = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(is);
		}

		@Override
		public void run() {
			String msg;
			try {
				while ((msg = reader.readLine()) != null) {
					System.out.println("Messege| " + msg);
					tellEveryone(msg);
				}
			} catch (Exception e) {
			}
		}
	}
}
