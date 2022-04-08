package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import customer.Complaints;
import customer.Customer;
import connector.DatabaseConnection;

public class ViewSpecificComplaint extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static Connection dbConnect1 = DatabaseConnection.getConnection();

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame("View All Complaints");
	private JLabel label;
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	public JTextArea textArea;
	public JTextField searchField = new JTextField(30);
    public JButton searchB = new JButton ("Search");
    public JTable result = new JTable();
	public JPanel panel = new JPanel();
    public JScrollPane scrollPane = new JScrollPane(result);
    private GridBagConstraints gbc;
    private JLabel instruction;
    private JLabel header= new JLabel();
    private JButton assignBtn;
    private static Connection dbConnect = DatabaseConnection.getConnection();
	private PreparedStatement pStmt;
	private ResultSet result1;
	
	public ViewSpecificComplaint() {
		label = new JLabel("Complaint Listings: ");
	    label.setBounds(50,45,350,20);
	    label.setFont(new Font("Ariel", Font.BOLD, 12));
	    label.setBackground(Color.black);
	    label.setForeground(Color.WHITE);
	    label.setOpaque(true);
	    frame.add(label);
		
	    assignBtn = new JButton("ASSIGN COMPLAINT TO TECHNICIAN");
	    
	    assignBtn.setBackground(Color.blue);
	    assignBtn.setForeground(Color.yellow);
	    assignBtn.setOpaque(true);
	    assignBtn.setBounds(160,450,260,50);
	    assignBtn.addActionListener(this);
	    
	    frame.add(assignBtn);
		frame.setLayout(null);
		frame.setSize(new Dimension(600,600));
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.black);
	    
	    navbar();
	   // addComponents();
	    table();
			
	 }
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == assignBtn) {
			AssignComplaint ac = new AssignComplaint();
		}
	}
	
	public void makeQuery(String category) 
	{		
		try 
		{	
			String query = "SELECT * FROM complaints WHERE category = '"+category+"'"; //get latest due date payment
			//String query = "SELECT * FROM customers WHERE issueType = '"+issueType+"'";
			//prepare the java statement
		    PreparedStatement pStmt = dbConnect1.prepareStatement(query);
		     
		     // execute the query, and get a java resultset
		     result1 = pStmt.executeQuery(query);
		     Complaints sp = new Complaints();
		     Customer info = new Customer();
		     // iterate through the java resultset
		      while (result1.next())
		      {
		    	  sp.setcNo(result1.getString("cNo"));
		    	  sp.setCategory(result1.getString("category"));
		    	  sp.setDetails(result1.getString("details"));
		    	  sp.setCustomerId(result1.getString("customerId"));
		    	  sp.setDate(result1.getString("date"));
		    	  sp.setStatus(result1.getString("status"));
		    	  sp.setResponseDate(result1.getString("responseDate"));
		    	  sp.setRespondent(result1.getString("respondent"));
		    	  
		    	  /*info.setId(result1.getString("id"));
		    	  info.setfName(result1.getString("fName"));
		    	  info.setlName(result1.getString("lName"));
		    	  info.setEmail(result1.getString("email"));
		    	  info.setContact(result1.getString("contact"));
		    	  info.setAddress(result1.getString("address"));
		    	  info.setIssueType(result1.getString("issueType"));
		    	  info.setIssueDetails(result1.getString("issueDetails"));*/
		    	  
	    		  System.out.println(sp.getcNo() +"\t"+ sp.getCategory() +"\t\t" + sp.getDetails() +"\t" + sp.getCustomerId() + "\t" + sp.getDate() + "\t" + sp.getStatus() + "\t" + sp.getResponseDate() + "\t" + sp.getRespondent());
		      }
		   pStmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void navbar() {
		//Create the Menu Bar
		menuBar = new JMenuBar();
		
		
		//Build Menu
		menu = new JMenu("Services");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menu.setBounds(250,70,50,15);
	    menu.setFont(new Font("Ariel", Font.BOLD, 12));
	    menu.setOpaque(true);
	    menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		//menu items
		menuItem = new JMenuItem("Make a Complain", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerComplaintWindow();
			}
			
		});
	
		menuItem.setBackground(Color.green);
	    menu.setForeground(Color.yellow);
		menu.add(menuItem);
		
		
		menuItem = new JMenuItem("Live Chat", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(Color.yellow);
	    menu.setForeground(Color.yellow);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ChatWindow();
			}
			
		});
		
		menuItem = new JMenuItem("Video Call A Representative", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(Color.yellow);
	    menu.setForeground(Color.yellow);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//new VideoCall();
			}
			
		});
		
		menu = new JMenu("Back");
		
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		menu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeePortal();
			}
			
		});
		
		
		menu = new JMenu("Help");
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//new LiveChat();
			}
			
		});
		
		menu = new JMenu("Log Out");
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		class MenuListener{
		  MenuListener listener =  new MenuListener();
		}
		
		frame.add(menuBar);
		frame.setJMenuBar(menuBar); 
	}
	
	public void table() {
		JTable table = new JTable();
		Object[]columns = {"Complaint Number", "Category", "Details", "Customer ID", "Date", "Status", "Response Date", "Respondent"};
		//Object[]columns = {"Customer ID","First Name", "Last Name", "Email", "Contact", "Address" , "Issue Type", "Issue Details"};
		DefaultTableModel mode = new DefaultTableModel();
		mode.setColumnIdentifiers(columns);
		
		table.setModel(mode);
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.red);
		table.setSelectionForeground(Color.white);
		table.setGridColor(Color.red);
		table.setRowHeight(50);
		table.setFont(new Font("Times", Font.PLAIN, 18));
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setForeground(Color.red);
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(10,80,530,350);
		
		frame.getContentPane().add(scroll);
		//frame.setVisible(true);
		} 
		
		
	public static void main(String[] args) {
		new ViewSpecificComplaint();

	}


}
