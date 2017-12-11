package password;

import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class MasterPassword {
	private boolean more;
	private boolean hasSetup;
	public Scanner scan;
	public Scanner scan2;
	public Scanner scan3;
	private String ultpassword;
	private String trypassword;
	private String copyOfText;
	private int count;
	File in;
	File out;
	String input;

	public MasterPassword() throws IOException {
		scan = new Scanner(System.in);
		out = new File("/home/proxycannon/git/MasterPass/MasterPassword/new.txt");		
		copyOfText = FileUtils.readFileToString(out, "UTF-8");		
		more = true;
		scan2 = new Scanner(copyOfText);
		hasSetup = scan2.hasNext();
		
		//out.delete();
		
		
		
	}	

	public void run() throws IOException {
		// Checks to see if password has been setup
		while (!hasSetup) {
			System.out.println("Please set up a password: ");
			String input = scan.next();
			System.out.println("Please enter the password again");
			String input2 = scan.next();
			if (input.equals(input2)) {
				FileUtils.writeStringToFile(out, (input + "\n"), "UTF-8", true);
				hasSetup = true;
			} else {
				System.out.println("Passwords do not match! Please try again.");
			}
			
		}
		copyOfText = FileUtils.readFileToString(out, "UTF-8");	
		scan2 = new Scanner(copyOfText);
		ultpassword = scan2.next();
		System.out.println("Enter your password: ");
		this.trypassword = scan.next();
		while (!trypassword.equals(ultpassword)) {
			System.out.println("Incorrect Password! Try again: ");
			this.trypassword = scan.next();
		}
		while (more) {
			more = false;
			
			
			//TODO: Implement encryption password with hash
			System.out.println("1. View your accounts \n2. Store new accounts \n3. Wipe data"
					+ " \n---------------------");
			int option = scan.nextInt();
			if (option == 1) {
				count = 0;
				copyOfText = FileUtils.readFileToString(out, "UTF-8");
				scan2 = new Scanner(copyOfText);
				//Recall all the accounts that are stored
				while (scan2.hasNext()) {					
					String value = scan2.nextLine();
					if (count > 0) {
						System.out.println(value);
					}
					count++;
				}
			} else if (option == 2) {				
				System.out.println("Type your name of the website: ");
				input = "Website: " + scan.next() + "\n";
				FileUtils.writeStringToFile(out, input, "UTF-8", true);
				
				System.out.println("Type your ID: ");
				input = "UserID: " + scan.next() + "\n";
				FileUtils.writeStringToFile(out, input, "UTF-8", true);
				
				System.out.println("Type your password: ");
				input = "Password: " + scan.next() + "\n\n";
				FileUtils.writeStringToFile(out, input, "UTF-8", true);
				
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
				
			} else {
				System.out.println("Invalid number, try again");
				more = true;
			}
			//				} else {
			//					System.out.println("Please run again and get the password right");
			//				}
			
		}
	}
	
		

	public static void main(String[] args) throws IOException {
		
		MasterPassword mypass = new MasterPassword();
		mypass.run();
		
	}
	
}

