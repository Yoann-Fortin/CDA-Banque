import java.util.HashSet;

public abstract class User {
	private String identifier;
	private String lastName;
	private String firstName;
	private String birthDate;
	private String adress;
	private String email;
	private String login;
	private String password;
	private boolean isActive = true;
	private HashSet<Account> listAccounts = new HashSet<Account>();
	
	public User(String identifier, String lastName, String firstName, String birthDate, String adress, String email, String login, String password) {
		this.identifier = identifier;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.adress = adress;
		this.email = email;
		this.login = login;
		this.password = password;
	}
	
	protected void checkAccounts() {
		for (Account account : listAccounts) {
			account.print();
		}
	}
	
	protected String getIdentifier() {
		return identifier;
	}

	protected void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	protected String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	protected String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	protected String getBirthDate() {
		return birthDate;
	}

	protected void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	protected String getAdress() {
		return adress;
	}

	protected void setAdress(String adress) {
		this.adress = adress;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected String getLogin() {
		return login;
	}

	protected void setLogin(String login) {
		this.login = login;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}
	
	protected HashSet<Account> getListAccounts() {
		return listAccounts;
	}
	
	protected void setListAccounts(Account account) {
		this.listAccounts.add(account);
	}

	protected boolean isActive() {
		return isActive;
	}

	protected void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}