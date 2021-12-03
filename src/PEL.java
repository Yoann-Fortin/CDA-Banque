
public class PEL extends Savings {
	private final float costs = 0.025f;

	public PEL(int nbAccount, int agencyCode, User user, float balance, boolean overdraft) {
		super(nbAccount, agencyCode, user, balance, overdraft);
	}
}
