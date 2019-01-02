package src;

public class Order {
	private String artikel;
	private int ordernummer;
	private String omschrijving;
	private String datum;
	private  Double prijs;
	private BetalingsMiddel typeBetaling;
	private Status orderStatus;
	private Leverancier leverancierNaam;
	private Klant klantNaam;
	

	public Order(String artikel, int ordernummer, String omschrijving, String datum, Double prijs,BetalingsMiddel typeBetaling, Status orderStatus, Leverancier leverancierNaam, Klant klantNaam ) {
		this.artikel = artikel;
		this.ordernummer = ordernummer;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.prijs = prijs;
		this.typeBetaling = typeBetaling;
		this.orderStatus = orderStatus;
		this.leverancierNaam = leverancierNaam;
		this.klantNaam = klantNaam;
	}
	
	

	
	public int getOrdernummer() {
		return ordernummer;
	}
	
	public String getArtikel() {
		return artikel;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public String getDatum() {
		return datum;
	}
	public Double getPrijs() {
		return prijs;
	}

}
