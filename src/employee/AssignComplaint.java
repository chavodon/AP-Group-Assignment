package employee;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AssignComplaint extends JFrame implements ActionListener {
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	public JMenuBar menuBar;
	private JTextField compID;
	private JButton assignBtn;
	private JButton cancelBtn;

	private JFrame frame = new JFrame("ASSIGN COMPLAINT TO TECHNICIAN");
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	
	public AssignComplaint() {
		
		frame.setSize(400, 200);
		frame.setLayout(new GridLayout(2,1));
		
		compID = new JTextField(5);
		compID.addActionListener(this);
		
		panel.add(new JLabel("Complaint ID# : "));
		panel.add(compID);
		
		
		//--------------CREATE PANEL AND BUTTON----------------------
		assignBtn = new JButton("ASSIGN");
		
		assignBtn.setForeground(Color.yellow);
		assignBtn.setBackground(Color.blue);
		assignBtn.setOpaque(true);
		assignBtn.setBounds(230,110,40,20);
		panel2.add(assignBtn);
		
		cancelBtn = new JButton("CANCEL");
		cancelBtn.setForeground(Color.yellow);
		cancelBtn.setBackground(Color.blue);
		cancelBtn.setOpaque(true);
		cancelBtn.setBounds(230,110,40,20);
		panel2.add(cancelBtn);
		
		//-----------------ADD LISTENERS---------------------
		assignBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		//-----------------ADD / DISPLAY COMPONENTS ON SCREEN-----------------------
		frame.add(panel);
		frame.add(panel2);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		//IF statement to select or determine what specific method to execute if the user clicks a particular button.
		JButton pressed = (JButton) e.getSource();
		if(pressed.equals(assignBtn)) {
			JOptionPane.showMessageDialog(null, "Complaint has been assigned to a technician.");
			frame.dispose();
		} else if (pressed.equals(cancelBtn)) {
			int cancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?");
			if (cancel == JOptionPane.YES_OPTION) {
				frame.dispose();
			}
		} 			
	}  

	public static void main(String[] args) {
		new AssignComplaint();
	}

}
