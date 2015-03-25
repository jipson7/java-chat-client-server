import java.io.IOException;
import java.net.Socket;


public class Client {

	private final static int PORT = 6677;//SET A CONSTANT VARIABLE PORT
	private final static String HOST = "localhost";//SET A CONSTANT VARIABLE HOST
	
	public static void main(String[] args) throws IOException
	{
		try 
		{
			
			Socket s = new Socket(HOST, PORT);//CONNECT TO THE SERVER
			
			System.out.println("You connected to " + HOST);//IF CONNECTED THEN PRINT IT OUT
			
			ClientThread client = new ClientThread(s);//START NEW CLIENT OBJECT
			
			Thread t = new Thread(client);//INITIATE NEW THREAD
			t.start();//START THREAD
			
		} 
		catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
		{
			System.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}
	}
}


