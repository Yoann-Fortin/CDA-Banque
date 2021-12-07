import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public abstract class UserMenu {
	protected Scanner scanner = new Scanner(System.in);
	
	protected User user;
	
	protected UserMenu(User user) {
		this.user = user;
	}
	
	protected ArrayList<String> listUserActions = new ArrayList<String>(Arrays.asList(
			"Consultez vos informations personnelles",
			"Consulez vos comptes",
			"Consutez les opérations d'un compte",
			"Imprimer les opérations d'un compte",
			"Effectuer un virement",
			"Effectuer un dépôt",
			"Effectuer un retrait"
			));

	protected void displayMenu() {
		for (int i = 0; i < listUserActions.size(); i++) {
			System.out.println((i + 1) + " - " + listUserActions.get(i));
		}
		System.out.println(listUserActions.size() + 1 + " - Quitter l'application" );
		System.out.println();
	}
	
	protected void action() throws FileNotFoundException, IOException {
		int choiceUser;
		
		displayMenu();
		System.out.println("Quelle action voulez-vous effectuer ?");
		choiceUser = scanner.nextInt();
		
		switch (choiceUser) {
		case 1:
			displayPersonnalInformations();
			break;
		case 2:
			displayAccountsUser();
			break;
		case 3:
			displayTransactionUserAccount();
			break;
		case 4:
			writeTransactionUserAccount();
			break;
		case 5:
			transfer();
			break;
		case 6:
			add();
			break;
		case 7:
			soustract();
			break;
		case 8:
			new Application().authentification();
			break;
		default:
			System.out.println("Je n'ai pas compris votre choix");
		}
	}

	private void writeTransactionUserAccount() throws IOException {
		Account[] accounts = new Account[user.getListAccounts().size()];
		user.getListAccounts().toArray(accounts);
		
		int choiceUser;
		
		displayListAccountsOfUser();
		
		System.out.println();
		System.out.println("Choisissez le compte à imprimer les transactions");
		choiceUser = scanner.nextInt();
		
		accounts[choiceUser - 1].printTransactionsText();
	}

	protected void displayPersonnalInformations() {
		System.out.println(user.getLastName() + " " + user.getFirstName());
		System.out.println(user.getBirthDate());
		System.out.println(user.getAdress());
		System.out.println(user.getEmail());
	}
	
	protected void displayAccountsUser() {
		user.checkAccounts();
	}
	
	protected void displayTransactionUserAccount() throws FileNotFoundException {
		Account[] accounts = new Account[user.getListAccounts().size()];
		user.getListAccounts().toArray(accounts);
		
		int choiceUser;
		
		displayListAccountsOfUser();
		
		System.out.println();
		System.out.println("Choisissez le compte à afficher les transactions");
		choiceUser = scanner.nextInt();
		
		accounts[choiceUser - 1].printTransactions();
	}
	
	protected void transfer() throws IOException {
		Account[] accounts = new Account[user.getListAccounts().size()];
		int debitAccount;
		int creditAccount;
		
		displayListAccountsOfUser();
		
		System.out.println("Quel compte voulez vous débiter ?");
		debitAccount = scanner.nextInt() - 1;
		
		System.out.println("Quel compte voulez vous créditer ?");
		creditAccount = scanner.nextInt() - 1;
		
		user.getListAccounts().toArray(accounts);
		accounts[debitAccount].transfer(accounts[creditAccount]);
	}
	
	protected void add() throws IOException {
		Account[] accounts = new Account[user.getListAccounts().size()];
		user.getListAccounts().toArray(accounts);
		
		int choiceUser;
		int value;
		
		displayListAccountsOfUser();
		
		System.out.println("Quel compte voulez vous cr�diter ?");
		choiceUser = scanner.nextInt() - 1;
		
		System.out.println("Veuillez saisir le montant � cr�diter.");
		value = scanner.nextInt();
		
		accounts[choiceUser].addCash(value);
	}
	
	protected void soustract() throws IOException {
		Account[] accounts = new Account[user.getListAccounts().size()];
		user.getListAccounts().toArray(accounts);
		
		int choiceUser;
		int value;
		
		displayListAccountsOfUser();
		
		System.out.println("Quel compte voulez vous d�biter ?");
		choiceUser = scanner.nextInt() - 1;
		
		System.out.println("Veuillez saisir le montant � d�biter.");
		value = scanner.nextInt();
		
		accounts[choiceUser].soustractCash(value);
	}
	
	protected void displayListAccountsOfUser() {
		Iterator<Account> account = user.getListAccounts().iterator();
		int i = 0;
		while (account.hasNext()) {
			System.out.println((i + 1) + " - " + account.next().getNbAccount());
			i++;
		}
	}
}
