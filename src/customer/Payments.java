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

public class Payments extends Customer implements Serializable
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
	
	//private static final Logger logger = LogManager.getLogger(Payments.class);
	private static Connection dbConnect = DatabaseConnection.getConnection();
	private PreparedStatement pStmt;
	private ResultSet result;
	//Scanner inp = new Scanner(System.in);
	
	//Default Constructor
	public Payments() 
	{
		pNo = "";
		id = "";
		status = "";
		amountDue = 0.0F;
		amountPaid = 0.0F;
		dueDate = "";
		paymentDate = "";		
	}
	//Primary Constructor
	public Payments(String pNo,String customerId, String status, double amountDue, double amountPaid, String dueDate, String paymentDate) 
	{
		super(customerId);
		this.pNo = pNo;
		this.status = status;
		this.amountDue = amountDue;
		this.amountPaid = amountPaid;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}
	//Accessors
	public String getpNo() 
	{
		return pNo;
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
	@Override
	public String toString() {
		return "Payments [pNo=" + pNo + ", status=" + status + ", amountDue=" + amountDue + ", amountPaid=" + amountPaid
				+ ", dueDate=" + dueDate + ", paymentDate=" + paymentDate + "]";
	}
}
