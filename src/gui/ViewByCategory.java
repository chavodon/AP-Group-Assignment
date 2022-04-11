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
import javax.swing.JComboBox;
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

import clientTCP.Client;
import customer.Complaints;

public class ViewByCategory 
{
	private JFrame frame = new JFrame("View Complaints By Category");
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	public JTextArea textArea;
	public JTextField searchField = new JTextField(30);
    public JButton searchBtn;
    public JTable result = new JTable();
	public JPanel panel = new JPanel();
    public JScrollPane scrollPane = new JScrollPane(result);
    
    JLabel titleLbl;
	String category[]= {"Customer Service", "Product/Service", "Bill Payment", "General"};
	JComboBox<String> categoryBox = new JComboBox<>(category);
	String categorySelected = "";
	
	public ViewByCategory() 
	{
		frame.setResizable(false);
		frame.setBounds(700, 300, 720, 531);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));

		titleLbl = new JLabel();
		titleLbl = new JLabel("Select a Category: ");
	    titleLbl.setBounds(20,25,250,40);
	    titleLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    titleLbl.setForeground(Color.black);
	    frame.add(titleLbl);
	    
		categoryBox.setSelectedIndex(0);
		categoryBox.setBounds(170,33,200,30);
		categoryBox.setFont(new Font("Serif", Font.PLAIN, 14));
        categoryBox.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                Object selected = categoryBox.getSelectedItem();
                if(selected.toString().equals("Customer Service"))
                {
                	categorySelected = "Customer Service";
                }
                else if(selected.toString().equals("Bill Payment"))
                {
                	categorySelected = "Bill Payment";
                }  else if(selected.toString().equals("Product/Service"))
                {
                	categorySelected = "Product/Service";
                }  if(selected.toString().equals("General"))
                {
                	categorySelected = "General";
                }
            }
        });
		frame.add(categoryBox);
		
		searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Serif", Font.BOLD, 16));
		searchBtn.setForeground(Color.white);
		searchBtn.setBorderPainted(false);
		searchBtn.setBounds(390, 33, 80, 35);
		searchBtn.setBackground(new Color(96, 96, 96));
		frame.getContentPane().add(searchBtn);
		searchBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Client client = new Client();
				client.sendAction("ByCategory");
				client.sendCategory(categorySelected);
				client.receiveResponse();
			}
	});
		frame.add(searchBtn);
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
		client.sendAction("ByCategory");
		client.sendCategory(categorySelected);
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
			new ViewByCategory();
		}
}
