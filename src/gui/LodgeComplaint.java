package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import clientTCP.Client;
import customer.Complaints;

public class LodgeComplaint
{
	private JFrame frame;
	private JLabel idLbl;
	private JLabel categoryLbl;
	private JLabel dateLbl;
	private JLabel detailsLbl;
	private JTextField idTxt;
	private JLabel customerLbl;
	private JTextField customerTxt;
	String category[]= {"Customer Service", "Product/Service", "Bill Payment", "General"};
	JComboBox<String> categoryBox = new JComboBox<>(category);
	private static JTextField dateTxt;
	private JTextArea detailsTxt;
	private JButton lodgeBtn;
	private JButton bckBtn;
	
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
	
    Complaints complaint = new Complaints();
    
    public LodgeComplaint()
    {
    	frame = new JFrame("Lodge Complaint");
    	frame.setResizable(false);
		frame.setBounds(700, 300, 584, 531);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
		customerLbl = new JLabel("Customer Id: ");
		customerLbl.setBounds(20, 10, 290, 35); //x, y, width, length
		customerLbl.setForeground(Color.black);
	    customerLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    frame.getContentPane().add(customerLbl);
	    
		customerTxt = new JTextField();
		customerTxt.setFont(new Font("Serif", Font.PLAIN, 16));
		customerTxt.setBounds(117, 10, 130, 30);
		frame.getContentPane().add(customerTxt);
	
		idLbl = new JLabel("cNo: ");
		idLbl.setFont(new Font("Serif", Font.BOLD, 16));
		idLbl.setForeground(Color.black);
		idLbl.setBounds(20, 50, 290, 35); //x, y, width, length
	    frame.getContentPane().add(idLbl);
		
		idTxt = new JTextField();
		idTxt.setFont(new Font("Serif", Font.PLAIN, 14));
		Random rand = new Random();
		idTxt.setText(String.format("%04d",rand.nextInt(10000)));
		idTxt.setBounds(117, 50, 130, 30); //x, y, width, length
		idTxt.setEditable(false);
		frame.getContentPane().add(idTxt);
		
		categoryLbl = new JLabel("Category: ");
		categoryLbl.setFont(new Font("Serif", Font.BOLD, 16));
		categoryLbl.setForeground(Color.black);
		categoryLbl.setBounds(20, 90, 290, 35); //x, y, width, length
	    frame.getContentPane().add(categoryLbl);
	    
		categoryBox.setFont(new Font("Serif", Font.PLAIN, 16));
		categoryBox.setBounds(117, 90, 150, 30);
		categoryBox.setSelectedIndex(0);
		frame.getContentPane().add(categoryBox);
		
		dateLbl = new JLabel("Date: ");
		dateLbl.setFont(new Font("Serif", Font.BOLD, 16));
		dateLbl.setForeground(Color.black);
		dateLbl.setBounds(20, 130, 290, 35); //x, y, width, length
	    frame.getContentPane().add(dateLbl);
	    
		dateTxt = new JTextField();
		dateTxt.setFont(new Font("Serif", Font.PLAIN, 16));
		dateTxt.setBounds(117, 130, 130, 30);
		dateTxt.setText("mm/dd/yyyy");
		dateTxt.addFocusListener(new FocusListener()
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				dateTxt.setText("");	
			}
			@Override
			public void focusLost(FocusEvent e)
			{
				// TODO Auto-generated method stub
			}
        });
		frame.getContentPane().add(dateTxt);
		
		detailsLbl = new JLabel("Details: ");
		detailsLbl.setForeground(Color.black);
		detailsLbl.setBounds(20, 170, 290, 35); //x, y, length, width
	    detailsLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    frame.getContentPane().add(detailsLbl);
	    
	    detailsTxt = new JTextArea();
	    detailsTxt.setBounds(117, 170, 290, 35); //x, y, width, length
	    detailsTxt.setSize(330,170);
	    detailsTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    frame.getContentPane().add(detailsTxt);
		
		lodgeBtn = new JButton("Lodge");
		lodgeBtn.setFont(new Font("Serif", Font.BOLD, 18));
		lodgeBtn.setForeground(Color.white);
		lodgeBtn.setBackground(new Color(96, 96, 96));
		lodgeBtn.setBounds(390, 370, 100, 30);
		lodgeBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(idTxt.getText().equals("")||detailsTxt.getText().equals("")||dateTxt.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Some Complaint Information Missing!","Lodge Status", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Client client = new Client();
					complaint.setcNo(idTxt.getText());
					complaint.setId(customerTxt.getText());
					complaint.setCategory(categoryBox.getSelectedItem().toString());					
					complaint.setDate(dateTxt.getText());
					complaint.setDetails(detailsTxt.getText());

					client.sendAction("Add");
					client.sendComplaint(complaint);
				}
			}
		});
		frame.getContentPane().add(lodgeBtn);

		bckBtn = new JButton("Back");
		bckBtn.setFont(new Font("Serif", Font.BOLD, 18));
		bckBtn.setForeground(Color.white);
		bckBtn.setBackground(new Color(96, 96, 96));
		bckBtn.setBounds(90, 370, 100, 30); 
		bckBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new CustomerDashboard();
				frame.dispose();
			}
		});
		frame.getContentPane().add(bckBtn);
		
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
		menuItem = new JMenuItem("Lodge New Complaint", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LodgeComplaint();
			}	
		});
		serviceMenu.add(menuItem);		
		
		menuItem = new JMenuItem("View a Complaint", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
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
		
		menuItem = new JMenuItem("Query Account Status", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				new QueryAccountStatus();
			}
		});
		serviceMenu.add(menuItem);
		
		menuItem = new JMenuItem("View Past Complaints", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				new ViewAllComplaint();
			}
		});
		serviceMenu.add(menuItem);
		
		menuItem = new JMenuItem("View Past Payments", KeyEvent.VK_T);
		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItem.setBackground(new Color(255, 255, 255));
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				new ViewPayments();
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
				new CustomerDashboard();
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
				new CustomerLoginWindow();
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
	public static void main(String args[])
	{
		  new LodgeComplaint(); 
	}
}
