package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class CustomerPortal implements ActionListener, MenuListener, KeyListener{
		JFrame frame = new JFrame("CLEGS CLIENT PORTAL");
		
		//Declare global variables
		
		private static final long serialVersionUID = 1L;
		//JPanel jp = new JPanel();
		private JButton newBtn;
		private JButton editBtn;
		private JButton viewBtn;
		private JMenuBar menuBar;
		private JMenu menu;
		private JMenuItem menuItem;
		
		public CustomerPortal() {
		//JFrame frame = new JFrame("CLEGS CLIENT PORTAL");
		//Declare global variables
		frame.setLayout(null);
		frame.setSize(new Dimension(550,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.black);
		
		//--------------CREATE BUTTONS----------------------
		
		
		newBtn = new JButton("NEW COMPLAIN");
		editBtn= new JButton("EDIT COMPLAIN");
		viewBtn = new JButton("VIEW COMPLAIN STATUS");
		
		
		newBtn.setBackground(Color.PINK);
		newBtn.setForeground(Color.gray);
		newBtn.setOpaque(true);
		
		editBtn.setForeground(Color.gray);
		editBtn.setBackground(Color.orange);
		editBtn.setOpaque(true);
		
		viewBtn.setBackground(Color.cyan);
		viewBtn.setForeground(Color.gray);
		viewBtn.setOpaque(true);
		
	    newBtn.setBounds(195,105,180,50);
		editBtn.setBounds(195,205,180,50);
		viewBtn.setBounds(195,305,180,50);
		
		//-----------------ADD LISTENERS---------------------
		
		newBtn.addActionListener(this);
		editBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		
		//-----------------ADD / DISPLAY BUTTONS ON SCREEN-----------------------
		frame.add(newBtn);
		frame.add(editBtn);
		frame.add(viewBtn);
		
		menu();
		
		
	}
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			//IF statement to select or determine what specific method to execute if the user clicks a particular button.

					if (e.getSource() == newBtn) {
						CustomerComplaintWindow ccw = new CustomerComplaintWindow();
						//validate password
					} 
					
				else if (e.getSource() == editBtn) {
						EditComplaint_1 ec = new EditComplaint_1(getTitle());	
					} 
					
				else if (e.getSource() == viewBtn) {
					ViewComplaintStatus view = new ViewComplaintStatus();	
				} 

					
				}  
		
		 private String getTitle() {
			// TODO Auto-generated method stub
			return null;
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
					

	
	private void addKeyListener(CustomerPortal customerPortal) {
			// TODO Auto-generated method stub
			
		}
	public static void main(String args[]){
		  new CustomerPortal();
	     
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
}

