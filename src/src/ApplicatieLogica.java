package src;

import java.util.ArrayList;

import Interface.DataBaseInterface;
import db.DataBaseManager;

public class ApplicatieLogica {
	public void insertKlant(DataBaseInterface db, String klantNaam, String klantAdres, String klantPlaats, String klantEmail, String klantTelefoonNr, String klantOpmerking) {
		Klant newKlant = new Klant(klantNaam, klantAdres, klantPlaats, klantEmail, klantTelefoonNr, klantOpmerking);	
		db.insertKlant(newKlant);
	}
	
	public void insertLeverancier(DataBaseInterface db, String levNaam, String contactPersoon, String levAdres, String levPlaats, String land, String levEmail, String LevTelefoonNr, String website, String levOpmerking) {
		Leverancier newLev = new Leverancier(levNaam, contactPersoon, levAdres, levPlaats, land, levEmail, LevTelefoonNr, website, levOpmerking);
		db.insertLeverancier(newLev);
	}
	
	public void disposeAndCreateDB() {
		DataBaseInterface resetDB = new DataBaseManager();
		resetDB.createDatabase();
	}
	
	public Order getOrderInfo(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		Order infoOrder = orders.get(indexNo);
		return infoOrder;
	}

	public Klant getKlantInfo(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Klant> klanten = newStichting.getKlanten(newStichting, db);
		Klant infoKlant = klanten.get(indexNo);	
		return infoKlant;
	}
	
	public Leverancier getLevInfo(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Leverancier> leveranciers = newStichting.getLeveranciers(newStichting, db);
		Leverancier infoLev = leveranciers.get(indexNo);	
		return infoLev;
	}
	
	public void prepKlantForDelete(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Klant> klanten = newStichting.getKlanten(newStichting, db);
		Klant deletedKlant = klanten.get(indexNo);
		db.deleteKlant(deletedKlant);
	}
	
	public void prepLevForDelete(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Leverancier> leveranciers = newStichting.getLeveranciers(newStichting, db);
		Leverancier deletedLev = leveranciers.get(indexNo);
		db.deleteLeverancier(deletedLev);
	}
	
	public void prepOrderForDelete(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		Order deletedOrder = orders.get(indexNo);
		db.deleteOrder(deletedOrder);
	}
	

}
