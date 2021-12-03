import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AdminMenu extends AdvisorMenu {

	public AdminMenu(User user) {
		super(user);
	}

	private ArrayList<String> listAdminActions = new ArrayList<String>();
	
	@Override
	protected ArrayList<String> addActionsInList() {
		listAdminActions.addAll(super.addActionsInList());
		listAdminActions.add("Créer une agence");
		listAdminActions.add("Désactiver un compte");
		listAdminActions.add("Désactiver un client");
		return listAdminActions;
	}
	
	@Override
	protected void displayMenu() {
		addActionsInList();
		for (int i = 0; i < listAdminActions.size(); i++) {
			System.out.println((i + 1) + " - " + listAdminActions.get(i));
		}
		System.out.println(listAdminActions.size() + 1 + " - Quitter l'application" );
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
			createAgency();
			break;
		case 13:
			changeAccountStatus();
			break;
		case 14:
			changeUserStatus();
			break;
		case 15:
			new Application().authentification();
			break;
		default:
			System.out.println("Je n'ai pas compris votre choix");
		}
	}

	private void createAgency() {
		((Admin) user).createAgency();
	}
	
	private void changeAccountStatus() {
		Account account = null;
		((Admin) user).changeAccountStatus(account);
	}
	
	private void changeUserStatus() {
		((Admin) user).changeUserStatus(user);
	}
}
