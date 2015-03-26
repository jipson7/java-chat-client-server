import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;

public class Server {

	public static Map<String, ArrayList<String>> messageBank = new HashMap<String, ArrayList<String>>();

	public static void main(String[] args) throws Exception {

		int PORT = Integer.parseInt(args[0]);
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Server started on PORT " + PORT);
		System.out.println("Ready for users to connect...");
	
		while (true) {		

			Socket s = server.accept();
			
			System.out.println("User connected from " + s.getLocalAddress().getHostName());	
			
			UserThread chat = new UserThread(s, messageBank);
			Thread t = new Thread(chat);
			t.start();

		}

	}

}

