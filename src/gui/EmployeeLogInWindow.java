package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clientTCP.Client;

public class EmployeeLogInWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JLabel title;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JButton loginBtn;
	private JButton backBtn;
	
	public EmployeeLogInWindow()
	{
		//Initialize OR Instantiate the components
		setTitle("Employee Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(160, 160, 160));
		setResizable(false);
		setBounds(700, 300, 584, 531);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); //center output on screen
		
		 title = new JLabel();
	     title.setFont(new Font("Myriad Pro", Font.ITALIC, 28)); 
	     title.setForeground(Color.black);
	     title.setText("Employee Login");
	     title.setBounds(200, 30, 480, 60); //x axis, y axis, length, width
		 add(title);
		 
		usernameLbl = new JLabel("Staff ID:");
		usernameLbl.setForeground(Color.black);
		usernameLbl.setFont(new Font("Serif", Font.BOLD, 18));
		usernameLbl.setBounds(90, 110, 480, 60); 
		add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(190, 124, 270, 30);
	    usernameTxt.setFont(new Font ("Serif", Font.PLAIN, 14));
	    add(usernameTxt);
		
		passwordLbl = new JLabel("Password: ");
		passwordLbl.setForeground(Color.black);
		passwordLbl.setFont(new Font("Serif", Font.BOLD, 18));
		passwordLbl.setBounds(90, 170, 480, 60); 
		add(passwordLbl);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(190, 184, 270, 30);
	    passwordTxt.setFont(new Font ("Serif", Font.PLAIN, 14));
	    add(passwordTxt);
	    
		loginBtn = new JButton("Login");
		loginBtn.setBackground(new Color(96, 96, 96));
		loginBtn.setFont(new Font ("Serif", Font.BOLD, 18));
		loginBtn.setForeground(Color.black);
		loginBtn.setBounds(70, 274, 150, 40);
		loginBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(usernameTxt.getText().equals("")||passwordTxt.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Login Details Missing!","Login Status", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Client client = new Client();
					client.sendAction("EmployeeLogin");
					client.sendLoginDetails(usernameTxt.getText(),passwordTxt.getText());
					client.receiveResponse();
				}
			}
		});
		add(loginBtn);
		
		backBtn = new JButton("Back");
		backBtn.setForeground(Color.black);
		backBtn.setBackground(new Color(96, 96, 96));
		backBtn.setFont(new Font ("Serif", Font.BOLD, 18));
		backBtn.setBounds(300, 274, 160, 40); //x, y, length, width
		add(backBtn);
		backBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new WelcomeWindow();
			}
		});
		setVisible(true);
	}
	public static void main(String args[])
	{
		  new EmployeeLogInWindow();
	}	
}
