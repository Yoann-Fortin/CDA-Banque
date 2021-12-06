
public class LivretA extends Savings {
	private float costs = 0.1f;

	public LivretA(int nbAccount, int agencyCode, User user, float balance, boolean overdraft) {
		super(nbAccount, agencyCode, user, balance, overdraft);
	}

	public float getCosts() {
		return costs;
	}
}
