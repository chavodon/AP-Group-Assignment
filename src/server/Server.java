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
import java.sql.Statement;
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
				JOptionPane.showMessageDialog(null, "Failed To Connect To Database!" + e,"Connection Failure", JOptionPane.ERROR_MESSAGE);
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
	private int numRecords()
	{
		int count = 0;
		Complaints complaint = new Complaints();
		String query = "SELECT * FROM complaints";
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setCustomerId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setResponseDate(result.getString(7));
				complaint.setRespondent(result.getString(8));
				complaint.setResponse(result.getString(9));
				complaint.setAssignedTo(result.getString(10));
				complaint.setVisitDate(result.getString(11));

				count++;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return count;
	}
	//-----------COMPLAINT FUNCTIONALITIES-----------------------
	private void addComplaint(Complaints complaint)
	{
		String query = "INSERT INTO complaints values ('"+complaint.getcNo()+"', '"+complaint.getCustomerId()+"','"+complaint.getCategory()+"', '"+complaint.getDate()+"', '"+complaint.getDetails()+"', '"+complaint.getStatus()+"', '"+complaint.getResponseDate()+"', '"+complaint.getRespondent()+"','"+complaint.getResponse()+"', '"+complaint.getAssignedTo()+"', '"+complaint.getVisitDate()+"')";
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
		Complaints complaint = new Complaints();
		String query = "SELECT * FROM complaints WHERE cNo = '"+compId+"' ";
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setCustomerId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setResponseDate(result.getString(7));
				complaint.setRespondent(result.getString(8));
				complaint.setResponse(result.getString(9));
				complaint.setAssignedTo(result.getString(10));
				complaint.setVisitDate(result.getString(11));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println(complaint);
		return complaint;
	}
	private void respondToComplaint(String compId, String response, String date)
	{
		String query = "UPDATE complaints SET response = '"+response+"', visitDate = '"+date+"' WHERE cNo = '"+compId+"' ";
		try
	     {
			stmt = dBConn.createStatement();
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
				JOptionPane.showMessageDialog(null, "Record updated in update successfully","Record Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "sql wrong","Record Status", JOptionPane.INFORMATION_MESSAGE);
				
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
	private void respondToComplaint(String compId, String response)
	{
		String query = "UPDATE complaints SET response = '"+response+"' WHERE cNo = '"+compId+"' ";
				try
	     {
			stmt = dBConn.createStatement();
			
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
				JOptionPane.showMessageDialog(null, "Record updated in update successfully","Record Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "sql wrong","Record Status", JOptionPane.INFORMATION_MESSAGE);
				
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
				complaint.setcNo(result.getString(1));
				complaint.setCustomerId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setResponseDate(result.getString(7));
				complaint.setRespondent(result.getString(8));
				complaint.setResponse(result.getString(9));
				complaint.setAssignedTo(result.getString(10));
				complaint.setVisitDate(result.getString(11));
				allComplaints.add(complaint);
				complaint = new Complaints();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allComplaints;
	}
	private Queue<Complaints> viewComplaintByCategory(String category)
	{
		int count = 0;
		Complaints complaint = new Complaints();
		String query = "SELECT * FROM complaints WHERE category  = '"+category+"' ";
		Queue<Complaints> allComplaints = new LinkedList<Complaints>();
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setCustomerId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setResponseDate(result.getString(7));
				complaint.setRespondent(result.getString(8));
				complaint.setResponse(result.getString(9));
				complaint.setAssignedTo(result.getString(10));
				complaint.setVisitDate(result.getString(11));
				
				allComplaints.add(complaint);
				count++;
				complaint = new Complaints();
			}
			if(count == 0)
			{
				JOptionPane.showMessageDialog(null, "No Record Of Such Category!","Search Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Record(s) Found!","Search Status", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allComplaints;
	}
	private void assignComplaint(String compId, String technician)//FIX
	{
		String query = "UPDATE complaints SET assignedTo = '"+technician+"' WHERE cNo = '"+compId+"' ";
		try
	     {
			stmt = dBConn.createStatement();
			
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
				JOptionPane.showMessageDialog(null, "Record updated in update successfully","Record Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "sql wrong","Record Status", JOptionPane.INFORMATION_MESSAGE);
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
	//-------------ACCOUNT-PAYMENT RELATED FUNCTIONALIES------------------------
	private Payments queryAccount(String customerId)
	{
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
				payment.setStatus(result.getString(6));
				payment.setDueDate(result.getString(7));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println(payment);
		return payment;
	}
	private Queue<Payments> viewPastPayments(String customerId)
	{
		Payments payment = new Payments();
		String query = "SELECT * FROM payments WHERE customerId  = '"+customerId+"' ";
		Queue<Payments> allPayments = new LinkedList<Payments>();
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				payment.setpNo(result.getString(1));
				payment.setCustomerId(result.getString(2));
				payment.setAmountDue(result.getDouble(3));
				payment.setAmountPaid(result.getDouble(4));
				payment.setPaymentDate(result.getString(5));
				payment.setStatus(result.getString(6));
				payment.setDueDate(result.getString(7));
				
				allPayments.add(payment);
				payment = new Payments();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allPayments;
	}
	
	//---------WAIT FOR REQUEST TO EXECUTE---------------------------------
	private void waitForRequests()
	{
		String action = "";
		String customerId = "";
		String category = "";
		String complaintId = "";
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
						//customerId = (String)objIs.readObject();
						//System.out.println(complaint);
						addComplaint(complaint);
						objOs.writeObject(true);
					}
					else if (action.equals("ViewComplaint"))
					{
						System.out.println("Wait for Requests");
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						objOs.writeObject(complaint);
					}
					else if (action.equals("ResponseView"))
					{
						System.out.println("Wait for Requests");
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						objOs.writeObject(complaint);
					}
					else if (action.equals("ResponseViewTech"))
					{
						System.out.println("Wait for Requests");
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						objOs.writeObject(complaint);
					}
					else if (action.equals("Respond"))
					{
						System.out.println("Wait for Requests");
						String comId = (String)objIs.readObject();
						String response = (String)objIs.readObject();
						String visitDate = (String)objIs.readObject();
						respondToComplaint(comId, response, visitDate);
						objOs.writeObject(true);
					}
					else if (action.equals("RepRespond"))
					{
						System.out.println("Wait for Requests");
						String comId = (String)objIs.readObject();
						String response = (String)objIs.readObject();
						 respondToComplaint(comId, response);
						objOs.writeObject(true);
					}
					else if (action.equals("QueryStatus"))
					{
						System.out.println("Wait for Requests");
						customerId  = (String)objIs.readObject();
						payment = queryAccount(customerId);
						objOs.writeObject(payment);
					}
					else if (action.equals("AllComplaints"))
					{
						System.out.println("Wait for Requests");
						customerId  = (String)objIs.readObject();
						
						allComplaints = viewPastComplaint(customerId);
						objOs.writeObject(allComplaints);
					}
					else if (action.equals("AllPayments"))
					{
						System.out.println("Wait for Requests");
						customerId  = (String)objIs.readObject();
						
						allPayments = viewPastPayments(customerId);
						objOs.writeObject(allPayments);
					}
					else if (action.equals("ByCategory"))
					{
						category  = (String)objIs.readObject();
						
						allComplaints = viewComplaintByCategory(category);
						objOs.writeObject(allComplaints);
					}
					if(action.equals("Assign"))
					{
						System.out.println("waiting for method");
						String comId = (String)objIs.readObject();
						String technician = (String)objIs.readObject();
						 assignComplaint(comId, technician);
						objOs.writeObject(true);
					}
					else if (action.equals("ViewDetails"))
					{
						System.out.println("Wait for Requests");
						String comId = (String)objIs.readObject();
						customerId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						payment = queryAccount(customerId);
						objOs.writeObject(complaint);
						objOs.writeObject(payment);
					}
					
					else if (action.equals("CountRecords"))
					{
						System.out.println("Wait for Requests");
						//int total = (int)objIs.readObject();
						int total = numRecords();
						System.out.println(total);
						objOs.writeObject(total);
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
