package password;
import java.util.Scanner;

public class MasterPassword {

	private String ID;
	private String password;
	private String website;
	
	public MasterPassword() {
		
	}
	
	public MasterPassword(String ID, String Password, String website) {
		this.ID = ID;
		this.password = Password;
		this.website = website;
	}
	
	public static String ultpassword;
	
	public static String nameofweb;
	
	public static String nameofid;
	
	public static String nameofpass;
	
	public static void main(String[] args) {
		MasterPassword mypass = new MasterPassword();
		
		String password1;
		String data;
		int option = 0;
		mypass.password = "";
		data = "";
		Scanner scan = new Scanner(System.in);
		while (ultpassword == null || ultpassword.isEmpty()) {
			System.out.println("Please setup the password!");			
			ultpassword = scan.nextLine();
		} 
		if (ultpassword != null && !ultpassword.isEmpty()) {
			System.out.println("Type your password to view");
			
			password1 = scan.nextLine();
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
					nameofid = scan3.nextLine();
					System.out.println("Type your ID");
					nameofpass = scan4.nextLine();
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
