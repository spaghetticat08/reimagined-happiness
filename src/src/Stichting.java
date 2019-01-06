package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DataBaseManager;
import Interface.DataBaseInterface;

public class Stichting {
	private String stichtingNaam;
	private Double balans;
	private ArrayList<Klant> klanten = new ArrayList();
	private ArrayList<Leverancier> leveranciers= new ArrayList();
	private ArrayList<Order> orders = new ArrayList();
	DataBaseInterface db;
	
	Stichting(String stichtingNaam, double balans) {
		this.stichtingNaam = stichtingNaam;
		this.balans = balans;
	}
	
	public Stichting() {}
	
	public String[] customerNames(Stichting newStichting, DataBaseInterface db){
		this.db = db;
		newStichting.getKlanten(newStichting, db);
		int arraySize = klanten.size();
		String[] listOfCustomerNames = new String[klanten.size()];
		int i=0;
		for (Klant klant: klanten) {
			listOfCustomerNames[i] = klant.getKlantNaam();
			i++;
		}
		return listOfCustomerNames;
	}
	
	public String[] leverancierNames(Stichting newStichting, DataBaseInterface db) {
		newStichting.getLeveranciers(newStichting, db);
		int arraySize = leveranciers.size();
		String[] listOfLeverancierNames = new String[leveranciers.size()];
		int i=0;
		for (Leverancier leverancier:leveranciers) {
			listOfLeverancierNames[i] = leverancier.getLeverancierNaam();
			i++;
		}	
		return listOfLeverancierNames;
	}
	
	public String getStichtingNaam() {
		return stichtingNaam;
	}
	
	public Double getBalans() {
		return balans;
	}
	public void setBalans(Double balans, Stichting newStichting) {
		this.balans = balans;
		StichtingGegevens updateGegevens = new StichtingGegevens();
		String xml = "C:\\Users\\Britt\\eclipse-workspace\\ShowCase\\src\\src\\gegevens.xml";
		String stichtingNaam = newStichting.getStichtingNaam();
		updateGegevens.saveToXML(xml, stichtingNaam, Double.toString(balans));
		
	}
	public ArrayList<Klant> getKlanten(Stichting newStichting, DataBaseInterface db) {
		//ArrayList<Klant> klanten = new ArrayList();
		newStichting.klanten = new ArrayList<Klant>();
		ResultSet rsCustomers=db.getCustomers();	
		
		try {
			while (rsCustomers.next()) {
				Klant tempKlant = new Klant(
						rsCustomers.getString("naam"), 
						rsCustomers.getString("adres"), 
						rsCustomers.getString("plaats"), 
						rsCustomers.getString("emailadres"), 
						rsCustomers.getString("telefoonnummer"), 
						rsCustomers.getString("opmerkingen"), 
						rsCustomers.getInt("gebruikerNummer"));
				newStichting.klanten.add(tempKlant);
				//klanten.add(tempKlant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return klanten;
	}	
	public void setKlanten(ArrayList<Klant> klanten) {
		this.klanten = klanten;
	}
	
	public ArrayList<Leverancier> getLeveranciers(Stichting newStichting, DataBaseInterface db) {
	    //ArrayList<Klant> klanten = new ArrayList();
		newStichting.leveranciers = new ArrayList<Leverancier>();
		ResultSet rsLeveranciers=db.getLeveranciers();			
			try {
				while (rsLeveranciers.next()) {
					Leverancier tempLeverancier = new Leverancier(
						rsLeveranciers.getString("naam"), 
						rsLeveranciers.getString("contactPersoon"), 
						rsLeveranciers.getString("adres"), 
						rsLeveranciers.getString("plaats"),
						rsLeveranciers.getString("land"),
						rsLeveranciers.getString("emailadres"), 
						rsLeveranciers.getString("telefoonnummer"), 
						rsLeveranciers.getString("website"), 
						rsLeveranciers.getString("opmerkingen"), 
						rsLeveranciers.getInt("gebruikerNummer"));
					newStichting.leveranciers.add(tempLeverancier);
						//klanten.add(tempKlant);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		return leveranciers;
	}
	public void setLeveranciers(ArrayList<Leverancier> leveranciers) {
		this.leveranciers = leveranciers;
	}
	public ArrayList<Order> getOrders(Stichting newStichting, DataBaseInterface db) {
		newStichting.orders = new ArrayList<Order>();
		ResultSet rsOrders = db.getOrders();
		
		try {
			while (rsOrders.next()) {
				Order tempOrder = new Order(
						rsOrders.getString("artikel"),
						rsOrders.getInt("orderNummer"),
						rsOrders.getString("omschrijving"),
						rsOrders.getString("datum"),
						rsOrders.getDouble("prijs"),
						BetalingsMiddel.valueOf(rsOrders.getString("typeBetaling")),
						Status.valueOf(rsOrders.getString("orderStatus")),
						rsOrders.getInt("gebGebruikerNummer"));
					newStichting.orders.add(tempOrder);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	};
	
	public double calculateNewBalance(Order newOrder, Stichting newStichting) {
		Double newBalans;
		newBalans = balans + newOrder.getPrijs();
		newStichting.setBalans(newBalans, newStichting);
		//setBalans(newBalans);
		return newBalans;
		
	}
	
	/*public ArrayList<String> getAllCustomerNames(Stichting newStichting, DataBaseManager db) {
		ResultSet rs=null;
		ArrayList<Klant> klanten = new ArrayList<>();
		
		// give the databasemanager the task to request query
		rs = DataBaseManager.getListOfCustomers();
		
		try {
			while (rs.next()) {
				Klant tempKlant = new Klant(rs.getString("naam"), rs.getString("adres"), rs.getString("plaats"), rs.getString("emailadres"), rs.getString("telefoonnummer"), rs.getString("opmerkingen"), rs.getInt("gebruikerNummer"), typeGebruiker.valueOf(rs.getString("typeGebruiker")));
			}
			
		} catch(Exception e) {}
		String[] customerNames = new String[klanten.size()];
		
		for(int i=0; i<klanten.size();i++) {
			customerNames[i] = klanten.get(0);
					
		}
		return customerNames;
}
	*/
}
