//Program created by Tony and Taehan
import java.util.Scanner;
public class MasterPassword {
//building constructors (getter and setter) to use it later on
	public String ID;
	public String Password;
	public String website;
	
	public void idandpass(String ID, String Password, String website) {
		this.ID = ID;
		this.Password = Password;
		this.website = website;
	}
	
	public void setID(String input) {
		this.ID = input;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setPassword(String input) {
		this.Password = input;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setwebsite(String input) {
		this.website = input;
	}
	
	public String getwebsite() {
		return website;
	}
	
	public static String ultpassword;
	
	public static String nameofweb;
	
	public static String nameofid;
	
	public static String nameofpass;
	
	public static void main(String[] args) {
		String password1;
		String data;
		int option = 0;
		password1 = "";
		data = "";
		if (ultpassword == null || ultpassword.isEmpty()) {
			System.out.println("Please setup the password!");
			Scanner scan = new Scanner(System.in);
			ultpassword = scan.nextLine();
		} 
		if (ultpassword != null && !ultpassword.isEmpty()) {
			System.out.println("Type your password to view");
			Scanner scan1 = new Scanner(System.in);
			password1 = scan1.nextLine();
			if (password1.equals(ultpassword)) {
				System.out.println("type 1 to view your accounts, or 2 to store new accounts");
				Scanner scan2 = new Scanner(System.in);
				option = scan2.nextInt();
				if (option == 1) {
					//Recall all the accounts that are stored
					System.out.println("sh");
				} else if (option == 2) {
					//store new account
					System.out.println("Type your name of the website");
					Scanner scan3 = new Scanner(System.in);
					nameofid = scan3.nextLine();
					System.out.println("Type your ID");
					Scanner scan4 = new Scanner(System.in);
					nameofpass = scan4.nextLine();
					Scanner scan5 = new Scanner(System.in);
					nameofweb = scan5.nextLine();
					// put these data into constructors
				} else {
					//wrong number so error
					System.out.println("You entered wrong number, try again from beginning");
				}
			} else {
				System.out.println("Please run again and get the password right");
			}
		}
	}
}
