package gui;

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

public class EmployeePortal extends JFrame implements ActionListener {
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	//JPanel jp = new JPanel();
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	private JButton vrcBtn;
	private JButton editBtn;
	private JButton viewBtn;

	private JFrame frame = new JFrame("CLEGS EMPLOYEES PORTAL");
	private JLabel label;
	
	public EmployeePortal() {
		label = new JLabel("Welcome ");
	    label.setBounds(50,45,350,20);
	    label.setFont(new Font("Ariel", Font.BOLD, 18));
	    label.setBackground(Color.black);
	    label.setForeground(Color.WHITE);
	    label.setOpaque(true);
	    frame.add(label);
		
		frame.setLayout(null);
		frame.setSize(new Dimension(550,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.black);
		
		//--------------CREATE BUTTONS----------------------
		
		
		viewBtn = new JButton("VIEW COMPLAINTS");
		editBtn= new JButton("EDIT SOLUTION");
		vrcBtn = new JButton("SHOW RESOLVED COMPLAINT");
		
		
		viewBtn.setBackground(Color.GREEN);
		viewBtn.setForeground(Color.blue);
		viewBtn.setOpaque(true);
		
		vrcBtn.setForeground(Color.blue);
		editBtn.setBackground(Color.gray);
		editBtn.setOpaque(true);
		
		vrcBtn.setBackground(Color.yellow);
		vrcBtn.setForeground(Color.blue);
		vrcBtn.setOpaque(true);
		
	    viewBtn.setBounds(185,105, 220,50);
		editBtn.setBounds(185,205,220,50);
		vrcBtn.setBounds(185,305,220,50);
		
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
		menuItem = new JMenuItem("View List of Resolved Complaint", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(Color.white);
	    menu.setForeground(Color.black);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Resolve a Complaint", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(Color.white);
	    menu.setForeground(Color.black);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Edit a complaint solution", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(Color.white);
	    menu.setForeground(Color.black);
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
					WelcomeWindow ww = new WelcomeWindow();	
				} 
				
			else if (e.getSource() == vrcBtn) {
				ViewResolvedComplaint vrc = new ViewResolvedComplaint();	
			} 

				
			}  

	public static void main(String[] args) {
		new EmployeePortal();

	}

}
