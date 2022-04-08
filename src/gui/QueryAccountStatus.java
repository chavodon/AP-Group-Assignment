package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import client.Client;
import customer.Payments;

public class QueryAccountStatus implements ActionListener 
{
	private JFrame frame = new JFrame("Query Account Status ");
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
    private JButton bckBtn;
    
    Payments payment = new Payments();
    
    public QueryAccountStatus()
    {
    	frame.setResizable(false);
		frame.setBounds(700, 300, 584, 531);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
		titleLbl = new JLabel("Customer Id: ");
		titleLbl.setBounds(20, 10, 290, 35); //x, y, width, length
	    titleLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    frame.getContentPane().add(titleLbl);
	    
	    resultTxt = new JTextArea();
	    resultTxt.setBounds(50, 90, 430, 300);
	    resultTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    resultTxt.setBackground(new Color(192, 192, 192));
	    resultTxt.setVisible(false);
	    frame.getContentPane().add(resultTxt);
	    
		searchField = new JTextField();
		searchField.setFont(new Font("Serif", Font.PLAIN, 14));
		searchField.setBounds(117, 10, 130, 30);
		frame.getContentPane().add(searchField );
	
		searchB = new JButton("Search");
		searchB.setFont(new Font("Serif", Font.BOLD, 14));
		searchB.setForeground(Color.white);
		searchB.setBorderPainted(false);
		searchB.setBounds(265, 10, 80, 30);
		searchB.setBackground(new Color(96, 96, 96));
		searchB.addActionListener(new ActionListener()
		{
			Client client = new Client();
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				payment.setpNo(searchField.getText());
				client.sendAction("QueryStatus");
				client.sendCustomerId(payment.getpNo());
				client.receiveResponse();			
				//frame.dispose();
			}
	});
		frame.getContentPane().add(searchB);
		
		bckBtn = new JButton("Back");
		bckBtn.setFont(new Font("Serif", Font.BOLD, 14));
		bckBtn.setForeground(Color.white);
		bckBtn.setBorderPainted(false);
		bckBtn.setBounds(365, 10, 80, 30);
		bckBtn.setBackground(new Color(96, 96, 96));
		bckBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new CustomerDashboard();
				frame.dispose();
			}
		});
		frame.getContentPane().add(bckBtn);
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
					new LodgeComplaintWindow();
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
    public void setText(Payments pay)
	 {
    	searchField.setText(pay.getCustomerId());
		 resultTxt.setVisible(true);
		 resultTxt.setText("Payment No: " + pay.getpNo() + "\nCustomer Id: " + pay.getCustomerId() + "\nAmount Due: $" + pay.getAmountDue() + "\nAmount Paid: $" + pay.getAmountPaid() + "\nDue Date: "+pay.getDueDate()+"\nPayment Date: " + pay.getPaymentDate() +  "\nStatus: " + pay.getStatus());
		 resultTxt.setEditable(false);
	 }	
public static void main(String[] args) {
	new QueryAccountStatus();
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
