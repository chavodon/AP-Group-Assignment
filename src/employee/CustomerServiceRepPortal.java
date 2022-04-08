package employee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import gui.CompleteSolution;
import gui.ViewResolvedComplaint;
import gui.ViewSpecificComplaint;

public class CustomerServiceRepPortal extends JFrame implements ActionListener {
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	private JButton viewBtn;
	private JButton editBtn;
	private JButton vrcBtn;
	private JButton custBtn;
	private JButton prodBtn;
	private JButton billBtn;
	private JButton genBtn;

	private JFrame frame = new JFrame("Customer Service Representative Dashboard");
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private JLabel label11;
	private JLabel label12;
	private JLabel label13;
	
	public CustomerServiceRepPortal() {
		label = new JLabel("Customer Service Representative Dashboard");
	    label.setBounds(400,25,500,20);
	    label.setFont(new Font("Ariel", Font.BOLD, 18));
	    label.setForeground(Color.black);
	    
	    label2 = new JLabel("Complaint Information");
	    label2.setBounds(775,85,250,20);
	    label2.setFont(new Font("Ariel", Font.BOLD, 18));
	    label2.setForeground(Color.black);
	    
	    label3 = new JLabel("Services");
	    label3.setBounds(250,85,100,20);
	    label3.setFont(new Font("Ariel", Font.BOLD, 18));
	    label3.setForeground(Color.black);
	    
	    label4 = new JLabel("Resolved Complaints");
	    label4.setBounds(725,120,150,20);
	    label4.setFont(new Font("Ariel", Font.BOLD, 12));
	    label4.setForeground(Color.black);
	    
	    label5 = new JLabel("Outstanding Complaints");
	    label5.setBounds(920,120,150,20);
	    label5.setFont(new Font("Ariel", Font.BOLD, 12));
	    label5.setForeground(Color.black);
	    	    
	    label6 = new JLabel("10");
	    label6.setBounds(778,170,20,20);
	    label6.setFont(new Font("Ariel", Font.BOLD, 15));
	    label6.setForeground(Color.black);
	    
	    label7 = new JLabel("10");
	    label7.setBounds(978,170,20,20);
	    label7.setFont(new Font("Ariel", Font.BOLD, 15));
	    label7.setForeground(Color.black);
	    
	    label8 = new JLabel("20");
	    label8.setBounds(778,270,20,20);
	    label8.setFont(new Font("Ariel", Font.BOLD, 15));
	    label8.setForeground(Color.black);
	    
	    label9 = new JLabel("25");
	    label9.setBounds(978,270,20,20);
	    label9.setFont(new Font("Ariel", Font.BOLD, 15));
	    label9.setForeground(Color.black);
	    
	    label10 = new JLabel("30");
	    label10.setBounds(778,370,20,20);
	    label10.setFont(new Font("Ariel", Font.BOLD, 15));
	    label10.setForeground(Color.black);
	    
	    label11 = new JLabel("35");
	    label11.setBounds(978,370,20,20);
	    label11.setFont(new Font("Ariel", Font.BOLD, 15));
	    label11.setForeground(Color.black);
	    
	    label12 = new JLabel("40");
	    label12.setBounds(778,470,20,20);
	    label12.setFont(new Font("Ariel", Font.BOLD, 15));
	    label12.setForeground(Color.black);
	    
	    label13 = new JLabel("45");
	    label13.setBounds(978,470,20,20);
	    label13.setFont(new Font("Ariel", Font.BOLD, 15));
	    label13.setForeground(Color.black);
	    
	    frame.add(label);
	    frame.add(label2);
	    frame.add(label3);
	    frame.add(label4);
	    frame.add(label5);
	    frame.add(label6);
	    frame.add(label7);
	    frame.add(label8);
	    frame.add(label9);
	    frame.add(label10);
	    frame.add(label11);
	    frame.add(label12);
	    frame.add(label13);
	    
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		frame.setResizable(false);
		frame.setSize(new Dimension(1200,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//frame.getContentPane().setBackground(Color.BLACK);
		
		//--------------CREATE BUTTONS----------------------
		
		viewBtn = new JButton("VIEW ALL COMPLAINTS");
		editBtn= new JButton("EDIT SOLUTION");
		vrcBtn = new JButton("RESOLVED COMPLAINTS");
		custBtn = new JButton("CUSTOMER SERVICE");
		prodBtn = new JButton("PRODUCT/SERVICE");
		billBtn = new JButton("BILL/PAYMENT");
		genBtn = new JButton("GENERAL");
		
		//---------------BUTTON DISPLAY----------------------
		viewBtn.setBackground(Color.blue);
		viewBtn.setForeground(Color.yellow);
		viewBtn.setOpaque(true);
		
		viewBtn.setBackground(Color.blue);
		viewBtn.setForeground(Color.yellow);
		viewBtn.setOpaque(true);
		
		editBtn.setBackground(Color.blue);
		editBtn.setForeground(Color.yellow);
		editBtn.setOpaque(true);
		
		vrcBtn.setBackground(Color.blue);
		vrcBtn.setForeground(Color.yellow);
		vrcBtn.setOpaque(true);
		
		custBtn.setBackground(Color.blue);
		custBtn.setForeground(Color.yellow);
		custBtn.setOpaque(true);
		
		prodBtn.setBackground(Color.blue);
		prodBtn.setForeground(Color.yellow);
		prodBtn.setOpaque(true);
		
		billBtn.setBackground(Color.blue);
		billBtn.setForeground(Color.yellow);
		billBtn.setOpaque(true);
		
		genBtn.setBackground(Color.blue);
		genBtn.setForeground(Color.yellow);
		genBtn.setOpaque(true);
		
		//--------------BUTTON BOUNDS---------------------
		
		viewBtn.setBounds(175,155,220,50);
		editBtn.setBounds(175,255,220,50);
		vrcBtn.setBounds(175,355,220,50);
		custBtn.setBounds(505,155,200,50);
		prodBtn.setBounds(505,255,200,50);
		billBtn.setBounds(505,355,200,50);
		genBtn.setBounds(505,455,200,50);
		
		//-----------------ADD LISTENERS---------------------
		viewBtn.addActionListener(this);
		editBtn.addActionListener(this);
		vrcBtn.addActionListener(this);
		custBtn.addActionListener(this);
		prodBtn.addActionListener(this);
		billBtn.addActionListener(this);
		genBtn.addActionListener(this);

		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		frame.add(viewBtn);
		frame.add(editBtn);
		frame.add(vrcBtn);
		frame.add(custBtn);
		frame.add(prodBtn);
		frame.add(billBtn);
		frame.add(genBtn);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		navbar();
	
	}
		
	public void navbar() {
		//Create the Menu Bar
		menuBar = new JMenuBar();
		
		
		//Build Menu
		menu = new JMenu("Services");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menu.setBounds(250,70,50,15);
	    menu.setFont(new Font("Ariel", Font.BOLD, 12));
	    menu.setOpaque(true);
	    menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		//menu items
		menuItem = new JMenuItem("Make a Complain", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(Color.green);
	    menu.setForeground(Color.yellow);
		menu.add(menuItem);
		
		menu = new JMenu("Back");
		
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		menu = new JMenu("Help");
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		menu = new JMenu("Log Out");
		menu.setFont(new Font("Ariel", Font.BOLD, 12));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menuBar.add(menu);
		//menuItem = new JMenuItem("")
		
		frame.add(menuBar);
		frame.setJMenuBar(menuBar); 
	}
	
	@SuppressWarnings("unused")
//	public void actionPerformed(ActionEvent e) {
//		//IF statement to select or determine what specific method to execute if the user clicks a particular button.
//		if (e.getSource() == viewBtn) {
//			//ViewAllComplaint vc = new ViewAllComplaint();
//		}			
//		else if (e.getSource() == editBtn) {
//			CompleteSolution ww = new CompleteSolution(" ");	
//		} 	
//		else if (e.getSource() == vrcBtn) {
//			ViewResolvedComplaint vrc = new ViewResolvedComplaint();	
//		}
//		else if (e.getSource() == custBtn) {
//			ViewSpecificComplaint vsc = new ViewSpecificComplaint();
//			vsc.makeQuery("Customer Service");
//		}
//		else if (e.getSource() == custBtn) {
//			ViewSpecificComplaint vsc = new ViewSpecificComplaint();
//			vsc.makeQuery("Product/Service");
//		}
//		else if (e.getSource() == custBtn) {
//			ViewSpecificComplaint vsc = new ViewSpecificComplaint();
//			vsc.makeQuery("Bill/Payment");
//		}
//		else if (e.getSource() == custBtn) {
//			ViewSpecificComplaint vsc = new ViewSpecificComplaint();
//			vsc.makeQuery("General");
//		}
	//}

	public static void main(String[] args) {
		new CustomerServiceRepPortal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
