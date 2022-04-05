package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpWindow extends JFrame {
		private static final long serialVersionUID = 1L;
		private JFrame frame;
		private JLabel usernameLabel;
		private JLabel passwordLabel;
		private JLabel confirmLabel;
		private JTextField usernameTextField;
		private JPasswordField passwordField;
		private JPasswordField confirmField;
		private JButton button;
		@SuppressWarnings("unused")
		private JPanel namePanel;
		@SuppressWarnings("unused")
		private JPanel passwordPanel;
		@SuppressWarnings("unused")
		private JPanel buttonPanel;
		
	    private JLabel welcomeLabel;
		
		//Create an object of the GridBagConstraints/ Settings
		
		private GridBagConstraints gbc;
		
		public SignUpWindow()
		{
			//Initialize OR Instantiate the components
			frame = new JFrame("CLEGS COMPLAINT MANAGEMENT SYSTEM");
			usernameLabel = new JLabel("Username:");
			usernameLabel.setForeground(Color.white);
			passwordLabel = new JLabel("");
			confirmLabel = new JLabel("");
			usernameTextField = new JTextField(20);
			confirmField = new JPasswordField(20);
			passwordField = new JPasswordField(20);
			button = new JButton("Sign Up");
			button.setForeground(Color.red);
			button.setBackground(Color.red);
			gbc = new GridBagConstraints();
			
			welcomeLabel= new JLabel("Sign Up");
			
			layoutComponents();
		}
		
		private void layoutComponents()
		{
			//Set the Layout Manager for the frame
			frame.setLayout(new GridBagLayout());
			
			welcomeLabel.setText("CUSTOMER- Sign Up");
			welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
			welcomeLabel.setBackground(Color.BLACK);
			welcomeLabel.setOpaque(true);
			//Font f = welcomeLabel.getFont();
			//welcomeLabel.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
			welcomeLabel.setForeground(Color.ORANGE);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(-75,50,2,2);
			frame.add(welcomeLabel, gbc);
			
			usernameLabel.setSize(150,20);
			//Set size of the label for each components re=initalize the gridbag constraints
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			//Set which column on the X-Axis the first component should go
			gbc.gridy = 0;
			//Set which row on the y-axis the first component should go
			gbc.gridwidth = 1;
			//Set how many colums the component should span
			
			//Set where in the frame the component should be located
			gbc.anchor = GridBagConstraints.WEST;
			//The inset field specifies the extername padding of the component, the minimum
			// The amount of space between the component and the edges of its display area
			gbc.insets = new Insets(9,10,0,0);
			//Add the component to the frame with all its contraints
			frame.add(usernameLabel, gbc);
			gbc = new GridBagConstraints();
			//Re-Initialize the gridbag
			gbc.gridx = 3;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			gbc.gridheight = 2;
			//Set the internal padding for the components width
			gbc.ipadx = 0;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			//The insets field specified the external padding of the component, the minimum
			//Amount of space between the component and the edges of its display area
			gbc.insets = new Insets(11,7,0,10);
			//Add the component to the frame with all its constraints
			frame.add(usernameTextField, gbc);
			
			passwordLabel.setText("Password:");
			passwordLabel.setForeground(Color.white);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(9,10,5,5);
			frame.add(passwordLabel, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridwidth = 3;
			gbc.gridheight = 2;
			gbc.ipadx = 0;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(11,7,3,10);
			frame.add(passwordField, gbc);
			
			confirmLabel.setText("Confirm Password:");
			confirmLabel.setForeground(Color.white);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(9,10,5,5);
			frame.add(confirmLabel, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 2;
			gbc.gridwidth = 3;
			gbc.gridheight = 2;
			gbc.ipadx = 0;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(11,7,3,10);
			frame.add(confirmField, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 4;
			gbc.gridwidth = 6;
			gbc.ipadx = 40;
			gbc.ipady = 6;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(17,0,0,0);
			frame.add(button, gbc);
			
			frame.setSize(new Dimension(550,450));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.getContentPane().setBackground(Color.BLACK);
		}
		
		public void actionPerformed(ActionEvent e) {
	//IF statement to select or determine what specific method to execute if the user clicks a particular button.

			if (e.getSource() == button) {
				
			} 

			
		}  

		public static void main(String args[]){
		    
			  new SignUpWindow();
		     
		   }
		
			

}
