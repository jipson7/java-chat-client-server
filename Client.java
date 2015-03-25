import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private final static int PORT = 6677;
	private final static String HOST = "localhost";

	
	
	public static void main(String[] args) throws Exception {
			
		Socket s = new Socket(HOST, PORT);
		
		System.out.println("You connected to " + HOST);
		
		ClientThread client = new ClientThread(s);
		
		Thread t = new Thread(client);
		t.start();
		t.join();
			
	}
}


