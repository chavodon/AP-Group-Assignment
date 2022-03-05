package dashboard;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StaffDashboard 
{
	private JFrame frame;
	private JLabel  option1, option2;
	private JButton viewBtn, assignBtn;
	
	public StaffDashboard()
	{
		frame = new JFrame();		
		frame.setTitle("Staff Dashboard");
		frame.setBounds(700, 300, 584, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setVisible(true);
		
		option1= new JLabel("1. View Customer Complaints");
		option1.setFont(new Font("Serif", Font.PLAIN, 18));
		option1.setBounds(80, 50, 390, 25);
		frame.getContentPane().add(option1);
		
		viewBtn = new JButton("View");
		viewBtn.setFont(new Font("Serif", Font.BOLD, 18));
		viewBtn.setBackground(new Color(255, 255, 153));
		viewBtn.setBorderPainted(true);
		viewBtn.setBounds(90, 90, 117, 39);
		frame.getContentPane().add(viewBtn);
		
		option2= new JLabel("2. Assign Complaint To Technician");
		option2.setFont(new Font("Serif", Font.PLAIN, 18));
		option2.setBounds(90, 170, 390, 25);
		frame.getContentPane().add(option2);
		
		assignBtn = new JButton("Assign");
		assignBtn.setFont(new Font("Serif", Font.BOLD, 18));
		assignBtn.setBackground(new Color(255, 255, 153));
		assignBtn.setBorderPainted(true);
		assignBtn.setBounds(90, 215, 117, 39);
		frame.getContentPane().add(assignBtn);
	}
}
