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
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
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

	@SuppressWarnings("unused")
	public class CustomerComplaintWindow extends JFrame implements ActionListener, MenuKeyListener, javax.swing.event.MenuListener, KeyListener{
	 {
		
		private GridBagConstraints gbc;
		
		private static final long serialVersionUID = 1L;
		public JMenuBar menuBar;
		public JMenu menu, subMenu;
		public JMenuItem menuItem;
		public JRadioButtonMenuItem rbtnMenuItem;
		public JCheckBoxMenuItem rbMenuItem;
		private JLabel fnLabel;
		private JLabel lnLabel;
		private JLabel teleLabel;
		private JLabel emailLabel;
		private JLabel complainLabel;
		private JTextField fnTextField;
		private JTextField lnTextField;
		private static JTextField teleTextField;
		private JTextField emailTextField;
		private JTextArea complainTextField;
		private JButton button;
		//public JTextArea textArea;
		
		JFrame frame = new JFrame(" Customer Portal: File New Complaint  ");
		
		public CustomerComplaintWindow(){
			
			//frame.setLayout(null);
			frame.setSize(new Dimension(550,450));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.getContentPane().setBackground(Color.BLACK);
		
		
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
		
			        layoutComponents();
			
		}
		
		private void layoutComponents()
		{
			
			fnLabel = new JLabel("First Name: ");
			lnLabel = new JLabel("Last Name: ");
			teleLabel = new JLabel("Telephone Number: ");
			emailLabel = new JLabel("Email: ");
			complainLabel = new JLabel("Complain: ");
			fnLabel.setForeground(Color.white);
			lnLabel.setForeground(Color.white);
			emailLabel.setForeground(Color.white);
			complainLabel.setForeground(Color.white);
			teleLabel.setForeground(Color.white);
			fnTextField = new JTextField(20);
			lnTextField = new JTextField(20);
			teleTextField = new JTextField(20);
			emailTextField = new JTextField(50);
			complainTextField = new JTextArea(5, 30);
			
			button = new JButton("Submit");
			button.setForeground(Color.blue);
			button.addActionListener(this);
			
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
			frame.add(fnLabel, gbc);
			fnLabel.setSize(150,20);
			//Set size of the label for each components re=initalize the gridbag constraints
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			//Set which column on the X-Axis the first component should go
			gbc.gridy = 0;
			//Set which row on the y-axis the first component should go
			gbc.gridwidth = 0;
			//Set how many colums the component should span
			//Set where in the frame the component should be located
			gbc.anchor = GridBagConstraints.WEST;
			//The inset field specifies the extername padding of the component, the minimum
			// The amount of space between the component and the edges of its display area
			gbc.insets = new Insets(9,10,0,10);
			//Add the component to the frame with all its contraints
			frame.add(fnLabel, gbc);
			
			gbc = new GridBagConstraints();
			//Re-Initialize the gridbag
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			gbc.gridheight = 2;
			//Set the internal padding for the components width
			gbc.ipadx = 300;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			//The insets field specified the external padding of the component, the minimum
			//Amount of space between the component and the edges of its display area
			gbc.insets = new Insets(11,7,10,10);
			//Add the component to the frame with all its constraints
			frame.add(fnTextField, gbc);
	////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//Label 
			lnLabel.setForeground(Color.white);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(9,10,5,5);
			frame.add(lnLabel, gbc);
			//field
			gbc = new GridBagConstraints();
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridwidth = 3;
			gbc.gridheight = 2;
			gbc.ipadx = 300;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(11,7,3,10);
			frame.add(lnTextField, gbc);
			
		/////////////////////////////////////////////////////////////////////////////////////////////////////	
			//label
			teleLabel.setForeground(Color.white);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(10,10,6,5);
			frame.add(teleLabel, gbc);
			//field
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 2;
			gbc.gridwidth = 4;
			gbc.gridheight = 2;
			gbc.ipadx = 300;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(11,7,3,10);
			frame.add(teleTextField, gbc);
			
			//label
			emailLabel.setForeground(Color.white);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 4;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(10,10,6,5);
			frame.add(emailLabel, gbc);
			//field
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 3;
			gbc.gridwidth = 4;
			gbc.gridheight = 2;
			gbc.ipadx = 300;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(17,7,3,10);
			frame.add(emailTextField, gbc);
			
			//label
			complainLabel.setForeground(Color.white);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.gridwidth = 5;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(10,10,6,5);
			frame.add(complainLabel, gbc);
			
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
			frame.add(complainTextField, gbc); 
			
			
			
			//Submit button
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 8;
			gbc.gridwidth = 6;
			gbc.ipadx = 40;
			gbc.ipady = 8;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(17,0,0,0);
			frame.add(button, gbc);
			

			frame.setSize(new Dimension(550,450));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.getContentPane().setBackground(Color.darkGray);
		
			button.addActionListener(this);
		}
		
		private void initiate() {
			
		}
		
		@SuppressWarnings("null")
		public void actionPerformed(ActionEvent e){
			
			String fName = fnTextField.getText();
			String lName = lnTextField.getText();
			String tele = teleTextField.getText();
			String email = emailTextField.getText();
			String complain = complainTextField.getText();
			
			try {
			String url = "jdbc:mysql://localhost:3306/CustomerComplaint.complain";
			Connection myConn = null;
			
			Statement stmt = myConn.createStatement();
			String sql = "INSERT INTO COMPLAIN VALUES ("+fName+", '"+lName+", '"+tele+"','"+email+"','"+complain+"')";
	
			//execute statement
			stmt.executeUpdate(sql);
			
			//display
			
			JOptionPane.showMessageDialog(this, "Complaint filed");
			//close connection
			myConn.close();
			
			if (e.getSource() == button) {
				CustomerPortal cport = new CustomerPortal();
				//validate password
			} 
			
			}
			catch(Exception ex) {
				
			} 
		}
		 
		 	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuKeyTyped(MenuKeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuKeyPressed(MenuKeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuKeyReleased(MenuKeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}

			
	
		
		public static void main(String args[]){
	        
			  new CustomerComplaintWindow();
		       
		     }
		


}
