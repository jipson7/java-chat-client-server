import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	static String HOST;

	static int PORT;

	static String initUser;

	static ClientThread client;

	public static void main(String[] args) throws Exception {

		if (args.length >= 2) {

			HOST = args[0];

			PORT = Integer.parseInt(args[1]);

		} else {

			System.err.println("Usage: java Client <hostname> <port> <name -optional>");

		}

		if (args.length >= 3) {

			initUser = args[2];

		}

			
		Socket s = new Socket(HOST, PORT);
		
		System.out.println("You connected to " + HOST);

		if (args.length == 2) {

			client = new ClientThread(s);
		} else {

			client = new ClientThread(s, initUser);

		}
		
		Thread t = new Thread(client);
		t.start();
		t.join();
			
	}
}


