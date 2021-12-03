import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FakeDataBase {
	private ArrayList<String> listUser = new ArrayList<String>();
	private ArrayList<String> listAccount = new ArrayList<String>();
	public ArrayList<String> listFiles = new ArrayList<String>();
	
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
	
	public boolean checkUser(String login, String password) throws FileNotFoundException {
		listAllFiles();
		ArrayList<String> file = new ArrayList<String>();
		
		for (String currentFile : listFiles) {
			Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\" + currentFile));
			
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			
			if(file.get(5).equals(login) && file.get(6).equals(password)) {
				constructUserFromFile(currentFile);
				return true;
			}
		}
		return false;
	}

	private void constructUserFromFile(String currentFile) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(System.getProperty("user.dir") + "\\" + currentFile));
		ArrayList<String> currentUser = new ArrayList<String>();
		
		while(scan.hasNextLine()) {
			currentUser.add(scan.nextLine()); 
		}
		
		String identifier = parseIdentifier(currentFile);
		String lastName = parseLastName(currentUser.get(0));
		String firstName = parseFirstName(currentUser.get(1));
		String birthDate = parseBirthDate(currentUser.get(2));
		String adress = parseAdress(currentUser.get(3));
		String email = parseEmail(currentUser.get(4));
		String login = parseLogin(currentUser.get(5));
		String password = parsePassword(currentUser.get(6));
		boolean isActive = parseIsActive(currentUser.get(7));
	}

	private String parseIdentifier(String currentFile) {
		String[] identifier = currentFile.split(".txt");
		return identifier[0];
	}

	private String parseLastName(String string) {
		String[] lastName = string.split("Nom: ");
		return lastName[0];
	}

	private String parseFirstName(String string) {
		String[] firstName = string.split("Prénom: ");
		return firstName[0];
	}

	private String parseBirthDate(String string) {
		String[] birthDate = string.split("Date de Naissance: ");
		return birthDate[0];
	}

	private String parseAdress(String string) {
		String[] adress = string.split("Adresse: ");
		return adress[0];
	}

	private String parseEmail(String string) {
		String[] email = string.split("Email: ");
		return email[0];
	}

	private String parseLogin(String string) {
		String[] login = string.split("Login: ");
		return login[0];
	}

	private String parsePassword(String string) {
		String[] password = string.split("Mot de passe: ");
		return password[0];
	}

	private boolean parseIsActive(String string) {
		String[] isActive = string.split("Utilisateur actif: ");
		if(isActive[0].equals("true")) {
			return true;
		}
		return false;
	}
}
