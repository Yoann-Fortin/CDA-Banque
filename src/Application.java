import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
	private Scanner scanner = new Scanner(System.in);
	
	public void authentification() throws FileNotFoundException {
		String login;
		String password;
		
		System.out.println("Veuillez saisir votre login");
		login = scanner.nextLine();
		System.out.println("Veuillez saisir votre mot de passe");
		password = scanner.nextLine();
		
		if (new FakeDataBase().checkUser(login, password)) {
			User user = null;
			displayMenu(user);
		}
	}

	public void displayMenu(User user) throws FileNotFoundException {
		if (user instanceof Admin) {
			displayAdminMenu(user);
		} else if (user instanceof Advisor) {
			displayAdvisorMenu(user);
		} else {
			displayClientMenu(user);
		}
	}

	private void displayAdminMenu(User user) throws FileNotFoundException {
		new AdminMenu(user).action();
	}
	
	private void displayAdvisorMenu(User user) {
		new AdvisorMenu(user).action();
	}

	private void displayClientMenu(User user) {
		new ClientMenu(user).action();
	}
}
