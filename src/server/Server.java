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
import customer.Customer;
import customer.Payments;
import employee.Employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server 
{
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	
	private static Connection dBConn;
	private static Connection empConn;
	private java.sql.Statement stmt;
	private ResultSet result;
	private static final Logger logger = LogManager.getLogger(Server.class);
	
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
				JOptionPane.showMessageDialog(null, "Customer DB Connected!","Connection Status", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null, "Failed To Connect To Customer DB!" + e,"Connection Failure", JOptionPane.ERROR_MESSAGE);
			}
		}
		return dBConn;
	}
	private static Connection getEmpDatabaseConnection()
	{
		if(empConn == null)
		{
			try
			{
				String url = "jdbc:mysql://localhost:3306/employeedb";
				empConn = DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "Employee DB Connected!","Connection Status", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null, "Failed To Connect To Employee DB!" + e,"Connection Failure", JOptionPane.ERROR_MESSAGE);
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

	//-----------COMPLAINT FUNCTIONALITIES-----------------------
	private void addComplaint(Complaints complaint)
	{
		String query = "INSERT INTO complaints values ('"+complaint.getcNo()+"', '"+complaint.getId()+"','"+complaint.getCategory()+"', '"+complaint.getDate()+"', '"+complaint.getDetails()+"', '"+complaint.getStatus()+"', '"+complaint.getAssignedTo()+"'"
				+ ", '"+complaint.getRepResponseDate()+"', '"+complaint.getRepResponse()+"','"+complaint.getRepRespondent()+"', '"+complaint.getTechResponseDate()+"', '"+complaint.getTechResponse()+"', '"+complaint.getTechRespondent()+"', '"+complaint.getVisitDate()+"')";
		try
	     {
			stmt = dBConn.createStatement();
			
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
				JOptionPane.showMessageDialog(null, "Complaint Lodged Successfully!","Lodge Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				objOs.writeObject(false);
			}
	     } 
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			logger.error("Input/Output Exception on Adding Complaint");
		}
	     catch (SQLException e) 
	     {
			e.printStackTrace();
			logger.error("SQL Exception on Adding Complaint");
		}
	}
	private Complaints viewComplaint(String compId)
	{
		Boolean found = false;
		Complaints complaint = new Complaints();
		String query = "SELECT * FROM complaints WHERE cNo = '"+compId+"' ";
		
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setAssignedTo(result.getString(7));	
				complaint.setRepResponseDate(result.getString(8));
				complaint.setRepResponse(result.getString(9));
				complaint.setRepRespondent(result.getString(10));
				complaint.setTechResponseDate(result.getString(11));
				complaint.setTechResponse(result.getString(12));
				complaint.setTechRespondent(result.getString(13));
				complaint.setVisitDate(result.getString(14));
				
				if(complaint.getcNo().equals(compId))
				{
					found = true;
				}
			}
			if(found==true)
			{
				JOptionPane.showMessageDialog(null, "Complaint Found!","View Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complaint Not Found!","View Status", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return complaint;
	}
	private void respondToComplaint(String techId, String compId, String response, String date)
	{
		String query = "UPDATE complaints SET techResponse = '"+response+"', visitDate = '"+date+"', techRespondent = '"+techId+"' WHERE cNo = '"+compId+"' ";
		try
	     {
			stmt = dBConn.createStatement();
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
			}
			else
			{
				objOs.writeObject(false);
				}
	     } 
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			logger.error("Input/Output Exception on Responding To Complaint");
		}
	     catch (SQLException e) 
	     {
			e.printStackTrace();
			logger.error("SQL Exception on Responding To Complaint");
		}
	}
	private void respondToComplaint(String id, String compId, String response)
	{
		String query = "UPDATE complaints SET repResponse = '"+response+"', repRespondent = '"+id+"' WHERE cNo = '"+compId+"' ";
		try
	     {
			stmt = dBConn.createStatement();
			
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
				JOptionPane.showMessageDialog(null, "Response Recorded Successfully!","Response Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Response Not Recorded","Response Status", JOptionPane.ERROR_MESSAGE);
				objOs.writeObject(false);
			}
	     } 
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			logger.error("Input/Output Exception on Responding To Complaint");
		}
	     catch (SQLException e) 
	     {
			e.printStackTrace();
			logger.error("SQLException on Responding To Complaint");
		}
	}
	private Queue<Complaints> viewPastComplaint(String customerId)
	{
		boolean found = false;
		Complaints complaint = new Complaints();
		Queue<Complaints> allComplaints = new LinkedList<Complaints>();
		
		String query = "SELECT * FROM complaints WHERE customerId  = '"+customerId+"' ";
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setAssignedTo(result.getString(7));	
				complaint.setRepResponseDate(result.getString(8));
				complaint.setRepResponse(result.getString(9));
				complaint.setRepRespondent(result.getString(10));
				complaint.setTechResponseDate(result.getString(11));
				complaint.setTechResponse(result.getString(12));
				complaint.setTechRespondent(result.getString(13));
				complaint.setVisitDate(result.getString(14));
				
				allComplaints.add(complaint);
				if(complaint.getId().equals(customerId))
				{
					found = true;
				}
				complaint = new Complaints();
			}
			if(found==true)
			{
				JOptionPane.showMessageDialog(null, "Complaint(s)Found!","View Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Complaint(s) Found!","View Status", JOptionPane.ERROR_MESSAGE);
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
		boolean found = false;
		Complaints complaint = new Complaints();
		Queue<Complaints> allComplaints = new LinkedList<Complaints>();
		String query = "SELECT * FROM complaints WHERE category  = '"+category+"' ";

		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setAssignedTo(result.getString(7));	
				complaint.setRepResponseDate(result.getString(8));
				complaint.setRepResponse(result.getString(9));
				complaint.setRepRespondent(result.getString(10));
				complaint.setTechResponseDate(result.getString(11));
				complaint.setTechResponse(result.getString(12));
				complaint.setTechRespondent(result.getString(13));
				complaint.setVisitDate(result.getString(14));
				
				allComplaints.add(complaint);
				if(complaint.getCategory().equals(category))
				{
					found = true;
				}
				complaint = new Complaints();
			}
			if(found==true)
			{
				JOptionPane.showMessageDialog(null, "Complaint(s)Found!","View Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Complaint(s) Found!","View Status", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allComplaints;
	}
	private void assignComplaint(String compId, String technician)
	{
		String query = "UPDATE complaints SET assignedTo = '"+technician+"' WHERE cNo = '"+compId+"' ";
		try
	     {
			stmt = dBConn.createStatement();
			
			if((stmt.executeUpdate(query)==1))
			{
				objOs.writeObject(true);
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
	private Queue<Complaints> countComplaints(String category)
	{
		Complaints complaint = new Complaints();
		Queue<Complaints> allComplaints = new LinkedList<Complaints>();
		String query = "SELECT * FROM complaints WHERE category = '"+category+"'";
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setAssignedTo(result.getString(7));	
				complaint.setRepResponseDate(result.getString(8));
				complaint.setRepResponse(result.getString(9));
				complaint.setRepRespondent(result.getString(10));
				complaint.setTechResponseDate(result.getString(11));
				complaint.setTechResponse(result.getString(12));
				complaint.setTechRespondent(result.getString(13));
				complaint.setVisitDate(result.getString(14));
		
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
	private Complaints viewTechComplaint(String compId, String techId)
	{
		Complaints complaint = new Complaints();
		String query = "SELECT * FROM complaints WHERE assignedTo = '"+techId+"' ";
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next())
			{
				complaint.setcNo(result.getString(1));
				complaint.setId(result.getString(2));
				complaint.setCategory(result.getString(3));
				complaint.setDate(result.getString(4));
				complaint.setDetails(result.getString(5));
				complaint.setStatus(result.getString(6));
				complaint.setAssignedTo(result.getString(7));	
				complaint.setRepResponseDate(result.getString(8));
				complaint.setRepResponse(result.getString(9));
				complaint.setRepRespondent(result.getString(10));
				complaint.setTechResponseDate(result.getString(11));
				complaint.setTechResponse(result.getString(12));
				complaint.setTechRespondent(result.getString(13));
				complaint.setVisitDate(result.getString(14));
				
				if(complaint.getcNo().equals(compId))
				{
					return complaint;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	private Customer viewCustomer(String customerId)
	{
		Customer customer = new Customer();
		String query = "SELECT * FROM customers WHERE id = '"+customerId+"' ";
		
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next())
			{
				customer.setId(result.getString(1));
				customer.setfName(result.getString(2));
				customer.setlName(result.getString(3));
				customer.setAddress(result.getString(4));
				customer.setEmail(result.getString(5));
				customer.setContact(result.getString(6));
				customer.setPassword(result.getString(7));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return customer;
	}
	//-------------ACCOUNT-PAYMENT RELATED FUNCTIONALIES------------------------
	private Payments queryAccount(String customerId)
	{
		boolean found = false;
		Payments payment = new Payments();
		String query = "SELECT * FROM payments WHERE customerId = '"+customerId+"' ORDER BY dueDate DESC LIMIT 1 "; //get latest due date payment
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next())
			{
				payment.setpNo(result.getString(1));
				payment.setId(result.getString(2));
				payment.setAmountDue(result.getDouble(3));
				payment.setAmountPaid(result.getDouble(4));
				payment.setPaymentDate(result.getString(5));
				payment.setStatus(result.getString(6));
				payment.setDueDate(result.getString(7));
				
				if(payment.getId().equals(customerId))
				{
					found = true;
				}
			}
			if(found==true)
			{
				JOptionPane.showMessageDialog(null, "Account Found!","Query Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Account Not Found!","Query Status", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception on Querying Account");
		}
		return payment;
	}
	private Queue<Payments> viewPastPayments(String customerId)
	{
		int value = 0;
		Payments payment = new Payments();
		Queue<Payments> allPayments = new LinkedList<Payments>();
		String query = "SELECT * FROM payments WHERE customerId  = '"+customerId+"' ";
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				payment.setpNo(result.getString(1));
				payment.setId(result.getString(2));
				payment.setAmountDue(result.getDouble(3));
				payment.setAmountPaid(result.getDouble(4));
				payment.setPaymentDate(result.getString(5));
				payment.setStatus(result.getString(6));
				payment.setDueDate(result.getString(7));
				
				if(payment.getId().equals(customerId))
				{
					value = 1;
				}
				allPayments.add(payment);
				payment = new Payments();
			}
			if(value == 0)
			{
				JOptionPane.showMessageDialog(null, "No Payment(s) Found!","View Status", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Payments(s) Found!","View Status", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			logger.error("SQL Exception on Viewing Past Payments");
		}
		return allPayments;
	}
	private int employeeLogin(String id, String password)
	{
		Employee emp = new Employee();
		int value=0;
		boolean found = false;
		boolean isRep = false;
		
		String query = "SELECT * FROM employee";
		try
		{
			stmt = empConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				emp.setId(result.getString(1));
				emp.setfName(result.getString(2));
				emp.setlName(result.getString(3));
				emp.setType(result.getString(4));
				emp.setPassword(result.getString(5));
				
				if(emp.getId().equals(id) && emp.getPassword().equals(password))
				{
					found = true;
					if(emp.getType().equals("Technician"))
					{
						value = 1;
					}
					if(emp.getType().equals("Representative"))
					{
						value = 2;
					}
					break;
				}
			}	
			if(found == false)
			{
				value = -1;
				JOptionPane.showMessageDialog(null, "Incorrect Login Details","Login Status", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Login Sucessful","Login Status", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			logger.error("SQL Exception on Employee Login Request");
		}
		return value;
	}
	private boolean customerLogin(String id, String password)
	{
		Customer customer = new Customer();
		boolean found = false;
		String query = "SELECT * FROM customers";
		try
		{
			stmt = dBConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				customer.setId(result.getString(1));
				customer.setfName(result.getString(2));
				customer.setlName(result.getString(3));
				customer.setAddress(result.getString(4));
				customer.setEmail(result.getString(5));
				customer.setContact(result.getString(6));
				customer.setPassword(result.getString(7));
				
				if(customer.getId().equals(id) && customer.getPassword().equals(password))
				{
					found = true;
					break;
				}
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			logger.error("SQL Exception on Customer Login Request");
		}
		return found;
	}

	//---------WAIT FOR REQUEST TO EXECUTE---------------------------------
	private void waitForRequests()
	{
		int value = 0;
		String action = "";
		String customerId = "";
		String category = "";
		String complaintId = "";
		boolean found = false;
		int numResolved = 0;
		int numUnresolved = 0;
		
		getDatabaseConnection();
		getEmpDatabaseConnection();
		Complaints complaint = new Complaints();
		Payments payment = new Payments();
		Customer customer = new Customer();
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
						addComplaint(complaint);
						objOs.writeObject(true);
						 logger.info("Server Fullfilled Add Complaint Request");
					}
					else if (action.equals("ViewComplaint"))
					{
						complaint=null;
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						objOs.writeObject(complaint);
						logger.info("Server Fullfilled View Complaint Request");
					}
					else if (action.equals("ViewDetails"))
					{
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						customer = viewCustomer(complaint.getId());
						objOs.writeObject(complaint);
						objOs.writeObject(customer);
						logger.info("Server Fullfilled View Complaints & Account Info Request");
					}
					else if (action.equals("ResponseView"))
					{
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						objOs.writeObject(complaint);
						logger.info("Server Fullfilled Representative View Complaint Request");
					}
					else if (action.equals("ResponseViewTech"))
					{
						String comId = (String)objIs.readObject();
						String techId = (String) objIs.readObject();
						complaint = viewTechComplaint(comId,techId);
						objOs.writeObject(complaint);
						logger.info("Server Fullfilled Technician View Complaint Request");
					}
					else if (action.equals("Respond"))
					{
						String comId = (String)objIs.readObject();
						String techId = (String)objIs.readObject();
						String response = (String)objIs.readObject();
						String visitDate = (String)objIs.readObject();
						respondToComplaint(techId, comId, response, visitDate);
						objOs.writeObject(true);
						logger.info("Server Fullfilled Technician Respond Request");
					}
					else if (action.equals("RepRespond"))
					{
						String comId = (String)objIs.readObject();
						String repId = (String)objIs.readObject();
						String response = (String)objIs.readObject();
						 respondToComplaint(repId, comId, response);
						objOs.writeObject(true);
						logger.info("Server Fullfilled Customer Rep Respond Request");
					}
					else if (action.equals("QueryStatus"))
					{
						customerId  = (String)objIs.readObject();
						payment = queryAccount(customerId);
						objOs.writeObject(payment);
						logger.info("Server Fullfilled Query Account Status Request");
					}
					else if (action.equals("AllComplaints"))
					{
						customerId  = (String)objIs.readObject();
						allComplaints = viewPastComplaint(customerId);
						objOs.writeObject(allComplaints);
						logger.info("Server Fullfilled View Past Complaints Request");
					}
					else if (action.equals("AllPayments"))
					{
						customerId  = (String)objIs.readObject();
						allPayments = viewPastPayments(customerId);
						objOs.writeObject(allPayments);
						logger.info("Server Fullfilled View Past Payments Request");
					}
					else if (action.equals("ByCategory"))
					{
						category  = (String)objIs.readObject();
						allComplaints = viewComplaintByCategory(category);
						objOs.writeObject(allComplaints);
						logger.info("Server Fullfilled View Complaints By Category Request");
					}
					if(action.equals("Assign"))
					{
						String comId = (String)objIs.readObject();
						String technician = (String)objIs.readObject();
						 assignComplaint(comId, technician);
						objOs.writeObject(true);
						logger.info("Server Fullfilled Assign Complaint Request");
					}
					else if (action.equals("ViewDetails"))
					{
						String comId = (String)objIs.readObject();
						complaint = viewComplaint(comId);
						customer = viewCustomer(complaint.getId());
						objOs.writeObject(complaint);
						objOs.writeObject(customer);
						logger.info("Server Fullfilled View Complaints & Account Info Request");
					}
					else if (action.equals("EmployeeLogin"))
					{
						String id = (String)objIs.readObject();
						String password =  (String)objIs.readObject();
						value = employeeLogin(id, password);
						objOs.writeObject(value);
						logger.info("Server Fullfilled Employee Login Request");
					}
					else if (action.equals("CustomerLogin"))
					{
						String id = (String)objIs.readObject();
						String password =  (String)objIs.readObject();
						found = customerLogin(id, password);
						objOs.writeObject(found);
						logger.info("Server Fullfilled Customer Login Request");
					}
					else if (action.equals("Count"))
					{
						category  = (String)objIs.readObject();
						allComplaints = countComplaints(category);
						for(Complaints comp: allComplaints)
						{
							if(comp.getStatus().equals("Unresolved"))
							{
								numUnresolved++;
							}
							if(comp.getStatus().equals("Resolved"))
							{
								numResolved++;
							}
						}
						objOs.writeObject(numUnresolved);
						objOs.writeObject(numResolved);
						System.out.println("Category "+category);
						numResolved = 0;
						numUnresolved = 0;
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
			logger.error("Server Connection Terminated");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		logger.info("Server Started/Connected");
		new Server();
	}
}
