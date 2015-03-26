import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {

	String initUser = null;

	private Socket socket;
	
	public ClientThread(Socket socket) {

		this.socket = socket;

	}

	public ClientThread(Socket socket, String initUser) {

		this.socket = socket;
		this.initUser = initUser;

	}
	
	@Override
	public void run() {
		try {
			Scanner chat = new Scanner(System.in);
			Scanner in = new Scanner(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			while (true) {		

				String input;

				if (initUser != null) {

					input = "login " + initUser;
					initUser = null;

				} else {

					input = chat.nextLine();

				}					
				out.println(input);
				out.flush();
				
				if(in.hasNext()){

					System.out.println(in.nextLine());

				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}

}

