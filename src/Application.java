import java.io.IOException;
import java.util.Scanner;

public class Application {
	private Scanner scanner = new Scanner(System.in);
	
	public void authentification() throws IOException {
		String login;
		String password;
		
		System.out.println("Veuillez saisir votre login");
		login = scanner.nextLine();
		System.out.println("Veuillez saisir votre mot de passe");
		password = scanner.nextLine();
		
		if (new FakeDataBase(login, password).checkUser()) {
			User user = new FakeDataBase(login, password).createUserWithLoginAndPassword();
			displayMenu(user);
		} else {
			System.out.println("Login ou Mot de passe incorrect");
		}
	}

	private void displayMenu(User user) throws IOException {
		if (user instanceof Admin) {
			displayAdminMenu(user);
		} else if (user instanceof Advisor) {
			displayAdvisorMenu(user);
		} else {
			displayClientMenu(user);
		}
	}

	private void displayAdminMenu(User user) throws IOException {
		new AdminMenu(user).action();
	}
	
	private void displayAdvisorMenu(User user) throws IOException {
		new AdvisorMenu(user).action();
	}

	private void displayClientMenu(User user) throws IOException {
		new ClientMenu(user).action();
	}
}
