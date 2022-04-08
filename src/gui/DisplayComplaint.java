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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class DisplayComplaint {
	
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	public JTextArea textArea;
	public JTextField searchField = new JTextField(30);
    public JButton searchB = new JButton ("Search");
    public JTable result = new JTable();
	public JPanel panel = new JPanel();
    public JScrollPane scrollPane = new JScrollPane(result);
   
	
	private JFrame frame = new JFrame("View Complaint");
	
	public DisplayComplaint() {
		
		frame.setLayout(null);
		frame.setSize(new Dimension(550,450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.darkGray);
		
		JTable table = new JTable();
		Object[]columns = {"First Name", "Last Name", "Telephone", "Email", "Complaint"};
		DefaultTableModel mode = new DefaultTableModel();
		mode.setColumnIdentifiers(columns);
		
		table.setModel(mode);
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.red);
		table.setSelectionForeground(Color.white);
		table.setGridColor(Color.red);
		table.setRowHeight(50);
		table.setFont(new Font("Times", Font.PLAIN, 18));
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setForeground(Color.red);
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(20,80,500,300);
		
		frame.getContentPane().add(scroll);
		frame.setVisible(true);
	}
	
	  public void menu() {
			//Create the Menu Bar
				menuBar = new JMenuBar();
				
				this.addKeyListener(this);
				
				//Build Menu
				menu = new JMenu("Services");
				menu.setMnemonic(KeyEvent.VK_A);
				menu.addMenuListener(this);
				menu.getAccessibleContext().setAccessibleDescription(null);
				menu.setBounds(250,70,50,15);
			    menu.setFont(new Font("Ariel", Font.BOLD, 12));
			    menu.setOpaque(true);
			    menuBar.add(menu);
				//menuItem = new JMenuItem("")
				
				//menu items
				menuItem = new JMenuItem("Make a Complain", KeyEvent.VK_T);
				menuItem.addKeyListener(this);
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						new CustomerComplaintWindow();
					}
					
				});
			
				menuItem.setBackground(Color.green);
			    menu.setForeground(Color.yellow);
				menu.add(menuItem);
				
				
				menuItem = new JMenuItem("Live Chat", KeyEvent.VK_T);
				menuItem.addKeyListener(this);
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.setBackground(Color.yellow);
			    menu.setForeground(Color.yellow);
				menu.add(menuItem);
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//new ChatWindow();
					}
					
				});
				
				menuItem = new JMenuItem("Video Call A Representative", KeyEvent.VK_T);
				menuItem.addKeyListener(this);
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.setBackground(Color.yellow);
			    menu.setForeground(Color.yellow);
				menu.add(menuItem);
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//new VideoCall();
					}
					
				});
				
				JMenu back = new JMenu("Back");
				
				back.setFont(new Font("Ariel", Font.BOLD, 12));
				back.setMnemonic(KeyEvent.VK_A);
				back.addMenuListener(this);
				back.getAccessibleContext().setAccessibleDescription(null);
				menuBar.add(back);
				back.addMenuListener(new javax.swing.event.MenuListener(){

					@Override
					public void menuSelected(MenuEvent e) {
						new CustomerPortal();
						
					}

					@Override
					public void menuDeselected(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void menuCanceled(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				
					
				});
				
				
				JMenu help = new JMenu("Help");
				help.setFont(new Font("Ariel", Font.BOLD, 12));
				help.setMnemonic(KeyEvent.VK_A);
				help.addMenuListener(this);
				help.getAccessibleContext().setAccessibleDescription(null);
				menuBar.add(help);
				help.addMenuListener(new javax.swing.event.MenuListener(){

					@Override
					public void menuSelected(MenuEvent e) {
						//new LiveChat();
						
					}

					@Override
					public void menuDeselected(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void menuCanceled(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				
				JMenu logout = new JMenu("Log Out");
				logout.setFont(new Font("Ariel", Font.BOLD, 12));
				logout.setMnemonic(KeyEvent.VK_A);
				logout.addMenuListener(this);
				logout.getAccessibleContext().setAccessibleDescription(null);
				menuBar.add(logout);
				logout.addMenuListener(new javax.swing.event.MenuListener(){

					@Override
					public void menuSelected(MenuEvent e) {
						new CustomerLoginWindow();
						
						
					}

					@Override
					public void menuDeselected(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void menuCanceled(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
		
		 
				});
				
				JMenu exit = new JMenu("Exit Program");
				exit.setFont(new Font("Ariel", Font.BOLD, 12));
				exit.setMnemonic(KeyEvent.VK_A);
				exit.addMenuListener(this);
				exit.getAccessibleContext().setAccessibleDescription(null);
				menuBar.add(exit);
				exit.addMenuListener(new javax.swing.event.MenuListener(){

					@Override
					public void menuSelected(MenuEvent e) {
							System.exit(0);
						
						
					}

					@Override
					public void menuDeselected(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void menuCanceled(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
		
		 
				});
				
				frame.add(menuBar);
				frame.setJMenuBar(menuBar); 
		 }
				
			
	 }
	
	public static void main(String[] args) {

		new DisplayComplaint();

	}

}
