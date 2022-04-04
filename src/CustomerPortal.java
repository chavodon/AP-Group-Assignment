package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CustomerPortal extends JFrame{
		JFrame frame = new JFrame("CLEGS CLIENT PORTAL");
		//Declare global variables
		
		private static final long serialVersionUID = 1L;
		//JPanel jp = new JPanel();
		private JButton addBtn;
		private JButton editBtn;
		private JButton viewBtn;
		
		public CustomerPortal() {
		
		setLayout(null);
		setSize(new Dimension(550,500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.black);
		
		//--------------CREATE BUTTONS----------------------
		
		addBtn = new JButton("NEW COMPLAIN");
		editBtn= new JButton("EDIT COMPLAIN");
		viewBtn = new JButton("VIEW COMPLAIN STATUS");
		
		
		addBtn.setBackground(Color.PINK);
		addBtn.setForeground(Color.gray);
		addBtn.setOpaque(true);
		
		editBtn.setForeground(Color.gray);
		editBtn.setBackground(Color.orange);
		editBtn.setOpaque(true);
		
		viewBtn.setBackground(Color.cyan);
		viewBtn.setForeground(Color.gray);
		viewBtn.setOpaque(true);
		
	    addBtn.setBounds(195,205,180,50);
		editBtn.setBounds(195,305,180,50);
		viewBtn.setBounds(195,405,180,50);
		
		//-----------------ADD LISTENERS---------------------
		
		
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		add(addBtn);
		add(editBtn);
		add(viewBtn);
		
	}
	
	public static void main(String args[]){
	    
		  new CustomerPortal();
	     
	   }
}

