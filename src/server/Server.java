package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import customer.Complaints;
import customer.Payments;

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
				JOptionPane.showMessageDialog(null, "Record added successfully","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
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
		System.out.println("In View Complaint");
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
	private Payments queryAccount(String customerId)
	{
		System.out.println("In Query");
		Payments payment = new Payments();
		
		String query = "SELECT * FROM payments WHERE customerId = '"+customerId+"' ORDER BY dueDate DESC LIMIT 1 "; //get latest due date payment
		
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next())
			{
				payment.setpNo(result.getString(1));
				payment.setCustomerId(result.getString(2));
				payment.setAmountDue(result.getDouble(3));
				payment.setAmountPaid(result.getDouble(4));
				payment.setPaymentDate(result.getString(5));
				payment.setDueDate(result.getString(6));
				payment.setStatus(result.getString(7));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return payment;
	}
	private Queue<Complaints> viewPastComplaint(String customerId)
	{
		System.out.println("In View All Complaint");
		Complaints complaint = new Complaints();
		String query = "SELECT * FROM complaints WHERE customerId  = '"+customerId+"' ";
		Queue<Complaints> allComplaints = new LinkedList<Complaints>();
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				//System.out.println("1");
				complaint.setcNo(result.getString(1));
				complaint.setCategory(result.getString(2));
				complaint.setDate(result.getString(3));
				complaint.setDetails(result.getString(4));
				complaint.setCustomerId(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setResponseDate(result.getString(7));
				complaint.setRespondent(result.getString(8));
				
				//System.out.println("1");
				allComplaints.add(complaint);
				complaint = new Complaints();
				System.out.println(complaint);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allComplaints;
	}
	private Queue<Payments> viewPastPayments(String customerId)
	{
		System.out.println("In View payments");
		Payments payment = new Payments();
		String query = "SELECT * FROM payments WHERE customerId  = '"+customerId+"' ";
		Queue<Payments> allPayments = new LinkedList<Payments>();
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				//System.out.println("1");
				payment.setpNo(result.getString(1));
				payment.setCustomerId(result.getString(2));
				payment.setAmountDue(result.getDouble(3));
				payment.setAmountPaid(result.getDouble(4));
				payment.setPaymentDate(result.getString(5));
				payment.setDueDate(result.getString(6));
				payment.setStatus(result.getString(7));
				
				//System.out.println("1");
				allPayments.add(payment);
				payment = new Payments();
				System.out.println(payment);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allPayments;
	}
	
	
	private void waitForRequests()
	{
		String action = "";
		String customerId = "";
		getDatabaseConnection();
		Complaints complaint = new Complaints();
		Payments payment = new Payments();
		Queue<Complaints> allComplaints = new LinkedList<Complaints>();
		Queue<Payments> allPayments = new LinkedList<Payments>();
		
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
					else if (action.equals("Query"))
					{
						System.out.println("Wait for Requests");
						customerId  = (String)objIs.readObject();
						payment = queryAccount(customerId);
						objOs.writeObject(payment);
					}
					else if (action.equals("All Complaints"))
					{
						System.out.println("Wait for Requests");
						customerId  = (String)objIs.readObject();
						
						allComplaints = viewPastComplaint(customerId);
						objOs.writeObject(allComplaints);
					}
					else if (action.equals("All Payments"))
					{
						System.out.println("Wait for Requests");
						customerId  = (String)objIs.readObject();
						
						allPayments = viewPastPayments(customerId);
						objOs.writeObject(allPayments);
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
				//this.closeConnection();
			}
		}
		catch(EOFException ex)
		{
			System.out.println("Client has terminated connections with the server");
			//ex.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		new Server();
	}
}
