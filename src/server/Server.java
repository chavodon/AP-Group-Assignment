package server;

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

import javax.swing.JOptionPane;

import customer.Complaints;

public class Server 
{
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	
	private static Connection dBConn;
	private java.sql.Statement stmt;
	private ResultSet result;
	
	public Server()
	{
		System.out.println("In server default class");
		this.createConnection();
		this.waitForRequests();
	}
	private void createConnection()
	{
		try 
		{
			serverSocket = new ServerSocket(4433);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private void configureStreams()
	{
		try 
		{
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private static Connection getDatabaseConnection()
	{
		if(dBConn == null)
		{
			try
			{
				String url = "jdbc:mysql://localhost:3306/customersdb";
				dBConn = DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "DB Connection Successful!","Connection Status", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null, "Could not connect to database\n" + e,"Connection Failure", JOptionPane.ERROR_MESSAGE);
			}
		}
		return dBConn;
	}
	private void closeConnection()
	{
		try 
		{
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private void addComplaint(Complaints complaint, String customerId)
	{
		String query = "INSERT INTO complaints values ('"+complaint.getcNo()+"', '"+complaint.getCategory()+"', '"+complaint.getDate()+"', '"+complaint.getDetails()+"', '"+customerId+"', '"+complaint.getStatus()+"', '"+complaint.getResponseDate()+"', '"+complaint.getRespondent()+"')";
		try
	     {
			stmt = dBConn.createStatement();
			
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
				JOptionPane.showMessageDialog(null, "Complaint Added Successfully!","Add Complaint Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				objOs.writeObject(false);
			}
	     } 
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	     catch (SQLException e) 
	     {
			e.printStackTrace();
		}
	}
	private Complaints viewComplaint(String compId)
	{
		Complaints complaint = new Complaints();
		String query = "SELECT * FROM complaints WHERE cNo = '"+compId+"' ";
	
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setCategory(result.getString(2));
				complaint.setDate(result.getString(3));
				complaint.setDetails(result.getString(4));
				complaint.setCustomerId(result.getString(5));
				complaint.setResponseDate(result.getString(6));
				complaint.setRespondent(result.getString(7));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return complaint;
	}
	private void waitForRequests()
	{
		String action = "";
		String customerId = "";
		getDatabaseConnection();
		Complaints complaint = new Complaints();
		
		try
		{
			while(true)
			{
				connectionSocket = serverSocket.accept();
				this.configureStreams();
				try
				{
					action = (String) objIs.readObject();
					if(action.equals("Add"))
					{
						complaint = (Complaints) objIs.readObject();
						customerId = (String)objIs.readObject();
						
						addComplaint(complaint, customerId);
						objOs.writeObject(true);
					}
					else if (action.equals("Search"))
					{
						System.out.println("Wait for Requests");
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						objOs.writeObject(complaint);
					}
				}
				catch(ClassNotFoundException ex)
				{
					ex.printStackTrace();
				}
				catch(ClassCastException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(EOFException ex)
		{
			System.out.println("Client has terminated connections with the server");
			ex.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		//System.out.println("Client has terminated connections with the server");
//		Server server = new Server();
		 new Server();
	}
}
