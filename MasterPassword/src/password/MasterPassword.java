package password;

import java.util.Random;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class MasterPassword {
	/**
	 * Keeps track if user wants to do more.
	 */
	private boolean more;
	
	/**
	 * Keeps track if the user has run the program before.
	 */
	private boolean hasSetup;
	public Scanner scan;
	public Scanner scan2;
	private String ultpassword;
	private String trypassword;
	private String copyOfText;
	private int count;
	//	private Random random;
	File in;
	File out;
	String input;

	/**
	 * Constructor for MasterPassword.
	 * Creates a file if it does not exist, and sets up scanners. 
	 * @throws IOException
	 */
	public MasterPassword() throws IOException {
		//TODO: Setup salting
		//		random = new Random();
		scan = new Scanner(System.in);
		out = new File("./new.txt");
		if (!out.exists()) {
			out.createNewFile();
		}
		copyOfText = FileUtils.readFileToString(out, "UTF-8");		
		more = true;
		scan2 = new Scanner(copyOfText);
		hasSetup = scan2.hasNext();				
	}
	
	/**
	 * Runs the entire program 
	 * TODO: Refactor
	 * @throws IOException
	 */
	public void run() throws IOException {
		// Checks to see if password has been setup
		while (!hasSetup) {
			System.out.println("Please set up a password: ");
			String input = scan.next();
			System.out.println("Please enter the password again");
			String input2 = scan.next();
			String hashed = Encryptor.sha256(input);
			if (input.equals(input2)) {
				FileUtils.writeStringToFile(out, (hashed + "\n"), "UTF-8", false);
				hasSetup = true;
			} else {
				System.out.println("Passwords do not match! Please try again.");
			}			
		}

		// Checks password
		copyOfText = FileUtils.readFileToString(out, "UTF-8");	
		scan2 = new Scanner(copyOfText);
		ultpassword = scan2.nextLine();
		System.out.println("Enter your password: ");
		this.trypassword = Encryptor.sha256(scan.next());
		while (!trypassword.equals(ultpassword)) {
			System.out.println("Incorrect Password! Try again: ");
			this.trypassword = Encryptor.sha256(scan.next());
		}

		// Uses MD5 as key for AES 
		String key = Encryptor.MD5(ultpassword);
		String temp = "";
		scan2 = new Scanner(copyOfText);
		count = 0;
		while (scan2.hasNextLine()) {
			String wow = scan2.nextLine();
			if (count > 0) {
				temp += wow;				
			}
			count++;
		}
		copyOfText = temp;

		// Decrypt the text file!
		copyOfText = Encryptor.decrypt(key, "0000000000000000", copyOfText);
		
		// TODO: Implement salt
		//		byte[] randombytes = new byte[8];
		//		random.nextBytes(randombytes);		
		//		String initVector = new String(randombytes);

		while (more) {
			more = false;			

			//TODO: Implement encryption password with hash
			System.out.println("1. View your accounts \n2. Store new accounts \n3. Wipe data"
					+ " \n---------------------");
			int option = scan.nextInt();
			if (option == 1) {
				count = 0;
				scan2 = new Scanner(copyOfText);
				//Recall all the accounts that are stored
				while (scan2.hasNextLine()) {					
					String value = scan2.nextLine();					
					System.out.println(value);
					count++;
				}
				System.out.println("Do you want to do more: ");
				String answer = scan.next();
				if (answer.toLowerCase().equals("y") 
						|| answer.toLowerCase().equals("ye")
						|| answer.toLowerCase().equals("yes")) {
					more = true;
				}
			} else if (option == 2) {				
				System.out.println("Type your name of the website: ");
				input = "Website: " + scan.next() + "\n";
				copyOfText += input;

				System.out.println("Type your ID: ");
				input = "UserID: " + scan.next() + "\n";
				copyOfText += input;

				System.out.println("Type your password: ");
				input = "Password: " + scan.next() + "\n\n";
				copyOfText += input;

				System.out.println("Do you want to do more: ");
				String answer = scan.next();
				if (answer.toLowerCase().equals("y") 
						|| answer.toLowerCase().equals("ye")
						|| answer.toLowerCase().equals("yes")) {
					more = true;
				}
			} else if (option == 3) {
				out.delete();
				FileUtils.writeStringToFile(out, "", "UTF-8", true);
				System.exit(0);

			} else {
				System.out.println("Invalid number, try again");
				more = true;
			}

		}
		
		// Takes in everything but the password
		String finalCopyOfText = "";
		scan2 = new Scanner(copyOfText);	
		count = 0;
		while (scan2.hasNextLine()) {
			String wow = scan2.nextLine();
			finalCopyOfText += wow + "\n";	
			count++;
		}
		
		// Takes the contents and encrypts it!
		finalCopyOfText = Encryptor.encrypt(key, "0000000000000000", finalCopyOfText);

		// Writes to file
		FileUtils.writeStringToFile(out, (ultpassword + "\n"), "UTF-8", false);
		FileUtils.writeStringToFile(out, finalCopyOfText, "UTF-8", true);
	}


	public static void main(String[] args) throws IOException {
		MasterPassword mypass = new MasterPassword();
		mypass.run();

	}

}

