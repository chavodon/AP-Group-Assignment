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
import customer.Complaints;

public class ResponseByRep implements ActionListener 
{
	private JFrame frame = new JFrame("Customer Service Rep - Respond To Complaint");
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
    
    private JLabel responseLbl;
    private JTextArea responseTxt;
    private JButton respondBtn;
    
    Complaints complaint = new Complaints();
	
	public ResponseByRep()
	{
		frame.setResizable(false);
		frame.setBounds(700, 300, 584, 581); //x, y, width, length
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
		titleLbl = new JLabel("Complaint Id: ");
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
		searchB.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Client client = new Client();
				
				complaint.setcNo(searchField.getText());
				client.sendAction("ResponseView");
				client.sendComplaintId(complaint.getcNo());
				client.receiveResponse();			
				frame.dispose();
			}
	});
		responseLbl = new JLabel("Response: ");
		responseLbl.setBounds(20, 280, 290, 35); //x, y, width, length
	    responseLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    responseLbl.setVisible(false);
	    frame.getContentPane().add(responseLbl);
	    
	    responseTxt = new JTextArea();
	    responseTxt.setBounds(110, 280, 290, 35); //x, y, width, length
	    responseTxt.setSize(330,100);
	    responseTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    responseTxt.setVisible(false);
	    frame.getContentPane().add(responseTxt);
		
	    respondBtn = new JButton("Respond");
		respondBtn.setFont(new Font("Serif", Font.BOLD, 18));
		respondBtn.setForeground(Color.white);
		respondBtn.setBorderPainted(false);
		respondBtn.setBounds(225, 450, 130, 30);
		respondBtn.setBackground(new Color(96, 96, 96));
		respondBtn.setVisible(false);
		frame.getContentPane().add(respondBtn);
		respondBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Client client = new Client();
				complaint.setcNo(searchField.getText());
				complaint.setResponse(responseTxt.getText());
				client.sendAction("RepRespond");
				client.sendComplaintId(complaint.getcNo());
				client.sendRepResponse(complaint.getResponse());
				client.receiveResponse();			
				frame.dispose();
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
			 searchField.setText(co.getcNo());
			 resultTxt.setVisible(true);
			 resultTxt.setText("Complaint No: " + co.getcNo() + "\nCustomer Id: " + co.getCustomerId() + "\nCategory: " + co.getCategory() + "\nDetails: " + co.getDetails() + "\nStatus: " + co.getStatus() + "\nResponse Date: " + co.getResponseDate() + "\nRespondent: "+co.getRespondent());
			 resultTxt.setEditable(false);
			 responseLbl.setVisible(true);
			 responseTxt.setVisible(true);
			 respondBtn.setVisible(true);
		 }	
	public static void main(String[] args) {
		new ResponseByRep();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
