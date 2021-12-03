import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		User firstUser = new Admin("007", "Bond", "James", "unknowm", "unknown", "james.bond@secretAgent.uk", null, null);
		Account firstAccount = new CurrentAccount(123, 12, firstUser, 1000, false);
		Account secondAccount = new LivretA(1234, 12, firstUser, 500, false);
		
		firstAccount.addCash(100f);
		firstAccount.soustractCash(150f);
//		
		firstUser.setListAccounts(firstAccount);
		firstUser.setListAccounts(secondAccount);
//		
//		firstUser.checkAccounts();
//		firstAccount.transfer(secondAccount);
//		firstUser.checkAccounts();
//		
//		firstAccount.printTransactions();
		
		Application userMenu = new Application();
		userMenu.displayMenu(firstUser);
	}
}
