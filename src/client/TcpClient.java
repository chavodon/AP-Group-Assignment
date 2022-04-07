package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import domain.Cus;

public class TcpClient {
	private Socket connectionSocket;
	private static ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action = "";
	
	public TcpClient() {
		this.createConnection();
		this.configureStreams();
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1", 8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void configureStreams() {
		try {
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendAction(String action) {
		this.action = action;
		try {
			objOs.writeObject(action);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendStudentId(String cusId) {
		try {
			objOs.writeObject(cusId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveResponse() {
		try {
			if (action.equalsIgnoreCase("Find Customer")) {
				Cus cs = new Cus();
				cs = (Cus) objIs.readObject();
				if (cs == null) {
					JOptionPane.showMessageDialog(null, "Record could not be found", "Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
