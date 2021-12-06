import java.io.File;
import java.io.FileWriter;
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
	
	@Override
	public void createUser() {
		String identifier;
		String lastName;
		String firstName;
		String birthDate;
		String adress;
		String email;
		String login;
		String password;
		String path;
		
		System.out.println("Veuillez saisir un identifiant");
		identifier = scanner.nextLine();
		System.out.println("Veuillez saisir le nom");
		lastName = scanner.nextLine();
		System.out.println("Veuillez saisir le prénom");
		firstName = scanner.nextLine();
		System.out.println("Veuillez saisir la date de naissance");
		birthDate = scanner.nextLine();
		System.out.println("Veuillez saisir l'adresse");
		adress = scanner.nextLine();
		System.out.println("Veuillez saisir l'email");
		email = scanner.nextLine();
		System.out.println("Veuillez saisir un login");
		login = scanner.nextLine();
		System.out.println("Veuillez saisir un mot de passe");
		password = scanner.nextLine();
		
		path = System.getProperty("user.dir") + "\\" + identifier + ".txt";
		
		try {
			File client = new File(path);
			if(client.createNewFile()) {
				try {
					FileWriter writer = new FileWriter(path);
					writer.write("Nom: " + lastName + "\n");
					writer.write("Prénom: " + firstName + "\n");
					writer.write("Date de Naissance: " + birthDate + "\n");
					writer.write("Adresse: " + adress + "\n");
					writer.write("Email: " + email + "\n");
					writer.write("Login: " + login + "\n");
					writer.write("Mot de passe: " + password + "\n");
					writer.write("Utilisateur actif: " + true + "\n");
					writer.close();
					
					switch (login.length()) {
					case 5:
						new Admin(identifier, lastName, firstName, birthDate, adress, email, login, password);
						break;
					case 6:
						new Advisor(identifier, lastName, firstName, birthDate, adress, email, login, password);
						break;
					default:
						new Client(identifier, lastName, firstName, birthDate, adress, email, login, password);
						break;
					}
					
				} catch (Exception e) {
					System.out.println("Une erreur est survenue");
					e.printStackTrace();
				}
			} else {
				System.out.println("Ce client existe déjà");
			}
		} catch (Exception e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}
	}
	
	public void changeUserStatus(User user) {
		user.setActive(!user.isActive());
	}
	
	public void changeAccountStatus(Account account) {
		account.setActive(!account.isActive());
	}
}
