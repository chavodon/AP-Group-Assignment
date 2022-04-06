package tcpServer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import domain.Cus;

public class TcpServer {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dBConn = null;
	private Statement stmt;
	private ResultSet result = null;

	public TcpServer() {
		this.createConnection();
		this.waitForRequests();
	}
	
	private void createConnection() {
		try {
			this.serverSocket = new ServerSocket(8888);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		
	private static Connection getDatabaseConnection() {
		if (dBConn == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/customersdb";
				dBConn = DriverManager.getConnection(url, "root", "");
				
				JOptionPane.showMessageDialog(null, "DB Connection Established", "CONNECTION STATUS", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Could not connect to database\n" + ex, "Connection Failure", JOptionPane.ERROR_MESSAGE);
			}
		}
		return dBConn;
	}
	
	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private Cus findCustomerById(int id) {
		Cus cs = new Cus();
		String query = "SELECT * FROM customersdb.customers WHERE id = " + id;
		try {
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				cs.setId(result.getString(2));
				cs.setFname(result.getString(3));
				cs.setLname(result.getString(4));
				cs.setEmail(result.getString(5));
				cs.setContact(result.getString(6));
				cs.setAddress(result.getString(7));
				System.out.println(result.getString(2) + result.getString(3) + result.getString(4) + result.getString(5) + result.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}
	
	private void waitForRequests() {
		String action = "";
		getDatabaseConnection();
		Cus cs = new Cus();
		try {
			while (true) {
				connectionSocket = serverSocket.accept();
				this.configureStreams();
				try {
					action = (String)objIs.readObject();
					
					if (action.equals("Find Customer")) {
						String cusId = (String) objIs.readObject();
						
						cs = findCustomerById(Integer.parseInt(cusId));
						objOs.writeObject(cs);
					}
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (ClassCastException ex) {
					ex.printStackTrace();
				}
				this.closeConnection();
			}
		} catch (EOFException ex) {
			System.out.println("Client has terminated connections with the server");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
