import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FakeDataBase {
	private String login;
	private String password;
	private String nbAccount;
	private ArrayList<String> listUsersFiles = new ArrayList<String>();
	private ArrayList<String> listAccountsFiles = new ArrayList<String>();
	private ArrayList<String> userList = new ArrayList<String>();
	
	public FakeDataBase(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public FakeDataBase(String nbAccount) {
		this.nbAccount = nbAccount;
	}
	
	private ArrayList<String> listAllUsers() {
		File path = new File(System.getProperty("user.dir") + "\\Users\\");
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path.toString()), "*.txt")) {
			for (Path file : stream) {
				listUsersFiles.add((file.getFileName().toString()));
			}
			return listUsersFiles;
		} catch (Exception e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}
		return listUsersFiles;
	}
	
	private ArrayList<String> listAllAccounts() {
		File path = new File(System.getProperty("user.dir") + "\\Accounts\\");
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path.toString()), "*.txt")) {
			for (Path file : stream) {
				listAccountsFiles.add((file.getFileName().toString()));
			}
			return listAccountsFiles;
		} catch (Exception e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}
		return listAccountsFiles;
	}
	
	public boolean checkUser() throws IOException {
		listAllUsers();
		ArrayList<String> file = new ArrayList<String>();
		for (String currentFile : listUsersFiles) {
			File files = new File(System.getProperty("user.dir") + "\\Users\\" + currentFile);
			Scanner scan = new Scanner(files);
			
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			
			if(parseLogin(file.get(5)).equals(login) && parsePassword(file.get(6)).equals(password)) {
				scan.close();
				return true;
			}
			scan.close();
		}
		return false;
	}

	@SuppressWarnings("resource")
	public User createUserWithLoginAndPassword() throws IOException {
		listAllUsers();
		ArrayList<String> file = new ArrayList<String>();
		User user = null;
		
		for (String currentFile : listUsersFiles) {
			Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\Users\\" + currentFile));
			
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			
			if(parseLogin(file.get(5)).equals(login) && parsePassword(file.get(6)).equals(password)) {
				String identifier = parseIdentifier(currentFile);
				userList.add(identifier);
				
				if(checkAccount(userList)) {
					return affectAccountWithUser(identifier);
				} else {
					String lastName = parseLastName(file.get(0));
					userList.add(lastName);
					
					String firstName = parseFirstName(file.get(1));
					userList.add(firstName);
					
					String birthDate = parseBirthDate(file.get(2));
					userList.add(birthDate);
					
					String adress = parseAdress(file.get(3));
					userList.add(adress);
					
					String email = parseEmail(file.get(4));
					userList.add(email);
					
					String currentLogin = parseLogin(file.get(5));
					userList.add(currentLogin);
					
					String currentPassword = parsePassword(file.get(6));
					userList.add(currentPassword);
					
					user = createUser();
				}
			}
			scan.close();
		}
		return user;
	}

	private User createUser() {
		User user;
		switch (login.length()) {
		case 5:
			user =  new Admin
					(
						userList.get(0),
						userList.get(1),
						userList.get(2),
						userList.get(3), 
						userList.get(4), 
						userList.get(5), 
						userList.get(6), 
						userList.get(7)
					);
			break;
		case 6:
			user =  new Advisor
					(
						userList.get(0),
						userList.get(1),
						userList.get(2),
						userList.get(3), 
						userList.get(4), 
						userList.get(5), 
						userList.get(6), 
						userList.get(7)
					);
			break;
		default:
			user = new Client
					(
						userList.get(0),
						userList.get(1),
						userList.get(2),
						userList.get(3), 
						userList.get(4), 
						userList.get(5), 
						userList.get(6), 
						userList.get(7)
					);
			break;
		}
		return user;
	}
	
	private boolean checkAccount(ArrayList<String> identifiers) throws IOException {
		listAllAccounts();
		ArrayList<String> file = new ArrayList<String>();
		
		for (String currentFile : listAccountsFiles) {
			Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\Accounts\\" + currentFile));
			
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			
			if(parseUserID(file.get(3)).equals(identifiers.get(0))) {
				scan.close();
				return true;
			}
		}
		return false;
	}
	
	public User createUserWithIdentifier(String identifier) throws FileNotFoundException {
		String path = System.getProperty("user.dir") + "\\Users\\" + identifier + ".txt";
		User user;
		
		File client = new File(path);
		
		if (client.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(client);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			userList.add(identifier);
			
			String lastName = parseLastName(file.get(0));
			userList.add(lastName);
			
			String firstName = parseFirstName(file.get(1));
			userList.add(firstName);
			
			String birthDate = parseBirthDate(file.get(2));
			userList.add(birthDate);
			
			String adress = parseAdress(file.get(3));
			userList.add(adress);
			
			String email = parseEmail(file.get(4));
			userList.add(email);
			
			String currentLogin = parseLogin(file.get(5));
			userList.add(currentLogin);
			
			String currentPassword = parsePassword(file.get(6));
			userList.add(currentPassword);
			
			user = createUser();
			return user;
		}
		return null;
	}
	
	private User affectAccountWithUser(String userID) throws FileNotFoundException {
		listAllAccounts();
		ArrayList<String> file = new ArrayList<String>();
		User user = null;
		
		for (String currentFile : listAccountsFiles) {
			Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\Accounts\\" + currentFile));
			
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			
			if(parseUserID(file.get(3)).equals(userID)) {
				user = createUserWithIdentifier(userID);
				user.setListAccounts(createAccount(parseIdentifier(currentFile)));
			}
		}
		return user;
	}

	private Account createAccount(String currentAccount) throws FileNotFoundException {
		String path = System.getProperty("user.dir") + "\\Accounts\\" + currentAccount + ".txt";
		File account = new File(path);
		
		if (account.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(account);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			String accountType = parseAccountType(file.get(0));
			int nbAccount = Integer.parseInt(parseNbAccount(file.get(1)));
			int agencyCode = Integer.parseInt(parseAgencyCode(file.get(2)));
			String userID = parseUserID(file.get(3));
			float balance = Float.parseFloat(parseBalance(file.get(4)));
			boolean overcraft = parseOvercraft(file.get(5));
			
			switch (accountType) {
			case "Livret A":
				return new LivretA(nbAccount, agencyCode, createUserWithIdentifier(userID), balance, overcraft);
			case "PEL":
				return new PEL(nbAccount, agencyCode, createUserWithIdentifier(userID), balance, overcraft);
			default:
				return new CurrentAccount(nbAccount, agencyCode, createUserWithIdentifier(userID), balance, overcraft);
			}
			
		}
		return null;
	}

	private String parseIdentifier(String currentFile) {
		String[] identifier = currentFile.split(".txt");
		return identifier[0];
	}

	private String parseLastName(String string) {
		String[] lastName = string.split("Nom: ");
		return lastName[1];
	}

	private String parseFirstName(String string) {
		String[] firstName = string.split("Prénom: ");
		return firstName[1];
	}

	private String parseBirthDate(String string) {
		String[] birthDate = string.split("Date de Naissance: ");
		return birthDate[1];
	}

	private String parseAdress(String string) {
		String[] adress = string.split("Adresse: ");
		return adress[1];
	}

	private String parseEmail(String string) {
		String[] email = string.split("Email: ");
		return email[1];
	}

	private String parseLogin(String string) {
		String[] login = string.split("Login: ");
		return login[1];
	}

	private String parsePassword(String string) {
		String[] password = string.split("Mot de passe: ");
		return password[1];
	}
	
	private String parseUserID(String string) {
		String[] userID = string.split("Identifiant client: ");
		return userID[1];
	}
	
	private String parseAccountType(String string) {
		String[] accountType = string.split("Type de compte: ");
		return accountType[1];
	}
	
	private String parseNbAccount(String string) {
		String[] nbAccount = string.split("Numéro de compte: ");
		return nbAccount[1];
	}
	
	private String parseAgencyCode(String string) {
		String[] agencyCode = string.split("Code agence: ");
		return agencyCode[1];
	}
	
	private String parseBalance(String string) {
		String[] balance = string.split("Solde: ");
		return balance[1];
	}
	
	private boolean parseOvercraft(String string) {
		String[] overcraft = string.split("Droit de découvert: ");
		if(overcraft[1].equals("true")) {
			return true;
		}
		return false;
	}
}
