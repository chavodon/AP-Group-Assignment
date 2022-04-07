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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CustomerLoginWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	JLabel title;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JButton submitBtn;
	private JButton bckBtn;
	
	private JLabel welcomeLabel;
	
	//Create an object of the GridBagConstraints/ Settings
	
	private GridBagConstraints gbc;
	
	public CustomerLoginWindow()
	{
		//Initialize OR Instantiate the components
		setTitle("Customer Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(160, 160, 160));
		setResizable(false);
		setBounds(700, 300, 584, 531);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); //center output on screen
		
		 title = new JLabel();
	     title.setFont(new Font("Myriad Pro", Font.ITALIC, 28)); 
	     title.setForeground(Color.black);
	     title.setText("Customer Login");
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
				new CustomerDashboard();
			}
		});
		
		add(submitBtn);
		
		bckBtn = new JButton("Back");
		bckBtn.setForeground(Color.blue);
		bckBtn.setBackground(Color.blue);
		//gbc = new GridBagConstraints();
		
		//welcomeLabel= new JLabel("WELCOME");
		setVisible(true);
		
//		bckBtn.addActionListener(this);
//		button.addActionListener(this);	
		//layoutComponents();
	}
	
//	private void layoutComponents()
//	{
//		//Set the Layout Manager for the frame
//		frame.setLayout(new GridBagLayout());
//		
//		welcomeLabel.setText("Customer Login");
//		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
//		welcomeLabel.setBackground(Color.BLACK);
//		welcomeLabel.setOpaque(true);
//		//Font f = welcomeLabel.getFont();
//		//welcomeLabel.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
//		welcomeLabel.setForeground(Color.ORANGE);
//		gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.gridwidth = 0;
//		gbc.anchor = GridBagConstraints.NORTH;
//		gbc.insets = new Insets(-75,50,2,2);
//		frame.add(welcomeLabel, gbc);
//		
//		usernameLbl.setSize(150,20);
//		//Set size of the label for each components re=initalize the gridbag constraints
//		gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		//Set which column on the X-Axis the first component should go
//		gbc.gridy = 0;
//		//Set which row on the y-axis the first component should go
//		gbc.gridwidth = 1;
//		//Set how many colums the component should span
//		
//		//Set where in the frame the component should be located
//		gbc.anchor = GridBagConstraints.WEST;
//		
//		//The inset field specifies the extername padding of the component, the minimum
//		// The amount of space between the component and the edges of its display area
//		gbc.insets = new Insets(9,10,0,0);
//		
//		//Add the component to the frame with all its contraints
//		frame.add(usernameLbl, gbc);
//		
//		gbc = new GridBagConstraints();
//		//Re-Initialize the gridbag
//		gbc.gridx = 3;
//		gbc.gridy = 0;
//		gbc.gridwidth = 3;
//		gbc.gridheight = 2;
//		//Set the internal padding for the components width
//		gbc.ipadx = 0;
//		gbc.anchor = GridBagConstraints.NORTHWEST;
//		//The insets field specified the external padding of the component, the minimum
//		//Amount of space between the component and the edges of its display area
//		gbc.insets = new Insets(11,7,0,10);
//		
//		//Add the component to the frame with all its constraints
//		frame.add(usernameTxt, gbc);
//		
//		passwordLbl.setText("Password:");
//		passwordLbl.setForeground(Color.white);
//		gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		gbc.gridwidth = 1;
//		gbc.anchor = GridBagConstraints.NORTHWEST;
//		gbc.insets = new Insets(9,10,5,5);
//		frame.add(passwordLbl, gbc);
//		
//		gbc = new GridBagConstraints();
//		gbc.gridx = 3;
//		gbc.gridy = 1;
//		gbc.gridwidth = 3;
//		gbc.gridheight = 2;
//		gbc.ipadx = 0;
//		gbc.anchor = GridBagConstraints.NORTHWEST;
//		gbc.insets = new Insets(11,7,3,10);
//		frame.add(passwordTxt, gbc);
//		
//		gbc = new GridBagConstraints();
//		gbc.gridx = 4;
//		gbc.gridy = 4;
//		gbc.gridwidth = 6;
//		gbc.ipadx = 40;
//		gbc.ipady = 6;
//		gbc.anchor = GridBagConstraints.CENTER;
//		gbc.insets = new Insets(17,0,0,0);
//		frame.add(submitBtn, gbc);
//		
//		gbc = new GridBagConstraints();
//		gbc.gridx = 4;
//		gbc.gridy = 4;
//		gbc.gridwidth = 1;
//		gbc.ipadx = 4;
//		gbc.ipady = 4;
//		gbc.anchor = GridBagConstraints.CENTER;
//		gbc.insets = new Insets(17,-10,-3,-3);
//		frame.add(bckBtn, gbc);
//		
//		frame.setSize(new Dimension(550,450));
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.getContentPane().setBackground(Color.BLACK);
//	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		//IF statement to select or determine what specific method to execute if the user clicks a particular button.

				if (e.getSource() == submitBtn) {
					EmployeePortal emp = new EmployeePortal();
					//validate password
				} 
				
			else if (e.getSource() == bckBtn) {
					WelcomeWindow ww = new WelcomeWindow();	
				} 

				
			}  

	public static void main(String args[]){
        
		  new CustomerLoginWindow();
	       
	     }
		
}
