package client;

import javax.swing.SwingUtilities;

import GUI.MFrame;

public class Client1 {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		      go();
		    }
		  });
		
		
	}

	private static void go() {
		MFrame frame = new MFrame();
		frame.setVisible(true);
	}

		
		
	}	

