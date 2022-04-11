package employee;

import user.User;

public class Employee extends User
{
	private String staffID;
	private String password;
	private String fName;
	private String lName;
	private String type;
	
	//Default Constructor
	public Employee() 
	{
		super();
		this.staffID = "";
		this.password = "";
		this.fName = "";
		this.lName = "";
		this.type = "";
	}
	
	public Employee(String staffID, String password, String fName, String lName, String type, String id) {
		super(id, password);
		this.staffID = staffID;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.type = type;
	}

	//Primary Constructor
	public Employee(String staffID, String password, String fName, String lName, String type) 
	{
		this.staffID = staffID;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.type = type;
	}
	
	//Accessors
	public String getStaffID() {
		return staffID;
	}
	
	public String getPassword() {
		return password;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String getType() {
		return type;
	}

	//Mutators
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setType(String type) {
		this.type = type;
	}

}