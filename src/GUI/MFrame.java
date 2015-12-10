package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import client.Connection;
import client.Main;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventListener;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.Button;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class MFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IpTextField = new JTextField();
	private JTextField LoginTextField;
	JPanel panel = new JPanel();
	JButton ConnectButton = new JButton("Connect");
	JButton DisconnectButton = new JButton("Disconnect");
	JLabel IpLabel = new JLabel("Ip adress to connect:");
	JLabel LoginLabel = new JLabel("Your login:");
	JPanel panel_1 = new JPanel();
	Color MsgPaneColor = new Color(167, 255, 235);
	JButton SendButton = new JButton("");
	JTextPane MsgTextPane = new JTextPane();
	String login = null;
	public JTextArea MsgTextArea = new JTextArea();
	
	
	
	
	public MFrame() {
		setTitle("ChatApp 2015");
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Color LeftPanelColor = new Color(100, 255, 218);
		
		panel.setBackground(LeftPanelColor);
		panel.setBorder(null);
		panel.setBounds(0, 0, 220, 398);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Color btnColor = new Color(29, 233, 182);
		
		ConnectButton.setContentAreaFilled(false);
		ConnectButton.setOpaque(true);
		ConnectButton.setBackground(btnColor);
		ConnectButton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		ConnectButton.setBounds(10, 330, 95, 25);
		panel.add(ConnectButton);
		ConnectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login = LoginTextField.getText();
				String Ip = IpTextField.getText();
				IpTextField.setEditable(false);
				LoginTextField.setEditable(false);
				ConnectButton.setEnabled(false);
				
				System.out.println(login + " to " + Ip);
				Connection connection = new Connection(Ip);	
			}
		});
		
		
		DisconnectButton.setContentAreaFilled(false);
		DisconnectButton.setOpaque(true);
		DisconnectButton.setBackground(btnColor);
		DisconnectButton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		DisconnectButton.setBounds(115, 330, 95, 25);
		panel.add(DisconnectButton);
		DisconnectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginTextField.setText("");
				IpTextField.setText("");
				IpTextField.setEditable(true);
				LoginTextField.setEditable(true);
				ConnectButton.setEnabled(true);
				try {
					Connection.disconnect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		IpTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		IpTextField.setBounds(10, 297, 200, 23);
		panel.add(IpTextField);
		IpTextField.setColumns(10);
		
		
		IpLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		IpLabel.setBounds(10, 273, 200, 23);
		panel.add(IpLabel);
		
		LoginTextField = new JTextField();
		LoginTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LoginTextField.setColumns(10);
		LoginTextField.setBounds(10, 250, 200, 23);
		panel.add(LoginTextField);
		
		
		LoginLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		LoginLabel.setBounds(10, 225, 200, 23);
		panel.add(LoginLabel);
		
		
		panel_1.setBackground(LeftPanelColor);
		panel_1.setBounds(218, 0, 426, 398);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		MsgTextPane.setBounds(10, 320, 340, 64);
		panel_1.add(MsgTextPane);
		
		
		
		SendButton.setContentAreaFilled(false);
		SendButton.setOpaque(true);
		SendButton.setBackground(LeftPanelColor);
		SendButton.setIcon(new ImageIcon("C:\\Users\\kadimka\\Desktop\\message32.png"));
		SendButton.setBounds(352, 320, 64, 64);
		panel_1.add(SendButton);
		MsgTextArea.setRows(100);
		MsgTextArea.setColumns(100);
		MsgTextArea.setLineWrap(true);
		MsgTextArea.setBounds(10, 11, 406, 303);
		
		panel_1.add(MsgTextArea);
		SendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = login + ": " + MsgTextPane.getText();
				MsgTextPane.setText("");
				MsgTextPane.requestFocus();
				try {
					Connection.sendMessegeToServer(msg);
					

					SwingUtilities.invokeLater(new Runnable() {
					    @Override
					    public void run() {
					    	MsgTextArea.append(Connection.getMsgToClient() + "\n");
							
					    }
					  });
					
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		}
		
	}




