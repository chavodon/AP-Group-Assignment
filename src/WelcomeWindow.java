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
	private JButton deptBtn;
	private JButton empBtn;
	private JLabel msgLabel;
	JFrame frame = new JFrame("CLEGS COMPLAINT MANAGEMENT SYSTEM - LOG IN");


public WelcomeWindow() {
		
		setLayout(null);
		setSize(new Dimension(550,450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.black);
		
		//--------------CREATE BUTTONS----------------------
		
		empBtn = new JButton("ADMIN LOGIN");
		deptBtn= new JButton("CUSTOMER LOGIN");
		
		
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
		
		empBtn.setBackground(Color.PINK);
		empBtn.setForeground(Color.gray);
		empBtn.setOpaque(true);
		
		deptBtn.setForeground(Color.gray);
		deptBtn.setBackground(Color.cyan);
		deptBtn.setOpaque(true);
		
		empBtn.setBounds(95,205,150,50);
		deptBtn.setBounds(295,205,150,50);
		
		//-----------------ADD LISTENERS---------------------
		empBtn.addActionListener(this);
		deptBtn.addActionListener(this);
		
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		add(empBtn);
		add(deptBtn);
		
		
		Design();
  }


	public void Design() {
		setTitle("WELCOME TO CLEGS COMPLAINT MANAGEMENT- LOG IN");
	}
	
	
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == empBtn) {
			EmployeeLogInWindow page = new EmployeeLogInWindow();
		}
		if (e.getSource() == deptBtn) {
			CustomerLoginWindow page = new CustomerLoginWindow();
		}
		
	}


	public static void main(String args[]){
	    
		  new WelcomeWindow();
	     
	   }

}
