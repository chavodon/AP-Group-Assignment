package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EmployeeLogInWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JButton submitBtn;	
	private JButton bckBtn;
	
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
		 
		usernameLbl = new JLabel("Username:");
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
	    
		submitBtn = new JButton("Login");
		submitBtn.setBackground(new Color(96, 96, 96));
		submitBtn.setFont(new Font ("Serif", Font.BOLD, 18));
		submitBtn.setForeground(Color.black);
		submitBtn.setBounds(192, 274, 180, 40);
		submitBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new EmployeePortal();
			}
		});
		
		add(submitBtn);
		
		bckBtn = new JButton("Back");
		bckBtn.setBackground(new Color(96, 96, 96));
		bckBtn.setFont(new Font ("Serif", Font.BOLD, 18));
		bckBtn.setForeground(Color.black);
		bckBtn.setBounds(192, 324, 180, 40);
		bckBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new WelcomeWindow();
			}
		});
		
		add(bckBtn);

		setVisible(true);
	}
	
	public static void main(String args[]){
		new EmployeeLogInWindow();
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

		
}
