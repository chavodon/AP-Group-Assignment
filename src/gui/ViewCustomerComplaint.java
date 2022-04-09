package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.mysql.cj.xdevapi.Table;

import client.Client;
import connector.DatabaseConnection;
import customer.Complaints;

public class ViewCustomerComplaint implements ActionListener 
{
	private JFrame frame = new JFrame("Respond To Complaint");
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	private JTextArea resultTxt;

	public JTextField searchField;
    public JButton searchB = new JButton ("Search");
    private JButton bckBtn = new JButton("Back");
	public JPanel panel = new JPanel();
    private JLabel titleLbl;
    
    private JLabel responseLbl;
    private JTextArea responseTxt;
    private JLabel dateLbl;
    private JTextField dateTxt;
    private JButton respondBtn;
    private static Connection dbConnect1 = DatabaseConnection.getConnection();
    
    Complaints complaint = new Complaints();
	
	public ViewCustomerComplaint()
	{
		frame.setResizable(false);
		//frame.setBounds(700, 300, 584, 581); //x, y, width, length
		frame.setSize(new Dimension(800,600));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
		titleLbl = new JLabel("Customer Id: ");
		titleLbl.setBounds(20, 10, 290, 35); //x, y, width, length
	    titleLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    frame.getContentPane().add(titleLbl);
	    
	    resultTxt = new JTextArea();
	    resultTxt.setBounds(50, 60, 430, 200); //x, y, width, length
	    resultTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    resultTxt.setBackground(new Color(192, 192, 192));
	    resultTxt.setVisible(false);
	    frame.getContentPane().add(resultTxt);
	    
		searchField = new JTextField();
		searchField.setFont(new Font("Serif", Font.PLAIN, 14));
		searchField.setBounds(117, 10, 130, 30);
		frame.getContentPane().add(searchField );
	
		searchB = new JButton("Search");
		searchB.setFont(new Font("Serif", Font.BOLD, 18));
		searchB.setForeground(Color.white);
		searchB.setBorderPainted(false);
		searchB.setBounds(265, 10, 110, 30);
		searchB.setBackground(new Color(96, 96, 96));
		frame.getContentPane().add(searchB);
		searchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table(searchField.getText());
			}
		});
		
		bckBtn.setBackground(Color.blue);
		bckBtn.setForeground(Color.yellow);
		bckBtn.setOpaque(true);
		bckBtn.setBounds(360,450,100,50);
		bckBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();				
			}
			
		});
	    
		frame.add(bckBtn);
		
		frame.setVisible(true);
		}
		
	public void table(String customerId) {
		
		JTable table = new JTable();
		Object[]columns = {"Complaint #", "Category", "Details", "Customer ID", "Date", "Status", "Response Date", "Respondent"};
		DefaultTableModel mode = new DefaultTableModel();
		mode.setColumnIdentifiers(columns);
			
		table.setModel(mode);
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.red);
		table.setSelectionForeground(Color.white);
		table.setRowHeight(30);
		table.setFont(new Font("Times", Font.PLAIN, 12));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setForeground(Color.red);
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(5,80,770,350);
		frame.getContentPane().add(scroll);
		
		try {
			Statement st = dbConnect1.createStatement();
			String query = "SELECT * FROM complaints WHERE customerId = '"+customerId+"'";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				mode.addRow(new Object [] {
						rs.getString("cNo"),
						rs.getString("category"),
						rs.getString("details"),
						rs.getString("customerId"),
						rs.getString("date"),
						rs.getString("status"),
						rs.getString("responseDate"),
						rs.getString("respondent"),
				});
			}
			
			rs.close();
			st.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
		 public void menu() 
		 {
			//Create the Menu Bar
				menuBar = new JMenuBar();
				//Build Menu
				menu = new JMenu("Services");
				menu.setFont(new Font("Serif", Font.BOLD, 14));
				menu.setMnemonic(KeyEvent.VK_A);
				menu.getAccessibleContext().setAccessibleDescription(null);
				menu.setBounds(250,70,50,15);
			    menu.setOpaque(true);
			    menuBar.add(menu);
				
				//menu items
				menuItem = new JMenuItem("Lodge New Complaint", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						new CustomerComplaintWindow();
					}	
				});
				menu.add(menuItem);		
				
				menuItem = new JMenuItem("Live Chat", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//new ChatWindow();
					}			
				});
				menu.add(menuItem);
				
				menuItem = new JMenuItem("Video Call A Representative", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//new VideoCall();
					}
				});
				menu.add(menuItem);
				
				menu = new JMenu("Back");
				menu.setFont(new Font("Serif", Font.BOLD, 14));
				menu.setMnemonic(KeyEvent.VK_A);
				menu.getAccessibleContext().setAccessibleDescription(null);
				menu.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						new EmployeePortal();
					}	
				});
				menuBar.add(menu);
				
				menu = new JMenu("Log Out");
				menu.setFont(new Font("Serif", Font.BOLD, 14));
				menu.setMnemonic(KeyEvent.VK_A);
				menu.getAccessibleContext().setAccessibleDescription(null);
				menu.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
					}	
				});
				menuBar.add(menu);
				
				class MenuListener{
				  MenuListener listener =  new MenuListener();
				}
				frame.add(menuBar);
				frame.setJMenuBar(menuBar); 
		 }
		 public void setText(Complaints co)
		 {
			 resultTxt.setVisible(true);
			 resultTxt.setText("Complaint No: " + co.getcNo() + "\nCustomer Id: " + co.getCustomerId() + "\nCategory: " + co.getCategory() + "\nDetails: " + co.getDetails() + "\nStatus: " + co.getStatus() + "\nResponse Date: " + co.getResponseDate() + "\nRespondent: "+co.getRespondent());
			 resultTxt.setEditable(false);
			 responseLbl.setVisible(true);
			 responseTxt.setVisible(true);
			 dateLbl.setVisible(true);
			 dateTxt.setVisible(true);
			 respondBtn.setVisible(true);
		 }	
	public static void main(String[] args) {
		new ViewCustomerComplaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
