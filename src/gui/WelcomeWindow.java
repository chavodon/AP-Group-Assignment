package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomeWindow extends JFrame
{ 
	//Declare global variables
	private static final long serialVersionUID = 1L;
	private JButton customerBtn;
	private JButton adminBtn;
	private JLabel title;

public WelcomeWindow() 
{
		setTitle("Micro-Star Cable Vision Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(160, 160, 160));
		setResizable(false);
		setBounds(700, 300, 584, 531);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); //center output on screen
		
		//--------------CREATE BUTTONS----------------------
		adminBtn = new JButton("Employee Login");
		customerBtn= new JButton("Customer Login");
		//--------------BUTTON LAYOUT AND ALIGNMENT----------
		 title = new JLabel();
	     title.setFont(new Font("Myriad Pro", Font.ITALIC, 28)); 
	     title.setForeground(Color.black);
	     title.setText("Welcome To Micro-Star Cable Vision");
	     title.setBounds(50, 120, 480, 60); //x axis, y axis, length, width
		 add(title);
		
	    adminBtn.setFont(new Font("Serif", Font.BOLD, 18));
		adminBtn.setBackground(Color.PINK);
		adminBtn.setForeground(Color.black);
		adminBtn.setOpaque(true);
		
		customerBtn.setFont(new Font("Serif", Font.BOLD, 18));
		customerBtn.setForeground(Color.black);
		customerBtn.setBackground(Color.cyan);
		customerBtn.setOpaque(true);
		
		adminBtn.setBounds(95,205,180,50);
		customerBtn.setBounds(295,205,170,50); //x,y,length, width
		
		//-----------------ADD LISTENERS---------------------
		adminBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				 new EmployeeLogInWindow();
			}
		});
		customerBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				 new CustomerLoginWindow();	
			}
		});
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		add(adminBtn);
		add(customerBtn);
		setVisible(true);
  }
	public static void main(String args[])
	{    
		  new WelcomeWindow();
	}
}