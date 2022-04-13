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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
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
		frame.setBounds(700, 300, 980, 591);
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
		//frame.getContentPane().add(searchBtn);
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
		serviceBar = new JMenuBar();
		serviceMenu = new JMenu("Services");
		serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
		serviceMenu.setMnemonic(KeyEvent.VK_A);
		serviceMenu.getAccessibleContext().setAccessibleDescription(null);
		serviceMenu.setBounds(250,70,50,15);
	    serviceMenu.setOpaque(true);
	    serviceBar.add(serviceMenu);
		
		//menu items
		menuItem = new JMenuItem("Respond To Complaint", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ResponseByRep();
			}	
		});
		serviceMenu.add(menuItem);		
		
		menuItem = new JMenuItem("View Complaint By Category", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				new ViewByCategory();
			}			
		});
		serviceMenu.add(menuItem);
		
		menuItem = new JMenuItem("Assign Complaint", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				new AssignComplaint();
			}
		});
		serviceMenu.add(menuItem);
		
		menuItem = new JMenuItem("View Complaint", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				new ViewComplaint();
			}
		});
		serviceMenu.add(menuItem);
	
		serviceMenu = new JMenu("Back");
		serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
		serviceMenu.setMnemonic(KeyEvent.VK_A);
		serviceMenu.getAccessibleContext().setAccessibleDescription(null);
		serviceMenu.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new RepPortal();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		serviceBar.add(serviceMenu);
		
		serviceMenu = new JMenu("Log Out");
		serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
		serviceMenu.setMnemonic(KeyEvent.VK_A);
		serviceMenu.getAccessibleContext().setAccessibleDescription(null);
		serviceMenu.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new EmployeeLogInWindow();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			});
			serviceBar.add(serviceMenu);
	    
		class MenuListener
		{
		  MenuListener listener =  new MenuListener();
		}
		frame.add(serviceBar);
		frame.setJMenuBar(serviceBar); 
		frame.setVisible(true);
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
		Object[]columns = {"cNo", "Category", "Date", "Details", "CustomerId", "Status"};
		JTable table = new JTable();
		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		mode.setColumnIdentifiers(columns);
	
		for (Complaints complaint: allComplaints) 
		{
            Object[] row = {complaint.getcNo(),complaint.getCategory(),complaint.getDate(),complaint.getDetails(),complaint.getId(),complaint.getStatus()};
			mode.addRow(row);
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
		scroll.setBounds(20,80,910,350);

		frame.add(scroll);
		frame.setVisible(true);
		} 
		
		public static void main(String[] args) 
		{
			new ViewByCategory();
		}
}
