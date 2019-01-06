package Interface;

import java.sql.ResultSet;

import db.DataBaseManager;
import src.Klant;
import src.Leverancier;
import src.Order;

public interface DataBaseInterface {
	public void createDatabase();
	
	void insertKlant(Klant newKlant);
	void insertLeverancier(Leverancier newLev);
	void insertOrder(Order newOrder);
	
	void deleteKlant(Klant deletedKlant);
	void deleteLeverancier(Leverancier deletedLev);
	void deleteOrder(Order deletedOrder);
	
	ResultSet getOrders();
	ResultSet getLeveranciers();
	ResultSet getCustomers();
	
}
