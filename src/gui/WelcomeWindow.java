package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
	
//Implements ActionListener to listen and respond to call.

public class WelcomeWindow extends JFrame implements ActionListener 
{ 
	
	//Declare global variables
	
	private static final long serialVersionUID = 1L;
	//JPanel jp = new JPanel();
	private JButton customerBtn;
	private JButton adminBtn;
	private JButton signUpBtn;
	private JLabel msgLabel;
	JFrame frame = new JFrame("CLEGS COMPLAINT MANAGEMENT SYSTEM - LOG IN");


public WelcomeWindow() {
		
		setLayout(null);
		setSize(new Dimension(550,450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.BLACK);
		
		//--------------CREATE BUTTONS----------------------
		
		adminBtn = new JButton("ADMIN LOGIN");
		customerBtn= new JButton("CUSTOMER LOGIN");
		signUpBtn = new JButton("SIGN UP");
		
		//--------------BUTTON LAYOUT AND ALIGNMENT----------
/*
 *In a setBounds method, the:
 -First digit controls the x axis of the component 
 *Second digit controls the y axis of the component 
 *Third digit controls the component length 
 *Fourth digit controls the component height 
 */
		 msgLabel = new JLabel("CLEGS COMPLAINT MANAGEMENT SYSTEM\n - LOG IN\n");
		    msgLabel.setBounds(100,75,350,50);
		    msgLabel.setFont(new Font("Ariel", Font.BOLD, 12));
		    msgLabel.setBackground(Color.black);
		    msgLabel.setForeground(Color.WHITE);
		    msgLabel.setOpaque(true);
		    add(msgLabel);
		
	    adminBtn.setFont(new Font("Trattatello", Font.BOLD, 15));
		adminBtn.setBackground(Color.PINK);
		adminBtn.setForeground(Color.darkGray);
		adminBtn.setOpaque(true);
		
		customerBtn.setFont(new Font("Trattatello", Font.BOLD, 15));
		customerBtn.setForeground(Color.darkGray);
		customerBtn.setBackground(Color.cyan);
		customerBtn.setOpaque(true);
		
		signUpBtn.setFont(new Font("Trattatello", Font.BOLD, 15));
		signUpBtn.setForeground(Color.darkGray);
		signUpBtn.setBackground(Color.red);
		signUpBtn.setOpaque(true);
		
		
		adminBtn.setBounds(95,205,150,50);
		customerBtn.setBounds(295,205,150,50);
		signUpBtn.setBounds(205,305,150,50);
		
		//-----------------ADD LISTENERS---------------------
		adminBtn.addActionListener(this);
		customerBtn.addActionListener(this);
		signUpBtn.addActionListener(this);
		
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		add(adminBtn);
		add(customerBtn);
		add(signUpBtn);
		
		
		Design();
  }


public void Design() {
	setTitle("WELCOME TO CLEGS COMPLAINT MANAGEMENT- LOG IN");
}


 @SuppressWarnings("unused")
@Override
	public void actionPerformed(ActionEvent e) {
//IF statement to select or determine what specific method to execute if the user clicks a particular button.

		if (e.getSource() == adminBtn) {
			EmployeeLogInWindow emp = new EmployeeLogInWindow();
		} 
		
	else if (e.getSource() == customerBtn) {
			CustomerLoginWindow cus = new CustomerLoginWindow();	
		} 

	else if (e.getSource() == signUpBtn) {
			SignUpWindow signup = new SignUpWindow();	
			
		}
		
	}  

	public static void main(String args[]){
	    
		  new WelcomeWindow();
	     
	   }

}