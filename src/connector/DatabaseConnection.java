package connector;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnection 
{
	private static Connection dbConnect = null;
	
	public static Connection getConnection()
	{
		try 
		{
			String url = "jdbc:mysql://localhost:3306/customersdb";
			dbConnect = DriverManager.getConnection(url, "root", ""); //get connection to local host/xampp server
			
			if(dbConnect != null) //if connection was made to xampp server
			{
				//JOptionPane.showMessageDialog(null, "Connection Successful!","JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dbConnect;
	}
}
