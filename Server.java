import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class Server {

	public static void main(String[] args) throws IOException {
		try 
		{
			final int PORT = 6677;//SET NEW CONSTANT VARIABLE: PORT
			ServerSocket server = new ServerSocket(PORT); //SET PORT NUMBER
			System.out.println("Waiting for clients...");//AT THE START PRINT THIS
		
			while (true)//WHILE THE PROGRAM IS RUNNING
			{												
				Socket s = server.accept();//ACCEPT SOCKETS(CLIENTS) TRYING TO CONNECT
				
				System.out.println("Client connected from " + s.getLocalAddress().getHostName());	//	TELL THEM THAT THE CLIENT CONNECTED
				
				UserThread chat = new UserThread(s);//CREATE A NEW CLIENT OBJECT
				Thread t = new Thread(chat);//MAKE A NEW THREAD
				t.start();//START THE THREAD
			}
		} 
		catch (Exception e) 
		{
			System.out.println("An error occured.");//IF AN ERROR OCCURED THEN PRINT IT
                        e.printStackTrace();
		}
	}

}

