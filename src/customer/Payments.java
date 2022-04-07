package customer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import connector.DatabaseConnection;

public class Payments implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pNo;
	private String status;
	private double amountDue;
	private double amountPaid;
	private String dueDate;
	private String paymentDate;
	private Customer customer = new Customer();
	private String customerId;
	
	//private static final Logger logger = LogManager.getLogger(Payments.class);
	private static Connection dbConnect = DatabaseConnection.getConnection();
	private PreparedStatement pStmt;
	private ResultSet result;
	//Scanner inp = new Scanner(System.in);
	
	//Default Constructor
	public Payments() 
	{
		pNo = "";
		status = "";
		amountDue = 0.0;
		amountPaid = 0.0;
		dueDate = "";
		paymentDate = "";		
		customerId = customer.getId();
	}
	//Primary Constructor
	public Payments(String pNo, String status, double amountDue, double amountPaid, String dueDate, String paymentDate, Customer customer) 
	{
		this.pNo = pNo;
		this.status = status;
		this.amountDue = amountDue;
		this.amountPaid = amountPaid;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		this.customer = customer;
	}
	
	//Accessors
	public String getpNo() 
	{
		return pNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStatus() 
	{
		return status;
	}
	public double getAmountDue() 
	{
		return amountDue;
	}
	public double getAmountPaid() 
	{
		return amountPaid;
	}
	public String getDueDate() 
	{
		return dueDate;
	}
	public String getPaymentDate() 
	{
		return paymentDate;
	}
	public Customer getCustomer() 
	{
		return customer;
	}
	
	//Mutators
	public void setpNo(String pNo) 
	{
		this.pNo = pNo;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public void setAmountDue(double amountDue) 
	{
		this.amountDue = amountDue;
	}
	public void setAmountPaid(double amountPaid) 
	{
		this.amountPaid = amountPaid;
	}
	public void setDueDate(String dueDate) 
	{
		this.dueDate = dueDate;
	}
	public void setPaymentDate(String paymentDate)
	{
		this.paymentDate = paymentDate;
	}
	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}
	
	public void makeQuery(String customerId) 
	{		
		try 
		{	
			//String query = "SELECT *  payments WHERE customerId = '"+customerId+"' ";
			//String query = "SELECT TOP 1 '"+pNo+"', '"+status+"', '"+amountDue+"', '"+dueDate+"' FROM payments ORDER BY dueDate DESC ";
			String query = "SELECT * FROM payments WHERE customerId = '"+customerId+"' ORDER BY dueDate DESC LIMIT 1 "; //get latest due date payment
			
			//prepare the java statement
		     pStmt = dbConnect.prepareStatement(query);
		     
		     // execute the query, and get a java resultset
		     result = pStmt.executeQuery(query);
		     
		     // iterate through the java resultset
		      while (result.next())
		      {
		    	  pNo = result.getString("pNo");
			      customerId = result.getString("customerId");
		    	  amountDue = result.getDouble("amountDue");
		    	  dueDate = result.getString("dueDate");
		    	  amountPaid = result.getDouble("amountPaid");
		    	  paymentDate = result.getString("paymentDate");
		    	  status = result.getString("status");
		    	  
	    		  System.out.println(pNo +"\t"+ status +"\t\t" + amountDue +"\t" + dueDate);
		      }
		   pStmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void viewPastPayments(String customerId)
	{
		try 
		{	
			String query = "SELECT * FROM payments WHERE customerId = '"+customerId+"' ";
			
			//prepare the java statement
		     PreparedStatement st = dbConnect.prepareStatement(query);
		     
		     // execute the query, and get a java resultset
		     result = st.executeQuery(query);
		     
		     // iterate through the java resultset
		      while (result.next())
		      {
		    	  pNo = result.getString("pNo");
			      customerId = result.getString("customerId");
		    	  amountDue = result.getDouble("amountDue");
		    	  dueDate = result.getString("dueDate");
		    	  amountPaid = result.getDouble("amountPaid");
		    	  paymentDate = result.getString("paymentDate");
		    	  status = result.getString("status");

		    	  System.out.println(pNo +"\t"+ customerId +"\t"+ amountDue +"\t"+ dueDate +"\t"+ amountPaid +"\t"+ paymentDate +"\t"+ status);
		      }
		   st.close();
		   //logger.info("Selected and viewed all past payments.");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void registerPayment()
	{
		Scanner inp = new Scanner(System.in);
		Random rand = new Random();
		pNo = String.format("%04d",rand.nextInt(100));
		
		System.out.println("Enter the customer's id number: ");
		String customerId = inp.next();
		
		System.out.println("Enter the amount due: ");
		amountDue = inp.nextDouble();
		
		System.out.println("Enter the payment due date: ");
		dueDate = inp.next();
	
		System.out.println("Enter the amount paid (or 0.0): ");
		amountPaid = inp.nextDouble();
		
		System.out.println("Enter the date the was paid (or N/A): ");
		paymentDate = inp.next();
		
		if(amountPaid == amountDue || amountPaid > amountDue)
		{
			status = "Paid";
		}
		else if(amountPaid < amountDue)
		{
			status = "Partially Paid";
			amountDue = amountPaid - amountDue;
		}
		else if(amountPaid == 0)
		{
			status = "Unpaid";
		}
		 String query = "INSERT INTO payments values ('"+pNo+"', '"+customerId+"', '"+amountDue+"', '"+dueDate+"', '"+amountPaid+"', '"+paymentDate+"', '"+status+"')";
		 try
		   {
		   	// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
				
			// execute the preparedstatement
			   preparedStmt.execute();
			   JOptionPane.showMessageDialog(null, "Data Added!");
			    preparedStmt.close();
		   } 
		   catch (SQLException e1) 
		   {
			   e1.printStackTrace();
		   }
		// logger.info("Staff registered a customer payment.");
	}
	public void makePayment()
	{
		Scanner inp = new Scanner(System.in);
	
		System.out.println("Enter the payment id: ");
		pNo = inp.next();
		
		System.out.println("Enter the amount paid: ");
		amountPaid = inp.nextDouble();
		
		System.out.println("Enter the date the was paid: ");
		paymentDate = inp.next();
		
		if(amountPaid == amountDue || amountPaid > amountDue)
		{
			status = "Paid";
		}
		else if(amountPaid < amountDue)
		{
			status = "Partially Paid";
			amountDue = amountPaid - amountDue;
		}
		else
		{
			status = "Unpaid";
		}
		
		 String query = "UPDATE payments SET amountDue = '"+amountDue+"', amountPaid = '"+amountPaid+"',status = '"+status+"', paymentDate = '"+paymentDate+"' WHERE pNo = '"+pNo+"' ";
		 try
		   {
		   	// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
				
			// execute the preparedstatement
			   preparedStmt.execute();
			   JOptionPane.showMessageDialog(null, "Data Added!");
			    preparedStmt.close();
		   } 
		   catch (SQLException e1) 
		   {
			   e1.printStackTrace();
		   }
		 //logger.info("Customer made a payment.");
	}
}
