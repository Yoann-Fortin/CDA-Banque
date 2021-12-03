
public class Agency {
	private int agencyCode;
	private String name;
	private String adress;
	
	public Agency(int agencyCode, String name, String adress) {
		this.agencyCode = agencyCode;
		this.name = name;
		this.adress = adress;
	}

	protected int getAgencyCode() {
		return agencyCode;
	}

	protected void setAgencyCode(int agencyCode) {
		this.agencyCode = agencyCode;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getAdress() {
		return adress;
	}

	protected void setAdress(String adress) {
		this.adress = adress;
	}
}
