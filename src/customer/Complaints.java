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

public class Complaints implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cNo;
	private String category;
	private String details;
	private Customer customer = new Customer();

	private String customerId;
	private String date;
	private String status;
	private String responseDate;
	private String respondent;

	private static Connection dbConnect = DatabaseConnection.getConnection();
	//Scanner inp = new Scanner(System.in).useDelimiter("\\n");
	
	public Complaints() 
	{
		cNo = "";
		category = "";
		details = "";
		date = "";
		status = "Unresolved";
		responseDate = "N/A";
		respondent = "N/A";
		customerId = customer.getId();
	}
	public Complaints(String id, String category, String details, String date, String status, String responseDate, String respondent) 
	{
		this.cNo = id;
		this.category = category;
		this.details = details;
		this.date = date;
		this.status = status;
		this.responseDate = responseDate;
		this.respondent = respondent;
	}
	
	//Accessors
	public String getcNo() 
	{
		return cNo;
	}
	public String getCategory() 
	{
		return category;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDetails() 
	{
		return details;
	}
	public String getDate() 
	{
		return date;
	}
	public String getStatus() 
	{
		return status;
	}
	public String getResponseDate() 
	{
		return responseDate;
	}
	public String getRespondent() 
	{
		return respondent;
	}
	
	//Mutators
	public void setcNo(String cNo) 
	{
		this.cNo = cNo;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}
	public void setDetails(String details) 
	{
		this.details = details;
	}
	public void setDate(String date) 
	{
		this.date = date;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public void setResponseDate(String responseDate) 
	{
		this.responseDate = responseDate;
	}
	public void setRespondent(String respondent) 
	{
		this.respondent = respondent;
	}

	public void lodgeComplaint(String customerId)
	{
		Scanner in = new Scanner(System.in);
		int type = 0;
		
		Random rand = new Random();
		cNo = String.format("%04d",rand.nextInt(10000));
		
		System.out.println("1. Customer Service Complaint \n2. Product/Service Complaint \n3. Bill/Payment Complaint \n4. General Complaint");
		System.out.println("Select complaint category: ");
		 type = in.nextInt();
		 
		 switch(type)
		 {
			 case 1: 
					category = "Customer Service";
					break;
				case 2:
					category = "Product/Service";
					break;
				case 3: 
					category = "Bill/Payment";
					break;
				case 4:
					category = "General";
					break;
				default:
					System.out.println("Invalid menu option!");
					break;
		 }
		 
//		 System.out.println("Enter the date of complaint (yyyy/mm/dd): ");
//		 date = inp.next();
//		 
//		 System.out.println("Enter complaint details: ");
//		 details = inp.next();
		 
		 String query = "INSERT INTO complaints values ('"+cNo+"', '"+category+"', '"+date+"', '"+details+"', '"+customerId+"', '"+status+"', '"+responseDate+"', '"+respondent+"')";
		 try
		   {
		   	// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
				
			// execute the preparedstatement
			   preparedStmt.execute();
			   JOptionPane.showMessageDialog(null, "Data Added!");
			    preparedStmt.close();
			   // logger.info("Complaint added to complaints table in database.");
		   } 
		   catch (SQLException e1) 
		   {
			   e1.printStackTrace();
		   }
	}
	
	public void viewAllComplaints(String customerId)
	{
		try
		{
			String query = "SELECT * FROM complaints";
			
			//prepare the java statement
		     PreparedStatement st =dbConnect.prepareStatement(query);
		     
		     // execute the query, and get a java resultset
		     ResultSet rs = st.executeQuery(query);
		     
		     System.out.println("cNo \t\tCategory \t\t\tDate \tCustomerId \tStatus \tResponse Date \tRespondent");
		     System.out.println("______________________________________________________________________________________");
		     
		     // iterate through the java resultset
		      while (rs.next())
		      {
		    	  cNo = rs.getString("cNo");
		    	  category = rs.getString("category");
		    	  date = rs.getString("date");
		    	  details = rs.getString("details");
		    	  customerId = rs.getString("customerId");
		    	  status = rs.getString("status");
		    	  responseDate = rs.getString("responseDate");
		    	  respondent = rs.getString("respondent");
		    	  
		    	  System.out.println(cNo + "\t" + category +"\t \t" + date +"\t"+ customerId +"\t"+ status + "\t\t"+ responseDate +"\t\t"+ respondent);
		      }
			   st.close();
			   //logger.info("Complaints displayed.");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Complaints [cNo=" + cNo + ", category=" + category + ", details=" + details + ", customerId="
				+ customerId + ", date=" + date + ", status=" + status + ", responseDate=" + responseDate
				+ ", respondent=" + respondent + "]";
	}
}
