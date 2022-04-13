package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import clientTCP.Client;
import customer.Complaints;

public class ResponseByRep implements ActionListener 
{
	private JFrame frame = new JFrame("Customer Service Rep - Respond To Complaint");
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
	private JTextArea resultTxt;

	public JTextField searchField;
    public JButton searchB = new JButton ("Search");
	public JPanel panel = new JPanel();
    private JLabel titleLbl;
    
    private JLabel idLbl;
    private JTextField idTxt;
    private JLabel responseLbl;
    private JTextArea responseTxt;
    private JButton respondBtn;
    
    Complaints complaint = new Complaints();
	
	public ResponseByRep()
	{
		frame.setResizable(false);
		frame.setBounds(700, 300, 980, 591); //x, y, width, length
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
		titleLbl = new JLabel("Complaint Id: ");
		titleLbl.setBounds(20, 10, 290, 35); //x, y, width, length
	    titleLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    frame.getContentPane().add(titleLbl);
	    
		idLbl = new JLabel("Customer Rep Id: ");
		idLbl.setBounds(280, 10, 290, 35); //x, y, width, length
	    idLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    frame.getContentPane().add(idLbl);
	    
		idTxt = new JTextField();
		idTxt.setFont(new Font("Serif", Font.PLAIN, 14));
		idTxt.setBounds(400, 10, 130, 30);
		frame.getContentPane().add(idTxt);
	    
	    resultTxt = new JTextArea();
	    resultTxt.setBounds(50, 60, 830, 280); //x, y, length, width
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
		searchB.setBounds(545, 10, 110, 30);
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
		responseLbl.setBounds(20, 350, 290, 35); //x, y, length, width
	    responseLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    responseLbl.setVisible(false);
	    frame.getContentPane().add(responseLbl);
	    
	    responseTxt = new JTextArea();
	    responseTxt.setBounds(110, 350, 290, 35); //x, y, width, length
	    responseTxt.setSize(330,100);
	    responseTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    responseTxt.setVisible(false);
	    frame.getContentPane().add(responseTxt);
		
	    respondBtn = new JButton("Respond");
		respondBtn.setFont(new Font("Serif", Font.BOLD, 18));
		respondBtn.setForeground(Color.white);
		respondBtn.setBorderPainted(false);
		respondBtn.setBounds(110, 470, 130, 30);
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
				complaint.setRepResponse(responseTxt.getText());
				complaint.setRepRespondent(idTxt.getText());
				
				client.sendAction("RepRespond");
				client.sendComplaintId(complaint.getcNo());
				client.sendRepResponse(complaint.getRepRespondent(),complaint.getRepResponse());
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
				serviceBar = new JMenuBar();
				serviceMenu = new JMenu("Services");
				serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
				serviceMenu.setMnemonic(KeyEvent.VK_A);
				serviceMenu.getAccessibleContext().setAccessibleDescription(null);
				serviceMenu.setBounds(250,70,50,15);
			    serviceMenu.setOpaque(true);
			    serviceBar.add(serviceMenu);
				
				//menu items
				menuItem = new JMenuItem("Respond To Complaint", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						new ResponseByRep();
					}	
				});
				serviceMenu.add(menuItem);		
				
				menuItem = new JMenuItem("View Complaint By Category", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frame.dispose();
						new ViewByCategory();
					}			
				});
				serviceMenu.add(menuItem);
				
				menuItem = new JMenuItem("Assign Complaint", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						frame.dispose();
						new AssignComplaint();
					}
				});
				serviceMenu.add(menuItem);
				
				menuItem = new JMenuItem("View Complaint", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						frame.dispose();
						new ViewComplaint();
					}
				});
				serviceMenu.add(menuItem);
			
				serviceMenu = new JMenu("Back");
				serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
				serviceMenu.setMnemonic(KeyEvent.VK_A);
				serviceMenu.getAccessibleContext().setAccessibleDescription(null);
				serviceMenu.addMouseListener(new MouseListener()
				{
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.dispose();
						new RepPortal();
					}
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
					}
				});
				serviceBar.add(serviceMenu);
				
				serviceMenu = new JMenu("Log Out");
				serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
				serviceMenu.setMnemonic(KeyEvent.VK_A);
				serviceMenu.getAccessibleContext().setAccessibleDescription(null);
				serviceMenu.addMouseListener(new MouseListener()
				{
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.dispose();
						new EmployeeLogInWindow();
					}
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					});
					serviceBar.add(serviceMenu);
			    
				class MenuListener
				{
				  MenuListener listener =  new MenuListener();
				}
				frame.add(serviceBar);
				frame.setJMenuBar(serviceBar); 
				frame.setVisible(true);
		 }
		 public void setText(Complaints co)
		 {
			 searchField.setText(co.getcNo());
			 resultTxt.setVisible(true);
			 resultTxt.setText("Complaint No: " + co.getcNo() + "\nCustomer Id: " + co.getId() + "\nCategory: " + co.getCategory() + "\nDetails: " + co.getDetails() + "\nStatus: " + co.getStatus() + "\nResponse Date: " + co.getRepResponseDate() + "\nRespondent: "+co.getRepRespondent() +"\nResponse: "+co.getRepResponse()
			 +"\nTech Response Date: " + co.getTechResponseDate()+"\nTech Respondent: "+co.getTechRespondent()+"\nResponse: "+co.getTechResponse());
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
