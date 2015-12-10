package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.SwingUtilities;

import GUI.MFrame;

public class Connection extends MFrame {
	
	private static final long serialVersionUID = 1L;
	final static int port = 28411;
	private static String host = null;
	private static String msgToClient;
	static Socket socket;
	private static BufferedReader reader;
	private static PrintWriter writer;
	Thread thread = new Thread(new Listener());
	

/*	public Connection(){
		
	}*/
	public Connection(String Ip){
		setHost(Ip);
		try {
			socket = new Socket(getHost(), port);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void setMsgToClient(String msgToClient) {
		Connection.msgToClient = msgToClient;
		
	}
	public static String getMsgToClient() {
		System.err.println(msgToClient);
		return msgToClient;
	}
	public static String getHost() {
		return host;
	}
	public void setHost(String host) {
		Connection.host = host;
	}
	
	public void sendNickHello(String nick){
		
	}
	public void sendNickBusy(String nick){
		
	}
	public void accept(){
		
	}
	public void reject(){		
	}
	public static void sendMessegeToServer(String messege) throws UnknownHostException{
		try {
			
			writer = new PrintWriter(socket.getOutputStream());
			writer.println(messege);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void sendMessegeToClient(String messege){
		System.out.println(messege + " 2");
		setMsgToClient(messege);
		
		
	}
		
	public static void disconnect() throws IOException{
		socket.close();
	}

	private static class Listener implements Runnable{
	

		@Override
		public void run() {
			
			String msg = null;
			
			try {
				InputStreamReader is = new InputStreamReader(socket.getInputStream()) ;
				reader = new BufferedReader(is);
				while(true){
					msg=reader.readLine();
					System.out.println(msg + " 1");

				  sendMessegeToClient(msg);

					
					//SwingUtilities.invokeLater(thread);
					
				}
			} catch (Exception e) {}
			
		}
		
	}

}


