package chat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class Server 
{
	static ServerSocket server ;
	static Socket con;
	private JFrame serverFrame;
    private static JTextArea chatArea;
    private JButton sendBtn;
    private JButton backBtn;
    private JLabel label;
    private JLabel title;
    private JLabel status;
    private JPanel chatPanel;
    private JScrollPane scrollPanel;
    private JTextField messageText;

	public Server() throws IOException
	{
		initialize();
	}
	private void initialize() throws IOException 
	{
		serverFrame = new JFrame();	
    	serverFrame.setTitle("Staff Chat");
		serverFrame.setBounds(700, 300, 584, 531);
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverFrame.setResizable(false);
		serverFrame.getContentPane().setLayout(null);
		serverFrame.setLocationRelativeTo(null); //center output on screen
		serverFrame.setVisible(true);
		serverFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		 chatPanel = new JPanel();
	     title = new JLabel();
	     status = new JLabel();
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

	     status.setFont(new Font ("Serif", 1, 14));
	     status.setBounds(10, 60, 300, 40);
		if (server.isClosed()) 
		{
			status.setText("Status: Offline");
			status.setForeground(new Color(139,0, 0));
		}else
		{
			status.setText("Status: Online");
			status.setForeground(new Color(34,139,34));
		}
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
	        		 if (JOptionPane.showConfirmDialog( serverFrame,"Are you sure you want to exit?","Customer-Staff Live Chat", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	        		 {
	        			 System.exit(0); 
	        		 }           
	             }
	        });
	        
	        sendBtn.addActionListener(new ActionListener()
	        {
	        	@Override
	             public void actionPerformed(ActionEvent e)
	             {
	        		if (messageText.getText().equals("")) 
	        		{
	    				JOptionPane.showMessageDialog(null, "Text Required!");
	    			}
	    				else if(messageText.isFocusable())
	    				{
	    					sendBtn.setEnabled(true);
	    					chatArea.setText(chatArea.getText() + "Staff: " + messageText.getText()+"\n");
	    					try
	    					{
	    						DataOutputStream output = new DataOutputStream(con.getOutputStream());
	    						output.writeUTF(messageText.getText());
	    					} 
	    					catch(NullPointerException nullEx)
    						{
    							JOptionPane.showMessageDialog(null, "Client Offline!","Connection Status", JOptionPane.ERROR_MESSAGE);
    						}
	    					catch (IOException e1) 
	    					{
	    						chatArea.setText(chatArea.getText() + "\nOops! Client Went Offline..");
	    						try 
	    						{
	    							Thread.sleep(2000);
	    							System.exit(0);
	    						} catch (InterruptedException e2) 
	    						{
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

	        GroupLayout layout = new GroupLayout(serverFrame.getContentPane());
	        serverFrame.getContentPane().setLayout(layout);
	        
	        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addComponent(chatPanel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE)
	        );
	        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, Short.MAX_VALUE))
	        );
	    serverFrame.setSize(new Dimension(530, 447));
	    serverFrame.setLocationRelativeTo(null);
	}

	private static void serverConnection() throws IOException 
	{
		 server = new ServerSocket(4433);
		 con = server.accept();
		 while (true) 
		 {
			try 
			{
				DataInputStream input = new DataInputStream(con.getInputStream());
				String string = input.readUTF();
				chatArea.setText(chatArea.getText() + "Client: " + string + "\n");
			} 
			catch (Exception ev) 
			{
				chatArea.setText(chatArea.getText() + "\nOops! Client Went Offline..");
				 try 
				 {
					Thread.sleep(2000);
					System.exit(0);
				 } catch (InterruptedException e) 
				 {
					e.printStackTrace();
				}
			}
		}//ENDWHILE
	}
	public static void main(String[] args) throws IOException   
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Server window = new Server();
					window.serverFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		 serverConnection();
	}
}