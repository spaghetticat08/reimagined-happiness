package src;

public class LeverancierBuitenland extends Leverancier {
	private String land;

	public LeverancierBuitenland(String naam, String contactPersoon, String Adres, String plaats, String land, String emailadres,
			String telefoonnummer, String website, String opmerking, int gebruikerNummer) {
		super(naam, contactPersoon, Adres, plaats, emailadres, telefoonnummer, website, opmerking, gebruikerNummer);
		
		this.land = land;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

}
