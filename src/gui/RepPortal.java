package gui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import clientTCP.Client;

import java.awt.event.WindowEvent;    
import java.awt.event.WindowListener;   

public class RepPortal extends JFrame{
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
	private JButton respondBtn;
	private JButton byCategoryBtn;
	private JButton assignBtn;
	private JButton viewBtn;
	
	private JButton generalBtn;
	private JButton custServiceBtn;
	private JButton billBtn;
	private JButton productBtn;
	
	private JFrame frame = new JFrame("Customer Rep Portal");
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	private JLabel resolvedLbl;
	private JLabel unresolvedLbl;
	
	public RepPortal()
	{
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		frame.setResizable(false);
		frame.setSize(new Dimension(1200,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //center output on screen;
		
		label = new JLabel("CUSTOMER REPRESENTATIVE DASHBOARD");
	    label.setBounds(350,25,570,60);
	    label.setFont(new Font("Serif", Font.ITALIC, 24));
	    label.setForeground(Color.black);
	    
	    label2 = new JLabel("Complaint Information");
	    label2.setBounds(775,115,250,20);
	    label2.setFont(new Font("Serif", Font.BOLD, 18));
	    label2.setForeground(Color.black);
	    
	    label3 = new JLabel("Services");
	    label3.setBounds(170,115,100,20);
	    label3.setFont(new Font("Serif", Font.BOLD, 18));
	    label3.setForeground(Color.black);
	    
	    label6 = new JLabel("View Number of: ");
	    label6.setBounds(405,115,150,20);
	    label6.setFont(new Font("Serif", Font.BOLD, 18));
	    label6.setForeground(Color.black);
	    
	    label4 = new JLabel("Resolved Complaints");
	    label4.setBounds(725,170,150,20);
	    label4.setFont(new Font("Serif", Font.BOLD, 16));
	    label4.setForeground(Color.black);
	    
	    label5 = new JLabel("Outstanding Complaints");
	    label5.setBounds(920,170,190,20);
	    label5.setFont(new Font("Serif", Font.BOLD, 16));
	    label5.setForeground(Color.black);
	    	    
	    frame.add(label);
	    frame.add(label2);
	    frame.add(label3);
	    frame.add(label4);
	    frame.add(label5);
	    frame.add(label6);
		//--------------CREATE BUTTONS----------------------
		respondBtn = new JButton("Respond To Complaint");
		byCategoryBtn= new JButton("Complaints By Category");
		assignBtn = new JButton("Assign Complaint");
		viewBtn = new JButton("View Complaint");
		
		custServiceBtn = new JButton("Customer Service Complaints");
		productBtn = new JButton("Product/Service Complaints");
		billBtn = new JButton("Bill Payment Complaints");
		generalBtn = new JButton("General Complaints");
		//---------------BUTTON DISPLAY----------------------
		respondBtn.setBackground(Color.PINK);
		respondBtn.setForeground(Color.black);
		respondBtn.setFont(new Font("Serif", Font.BOLD, 18));
		respondBtn.setBounds(80,155,220,50);
		respondBtn.setOpaque(true);
		
		custServiceBtn.setBackground(Color.PINK);
		custServiceBtn.setForeground(Color.black);
		custServiceBtn.setFont(new Font("Serif", Font.BOLD, 18));
		custServiceBtn.setBounds(405,155,271,50);
		custServiceBtn.setOpaque(true);
		
		byCategoryBtn.setBackground(Color.cyan);
		byCategoryBtn.setForeground(Color.black);
		byCategoryBtn.setBounds(80,220,230,50);
		byCategoryBtn.setOpaque(true);
		byCategoryBtn.setFont(new Font("Serif", Font.BOLD, 18));
		
		productBtn.setBackground(Color.cyan);
		productBtn.setForeground(Color.black);
		productBtn.setFont(new Font("Serif", Font.BOLD, 18));
		productBtn.setBounds(405,220,260,50);
		productBtn.setOpaque(true);
	
		assignBtn.setBackground(Color.orange);
		assignBtn.setForeground(Color.black);
		assignBtn.setFont(new Font("Serif", Font.BOLD, 18));
		assignBtn.setBounds(80,295,220,50);
		assignBtn.setOpaque(true);	
		
		billBtn.setBackground(Color.orange);
		billBtn.setForeground(Color.black);
		billBtn.setFont(new Font("Serif", Font.BOLD, 18));
		billBtn.setBounds(405,295,260,50);
		billBtn.setOpaque(true);	
		
		viewBtn.setBackground(Color.PINK);
		viewBtn.setForeground(Color.black);
		viewBtn.setFont(new Font("Serif", Font.BOLD, 18));
		viewBtn.setBounds(80,355,220,50);
		viewBtn.setOpaque(true);
		
		generalBtn.setBackground(Color.PINK);
		generalBtn.setForeground(Color.black);
		generalBtn.setFont(new Font("Serif", Font.BOLD, 18));
		generalBtn.setBounds(405,355,220,50);
		generalBtn.setOpaque(true);
		
		//-----------------ADD LISTENERS---------------------
		respondBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				new ResponseByRep();
			}
		});
		custServiceBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				Client client = new Client();
				client.sendAction("Count");
				client.sendCategory("Customer Service");
				client.receiveResponse();
			}
		});
		billBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				Client client = new Client();
				client.sendAction("Count");
				client.sendCategory("Bill Payment");
				client.receiveResponse();
			}
		});
		generalBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				Client client = new Client();
				client.sendAction("Count");
				client.sendCategory("General");
				client.receiveResponse();
			}
		});
		productBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				Client client = new Client();
				client.sendAction("Count");
				client.sendCategory("Product/Service");
				client.receiveResponse();
			}
		});
		byCategoryBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				new ViewByCategory();
			}
		});
		assignBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				new AssignComplaint();
			}
		});
		viewBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				new ViewAllDetails();
			}
		});
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		frame.add(respondBtn);
		frame.add(byCategoryBtn);
		frame.add(assignBtn);
		frame.add(viewBtn);
		
		frame.add(custServiceBtn);
		frame.add(productBtn);
		frame.add(billBtn);
		frame.add(generalBtn);
		
		navbar();
		frame.setVisible(true);
	}
		
	public void navbar() 
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

	public void setNumComplaints(int numUnresolved, int numResolved)
	{ 
		resolvedLbl = new JLabel();
		resolvedLbl.setText(Integer.toString(numResolved));
		resolvedLbl.setBounds(778,200,20,20);
	    resolvedLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    resolvedLbl.setForeground(Color.black);
	    frame.add(resolvedLbl);
	    
	    unresolvedLbl = new JLabel();
	    unresolvedLbl.setText(Integer.toString(numUnresolved));
	    unresolvedLbl.setBounds(978,200,20,20);
	    unresolvedLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    unresolvedLbl.setForeground(Color.black);
	    frame.add(unresolvedLbl);
	}
	public static void main(String[] args)
	{
		new RepPortal();
	}
}
