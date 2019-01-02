package src;

public class LeverancierNL extends Leverancier {

	private int kvkNummer;
	
	public LeverancierNL(String naam, String contactPersoon, String Adres, String plaats, String emailadres, String telefoonnummer, String website,
			String opmerking, int gebruikerNummer, int kvkNummer) {
		super(naam, contactPersoon, Adres, plaats, emailadres, telefoonnummer, website, opmerking, gebruikerNummer);
		this.kvkNummer = kvkNummer;
	}

}
