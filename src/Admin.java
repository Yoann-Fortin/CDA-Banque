import java.util.Scanner;

public class Admin extends Advisor {
	private Scanner scanner = new Scanner(System.in);

	public Admin(String identifier, String lastName, String firstName, String birthDate, String adress, String email, String login, String password) {
		super(identifier, lastName, firstName, birthDate, adress, email, login, password);
	}

	public void createAgency() {
		int agencyCode;
		String name;
		String adress;
		
		System.out.println("Saisissez le code agence");
		agencyCode = scanner.nextInt();
		System.out.println("Saisissez le nom de l'agence");
		name = scanner.nextLine();
		System.out.println("Saisissez l'adresse de l'agence");
		adress = scanner.nextLine();
		
		new Agency(agencyCode, name, adress);
	}
	
	public void changeUserStatus(User user) {
		user.setActive(!user.isActive());
	}
	
	public void changeAccountStatus(Account account) {
		account.setActive(!account.isActive());
	}
}
