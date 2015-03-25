import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


public class UserThread extends Thread{

	private Map<String, ArrayList<String>> messageBank;

	private Socket socket;

	private Scanner in;

	private PrintWriter out;

	private String username;

	private boolean loginStatus = false;
	
	public UserThread(Socket socket, Map<String, ArrayList<String>> messageBank) {

		this.messageBank = messageBank;

		this.socket = socket;
	}

	public void run() {

		try {

			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
			
			while (true) {		

				if (in.hasNext()) {
					String input = in.nextLine();
					handleInput(input);

				}

			}

		} 

		catch (Exception e) {

			e.printStackTrace();

		}	

	}

	private void handleInput(String input) {

		System.out.println("Incoming: " + input);

		String[] splitInput = input.split(" ");

		if (splitInput[0].compareTo("login") == 0) {

			handleLogin(splitInput);
		
		}else if (splitInput[0].compareTo("send") == 0) {

			handleSend(splitInput);

		}else if (splitInput[0].compareTo("fetch") == 0) {

			handleFetch(splitInput);

		} else {

			out.println(input);
			out.flush();

		}

	}

	private void handleFetch(String[] splitInput) {

		if (!loginStatus) {

			out.println("Must be logged in to receive messages.");
			out.flush();
			return;

		}

		ArrayList<String> toOutput = messageBank.get(username);

		for (String x : toOutput) {

			out.println(x);
			out.flush();

		}


	}

	private void handleSend(String[] splitInput) {

		if (!loginStatus) {

			out.println("Must be logged in to send a message.");
			out.flush();
			return;

		}

		if (splitInput.length >= 3) {

			String toUser = splitInput[1];

			String outgoingMessage = splitInput[2];

			for (int i = 3; i < splitInput.length; i++) {

				outgoingMessage += " " + splitInput[i];

			}

			addToMessageBank(toUser, outgoingMessage);

			out.println("Message sent successfully.");
			out.flush();

		} else {

			out.println("Must provide a user to send to and at least a 1 word message.");
			out.flush();

		}

	}

	private void addToMessageBank(String name, String message) {

		if (messageBank.containsKey(name)) {

			messageBank.get(name).add(message);

		} else {

			ArrayList<String> mailBox = new ArrayList<String>();

			mailBox.add(message);

			messageBank.put(name, mailBox);

		}

	}

	private void handleLogin(String[] splitInput) {

		if (splitInput.length >= 2) {

			username = splitInput[1];
			loginStatus = true;
			out.println(username + " was logged in sucessfully!");
			out.flush();

		} else {

			out.println("Must provide a username to log in...");
			out.flush();

		}

	}

}


