import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	private ArrayList<String> listFiles = new ArrayList<String>();
	private ArrayList<String> userList = new ArrayList<String>();
	
	public FakeDataBase(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public FakeDataBase(String nbAccount) {
		this.nbAccount = nbAccount;
	}
	
	private ArrayList<String> listAllFiles() {
		File path = new File(System.getProperty("user.dir"));
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path.toString()), "*.txt")) {
			for (Path file : stream) {
				listFiles.add((file.getFileName().toString()));
			}
			return listFiles;
		} catch (Exception e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}
		return listFiles;
	}
	
	public boolean checkUser() throws FileNotFoundException {
		listAllFiles();
		ArrayList<String> file = new ArrayList<String>();
		
		for (String currentFile : listFiles) {
			Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\" + currentFile));
			
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
	public User createUserWithLoginAndPassword() throws FileNotFoundException {
		listAllFiles();
		ArrayList<String> file = new ArrayList<String>();
		
		for (String currentFile : listFiles) {
			Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\" + currentFile));
			
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			
			if(parseLogin(file.get(5)).equals(login) && parsePassword(file.get(6)).equals(password)) {
				String identifier = parseIdentifier(currentFile);
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
				
				switch (login.length()) {
				case 5:
					return new Admin
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
				case 6:
					return new Advisor
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
				default:
					return new Client
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
				}	
			}
			scan.close();
		}
		return null;
	}
	
	public User createUserWithIdentifier(String identifier) throws FileNotFoundException {
		String path = System.getProperty("user.dir") + nbAccount + ".txt";
		
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
			
			switch (currentLogin.length()) {
			case 5:
				return new Admin
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
			case 6:
				return new Advisor
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
			default:
				return new Client
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
			}
		}
		return null;
	}
	
	private void affectAccountWithUser(String userID) throws FileNotFoundException {
		listAllFiles();
		ArrayList<String> file = new ArrayList<String>();
		
		for (String currentFile : listFiles) {
			Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\" + currentFile));
			
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			
			if(parseUserID(file.get(5)).equals(userID)) {
				User user = createUserWithIdentifier(userID);
				user.setListAccounts(createAccount(currentFile));
			}
		}
	}

	private Account createAccount(String currentAccount) throws FileNotFoundException {
		String path = System.getProperty("user.dir") + currentAccount + ".txt";
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
