package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class EditComplaint extends JFrame{
	
	private JFrame frame = new JFrame("COMPLAINT EDITOR");
	public JMenuBar menuBar;
	public JMenu menu, subMenu;
	public JMenuItem menuItem;
	public JRadioButtonMenuItem rbtnMenuItem;
	public JCheckBoxMenuItem rbMenuItem;
	
	private static final long serialVersionUID = 1L;
	
	public EditComplaint(){
	frame.setLayout(null);
	frame.setSize(new Dimension(550,450));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.getContentPane().setBackground(Color.BLACK);

	//Create the Menu Bar
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
	menuItem.setBackground(Color.green);
    menu.setForeground(Color.yellow);
	menu.add(menuItem);
	
	menu = new JMenu("Back");
	
	menu.setFont(new Font("Ariel", Font.BOLD, 12));
	menu.setMnemonic(KeyEvent.VK_A);
	menu.getAccessibleContext().setAccessibleDescription(null);
	menuBar.add(menu);
	//menuItem = new JMenuItem("")
	
	menu = new JMenu("Help");
	menu.setFont(new Font("Ariel", Font.BOLD, 12));
	menu.setMnemonic(KeyEvent.VK_A);
	menu.getAccessibleContext().setAccessibleDescription(null);
	menuBar.add(menu);
	//menuItem = new JMenuItem("")
	
	menu = new JMenu("Log Out");
	menu.setFont(new Font("Ariel", Font.BOLD, 12));
	menu.setMnemonic(KeyEvent.VK_A);
	menu.getAccessibleContext().setAccessibleDescription(null);
	menuBar.add(menu);
	//menuItem = new JMenuItem("")
	
	frame.add(menuBar);
	frame.setJMenuBar(menuBar); 
	}

}
