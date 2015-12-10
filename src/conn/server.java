package conn;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	private static String msgToAllClient;
	
	public static String getMsgToAllClient() {
		return msgToAllClient;
	}
	public static void setMsgToAllClient(String msgToAllClient) {
		server.msgToAllClient = msgToAllClient;
	}


	public static void main(String[] args) {
		final int port = 28411;
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("waiting for a client...");
			while (true) {
				Socket socket = ss.accept();
				System.out.println("ready");
				Runnable ri = new ThreadedEchoHandler(socket);
				Thread i = new Thread(ri);
				i.start();
				OutputStream sout = socket.getOutputStream();
				DataOutputStream out = new DataOutputStream(sout);
				out.writeUTF(getMsgToAllClient());
				
			}
			} catch (Exception e) {
			System.err.println("Error");
		}
	}
}
