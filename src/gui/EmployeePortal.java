package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class EmployeePortal extends JFrame implements ActionListener, WindowListener {
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	//JPanel jp = new JPanel();
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	private JButton allBtn;
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
		allBtn = new JButton("SHOW RESOLVED COMPLAINT");
		
		viewBtn.setBackground(Color.GREEN);
		viewBtn.setForeground(Color.blue);
		viewBtn.setOpaque(true);
		
		editBtn.setForeground(Color.blue);
		editBtn.setBackground(Color.gray);
		editBtn.setOpaque(true);
		
		allBtn.setBackground(Color.yellow);
		allBtn.setForeground(Color.blue);
		allBtn.setOpaque(true);
		
	    viewBtn.setBounds(185,105, 220,50);
		editBtn.setBounds(185,205,220,50);
		allBtn.setBounds(185,305,220,50);
		
		viewBtn.addActionListener(this);
		editBtn.addActionListener(this);
		allBtn.addActionListener(this);
		
		
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		frame.add(viewBtn);
		frame.add(editBtn);
		frame.add(allBtn);
		
		navbar();
	
	}
	
	public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
    {  
		//Link to correct page when those are completed
		if(ae.getSource() == viewBtn) {
			//create instance of the NewPage  
            EmployeePortal page = new EmployeePortal();  
              
            //make page visible to the user  
            page.setVisible(true);
		}
		if(ae.getSource() == editBtn) {
			//create instance of the NewPage  
            WelcomeWindow page = new WelcomeWindow();  
              
            //make page visible to the user  
            page.setVisible(true);
		}
		if(ae.getSource() == allBtn) {
			//create instance of the NewPage  
            EmployeeLogInWindow page = new EmployeeLogInWindow();  
              
            //make page visible to the user  
            page.setVisible(true);
		}
        
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

	public static void main(String[] args) {
		new EmployeePortal();

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
