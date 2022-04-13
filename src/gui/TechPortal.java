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
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import clientTCP.Client;

public class TechPortal extends JFrame{
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
	private JButton respondBtn;
	private JButton byCategoryBtn;

	private JFrame frame = new JFrame("Technician Portal");
	private JLabel label;

	public TechPortal()
	{
		//getClient();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		frame.setResizable(false);
		frame.setSize(new Dimension(1200,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //center output on screen;

		label = new JLabel("TECHNICIAN DASHBOARD");
	    label.setBounds(450,25,570,60);
	    label.setFont(new Font("Serif", Font.ITALIC, 24));
	    label.setForeground(Color.black);
	    frame.add(label);
		
		//--------------CREATE BUTTONS----------------------
		respondBtn = new JButton("Respond To Complaint");
		byCategoryBtn= new JButton("Complaints By Category");
		
		//---------------BUTTON DISPLAY----------------------
		respondBtn.setBackground(Color.PINK);
		respondBtn.setForeground(Color.black);
		respondBtn.setFont(new Font("Serif", Font.BOLD, 18));
		respondBtn.setBounds(175,155,220,50);
		respondBtn.setOpaque(true);
		
		byCategoryBtn.setBackground(Color.cyan);
		byCategoryBtn.setForeground(Color.black);
		byCategoryBtn.setBounds(650,155,250,50);
		byCategoryBtn.setOpaque(true);
		byCategoryBtn.setFont(new Font("Serif", Font.BOLD, 18));
		
		//-----------------ADD LISTENERS---------------------
		respondBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				new ResponseByTechnician();
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
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		frame.add(respondBtn);
		frame.add(byCategoryBtn);

		navbar();
		frame.setVisible(true);
	}		
	public void navbar() {
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
						new ResponseByTechnician();
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
			
				serviceMenu = new JMenu("Back");
				serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
				serviceMenu.setMnemonic(KeyEvent.VK_A);
				serviceMenu.getAccessibleContext().setAccessibleDescription(null);
				serviceMenu.addMouseListener(new MouseListener()
				{
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.dispose();
						new TechPortal();
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

	public static void main(String[] args) 
	{
		 TechPortal emp = new TechPortal ();
	}
}
