package gui;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.HeadlessException;
	import java.awt.Insets;
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
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
	import javax.swing.KeyStroke;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.JTable;

	public class CompleteSolution extends JFrame implements ActionListener{
		
		private JFrame frame = new JFrame("Resolve a Complaint");
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
	    private GridBagConstraints gbc;
	    private JLabel instruction;
	    private JLabel header= new JLabel();
		
		private static final long serialVersionUID = 1L;
		
		public CompleteSolution(String title)throws HeadlessException {
			 super(title);
			 setResizable(true);
			 addComponents();
			 menu();
			//frame.setLayout(null);
			frame.setSize(new Dimension(700,550));
			panel.setBackground(Color.darkGray);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.getContentPane().setBackground(Color.darkGray);
			
			instruction = new JLabel("PLEASE ENTER YOUR COMPLAINT CODE ... \n");
		    instruction.setBounds(155,175,490,50);
		    instruction.setFont(new Font("American Typewriter", Font.BOLD, 14));
		    instruction.setBackground(Color.darkGray);
		    instruction.setForeground(Color.white);
		    instruction.setOpaque(true);
		    frame.add(instruction);
		    
		    
		    
			}
		
		
			 private void addComponents() {
				 frame.setLayout(new GridBagLayout());
				 gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 0;
					gbc.gridheight = 0;
					gbc.ipadx = 0;
					gbc.anchor = GridBagConstraints.NORTHWEST;
					gbc.insets = new Insets(-75,23,3,3);
					frame.add(searchField, gbc);
					panel.add(searchField);
					
					frame.setLayout(new GridBagLayout());
					 gbc = new GridBagConstraints();
						gbc.gridx = 0;
						gbc.gridy = 0;
						gbc.gridwidth = 0;
						gbc.gridheight = 0;
						gbc.ipadx = 0;
						gbc.anchor = GridBagConstraints.NORTHEAST;
						gbc.insets = new Insets(-153,3,3,3);
						frame.add(searchB, gbc);
					
						panel.add(searchB);
						frame.add(panel);
			
						
						
						//header = new JLabel("Edit a Complain");
						header.setForeground(Color.white);
						header.setSize(150,20);
						header.setFont(new Font("Serif", Font.BOLD, 20));
						header.setBackground(Color.BLACK);
						frame.setLayout(new GridBagLayout());
						gbc = new GridBagConstraints();
						gbc.gridx = 0;
						gbc.gridy = 0;
						gbc.gridwidth = 2;
						gbc.gridheight = 2;
					    gbc.ipadx = 2;
						gbc.anchor = GridBagConstraints.CENTER;
					 //   gbc.insets = new Insets(-310,3,3,3);
						frame.add(header, gbc);
						 
			 }
			 
			 public void menu() {
				 menuBar = new JMenuBar();
			 		
			 		
			 		//Build Menu
			 		menu = new JMenu("Services");
			 		menu.setMnemonic(KeyEvent.VK_A);
			 		menu.getAccessibleContext().setAccessibleDescription(null);
			 		menu.setBounds(250,70,50,15);
			 	    menu.setFont(new Font("Ariel", Font.BOLD, 12));
			 	    menu.setOpaque(true);
			 	    menuBar.add(menu);
			 		//menuItem = new JMenuItem("")
			 		
			 		//menu items
			 		menuItem = new JMenuItem("Make a Complain", KeyEvent.VK_T);
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
			 		
			 		menu = new JMenu("Back");
			 		
			 		menu.setFont(new Font("Ariel", Font.BOLD, 12));
			 		menu.setMnemonic(KeyEvent.VK_A);
			 		menu.getAccessibleContext().setAccessibleDescription(null);
			 		menuBar.add(menu);
			 		menu.addActionListener(new ActionListener(){
			 			@Override
			 			public void actionPerformed(ActionEvent e) {
			 				new EmployeePortal();
			 			}
			 			
			 		});
			 		
			 		
			 		menu = new JMenu("Help");
			 		menu.setFont(new Font("Ariel", Font.BOLD, 12));
			 		menu.setMnemonic(KeyEvent.VK_A);
			 		menu.getAccessibleContext().setAccessibleDescription(null);
			 		menuBar.add(menu);
			 		menuItem.addActionListener(new ActionListener(){
			 			@Override
			 			public void actionPerformed(ActionEvent e) {
			 				//new LiveChat();
			 			}
			 			
			 		});
			 		
			 		menu = new JMenu("Log Out");
			 		menu.setFont(new Font("Ariel", Font.BOLD, 12));
			 		menu.setMnemonic(KeyEvent.VK_A);
			 		menu.getAccessibleContext().setAccessibleDescription(null);
			 		menuBar.add(menu);
			 		//menuItem = new JMenuItem("")
			 		
			 		class MenuListener{
			 		  MenuListener listener =  new MenuListener();
			 		}
			 		
					
					frame.add(menuBar);
					frame.setJMenuBar(menuBar); 
					//searchBox();
					
			 }
			//searchBox();
			
			/*JTable table = new JTable();
			Object[]columns = {"First Name", "Last Name", "Telephone", "Email", "Complaint"};
			DefaultTableModel mode = new DefaultTableModel();
			mode.setColumnIdentifiers(columns);
			
			table.setModel(mode);
			table.setBackground(Color.white);
			table.setForeground(Color.black);
			table.setSelectionBackground(Color.red);
			table.setSelectionForeground(Color.white);
			table.setGridColor(Color.red);
			table.setRowHeight(50);
			table.setFont(new Font("Times", Font.PLAIN, 18));
			
			JScrollPane scroll = new JScrollPane(table);
			scroll.setForeground(Color.red);
			scroll.setBackground(Color.WHITE);
			scroll.setBounds(10,10,530,350);
			
			frame.getContentPane().add(scroll);
			//frame.setVisible(true);
			} */
			
				
		public static void main(String[] args) {

			new CompleteSolution("Complete Solution");

		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		

}
