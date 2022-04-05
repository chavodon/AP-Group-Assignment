package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


public class Connector {
	

	public static void main(String[] args) throws ClassNotFoundException 
	{
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		Connection myConn = null;
		
		//Connection con = DriverManager.getConnection(url)
		
		
		try
		{
			//Get connection to local host
			myConn = DriverManager.getConnection(url, "root", "Saskim1618");
			
			if(myConn != null)
			{
				JOptionPane.showMessageDialog(null, "Connected to local server",
						"JDBC Connection Status",JOptionPane.INFORMATION_MESSAGE);
			}
			// Create a statement
			
			Statement stmt = myConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * jdbcdemo.usr");
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
				
				
			}
			//Execute SQL Query
			
			//Process the result
		}
		
		catch(SQLException ex) {
			
			Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
