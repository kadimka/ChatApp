package conn;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class OutputThread implements Runnable {
	private Socket socket;
	private String msgToAllClient;
	public String getMsgToAllClient() {
		return msgToAllClient;
	}

	public void setMsgToAllClient(String msgToAllClient) {
		this.msgToAllClient = msgToAllClient;
	}

	public OutputThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			OutputStream sout = socket.getOutputStream();
			DataOutputStream out = new DataOutputStream(sout);
			out.writeUTF(getMsgToAllClient());
			out.flush();
		} catch (Exception e) {

		}
	}

}
