package conn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadedEchoHandler implements Runnable {
	private Socket socket;
	server server = new server();
	
	public ThreadedEchoHandler(Socket socket){
		this.socket = socket;

	}
	public ThreadedEchoHandler(){
		
	}
	
	
	public void run(){
		try{
			InputStream sin = socket.getInputStream();
			DataInputStream in = new DataInputStream(sin);
			
			
			String line = null;

			while (true) {
				line = in.readUTF();
				System.err.println("User send msg: " + line);
				conn.server.setMsgToAllClient(line);
				
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
