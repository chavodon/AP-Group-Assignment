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

import clientTCP.Client;
import customer.Complaints;

public class ViewAllDetails implements ActionListener 
{
	private JFrame frame = new JFrame("View Complaint & Account Details");
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	private JTextArea resultTxt;

	public JTextField searchField;
    public JButton searchB = new JButton ("Search");
	public JPanel panel = new JPanel();
    private JLabel titleLbl;
    private JLabel compLbl;
    private JTextField compTxt;
    
    Complaints complaint = new Complaints();
	
	public ViewAllDetails()
	{
		frame.setResizable(false);
		frame.setBounds(700, 300, 884, 731);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
		titleLbl = new JLabel("Customer Id: ");
		titleLbl.setBounds(20, 10, 290, 35); //x, y, width, length
	    titleLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    frame.getContentPane().add(titleLbl);
	    
		searchField = new JTextField();
		searchField.setFont(new Font("Serif", Font.PLAIN, 14));
		searchField.setBounds(137, 10, 130, 30);
		frame.getContentPane().add(searchField );
		
		compLbl = new JLabel("Complaint Id: ");
		compLbl.setBounds(20, 60, 290, 35); //x, y, width, length
	    compLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    frame.getContentPane().add(compLbl);
	    
		compTxt = new JTextField();
		compTxt.setFont(new Font("Serif", Font.PLAIN, 14));
		compTxt.setBounds(137, 60, 130, 30);
		frame.getContentPane().add(compTxt);
	    
	    resultTxt = new JTextArea();
	    resultTxt.setBounds(50, 90, 430, 300);
	    resultTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    resultTxt.setBackground(new Color(192, 192, 192));
	    resultTxt.setVisible(false);
	    frame.getContentPane().add(resultTxt);
	    
		searchB = new JButton("Search");
		searchB.setFont(new Font("Serif", Font.BOLD, 18));
		searchB.setForeground(Color.white);
		searchB.setBorderPainted(false);
		searchB.setBounds(285, 35, 90, 40);
		searchB.setBackground(new Color(96, 96, 96));
		frame.getContentPane().add(searchB);
		searchB.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Client client = new Client();
				
				complaint.setcNo(searchField.getText());
				client.sendAction("ViewDetails");
				client.sendComplaintId(complaint.getcNo());
				client.sendCustomerId(compTxt.getText());
				
				client.receiveResponse();			
				//frame.dispose();
			}
	});

		menu();
		frame.setVisible(true);
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
		 }	
	public static void main(String[] args) {
		new ViewAllDetails();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
