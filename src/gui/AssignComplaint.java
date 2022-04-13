package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import clientTCP.Client;
import customer.Complaints;

public class AssignComplaint implements ActionListener 
{
	private JFrame frame = new JFrame("Customer Service Rep - Assign Complaint");
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
	private JTextArea resultTxt;

	public JTextField idTxt;
    public JButton assignBtn;
	public JPanel panel = new JPanel();
    private JLabel idLbl;
    private JLabel techLbl;
    
    private JTextField techTxt;
    private JButton respondBtn;
    
    Complaints complaint = new Complaints();
	
	public AssignComplaint()
	{
		frame.setResizable(false);
		frame.setBounds(700, 300, 534, 421); //x, y, width, length
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		
		idLbl = new JLabel("Complaint Id: ");
		idLbl.setBounds(20, 10, 290, 35); //x, y, width, length
	    idLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    frame.getContentPane().add(idLbl);
	    
		idTxt = new JTextField();
		idTxt.setFont(new Font("Serif", Font.PLAIN, 14));
		idTxt.setBounds(147, 10, 130, 30);
		frame.getContentPane().add(idTxt );
	
	    resultTxt = new JTextArea();
	    resultTxt.setBounds(50, 60, 430, 200); //x, y, width, length
	    resultTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    resultTxt.setBackground(new Color(192, 192, 192));
	    resultTxt.setVisible(false);
	    frame.getContentPane().add(resultTxt);
	    
		assignBtn = new JButton("Assign");
		assignBtn.setFont(new Font("Serif", Font.BOLD, 18));
		assignBtn.setForeground(Color.white);
		assignBtn.setBorderPainted(false);
		assignBtn.setBounds(200, 130, 110, 35);
		assignBtn.setBackground(new Color(96, 96, 96));
		
		assignBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Client client = new Client();
				
				complaint.setcNo(idTxt.getText());
				complaint.setAssignedTo(techTxt.getText());
				client.sendAction("Assign");
				client.sendComplaintId(complaint.getcNo());
				client.sendTechnician(complaint.getAssignedTo());
				client.receiveResponse();			
				frame.dispose();
			}
	});
		frame.getContentPane().add(assignBtn);
		techLbl = new JLabel("Technician Id: ");
		techLbl.setBounds(20, 70, 290, 35); //x, y, width, length
	    techLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    frame.getContentPane().add(techLbl);
	    
	    techTxt = new JTextField();
	    techTxt.setBounds(147, 70, 130, 30); //x, y, width, length
	    techTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    frame.getContentPane().add(techTxt);
	    
		menu();
		frame.setVisible(true);
		}
		 public void menu() 
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
				menuItem = new JMenuItem("Lodge New Complaint", KeyEvent.VK_T);
				menuItem.setFont(new Font("Serif", Font.BOLD, 14));
				menuItem.setBackground(new Color(255, 255, 255));
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
				menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
				menuItem.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
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
		
	public static void main(String[] args) 
	{
		new AssignComplaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
