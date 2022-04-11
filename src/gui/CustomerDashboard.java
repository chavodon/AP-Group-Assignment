package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import chat.ClientChat;
import chat.ServerChat;
import clientTCP.Client;

public class CustomerDashboard extends JFrame
{
		//Declare global variables
		private static final long serialVersionUID = 1L;
		private JLabel title;
		private JButton lodgeBtn;
		private JButton queryBtn;
		private JButton viewComplaint;
		private JButton viewPastComplaint;
		private JButton viewPastPayments;
		
		public CustomerDashboard() 
		{
		setTitle("Customer Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(160, 160, 160));
		setResizable(false);
		setBounds(700, 300, 584, 531);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); //center output on screen;
		
		 title = new JLabel();
	     title.setFont(new Font("Myriad Pro", Font.ITALIC, 28)); 
	     title.setForeground(Color.black);
	     title.setText("Customer Dashboard");
	     title.setBounds(150, 20, 480, 60); //x axis, y axis, length, width
		 add(title);
		
		//--------------CREATE BUTTONS----------------------
		lodgeBtn = new JButton("Lodge Complaint");
		queryBtn= new JButton("Query Account Status");
		viewComplaint = new JButton("View a Complaint");
		viewPastComplaint = new JButton("View Past Complaints");
		viewPastPayments = new JButton("View Past Payments");
		
		lodgeBtn.setBackground(Color.PINK);
		lodgeBtn.setFont(new Font("Serif", Font.BOLD, 18));
		lodgeBtn.setForeground(Color.black);
	    lodgeBtn.setBounds(50,105,180,40); //x axis, y axis, length, width
		lodgeBtn.setOpaque(true);
		
		queryBtn.setForeground(Color.black);
		queryBtn.setBackground(Color.orange);
		queryBtn.setFont(new Font("Serif", Font.BOLD, 18));
		queryBtn.setBounds(270,105,230,40); //x axis, y axis, length, width
		queryBtn.setOpaque(true);
		
		viewComplaint.setBackground(Color.cyan);
		viewComplaint.setForeground(Color.black);
		viewComplaint.setFont(new Font("Serif", Font.BOLD, 18));
		viewComplaint.setBounds(50,180,180,40); //x axis, y axis, length, width
		viewComplaint.setOpaque(true);
		
		viewPastComplaint.setBackground(Color.PINK);
		viewPastComplaint.setForeground(Color.black);
		viewPastComplaint.setFont(new Font("Serif", Font.BOLD, 18));
		viewPastComplaint.setBounds(270,180,210,40); //x axis, y axis, length, width
		viewPastComplaint.setOpaque(true);
		
		viewPastPayments.setBackground(Color.orange);
		viewPastPayments.setForeground(Color.black);
		viewPastPayments.setFont(new Font("Serif", Font.BOLD, 18));
		viewPastPayments.setBounds(50,255,199,40); //x axis, y axis, length, width
		viewPastPayments.setOpaque(true);
		
		//-----------------ADD LISTENERS---------------------
		lodgeBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				  new LodgeComplaintWindow();
				  dispose();
			}
		});
		queryBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new QueryAccountStatus();
				dispose();
			}
		});
		viewComplaint.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new ViewComplaint();
				dispose();
			}
		});
		viewPastComplaint.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ViewAllComplaint view = new ViewAllComplaint();
				dispose();
			}
		});
		viewPastPayments.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ViewPayments view = new ViewPayments();
				dispose();
			}
		});
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		add(lodgeBtn);
		add(queryBtn);
		add(viewComplaint);
		add(viewPastComplaint);
		add(viewPastPayments);
		setVisible(true);
	}
	public static void main(String args[])
	{
		  new CustomerDashboard();
	 }
}

