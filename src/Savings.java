
public class Savings extends Account {
	protected float costs;

	protected Savings(int nbAccount, int agencyCode, User user, float balance, boolean overdraft) {
		super(nbAccount, agencyCode, user, balance, overdraft);
	}

	@Override
	protected void calculMaintenance() {
		super.setBalance(super.getBalance() - (super.getBalance() * costs) + super.getMaintenance());
	}
}
