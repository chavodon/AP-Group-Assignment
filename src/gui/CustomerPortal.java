package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CustomerPortal extends JFrame implements ActionListener{
		JFrame frame = new JFrame("CLEGS CLIENT PORTAL");
		//Declare global variables
		
		private static final long serialVersionUID = 1L;
		//JPanel jp = new JPanel();
		private JButton newBtn;
		private JButton editBtn;
		private JButton viewBtn;
		
		public CustomerPortal() {
		JFrame frame = new JFrame("CLEGS CLIENT PORTAL");
		//Declare global variables
		setLayout(null);
		setSize(new Dimension(550,500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.black);
		
		//--------------CREATE BUTTONS----------------------
		
		newBtn = new JButton("NEW COMPLAIN");
		editBtn= new JButton("EDIT COMPLAIN");
		viewBtn = new JButton("VIEW COMPLAIN STATUS");
		
		
		newBtn.setBackground(Color.PINK);
		newBtn.setForeground(Color.gray);
		newBtn.setOpaque(true);
		
		editBtn.setForeground(Color.gray);
		editBtn.setBackground(Color.orange);
		editBtn.setOpaque(true);
		
		viewBtn.setBackground(Color.cyan);
		viewBtn.setForeground(Color.gray);
		viewBtn.setOpaque(true);
		
	    newBtn.setBounds(195,105,180,50);
		editBtn.setBounds(195,205,180,50);
		viewBtn.setBounds(195,305,180,50);
		
		//-----------------ADD LISTENERS---------------------
		
		newBtn.addActionListener(this);
		editBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		add(newBtn);
		add(editBtn);
		add(viewBtn);
		
	}
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			//IF statement to select or determine what specific method to execute if the user clicks a particular button.

					if (e.getSource() == newBtn) {
						CustomerComplaintWindow ccw = new CustomerComplaintWindow();
						//validate password
					} 
					
				else if (e.getSource() == editBtn) {
						EditComplaint_1 ec = new EditComplaint_1(getTitle());	
					} 
					
				else if (e.getSource() == viewBtn) {
					ViewComplaint view = new ViewComplaint();	
				} 
					
				}  

	
	public static void main(String args[]){
	    
		  new CustomerPortal();
	     
	   }
}

