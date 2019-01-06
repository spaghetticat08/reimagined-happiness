package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Interface.DataBaseInterface;
import db.DataBaseManager;

public class ApplicatieLogica {
	boolean descendingP = true;
	boolean descendingN = true;
	boolean descendingD = true;
	
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
	
	public ArrayList<Order> sortPrices(Stichting newStichting, DataBaseInterface db){
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		if (descendingP == true){
		Collections.sort(orders, new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return o1.getPrijs().compareTo(o2.getPrijs());
			}
		});
		descendingP = false;
		} else {
			Collections.sort(orders, new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					return o2.getPrijs().compareTo(o1.getPrijs());
				}
			});
			descendingP = true;
		}
		return orders;
	}
	
	public ArrayList<Order> sortOrderNumbers(Stichting newStichting, DataBaseInterface db){
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		if(descendingN ==true) {
			Collections.sort(orders, new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					int orderNo1 = o1.getOrdernummer();
					int orderNo2 = o2.getOrdernummer();
					return orderNo1 - orderNo2;
				}
			});
			descendingN = false;
		}else {
			Collections.sort(orders, new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					int orderNo1 = o1.getOrdernummer();
					int orderNo2 = o2.getOrdernummer();
					return orderNo2 - orderNo1;
				}
			});
			descendingN = true;
		}
		return orders;
	}
}
