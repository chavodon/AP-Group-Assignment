package chat;


import java.awt.Color;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private DataInputStream input;
	private DataOutputStream output;
	static ServerSocket client ;
	private Socket con;
	
	public ClientHandler (Socket clientSocket) throws IOException {
		this.con = clientSocket;
		input = new DataInputStream(clientSocket.getInputStream());
		output = new DataOutputStream(clientSocket.getOutputStream());
	}
	@Override
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frmServerChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		try {
			client = new ServerSocket(8000);
			con = client.accept();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 while (true) {
			try {
				
				DataInputStream input = new DataInputStream(con.getInputStream());
				String string = input.readUTF();
			} catch (Exception ev) {				 
					try {
						Thread.sleep(2000);
						System.exit(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}

		}
	}

}
