package gui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import client.Client;

public class TechnicianPortal extends JFrame implements ActionListener {
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	private JButton viewBtn;
	private JButton editBtn;

	private JFrame frame = new JFrame("Employee Portal");
	private JLabel label;
	private JLabel label2;
	
	public TechnicianPortal() {
		label = new JLabel("TECHNICIAN DASHBOARD");
	    label.setBounds(120,25,250,20);
	    label.setFont(new Font("Serif", Font.BOLD, 18));
	    label.setForeground(Color.black);
	    
	    label2 = new JLabel("Services");
	    label2.setBounds(210,85,100,20);
	    label2.setFont(new Font("Serif", Font.BOLD, 18));
	    label2.setForeground(Color.black);
	    
	    frame.add(label);
	    frame.add(label2);
	    
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		frame.setResizable(false);
		frame.setSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		//--------------CREATE BUTTONS----------------------
		
		viewBtn = new JButton("Respond To Complaint");
		editBtn= new JButton("View Customer Complaint");
		
		//---------------BUTTON DISPLAY----------------------
		viewBtn.setBackground(Color.blue);
		viewBtn.setForeground(Color.yellow);
		viewBtn.setOpaque(true);
		
		editBtn.setBackground(Color.blue);
		editBtn.setForeground(Color.yellow);
		editBtn.setOpaque(true);
		
		//--------------BUTTON BOUNDS---------------------
		
		viewBtn.setBounds(140,155,220,50);
		editBtn.setBounds(140,255,220,50);
		
		//-----------------ADD LISTENERS---------------------
		viewBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new RespondToComplaint();
			}
		});		
		
		editBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new ViewCustomerComplaint();
			}
		});	

		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		frame.add(viewBtn);
		frame.add(editBtn);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		navbar();
	
	}
		
	public void navbar() {
		//Create the Menu Bar
		menuBar = new JMenuBar();
		
		
		//Build Menu
		menu = new JMenu("Services");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menu.setBounds(250,70,50,15);
	    menu.setFont(new Font("Serif", Font.BOLD, 12));
	    menu.setOpaque(true);
	    menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		//menu items
		menuItem = new JMenuItem("Make a Complaint", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(Color.green);
	    menu.setForeground(Color.yellow);
		menu.add(menuItem);
		
		menu = new JMenu("Back");
		
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		menu = new JMenu("Help");
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		menu = new JMenu("Log Out");
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		frame.add(menuBar);
		frame.setJMenuBar(menuBar); 
	}
	

	public void getClient()
	{
		Client client = new Client();
		client.sendAction("ResolvedNum");
		
	}
	public static void main(String[] args) {
		new TechnicianPortal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
