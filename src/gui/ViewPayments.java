package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Queue;

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

import client.Client;
import customer.Payments;

public class ViewPayments 
{
	private JFrame frame = new JFrame("View Past Payments");
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
	
	public ViewPayments() 
	{
		frame.setResizable(false);
		frame.setBounds(700, 300, 720, 531);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
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
	}
	public void callClient()
	{
		Client client = new Client();
		client.sendAction("All Payments");
		client.sendCustomerId("1001");
		client.receiveResponse();
	}
	public void table(Queue<Payments> allPayments) 
	{	
		System.out.println("Table");
		Object[]columns = {"pNo", "Customer Id", "AmountDue", "AmountPaid", "DueDate", "PaymentDate", "Status"};
		JTable table = new JTable();
		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		mode.setColumnIdentifiers(columns);
	
		for (Payments payment: allPayments) 
		{
            Object[] row = {payment.getpNo(),payment.getCustomerId(),payment.getAmountDue(),payment.getAmountPaid(),payment.getDueDate(),payment.getPaymentDate(),payment.getStatus()};
			mode.addRow(row);
			System.out.println(payment.getpNo());
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
			new ViewPayments();
		}
}
