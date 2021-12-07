import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
		System.out.println("Veuillez saisir le pr�nom");
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
		
		path = System.getProperty("user.dir") + "\\Users\\" + identifier + ".txt";
		
		try {
			File client = new File(path);
			if(client.createNewFile()) {
				try {
					FileWriter writer = new FileWriter(path);
					writer.write("Nom: " + lastName + "\n");
					writer.write("Pr�nom: " + firstName + "\n");
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
		
		String pathAccount = System.getProperty("user.dir") + "\\Accounts\\" + nbAccount + ".txt";
		String pathTransaction = System.getProperty("user.dir") + "\\Transactions\\" + nbAccount + ".txt";
		
		try {
			File account = new File(pathAccount);
			File transaction = new File(pathTransaction);
			if(account.createNewFile() && transaction.createNewFile()) {
				try {
					FileWriter writerAccount = new FileWriter(pathAccount);
					writerAccount.write("Type de compte: " + accountType + "\n");
					writerAccount.write("Numéro de compte: " + nbAccount + "\n");
					writerAccount.write("Code agence: " + agencyCode + "\n");
					writerAccount.write("Identifiant client: " + userID + "\n");
					writerAccount.write("Solde: " + balance + "\n");
					writerAccount.write("Droit de découvert: " + false + "\n");
					writerAccount.write("Compte actif: " + true + "\n");
					writerAccount.close();
					
					FileWriter writerTransaction = new FileWriter(pathTransaction);
					writerTransaction.write("Compte crée le :" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
					writerTransaction.close();
					
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
		
		switch (accountType) {
		case "PEL":
			new PEL(nbAccount, agencyCode, new FakeDataBase(Integer.toString(nbAccount)).createUserWithIdentifier(userID), balance, false);
			break;
		case "Livret A":
			new LivretA(nbAccount, agencyCode, new FakeDataBase(Integer.toString(nbAccount)).createUserWithIdentifier(userID), balance, false);
			break;
		default:
			new CurrentAccount(nbAccount, agencyCode, new FakeDataBase(Integer.toString(nbAccount)).createUserWithIdentifier(userID), balance, false);
			break;
		}
	}
	
	public void changeUserAdress() throws IOException { 
		System.out.println("Saisissez l'identifiant client");
		String user = scanner.nextLine();
		
		String path = System.getProperty("user.dir") + "\\Users\\" + user + ".txt";
		
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
				writer.write("Pr�nom: " + firstName + "\n");
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
 