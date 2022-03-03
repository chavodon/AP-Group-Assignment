package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dashboard.StaffDashboard;

public class UserLogin 
{
	private JFrame frame;
	private JLabel idLbl, passwordLbl, userLbl, titleLbl;
	private JTextField idTextfield;
	private JPasswordField passwordField ;
	private JCheckBox showPassword;
	private JButton signinBtn;
	private JRadioButton rbtnStaff;
	private JRadioButton rbtnCustomer;
	
	public UserLogin() 
    {
    	frame = new JFrame();		
		frame.setTitle("User Login");
		frame.setBounds(700, 300, 584, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255, 255, 153));
	       
		//Light Yellow = 255-255-153
		//Light Blue = 51-153-255
		
		userLbl= new JLabel("Welcome to Micro-Star Cable Vision");
		userLbl.setFont(new Font("Serif", Font.BOLD, 25));
		userLbl.setBounds(80, 50, 390, 25);
		frame.getContentPane().add(userLbl);
		
		idLbl= new JLabel("User ID");
		idLbl.setFont(new Font("Serif", Font.BOLD, 20));
		idLbl.setBounds(123, 141, 124, 25);
		frame.getContentPane().add(idLbl);
		
		passwordLbl= new JLabel("Password");
		passwordLbl.setFont(new Font("Serif", Font.BOLD, 21));
		passwordLbl.setBounds(123, 197, 109, 25);
		frame.getContentPane().add(passwordLbl);
		
		idTextfield = new JTextField();
		idTextfield.setFont(new Font("Serif", Font.PLAIN, 18));
		idTextfield.setBounds(259, 134, 227, 39);
		frame.getContentPane().add(idTextfield );
	
	    passwordField = new JPasswordField();
	    passwordField .setFont(new Font("Serif", Font.PLAIN, 18));
	    passwordField .setBounds(259, 200, 227, 39);
		frame.getContentPane().add(passwordField);
		
		showPassword = new JCheckBox("Show Password");
		showPassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				 if (e.getSource() == showPassword ) 
				 {
			            if (showPassword .isSelected()) 
			            {
			            	passwordField.setEchoChar((char)0);
			            } 
			            else 
			            {
			            	passwordField.setEchoChar('*');
			            }
			     }
			}
		});
		
		showPassword.setFont(new Font("Serif", Font.BOLD, 18));
		showPassword.setBounds(254, 260, 147, 30);
		showPassword.setBackground(new Color (255,255,153));
		frame.getContentPane().add(showPassword);
		  
		userLbl= new JLabel("Which type of user are you?");
		userLbl.setFont(new Font("Serif", Font.BOLD, 18));
		userLbl.setBounds(120, 290, 230, 50);
		frame.getContentPane().add(userLbl);
		
		rbtnCustomer = new JRadioButton("Customer");
		rbtnCustomer.setBounds(221, 335, 110, 39);
		rbtnCustomer.setBackground(new Color(255,255,153));
		rbtnCustomer.setFont(new Font("Serif", Font.BOLD, 16));
		
		rbtnStaff = new JRadioButton("Staff");
		rbtnStaff.setBounds(101, 335, 120, 39);
		rbtnStaff.setBackground(new Color(255,255,153));
		rbtnStaff.setFont(new Font("Serif", Font.BOLD, 16));
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbtnCustomer);
		bg.add(rbtnStaff);
		frame.add(rbtnCustomer);
		frame.add(rbtnStaff);
		
		signinBtn = new JButton("Sign in");
		signinBtn.setFont(new Font("Serif", Font.BOLD, 21));
		signinBtn.setBackground(new Color(255, 204, 51));
		signinBtn.setBorderPainted(false);
		signinBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String user = "staff";
				String pwd = "123";
				
				@SuppressWarnings("deprecation")
				String username=idTextfield.getText();
				String password=  passwordField.getText();
				
				if (!username.equals(user) || !password.equals(pwd))
				{
					JOptionPane.showMessageDialog(null, "Incorrect login details!");
				}
				else 
				{
					//StaffDashboard staff= new StaffDashboard();
					//staff.serviceList();
				}
			}
		});

		signinBtn.setBounds(241, 389, 117, 39);
		frame.getContentPane().add(signinBtn);
		
		JLabel backgroundJLabel = new JLabel(new ImageIcon("image/logo.png")); //\\src\\project
        backgroundJLabel.setBounds(80, 50, 390, 25);
        backgroundJLabel.setVisible(true);
        frame.add(backgroundJLabel);
        
		frame.setVisible(true);
	}
}
