import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	protected void printTransactions() throws FileNotFoundException {
		String path = System.getProperty("user.dir") + "\\Transactions\\" + nbAccount + ".txt";
		File transaction = new File(path);
		
		if (transaction.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(transaction);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			for (String string : file) {
				System.out.println(string);
			}
		}
	}
	
	protected void printTransactionsText() throws IOException {
		String path = System.getProperty("user.dir") + "\\Transactions\\" + nbAccount + ".txt";
		String copyPath = System.getProperty("user.dir") + nbAccount + ".txt";
		File transaction = new File(path);
		File copy = new File(copyPath);
		
		if (transaction.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(transaction);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			if(copy.exists()) {
				copy.delete();
			}
			copy.createNewFile();
			
			try {
				FileWriter writer = new FileWriter(copy);
				for (String string : file) {
					writer.write(string);
				}
				writer.close();
				
			} catch (Exception e) {
				System.out.println("Une erreur est survenue");
				e.printStackTrace();
			}
		}
	}
	
	protected void transfer(Account account) throws IOException {
		float value;
		System.out.println("Veuillez saisir le montant à tansférer");
		value = scanner.nextFloat();
		
		if (value > balance) {
			System.out.println("Le montant saisi est supérieur à la liquidité disponible sur ce compte");
		} else {
			this.setBalance(this.getBalance() - value);
			writeTransactionOtherAccount(account, value);
			writeOtherAccount(account);
		}
	}

	private void writeOtherAccount(Account account) throws FileNotFoundException, IOException {
		String path = System.getProperty("user.dir") + "\\Accounts\\" + account.getNbAccount() + ".txt";
		File currentAccount = new File(path);
		
		if (currentAccount.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(currentAccount);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			currentAccount.delete();
			currentAccount.createNewFile();
			
			try {
				FileWriter writerAccount = new FileWriter(currentAccount);
				writerAccount.write(file.get(0) + "\n");
				writerAccount.write(file.get(1) + "\n");
				writerAccount.write(file.get(2) + "\n");
				writerAccount.write(file.get(3) + "\n");
				writerAccount.write("Solde: " + account.getBalance() + "\n");
				writerAccount.write(file.get(5) + "\n");
				writerAccount.write(file.get(6) + "\n");
				writerAccount.close();
				
			} catch (Exception e) {
				System.out.println("Une erreur est survenue");
				e.printStackTrace();
			}
		}
	}

	private void writeThisAccount() throws FileNotFoundException, IOException {
		String path = System.getProperty("user.dir") + "\\Accounts\\" + nbAccount + ".txt";
		File currentAccount = new File(path);
		
		if (currentAccount.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(currentAccount);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			currentAccount.delete();
			currentAccount.createNewFile();
			
			try {
				FileWriter writerAccount = new FileWriter(currentAccount);
				writerAccount.write(file.get(0) + "\n");
				writerAccount.write(file.get(1) + "\n");
				writerAccount.write(file.get(2) + "\n");
				writerAccount.write(file.get(3) + "\n");
				writerAccount.write("Solde: " + getBalance() + "\n");
				writerAccount.write(file.get(5) + "\n");
				writerAccount.write(file.get(6) + "\n");
				writerAccount.close();
				
			} catch (Exception e) {
				System.out.println("Une erreur est survenue");
				e.printStackTrace();
			}
		}
	}

	private void writeTransactionOtherAccount(Account account, float value) throws FileNotFoundException, IOException {
		String path = System.getProperty("user.dir") + "\\Transactions\\" + account.getNbAccount() + ".txt";
		File transaction = new File(path);
		
		if (transaction.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(transaction);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			transaction.delete();
			transaction.createNewFile();
			
			try {
				FileWriter writer = new FileWriter(path);
				for (String string : file) {
					writer.write(string + "\n");
				}
				writer.write("Compte créditer de " + value + " depuis le compte N° " + nbAccount + " le " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n");
				writer.close();
				
			} catch (Exception e) {
				System.out.println("Une erreur est survenue");
				e.printStackTrace();
			}
		}
	}

	private void writeTransactionThisAccount(Account account, float value) throws FileNotFoundException, IOException {
		String path = System.getProperty("user.dir") + "\\Transactions\\" + nbAccount + ".txt";
		File transaction = new File(path);
		
		if (transaction.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(transaction);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			transaction.delete();
			transaction.createNewFile();
			
			try {
				FileWriter writer = new FileWriter(path);
				for (String string : file) {
					writer.write(string + "\n");
				}
				writer.write("Transfert de " + value + " vers le compte N° " + account.getNbAccount() + " le " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n");
				writer.close();
				
			} catch (Exception e) {
				System.out.println("Une erreur est survenue");
				e.printStackTrace();
			}
		}
	}
	
	protected void addCash(float value) throws IOException {
		setBalance(getBalance() + value);transactions.add("Dépôt de " + value + " euros le ");
		writeAddTransaction(value);
		writeThisAccount();
	}

	private void writeAddTransaction(float value) throws FileNotFoundException, IOException {
		String path = System.getProperty("user.dir") + "\\Transactions\\" + nbAccount + ".txt";
		File transaction = new File(path);
		
		if (transaction.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(transaction);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			transaction.delete();
			transaction.createNewFile();
			
			try {
				FileWriter writer = new FileWriter(path);
				for (String string : file) {
					writer.write(string + "\n");
				}
				writer.write("Dépôt de " + value + " euros le " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n");
				writer.close();
				
			} catch (Exception e) {
				System.out.println("Une erreur est survenue");
				e.printStackTrace();
			}
		}
	}
	
	protected void creditCash(Account account, float value) throws FileNotFoundException, IOException {
		setBalance(getBalance() + value);transactions.add("Compte crédité de " + value + " euros depuis le compte N° " + account.getNbAccount() + " le " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
		writeTransactionThisAccount(account, value);
		writeThisAccount();
	}
	
	protected void soustractCash(float value) throws IOException {
		setBalance(getBalance() - value);
		writeSoustractTransaction(value);
	}

	private void writeSoustractTransaction(float value) throws FileNotFoundException, IOException {
		String path = System.getProperty("user.dir") + "\\Transactions\\" + nbAccount + ".txt";
		File transaction = new File(path);
		
		if (transaction.exists()) {
			ArrayList<String> file = new ArrayList<String>();
			Scanner scan = new Scanner(transaction);
				
			while(scan.hasNextLine()) {
				file.add(scan.nextLine()); 
			}
			scan.close();
			
			transaction.delete();
			transaction.createNewFile();
			
			try {
				FileWriter writer = new FileWriter(path);
				for (String string : file) {
					writer.write(string + "\n");
				}
				writer.write("Retrait de " + value + " euros le " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n");
				writer.close();
				
			} catch (Exception e) {
				System.out.println("Une erreur est survenue");
				e.printStackTrace();
			}
		}
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
