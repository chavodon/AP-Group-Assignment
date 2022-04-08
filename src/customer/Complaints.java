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
	private Customer customer = new Customer();
	private String customerId;
	private String category;
	private String details;
	private String date;
	private String status;
	private String responseDate;
	private String respondent;
	private String response;
	private String assignedTo;
	private String visitDate;

	private static Connection dbConnect = DatabaseConnection.getConnection();
	
	public Complaints() 
	{
		cNo = "";
		customerId = customer.getId();
		category = "";
		details = "";
		date = "";
		status = "Unresolved";
		responseDate = "N/A";
		respondent = "N/A";
		response = "N/A";
		assignedTo = "N/A";
		visitDate = "N/A";
	}
	public Complaints(String cNo, String customerId, String category, String details, String date, String status,String responseDate, String respondent, String response, String assignedTo, String visitDate) 
	{
		this.cNo = cNo;
		this.customerId = customerId;
		this.category = category;
		this.details = details;
		this.date = date;
		this.status = status;
		this.responseDate = responseDate;
		this.respondent = respondent;
		this.response = response;
		this.assignedTo = assignedTo;
		this.visitDate = visitDate;
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
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
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
	@Override
	public String toString() {
		return "Complaints [cNo=" + cNo + ", customerId=" + customerId + ", category=" + category + ", details="
				+ details + ", date=" + date + ", status=" + status + ", responseDate=" + responseDate + ", respondent="
				+ respondent + ", response=" + response + ", assignedTo=" + assignedTo + ", visitDate=" + visitDate
				+ "]";
	}
	
}
