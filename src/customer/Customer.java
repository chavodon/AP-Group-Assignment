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
	private String password;
	
	//Default Constructor
	public Customer() 
	{
		this.id = "";
		this.fName = "";
		this.lName = "";
		this.email = "";
		this.contact = "";
		this.address = "";
		this.password="";
	}
	public Customer(String id, String fName, String lName, String email, String contact, String address,
			String password) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
}