package gui;

import java.awt.Color;
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
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
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
				client.sendTechnician(complaint.getAssignedTo());
				client.receiveResponse();			
				frame.dispose();
			}
	});
		frame.getContentPane().add(assignBtn);
		techLbl = new JLabel("Technician Name: ");
		techLbl.setBounds(20, 70, 290, 35); //x, y, width, length
	    techLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    frame.getContentPane().add(techLbl);
	    
	    techTxt = new JTextField();
	    techTxt.setBounds(180, 70, 280, 30); //x, y, width, length
	    techTxt.setFont(new Font("Serif", Font.PLAIN, 16));
	    frame.getContentPane().add(techTxt);
	    
		menu();
		frame.setVisible(true);
		}
		 public void menu() 
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
						new CustomerComplaintWindow();
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
				menu.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						new EmployeePortal();
					}	
				});
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
				
				class MenuListener{
				  MenuListener listener =  new MenuListener();
				}
				frame.add(menuBar);
				frame.setJMenuBar(menuBar); 
		 }
		
	public static void main(String[] args) {
		new AssignComplaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
