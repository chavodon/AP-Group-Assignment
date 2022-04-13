package clientTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import customer.Complaints;
import customer.Customer;
import customer.Payments;
import gui.CustomerDashboard;
import gui.EmployeeLogInWindow;
import gui.QueryAccountStatus;
import gui.RepPortal;
import gui.ResponseByRep;
import gui.ResponseByTechnician;
import gui.TechPortal;
import gui.ViewAllComplaint;
import gui.ViewAllDetails;
import gui.ViewByCategory;
import gui.ViewComplaint;
import gui.ViewPayments;
import gui.WelcomeWindow;

public class Client 
{
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action = "";
	Complaints complaint = new Complaints();
	Payments payment = new Payments();
	Queue<Complaints> allComplaints = new LinkedList<Complaints>();
	Queue<Payments> allPayments = new LinkedList<Payments>();
	private static final Logger logger = LogManager.getLogger(Client.class);
	
	public Client()
	{
		this.createConnection();
		this.configureStreams();
	}
	private void createConnection()
	{
		try
		{
			connectionSocket = new Socket("127.0.0.1", 4433);
		}
		catch(IOException e)
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
	public void closeConnection()
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
	public void sendAction(String action)
	{
		this.action = action;
		try
		{
			objOs.writeObject(action);
			 logger.info("Client Sent Action Request");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Action To Server");
		}
	}
	public void sendComplaint(Complaints compObj)
	{
		try
		{
			objOs.writeObject(compObj);
			logger.info("Client Sent Complaint to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Complaint To Server");
		}
	}
	public void sendComplaintId(String comId)
	{
		try
		{
			objOs.writeObject(comId);
			logger.info("Client Sent Complaint Id to Server");
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Complaint Id To Server");
		}
	}
	public void sendCustomerId(String customerId)
	{
		try
		{
			objOs.writeObject(customerId);
			logger.info("Client Sent Customer Id to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Customer Id To Server");
		}
	}
	public void sendTechId(String techId)
	{
		try
		{
			objOs.writeObject(techId);
			logger.info("Client Sent Technician Id to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Technician Id To Server");
		}
	}
	public void sendCategory(String category)
	{
		try
		{
			objOs.writeObject(category);
			logger.info("Client Sent Complaint Category to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Category To Server");
		}
	}
	public void sendTechResponse(String id, String response, String date)
	{
		try
		{
			objOs.writeObject(id);
			objOs.writeObject(response);
			objOs.writeObject(date);
			logger.info("Client Sent Technician Response to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Technician Response To Server");
		}
	}
	public void sendRepResponse(String id, String response)
	{
		try
		{
			objOs.writeObject(id);
			objOs.writeObject(response);
			logger.info("Client Sent Representative Response to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Representative Response To Server");
		}
	}
	public void sendTechnician(String technician)
	{
		try
		{
			objOs.writeObject(technician);
			logger.info("Client Sent Technician to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Technician Info To Server");
		}
	}
	public void sendLoginDetails(String id, String password)
	{
		try
		{
			objOs.writeObject(id);
			objOs.writeObject(password);
			logger.info("Client Sent Login Details to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Login Details To Server");
		}
	}	
	public void receiveResponse()
	{
		int value = 0;
		boolean found = false;
		complaint = null;
		Customer customer = new Customer();
		try
		{
			if(action.equalsIgnoreCase("Add"))
			{
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null, "Complaint Added Successfully","Lodge Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Request To Add Complaint Successful");
				}
			}
			if(action.equals("ViewComplaint"))
			{
				complaint = (Complaints) objIs.readObject();
				if(complaint == null)
				{
					logger.error("Request To View Complaint Unsuccessful");
					return;
				}
				else
				{
					logger.info("Request To View Complaint Successful");
					ViewComplaint view = new ViewComplaint();
					view.setText(complaint);
				}
			}
			if(action.equals("ViewDetails"))
			{
				complaint = (Complaints) objIs.readObject();
				customer = (Customer) objIs.readObject();
				if(complaint == null)
				{
					logger.error("Request To View Complaint Unsuccessful");
					return;
				}
				else
				{
					logger.info("Request To View Complaint Successful");
					ViewAllDetails view = new ViewAllDetails();
					view.setText(complaint,customer);
				}
			}
			if(action.equals("QueryStatus"))
			{
				payment =  (Payments) objIs.readObject();
				
				if(payment == null)
				{
					logger.error("Request To Query Account Status Unsuccessful");
					return;
				}
				else
				{
					logger.info("Request To Query Account Status Successful");
					QueryAccountStatus query = new QueryAccountStatus();
					query.setText(payment);
				}
			}
			if(action.equals("AllComplaints"))
			{
				allComplaints = null;
				allComplaints =  (Queue<Complaints>) objIs.readObject();
				
				if(allComplaints == null)
				{
					logger.error("Request To View Past Complaints Unsuccessful");
					return;
				}
				else
				{
					logger.info("Request To View Past Complaints Successful");
					ViewAllComplaint viewAll = new ViewAllComplaint();
					viewAll.table(allComplaints);
				}
			}
			if(action.equals("AllPayments"))
			{
				logger.error("Request To View Past Payments Unsuccessful");
				allPayments =  (Queue<Payments>) objIs.readObject();
				
				if(allPayments == null)
				{
					return;
				}
				else
				{
					logger.info("Request To View Past Payments Successful");
					ViewPayments viewAll = new ViewPayments();
					viewAll.table(allPayments);
				}
			}
			if(action.equals("ResponseView"))
			{
				complaint = (Complaints) objIs.readObject();
				
				if(complaint == null)
				{
					return;
				}
				else
				{
					ResponseByRep respond = new ResponseByRep();
					respond.setText(complaint);
				}
			}
			if(action.equals("ResponseViewTech"))
			{
				complaint = (Complaints) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "No Such Complaint Assigned!","View Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Complaint Found!","View  Status", JOptionPane.INFORMATION_MESSAGE);
					ResponseByTechnician respond = new ResponseByTechnician();
					respond.setText(complaint);
				}
			}
			if(action.equals("Respond"))
			{
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true)
				{
					logger.info("Request To Respond To Complaint Successful");
					JOptionPane.showMessageDialog(null, "Response Recorded Successfully!","Response Status", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					logger.error("Request To Respond To Complaint Unsuccessful");
					JOptionPane.showMessageDialog(null, "Response Not Recorded!","Response Status", JOptionPane.ERROR_MESSAGE);
				}
			}
				if(action.equals("RepRespond"))
				{
						Boolean flag = (Boolean) objIs.readObject();
						if(flag == true)
						{
							logger.info("Request To Respond To Complaint Successful");
							JOptionPane.showMessageDialog(null, "Response Recorded Successfully!","Response Status", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							logger.error("Request To Respond To Complaint Unsuccessful");
							JOptionPane.showMessageDialog(null, "Response Not Recorded!","Response Status", JOptionPane.ERROR_MESSAGE);
						}
				}
				if(action.equals("Assign"))
				{
					Boolean flag = (Boolean) objIs.readObject();
					if(flag == true)
					{
						logger.info("Request To Assign Complaint Successful");
						JOptionPane.showMessageDialog(null, "Complaint Assigned Sucessfully!","Assign Status", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						logger.error("Request To Assign Complaint Unsuccessful");
						JOptionPane.showMessageDialog(null, "Complaint Not Assigned!","Assign Status", JOptionPane.ERROR_MESSAGE);
					}
				}
			if(action.equals("ByCategory"))
			{
				allComplaints=null;
				allComplaints =  (Queue<Complaints>) objIs.readObject();
				
				if (allComplaints != null)
				{
					logger.info("Request To View Complaints By Category Successful");
					ViewByCategory viewAll = new ViewByCategory();
					viewAll.table(allComplaints);
				}
			}
			if(action.equals("Count"))
			{
				int numUnresolved = (int) objIs.readObject();
				int numResolved = (int) objIs.readObject();
				RepPortal rep = new RepPortal();
				rep.setNumComplaints(numUnresolved, numResolved);
			}
			if(action.equals("EmployeeLogin"))
			{
				value = (int) objIs.readObject();
//				int numUnresolved = (int) objIs.readObject();
//				int numResolved = (int) objIs.readObject();
//				
				if(value == 1)
				{
					logger.info("Request to Login Successful");
					TechPortal tech = new TechPortal();
				}
				if(value == 2)
				{
					logger.info("Request to Login Successful");
					RepPortal rep = new RepPortal();
//					rep.setNumComplaints(numUnresolved, numResolved);
//					numResolved = 0;
//					numUnresolved = 0;
				}
				if(value == -1)
				{
					logger.error("Request to Login Failed");
					EmployeeLogInWindow login = new EmployeeLogInWindow();
				}
			}
			if(action.equals("CustomerLogin"))
			{
				found = (boolean) objIs.readObject();
				
				if(found == false)
				{
					JOptionPane.showMessageDialog(null, "Incorrect Login Details","Login Status", JOptionPane.ERROR_MESSAGE);
					logger.error("Request to Login Failed");
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Login Sucessful","Login Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Request to Login Successful");
					CustomerDashboard cusDash = new CustomerDashboard();
				}
			}
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
			logger.error("Class Not Found Exception");
		}
		catch(ClassCastException e)
		{
			e.printStackTrace();
			logger.error("Class Cast Exception");
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			logger.error("Input/Output Exception");
		}
	}
	public static void main(String[] args) 
	{
		new Client();
	}
}
