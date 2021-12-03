import java.util.ArrayList;
import java.util.Scanner;

public abstract class Account {
	private int nbAccount;
	private int agencyCode;
	private User user;
	private float balance;
	private boolean overdraft;
	private ArrayList<String> transactions = new ArrayList<String>();
	private float maintenance = 25f;
	private boolean isActive = true;
	
	private Scanner scanner = new Scanner(System.in);
	
	protected Account (int nbAccount, int agencyCode, User user, float balance, boolean overdraft) {
		this.nbAccount = nbAccount;
		this.agencyCode = agencyCode;
		this.user = user;
		this.balance = balance;
		this.overdraft = overdraft;
	}
	
	protected void print() {
		System.out.println(nbAccount + "\t" + balance + "\t\t" + emoji());
	}
	
	protected void printTransactions() {
		for (String transaction : transactions) {
			System.out.println(transaction);
		}
	}
	
	protected void transfer(Account account) {
		float value;
		System.out.println("Veuillez saisir le montant à tansférer");
		value = scanner.nextFloat();
		
		if (value > balance) {
			System.out.println("Le montant saisi est supérieur à la liquidité disponible sur ce compte");
		} else {
			account.setBalance(account.getBalance() + value);
			this.setBalance(this.getBalance() - value);
			transactions.add("Transfert de " + value + " vers le compte N° " + account.getNbAccount() + " le ");
		}
	}
	
	protected void addCash(float value) {
		setBalance(getBalance() + value);
		transactions.add("Dépot de " + value + " euros le ");
	}
	
	protected void creditCash(Account account, float value) {
		setBalance(getBalance() + value);
		transactions.add("Compte crédité de " + value + " euros depuis le compte N° " + account.getNbAccount() + " le ");
	}
	
	protected void soustractCash(float value) {
		setBalance(getBalance() - value);
		transactions.add("Retrait de " + value + " euros le ");
	}
	
	protected String emoji() {
		if (balance > 0) {
			return ":-)";
		} else if (balance < 0) {
			return ":-(";
		} else {
			return ":-|";
		}
	}
	
	protected void calculMaintenance() {
		balance -= maintenance;
	}

	protected int getNbAccount() {
		return nbAccount;
	}

	protected void setNbAccount(int nbAccount) {
		this.nbAccount = nbAccount;
	}

	protected int getAgencyCode() {
		return agencyCode;
	}

	protected void setAgencyCode(int agencyCode) {
		this.agencyCode = agencyCode;
	}

	protected float getBalance() {
		return balance;
	}

	protected void setBalance(float balance) {
		this.balance = balance;
	}

	protected boolean isOverdraft() {
		return overdraft;
	}

	protected void setOverdraft(boolean overdraft) {
		this.overdraft = overdraft;
	}

	protected float getMaintenance() {
		return maintenance;
	}

	protected void setMaintenance(float maintenance) {
		this.maintenance = maintenance;
	}

	public User getUser() {
		return user;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}	
