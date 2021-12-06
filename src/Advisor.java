import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Advisor extends User {
	private Scanner scanner = new Scanner(System.in);

	public Advisor(String identifier, String lastName, String firstName, String birthDate, String adress, String email, String login, String password) {
		super(identifier, lastName, firstName, birthDate, adress, email, login, password);
	}

	public void createUser () {
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
	
	public void createAccount() throws FileNotFoundException {
		String accountType;
		int nbAccount;
		int agencyCode;
		String userID;
		float balance;
		
		System.out.println("Saisissez le type de compte");
		accountType = scanner.nextLine();
		System.out.println("Saisissez l'identifiant client");
		userID = scanner.nextLine();
		System.out.println("Saisissez un numéro de compte ");
		nbAccount = scanner.nextInt();
		System.out.println("Saissez le code agence ");
		agencyCode = scanner.nextInt();
		System.out.println("Saisissez le montant du dépôt");
		balance = scanner.nextInt();
		
		String path = System.getProperty("user.dir") + "\\" + nbAccount + ".txt";
		
		try {
			File account = new File(path);
			if(account.createNewFile()) {
				try {
					FileWriter writer = new FileWriter(path);
					writer.write("Type de compte: " + accountType + "\n");
					writer.write("Numéro de compte: " + nbAccount + "\n");
					writer.write("Code agence: " + agencyCode + "\n");
					writer.write("Identifiant client: " + userID + "\n");
					writer.write("Solde: " + balance + "\n");
					writer.write("Droit de découvert: " + false + "\n");
					writer.write("Compte actif: " + true + "\n");
					writer.close();
					
				} catch (Exception e) {
					System.out.println("Une erreur est survenue");
					e.printStackTrace();
				}
			} else {
				System.out.println("Ce numéro de compte existe déjà");
			}
		} catch (Exception e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}
		
		if(accountType.equals("PEL")) {
			new PEL(nbAccount, agencyCode, new FakeDataBase(Integer.toString(nbAccount)).createUserWithIdentifier(userID), balance, false);
		} else if(accountType.equals("Livret A")) {
			new LivretA(nbAccount, agencyCode, new FakeDataBase(Integer.toString(nbAccount)).createUserWithIdentifier(userID), balance, false);
		} else {
			new CurrentAccount(nbAccount, agencyCode, new FakeDataBase(Integer.toString(nbAccount)).createUserWithIdentifier(userID), balance, false);
		}
	}
	
	public void changeUserAdress() throws IOException { 
		System.out.println("Saisissez l'identifiant client");
		String user = scanner.nextLine();
		
		String path = System.getProperty("user.dir") + user + ".txt";
		
		File client = new File(path);
		
		if (client.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(client);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			System.out.println("Saisissez la nouvelle adresse: ");
			
			String identifier = user;
			String lastName = file.get(0);
			String firstName = file.get(1);
			String birthDate = file.get(2);
			String adress = scanner.nextLine();
			String email = file.get(4);
			String login = file.get(5);
			String password = file.get(6);
			
			client.delete();
			client.createNewFile();
			
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
		}
	}
	
	public void changeUserInfos() {
		createUser();
	}
}
 