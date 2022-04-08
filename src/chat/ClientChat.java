package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ClientChat 
{
	private JFrame clientFrame;
    private static JTextArea chatArea;
    private JButton sendBtn;
    private JButton backBtn;
    private JLabel label;
    private JLabel title;
    private JLabel status;
    private JPanel chatPanel;
    private JScrollPane scrollPanel;
    private JTextField messageText;
    private static Socket con;
    DataInputStream input;
    DataOutputStream output;
  
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChat window = new ClientChat();
					window.clientFrame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try
		{
			con = new Socket("127.0.0.1", 8000);
		}
		catch(ConnectException Ex)
		{
			 JOptionPane.showMessageDialog(null, "Server Offline!","Connection Status", JOptionPane.ERROR_MESSAGE);
		}

		 while (true) {
			try {
				
				DataInputStream input = new DataInputStream(con.getInputStream());
				String string = input.readUTF();
				chatArea.setText(chatArea.getText() + "Server: " + string +"\n");
			} 
			catch (Exception ev) {
				chatArea.setText(chatArea.getText() + "\nOops! Server Went Offline..");

				 try {
						Thread.sleep(2000);
						System.exit(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	public ClientChat() {
		initialize();
	}

	private void initialize() 
	{
		clientFrame = new JFrame();	
    	clientFrame.setTitle("Client Chat");
		clientFrame.setBounds(700, 300, 584, 531);
		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientFrame.setResizable(false);
		clientFrame.getContentPane().setLayout(null);
		clientFrame.setLocationRelativeTo(null); //center output on screen
		clientFrame.setVisible(true);
		clientFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		 chatPanel = new JPanel();
	     title = new JLabel();
	     status = new JLabel();
	     status.setText("Status: Online");
	     status.setFont(new Font ("Serif", 1, 14));
	     status.setBounds(10, 60, 300, 40);
	     status.setForeground(new Color(34,139,34));
	     scrollPanel = new JScrollPane();
	     chatArea = new JTextArea();
	     messageText = new JTextField("Type message..");
	     sendBtn = new JButton();
	     backBtn = new JButton();
	     label = new JLabel();
	        
	     chatPanel.setBackground(new Color(197, 218, 245));
	     chatPanel.setLayout(null);
	     chatArea.setColumns(20);
	     chatArea.setRows(7);
	     chatArea.setEditable(false);
	     chatArea.setFont(new Font ("Serif", Font.PLAIN, 14));
	     scrollPanel.setViewportView(chatArea);
	     
	     chatPanel.add(status);
		    chatPanel.add(scrollPanel);
		    scrollPanel.setBounds(10, 90, 480, 250);
		    chatPanel.add(messageText);
		    messageText.setBounds(10, 350, 400, 40);
		    messageText.setFont(new Font ("Serif", Font.PLAIN, 14));
		        
		   title.setFont(new Font("Myriad Pro", Font.ITALIC, 26)); 
		   title.setForeground(new Color(51, 0, 51));
		   title.setText("Micro-Star Cable Vision");
		   title.setBounds(120, 10, 340, 60);
		   chatPanel.add(title);
		        
		   messageText.addMouseListener(new MouseListener() 
		   {
			   @Override
			   public void mouseClicked(MouseEvent e)
				{
					 messageText.setText("");
				}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
		    });
		        backBtn.setBackground(new Color(102, 102, 255));
		        backBtn.setFont(new Font("Garamond", Font.PLAIN, 14)); 
		        backBtn.setForeground(new Color(255, 255, 255));
		        backBtn.setText("End Chat");
		        backBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		        backBtn.setBounds(445, 10, 65, 20);
		        chatPanel.add(backBtn);
		        
		        sendBtn.setBackground(new Color(102, 102, 255));
		        sendBtn.setFont(new Font("Garamond", Font.PLAIN, 24)); 
		        sendBtn.setForeground(new Color(255, 255, 255));
		        sendBtn.setText("Send");
		        sendBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		        sendBtn.setBounds(420, 350, 80, 40);
		        chatPanel.add(sendBtn);
		        backBtn.addActionListener(new ActionListener()
		        {
		        	@Override
		             public void actionPerformed(ActionEvent e)
		             {
		        		 if (JOptionPane.showConfirmDialog( clientFrame,"Are you sure you want to exit?","Customer-Staff Live Chat", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		        		 {
		        			 System.exit(0); 
		        		 }           
		             }
		        });
		
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (messageText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Text Required!");
				}else  {
					chatArea.setText(chatArea.getText() +"Client : " + messageText.getText() + "\n");
					try {
						DataOutputStream output = new DataOutputStream(con.getOutputStream());
						output.writeUTF(messageText.getText());
					} catch (IOException e1) {
						chatArea.setText(chatArea.getText() + "\nOops! Server Went Offline..");
						try {
							Thread.sleep(2000);
							System.exit(0);
						} catch (InterruptedException e2) {
							e2.printStackTrace();
						}
					}
					messageText.setText("");
				}
			}	
		});
		 label.setBackground(new Color(153, 255, 204));
	        chatPanel.add(label);
	        label.setBounds(0, 35, 460, 410);

	        GroupLayout layout = new GroupLayout(clientFrame.getContentPane());
	        clientFrame.getContentPane().setLayout(layout);
	        
	        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addComponent(chatPanel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE)
	        );
	        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, Short.MAX_VALUE))
	        );
	    clientFrame.setSize(new Dimension(530, 447));
	    clientFrame.setLocationRelativeTo(null);
	}
}