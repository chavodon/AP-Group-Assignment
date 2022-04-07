package client;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Client a run");
		Client client = new Client();
		
		client.sendAction("Find Student");
		client.sendStudentId("1");
		client.receiveResponse();
		
		client.closeConnection();
		
	}

}
