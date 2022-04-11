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
import customer.Payments;
import gui.CustomerDashboard;
import gui.EmployeePortal;
import gui.QueryAccountStatus;
import gui.ResponseByRep;
import gui.ResponseByTechnician;
import gui.ViewAllComplaint;
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
	//private static final Logger logger = LogManager.getLogger(Client.class);
	
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
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendComplaint(Complaints compObj)
	{
		try
		{
			objOs.writeObject(compObj);
			//logger.info("Client Sent Complaint to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendComplaintId(String comId)
	{
		try
		{
			objOs.writeObject(comId);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendCustomerId(String customerId)
	{
		try
		{
			objOs.writeObject(customerId);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendCategory(String category)
	{
		try
		{
			objOs.writeObject(category);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendTechResponse(String response, String date)
	{
		try
		{
			objOs.writeObject(response);
			objOs.writeObject(date);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendRepResponse(String response)
	{
		try
		{
			objOs.writeObject(response);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendTechnician(String technician)
	{
		System.out.println("tech");
		try
		{
			objOs.writeObject(technician);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendLoginDetails(String id, String password)
	{
		try
		{
			objOs.writeObject(id);
			objOs.writeObject(password);
			//logger.info("Client Sent Login Details to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}	
	public void receiveResponse()
	{
		boolean found=false;
		try
		{
			if(action.equalsIgnoreCase("Add"))
			{
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null, "Complaint Added Successfully","Lodge Status", JOptionPane.INFORMATION_MESSAGE);
				//	logger.info("Request To Add Complaint Successful");
				}
			}
			if(action.equals("ViewComplaint"))
			{
				complaint = (Complaints) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
				//	logger.info("Request To View Complaint Successful");
					ViewComplaint view = new ViewComplaint();
					view.setText(complaint);
				}
			}
			if(action.equals("QueryStatus"))
			{
				payment =  (Payments) objIs.readObject();
				
				if(payment == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
				//	logger.info("Request To Query Account Status Successful");
					QueryAccountStatus query = new QueryAccountStatus();
					query.setText(payment);
				}
			}
			if(action.equals("AllComplaints"))
			{
				allComplaints =  (Queue<Complaints>) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
					ViewAllComplaint viewAll = new ViewAllComplaint();
					viewAll.table(allComplaints);
				}
			}
			if(action.equals("AllPayments"))
			{
				allPayments =  (Queue<Payments>) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
					ViewPayments viewAll = new ViewPayments();
					viewAll.table(allPayments);
					
				}
			}
			if(action.equals("ResponseView"))
			{
				complaint = (Complaints) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
					ResponseByRep respond = new ResponseByRep();
					respond.setText(complaint);
				}
			}
			if(action.equals("ResponseViewTech"))
			{
				complaint = (Complaints) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
					ResponseByTechnician respond = new ResponseByTechnician();
					respond.setText(complaint);
				}
			}
			if(action.equals("Respond"))
			{
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null, "Record updated successfully","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Record not saved","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
				if(action.equals("RepRespond"))
				{
						Boolean flag = (Boolean) objIs.readObject();
						if(flag == true)
						{
							JOptionPane.showMessageDialog(null, "Record updated successfully","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Record not saved","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
						}
				}
				if(action.equals("Assign"))
				{
					System.out.println("receive");
					Boolean flag = (Boolean) objIs.readObject();
					if(flag == true)
					{
						JOptionPane.showMessageDialog(null, "Record updated successfully","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Record not saved","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			if(action.equals("ByCategory"))
			{
				allComplaints =  (Queue<Complaints>) objIs.readObject();
				if (complaint != null)
				{
					//JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
					ViewByCategory viewAll = new ViewByCategory();
					viewAll.table(allComplaints);
				}
			}
			if(action.equals("Assign"))
			{
				allComplaints =  (Queue<Complaints>) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
//					ViewByCategory viewAll = new ViewByCategory();
//					viewAll.table(allComplaints);
				}
			}
			if(action.equals("ViewDetails"))
			{
				complaint = (Complaints) objIs.readObject();
				payment = (Payments) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
					//ViewComplaint view = new ViewComplaint();
					//view.setText(complaint);
					System.out.println(complaint);
					System.out.println(payment);
				}
			}
			if(action.equals("CountRecords"))
			{
				int total = (int) objIs.readObject();
				System.out.println(total);
				if(total >= 0)
				{
					JOptionPane.showMessageDialog(null, "Record(s) Count Successful","Count Status", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(total == 0)
				{
					JOptionPane.showMessageDialog(null, "Failed To Count Records","Count Status", JOptionPane.ERROR_MESSAGE);
				}
				EmployeePortal emp = new EmployeePortal();
				emp.setNewText(total);
			}
			if(action.equals("EmployeeLogin"))
			{
				found = (boolean) objIs.readObject();
				
				if(found == false)
				{
					JOptionPane.showMessageDialog(null, "Incorrect Login Details","Login Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Login Sucessful","Login Status", JOptionPane.INFORMATION_MESSAGE);
					EmployeePortal empPortal = new EmployeePortal();
				}
			}
			if(action.equals("CustomerLogin"))
			{
				found = (boolean) objIs.readObject();
				
				if(found == false)
				{
					JOptionPane.showMessageDialog(null, "Incorrect Login Details","Login Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Login Sucessful","Login Status", JOptionPane.INFORMATION_MESSAGE);
					CustomerDashboard cusDash = new CustomerDashboard();
				}
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
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		new Client();
	}
}
