package employee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.event.InternalFrameEvent;

import gui.CompleteSolution;
import gui.ViewAllComplaint;
import gui.ViewResolvedComplaint;

public class EmployeePortal extends JFrame implements ActionListener {
	
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

	private JFrame frame = new JFrame("CLEGS EMPLOYEE PORTAL");
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
	private JLabel label14;
	private JLabel label15;
	private JLabel label16;
	private JLabel label17;
	
	public EmployeePortal() {
		label = new JLabel("CLEGS DASHBOARD");
	    label.setBounds(500,25,200,20);
	    label.setFont(new Font("Ariel", Font.BOLD, 18));
	    label.setBackground(Color.black);
	    label.setForeground(Color.white);
	    label.setOpaque(true);
	    
	    label2 = new JLabel("Complaint Information");
	    label2.setBounds(775,85,250,20);
	    label2.setFont(new Font("Ariel", Font.BOLD, 18));
	    label2.setBackground(Color.black);
	    label2.setForeground(Color.white);
	    label2.setOpaque(true);
	    
	    label3 = new JLabel("Services");
	    label3.setBounds(250,85,100,20);
	    label3.setFont(new Font("Ariel", Font.BOLD, 18));
	    label3.setBackground(Color.black);
	    label3.setForeground(Color.white);
	    label3.setOpaque(true);
	    
	    label4 = new JLabel("Resolved Complaints");
	    label4.setBounds(725,120,150,20);
	    label4.setFont(new Font("Ariel", Font.BOLD, 12));
	    label4.setBackground(Color.black);
	    label4.setForeground(Color.white);
	    label4.setOpaque(true);
	    
	    label5 = new JLabel("Outstanding Complaints");
	    label5.setBounds(920,120,150,20);
	    label5.setFont(new Font("Ariel", Font.BOLD, 12));
	    label5.setBackground(Color.black);
	    label5.setForeground(Color.white);
	    label5.setOpaque(true);
	    
	    label6 = new JLabel("Customer Service");
	    label6.setBounds(525,155,150,20);
	    label6.setFont(new Font("Ariel", Font.BOLD, 15));
	    label6.setBackground(Color.black);
	    label6.setForeground(Color.white);
	    label6.setOpaque(true);
	    
	    label7 = new JLabel("Product/Service");
	    label7.setBounds(525,255,150,20);
	    label7.setFont(new Font("Ariel", Font.BOLD, 15));
	    label7.setBackground(Color.black);
	    label7.setForeground(Color.white);
	    label7.setOpaque(true);
	    
	    label8 = new JLabel("Bill/Payment");
	    label8.setBounds(525,355,150,20);
	    label8.setFont(new Font("Ariel", Font.BOLD, 15));
	    label8.setBackground(Color.black);
	    label8.setForeground(Color.white);
	    label8.setOpaque(true);
	    
	    label9 = new JLabel("General");
	    label9.setBounds(525,455,150,20);
	    label9.setFont(new Font("Ariel", Font.BOLD, 15));
	    label9.setBackground(Color.black);
	    label9.setForeground(Color.white);
	    label9.setOpaque(true);
	    
	    label10 = new JLabel("10");
	    label10.setBounds(778,155,20,20);
	    label10.setFont(new Font("Ariel", Font.BOLD, 15));
	    label10.setBackground(Color.black);
	    label10.setForeground(Color.white);
	    label10.setOpaque(true);
	    
	    label11 = new JLabel("10");
	    label11.setBounds(978,155,20,20);
	    label11.setFont(new Font("Ariel", Font.BOLD, 15));
	    label11.setBackground(Color.black);
	    label11.setForeground(Color.white);
	    label11.setOpaque(true);
	    
	    label12 = new JLabel("20");
	    label12.setBounds(778,255,20,20);
	    label12.setFont(new Font("Ariel", Font.BOLD, 15));
	    label12.setBackground(Color.black);
	    label12.setForeground(Color.white);
	    label12.setOpaque(true);
	    
	    label13 = new JLabel("25");
	    label13.setBounds(978,255,20,20);
	    label13.setFont(new Font("Ariel", Font.BOLD, 15));
	    label13.setBackground(Color.black);
	    label13.setForeground(Color.white);
	    label13.setOpaque(true);
	    
	    label14 = new JLabel("30");
	    label14.setBounds(778,355,20,20);
	    label14.setFont(new Font("Ariel", Font.BOLD, 15));
	    label14.setBackground(Color.black);
	    label14.setForeground(Color.white);
	    label14.setOpaque(true);
	    
	    label15 = new JLabel("35");
	    label15.setBounds(978,355,20,20);
	    label15.setFont(new Font("Ariel", Font.BOLD, 15));
	    label15.setBackground(Color.black);
	    label15.setForeground(Color.white);
	    label15.setOpaque(true);
	    
	    label16 = new JLabel("40");
	    label16.setBounds(778,455,20,20);
	    label16.setFont(new Font("Ariel", Font.BOLD, 15));
	    label16.setBackground(Color.black);
	    label16.setForeground(Color.white);
	    label16.setOpaque(true);
	    
	    label17 = new JLabel("45");
	    label17.setBounds(978,455,20,20);
	    label17.setFont(new Font("Ariel", Font.BOLD, 15));
	    label17.setBackground(Color.black);
	    label17.setForeground(Color.white);
	    label17.setOpaque(true);
	    
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
	    frame.add(label14);
	    frame.add(label15);
	    frame.add(label16);
	    frame.add(label17);
	    
		frame.setLayout(null);
		frame.setSize(new Dimension(1200,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.BLACK);
		
		//--------------CREATE BUTTONS----------------------
		
		viewBtn = new JButton("VIEW ALL COMPLAINTS");
		editBtn= new JButton("EDIT SOLUTION");
		vrcBtn = new JButton("RESOLVED COMPLAINTS");
		
		
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
		
		viewBtn.setBounds(175,140,220,50);
		editBtn.setBounds(175,240,220,50);
		vrcBtn.setBounds(175,340,220,50);	
		
		//-----------------ADD LISTENERS---------------------
		viewBtn.addActionListener(this);
		editBtn.addActionListener(this);
		vrcBtn.addActionListener(this);

		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		frame.add(viewBtn);
		frame.add(editBtn);
		frame.add(vrcBtn);

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
	public void actionPerformed(ActionEvent e) {
		//IF statement to select or determine what specific method to execute if the user clicks a particular button.
		if (e.getSource() == viewBtn) {
			ViewAllComplaint vc = new ViewAllComplaint();
		}			
		else if (e.getSource() == editBtn) {
			CompleteSolution ww = new CompleteSolution(" ");	
		} 	
		else if (e.getSource() == vrcBtn) {
			ViewResolvedComplaint vrc = new ViewResolvedComplaint();	
		}		
	}

	public static void main(String[] args) {
		new EmployeePortal();
	}

}
