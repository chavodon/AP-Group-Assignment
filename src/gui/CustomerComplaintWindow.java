package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import clientTCP.Client;
import customer.Complaints;

	@SuppressWarnings("unused")
	public class CustomerComplaintWindow extends JFrame implements ActionListener 
	{	
		Complaints complaint = new Complaints();
		private GridBagConstraints gbc;
		
		private static final long serialVersionUID = 1L;
		private JFrame frame;
		public JMenuBar menuBar;
		public JMenu menu, subMenu;
		public JMenuItem menuItem;
		public JRadioButtonMenuItem rbtnMenuItem;
		public JCheckBoxMenuItem rbMenuItem;
		private JLabel idLbl;
		private JLabel categoryLbl;
		private JLabel dateLbl;
		private JLabel detailsLbl;
		private JTextField idTxt;
		String category[]= {"Customer Service", "Product/Service", "Bill Payment", "General"};
		JComboBox<String> categoryBox = new JComboBox<>(category);
		private static JTextField dateTxt;
		private JTextArea detailsTxt;
		private JButton lodgeBtn;
		
		public CustomerComplaintWindow()
		{
			frame = new JFrame("Customer Portal: File New Complaint");
			frame.setResizable(false);
			frame.setBounds(700, 300, 584, 531);
			frame.getContentPane().setLayout(null);
			frame.setLocationRelativeTo(null); //center output on screen
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setBackground(new Color(160, 160, 160));
		
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
			
			class MenuListener
			{
			  MenuListener listener =  new MenuListener();
			}
			
			frame.add(menuBar);
			frame.setJMenuBar(menuBar); 
		
			layoutComponents();
		}
		
		private void layoutComponents()
		{
			idLbl = new JLabel("cNo: ");
			idLbl.setFont(new Font("Serif", Font.BOLD, 18));
			idLbl.setForeground(Color.black);
			
			categoryLbl = new JLabel("Category: ");
			categoryLbl.setFont(new Font("Serif", Font.BOLD, 18));
			categoryLbl.setForeground(Color.black);
			
			dateLbl = new JLabel("Date: ");
			dateLbl.setFont(new Font("Serif", Font.BOLD, 18));
			dateLbl.setForeground(Color.black);
			
			detailsLbl = new JLabel("Details: ");
			detailsLbl.setFont(new Font("Serif", Font.BOLD, 18));
			detailsLbl.setForeground(Color.black);
			
			idTxt = new JTextField();
			idTxt.setFont(new Font("Serif", Font.PLAIN, 14));
			Random rand = new Random();
			idTxt.setText(String.format("%04d",rand.nextInt(10000)));
			idTxt.setEditable(false);
			
			categoryBox.setSelectedIndex(1);
			categoryBox.setFont(new Font("Serif", Font.PLAIN, 14));
			
			dateTxt = new JTextField();
			dateTxt.setFont(new Font("Serif", Font.PLAIN, 14));
			dateTxt.setText("mm/dd/yyyy");
			
			dateTxt.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e) 
				{
					dateTxt.setText("");	
				}
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
				}
	        });
			
			detailsTxt = new JTextArea();
			detailsTxt.setFont(new Font("Serif", Font.PLAIN, 14));
			//detailsTxt.setPreferredSize(new Dimension(290, 100));
	        detailsTxt.setLayout(null);  
			
			lodgeBtn = new JButton("Lodge");
			lodgeBtn.setFont(new Font("Serif", Font.BOLD, 18));
			lodgeBtn.setForeground(Color.white);
			lodgeBtn.setBackground(new Color(96, 96, 96));
			lodgeBtn.addActionListener(this);
			
			gbc = new GridBagConstraints();
		////////////////////////////////////////////////////////////////////////////////////////////
			//Set the Layout Manager for the frame
			frame.setLayout(new GridBagLayout());
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(-75,50,5,5);
			frame.add(idLbl, gbc);
			idLbl.setSize(150,20);
			
			//Set size of the label for each components re=initalize the gridbag constraints
			gbc = new GridBagConstraints();
			gbc.gridx = 0; //Set which column on the X-Axis the first component should go
			gbc.gridy = 0;	//Set which row on the y-axis the first component should go
			gbc.gridwidth = 0;//Set how many colums the component should span
			//Set where in the frame the component should be located
			gbc.anchor = GridBagConstraints.WEST;
			//The inset field specifies the extername padding of the component, the minimum
			// The amount of space between the component and the edges of its display area
			gbc.insets = new Insets(9,10,0,10);
			//Add the component to the frame with all its contraints
			frame.add(idLbl, gbc);
			
			gbc = new GridBagConstraints();//Re-Initialize the gridbag
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			gbc.gridheight = 2;
			//Set the internal padding for the components width
			gbc.ipadx = 150;
			gbc.ipady = 10;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			//The insets field specified the external padding of the component, the minimum
			//Amount of space between the component and the edges of its display area
			gbc.insets = new Insets(5,7,10,10);
			frame.add(idTxt, gbc);	//Add the component to the frame with all its constraints
	////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//Label 
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(9,10,5,5);
			frame.add(categoryLbl, gbc);
			//field
			gbc = new GridBagConstraints();
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridwidth = 3;
			gbc.gridheight = 2;
			gbc.ipadx = 90;
			gbc.ipady = 8;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(11,7,3,10);
			frame.add(categoryBox, gbc);
			
		/////////////////////////////////////////////////////////////////////////////////////////////////////	
			//label
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(10,10,6,5);
			frame.add(dateLbl, gbc);
			//field
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 2;
			gbc.gridwidth = 4;
			gbc.gridheight = 2;
			gbc.ipadx = 305;
			gbc.ipady = 8;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(11,7,3,10);
			frame.add(dateTxt, gbc);
			
			//label
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.gridwidth = 5;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(10,10,6,5);
			frame.add(detailsLbl, gbc);
			
			//field
			gbc = new GridBagConstraints();
			gbc.gridx = 2;
			gbc.gridy = 5;
			gbc.gridwidth = 4;
			gbc.gridheight = 2;
			gbc.ipadx = 305;
			gbc.ipady = 110;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(10,10,6,5);
			frame.add(detailsTxt, gbc); 			
			
			//Lodge button
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 7;
			gbc.gridwidth = 6;
			gbc.ipadx = 45;
			gbc.ipady = 8;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(12,0,0,0);
			frame.add(lodgeBtn, gbc);
			
			frame.setVisible(true);
		
			//button.addActionListener(this);
			lodgeBtn.addActionListener(new ActionListener() 
			{
				Client client = new Client();
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Client client = new Client();
					complaint.setcNo(idTxt.getText());
					//complaint.setCustomerId(customerTxt.getText());
					complaint.setCategory(categoryBox.getSelectedItem().toString());					
					complaint.setDate(dateTxt.getText());
					complaint.setDetails(detailsTxt.getText());
					complaint.setStatus("Unresolved");
					complaint.setResponseDate("N/A");
					complaint.setRespondent("N/A");
					complaint.setResponse("N/A");
					complaint.setAssignedTo("N/A");
					complaint.setVisitDate("N/A");
					
					client.sendAction("Add");
					client.sendComplaint(complaint);
				}
			});
		}
		public static void main(String args[])
		{
			  new CustomerComplaintWindow(); 
		}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
