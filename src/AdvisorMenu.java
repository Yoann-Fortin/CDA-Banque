import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AdvisorMenu extends UserMenu {

	public AdvisorMenu(User user) {
		super(user);
	} 
	
	protected ArrayList<String> listAdvisorActions = new ArrayList<String>();
	
	protected ArrayList<String> addActionsInList() {
		listAdvisorActions.addAll(listUserActions);
		listAdvisorActions.add("Créer un compte");
		listAdvisorActions.add("Créer un client");
		listAdvisorActions.add("Mettre à jour l'adrese d'un client");
		listAdvisorActions.add("Mettre à jour les informations d'un client");
		return listAdvisorActions;
	}
	
	@Override
	protected void displayMenu() {
		addActionsInList();
		for (int i = 0; i < listAdvisorActions.size(); i++) {
			System.out.println((i + 1) + " - " + listAdvisorActions.get(i));
		}
		System.out.println(listAdvisorActions.size() + 1 + " - Quitter l'application" );
		System.out.println();
	}
	
	@Override
	protected void action() throws FileNotFoundException {
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
			createAccount();
			break;
		case 9:
			createUser();
			break;
		case 10:
			changeUserAdress();
			break;
		case 11:
			changeUserInfos();
			break;
		case 12:
			new Application().authentification();
			break;
		default:
			System.out.println("Je n'ai pas compris votre choix");
		}
	}

	protected void createAccount() {
		((Advisor) user).createAccount(user);
	}
	
	protected void createUser() {
		((Advisor) user).createUser();
	}
	
	protected void changeUserAdress() {
		((Advisor) user).changeUserAdress(user);
	}
	
	protected void changeUserInfos() {
		((Advisor) user).changeUserInfos(user);
	}
}
