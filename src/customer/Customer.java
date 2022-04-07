package customer;

import java.io.Serializable;

public class Customer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String fName;
	private String lName;
	private String email;
	private String contact;
	private String address;
	private String issueType;
	private String issueDetails;
	
	//Default Constructor
	public Customer() 
	{
		this.id = "";
		this.fName = "";
		this.lName = "";
		this.email = "";
		this.contact = "";
		this.address = "";
		this.issueType = "";
		this.issueDetails = "";
	}
	//Primary Constructor
	public Customer(String id, String fName, String lName, String email, String contact, String address,String issueType, String issueDetails) 
	{
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.issueType = issueType;
		this.issueDetails = issueDetails;
	}
	
	//Accessors
	public String getId() 
	{
		return id;
	}
	public String getfName() 
	{
		return fName;
	}
	public String getlName()
	{
		return lName;
	}
	public String getEmail() 
	{
		return email;
	}
	public String getContact() 
	{
		return contact;
	}
	public String getAddress() 
	{
		return address;
	}
	public String getIssueType()
	{
		return issueType;
	}
	public String getIssueDetails() 
	{
		return issueDetails;
	}
	
	//Mutators
	public void setId(String id)
	{
		this.id = id;
	}
	public void setfName(String fName) 
	{
		this.fName = fName;
	}
	public void setlName(String lName) 
	{
		this.lName = lName;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public void setIssueType(String issueType) 
	{
		this.issueType = issueType;
	}
	public void setIssueDetails(String issueDetails) 
	{
		this.issueDetails = issueDetails;
	}
}