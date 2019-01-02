package src;

public class LeverancierBuitenland extends Leverancier {
	private String land;

	public LeverancierBuitenland(String naam, String contactPersoon, String Adres, String plaats, String emailadres,
			String telefoonnummer, String website, String opmerking, int gebruikerNummer, String land) {
		super(naam, contactPersoon, Adres, plaats, emailadres, telefoonnummer, website, opmerking, gebruikerNummer);
		
		this.land = land;
	}

}
