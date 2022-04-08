package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import client.Client;
import customer.Complaints;

public class ViewAllComplaint extends JFrame implements ActionListener
{
	private JFrame frame = new JFrame("View All Complaints");
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
	
	public ViewAllComplaint() 
	{
		frame.setResizable(false);
		frame.setBounds(700, 300, 720, 531);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
//		Client client = new Client();
//		client.sendAction("All Complaints");
//		client.sendCustomerId("1334");
//		client.receiveResponse();
		
		frame.setVisible(true);
	    navbar();
	 }
	
	
	public void navbar() 
	{
		//Create the Menu Bar
		menuBar = new JMenuBar();
		//Build Menu
		menu = new JMenu("Services");
		menu.setFont(new Font("Serif", Font.BOLD, 14));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menu.setBounds(250,70,50,15);
	    menu.setOpaque(true);
	    menuBar.add(menu);
		
		//menu items
		menuItem = new JMenuItem("Lodge New Complaint", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				new LodgeComplaintWindow();
			}	
		});
		menu.add(menuItem);		
		
		menuItem = new JMenuItem("Live Chat", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//new ChatWindow();
			}			
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Video Call A Representative", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//new VideoCall();
			}
		});
		menu.add(menuItem);
		
		menu = new JMenu("Back");
		menu.setFont(new Font("Serif", Font.BOLD, 14));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerDashboard();
			}	
		});
		
		if(menu.isSelected())
		{
			new CustomerDashboard();
		}
		menu.addMenuListener(null);
//		menu.addMenuListener(new MenuListener()			
//		{
//			
//		});
//		menuSelected(MenuEvent e)
//		{
//			
//		}
		menuBar.add(menu);
		
		menu = new JMenu("Log Out");
		menu.setFont(new Font("Serif", Font.BOLD, 14));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(null);
		menu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			}	
		});
		menuBar.add(menu);
		
		class MenuListener
		{
		  MenuListener listener =  new MenuListener();
		}
		
		frame.add(menuBar);
		frame.setJMenuBar(menuBar); 
		frame.setVisible(true);
		
//		Client client = new Client();
//		client.sendAction("All Complaints");
//		client.sendCustomerId("1334");
//		client.receiveResponse();
		
		
//		//Create the Menu Bar
//		menuBar = new JMenuBar();
//		
//		
//		//Build Menu
//		menu = new JMenu("Services");
//		menu.setMnemonic(KeyEvent.VK_A);
//		menu.getAccessibleContext().setAccessibleDescription(null);
//		menu.setBounds(250,70,50,15);
//	    menu.setFont(new Font("Ariel", Font.BOLD, 12));
//	    menu.setOpaque(true);
//	    menuBar.add(menu);
//		//menuItem = new JMenuItem("")
//		
//		//menu items
//		menuItem = new JMenuItem("Make a Complain", KeyEvent.VK_T);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
//		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
//		menuItem.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new LodgeComplaintWindow();
//			}
//			
//		});
//	
//		menuItem.setBackground(Color.green);
//	    menu.setForeground(Color.yellow);
//		menu.add(menuItem);
//		
//		
//		menuItem = new JMenuItem("Live Chat", KeyEvent.VK_T);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
//		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
//		menuItem.setBackground(Color.yellow);
//	    menu.setForeground(Color.yellow);
//		menu.add(menuItem);
//		menuItem.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//new ChatWindow();
//			}
//			
//		});
//		
//		menuItem = new JMenuItem("Video Call A Representative", KeyEvent.VK_T);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
//		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
//		menuItem.setBackground(Color.yellow);
//	    menu.setForeground(Color.yellow);
//		menu.add(menuItem);
//		menuItem.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//new VideoCall();
//			}
//			
//		});
//		
//		menu = new JMenu("Back");
//		
//		menu.setFont(new Font("Ariel", Font.BOLD, 12));
//		menu.setMnemonic(KeyEvent.VK_A);
//		menu.getAccessibleContext().setAccessibleDescription(null);
//		menuBar.add(menu);
//		menu.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new EmployeePortal();
//			}
//			
//		});
//		
//		
//		menu = new JMenu("Help");
//		menu.setFont(new Font("Ariel", Font.BOLD, 12));
//		menu.setMnemonic(KeyEvent.VK_A);
//		menu.getAccessibleContext().setAccessibleDescription(null);
//		menuBar.add(menu);
//		menuItem.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//new LiveChat();
//			}
//			
//		});
//		
//		menu = new JMenu("Log Out");
//		menu.setFont(new Font("Ariel", Font.BOLD, 12));
//		menu.setMnemonic(KeyEvent.VK_A);
//		menu.getAccessibleContext().setAccessibleDescription(null);
//		menuBar.add(menu);
//		//menuItem = new JMenuItem("")
//		
//		class MenuListener{
//		  MenuListener listener =  new MenuListener();
//		}
		
		//frame.add(menuBar);
		//frame.setJMenuBar(menuBar); 
		}
	public void callClient()
	{
		Client client = new Client();
		client.sendAction("All Complaints");
		client.sendCustomerId("1334");
		client.receiveResponse();
	}
	public void table(Queue<Complaints> allComplaints) 
	{	
		System.out.println("Table");
		Object[]columns = {"cNo", "Category", "Date", "Details", "CustomerId", "Status","ResponseDate", "Respondent"};
		JTable table = new JTable();
		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		mode.setColumnIdentifiers(columns);
	
		for (Complaints complaint: allComplaints) 
		{
            Object[] row = {complaint.getcNo(),complaint.getCategory(),complaint.getDate(),complaint.getDetails(),complaint.getCustomerId(),complaint.getStatus(),complaint.getResponseDate(),complaint.getRespondent()};
			mode.addRow(row);
			System.out.println(complaint.getcNo());
        }

		table.setModel(mode);
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.lightGray);
		table.setSelectionForeground(Color.white);
		table.setGridColor(Color.red);
		table.setRowHeight(30);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setForeground(Color.lightGray);
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(40,80,630,350);

		frame.add(scroll);
		frame.setVisible(true);
		} 
		
		public static void main(String[] args) 
		{
			new ViewAllComplaint();
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
