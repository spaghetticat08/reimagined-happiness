package src;

public abstract class Leverancier {
	private String leverancierNaam;
	private String contactPersoon;
	private String leverancierAdres;
	private String leverancierPlaats;
	private String leverancierEmailadres;
	private String leverancierTelNr;
	private String website;
	private String leverancierOpmerking;
	private int gebruikerNummer;

	public Leverancier(String naam, String contactPersoon, String Adres, String plaats, String emailadres, String telefoonnummer, String website, String opmerking, int gebruikerNummer) {
		// TODO Auto-generated constructor stub
		this.leverancierNaam= naam;
		this.contactPersoon = contactPersoon;
		this.leverancierAdres = Adres;
		this.leverancierPlaats = plaats;
		this.leverancierEmailadres = emailadres;
		this.leverancierTelNr = telefoonnummer;
		this.website = website;
		this.leverancierOpmerking = opmerking;
		this.gebruikerNummer = gebruikerNummer;
	}

}
