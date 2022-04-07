package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import customer.Complaints;
import gui.ViewComplaintStatus;

public class Client 
{
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action = "";
	Complaints complaint = new Complaints();
	
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
		System.out.println("In Send Action");
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
	public void sendComplaint(Complaints compObj, String customerId)
	{
		try
		{
			objOs.writeObject(compObj);
			objOs.writeObject(customerId);
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
			System.out.println("In Send ComplaintId");
			objOs.writeObject(comId);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void receiveResponse()
	{
		try
		{
			if(action.equalsIgnoreCase("Add"))
			{
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null, "Record added successfully","Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equals("Search"))
			{
				//Complaints complaint = new Complaints();
				complaint =  (Complaints) objIs.readObject();
				
				if(complaint == null)
				{
					JOptionPane.showMessageDialog(null, "Record could not be found","Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search successful","Find Record Status", JOptionPane.INFORMATION_MESSAGE);
					ViewComplaintStatus view = new ViewComplaintStatus();
					view.setText(complaint);
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
	public class Driver {
		public static void main(String[] args) 
		{
		}
}
}
