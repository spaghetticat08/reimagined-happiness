package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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
	
	public String getStichtingNaam() {
		return stichtingNaam;
	}
	
	public Double getBalans() {
		return balans;
	}
	public boolean setBalans(Double balans, Stichting newStichting) {
		this.balans = balans;
		StichtingGegevens updateGegevens = new StichtingGegevens();
		String xml = "C:\\Users\\Britt\\eclipse-workspace\\ShowCase\\src\\src\\gegevens.xml";
		String stichtingNaam = newStichting.getStichtingNaam();
		boolean succes = updateGegevens.writeXML(xml, stichtingNaam, Double.toString(balans));
		if (succes==true) 
			{return succes;
				}
		return false;
		
	}
	public ArrayList<Klant> getKlanten(Stichting newStichting, DataBaseInterface db) {
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
		newStichting.klanten = getKlanten(newStichting, db);
		newStichting.leveranciers = getLeveranciers(newStichting, db);
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
						null,
						null,
						rsOrders.getInt("gebGebruikerNummer"));
				for (Klant k: klanten ) {
					if(k.getGebruikerNummer() == tempOrder.getGebruikerNummer()) {
						tempOrder.setKlantNaam(k);
					}
				}
				for (Leverancier l:leveranciers) {
					if (l.getGebruikerNummer()== tempOrder.getGebruikerNummer()) {
						tempOrder.setLeverancierNaam(l);
					}
				}
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
	
	public double calculateNewBalance(Double prijs, Stichting newStichting) {
		Double newBalans;
		newBalans = balans + prijs;
		newStichting.setBalans(newBalans, newStichting);
		return newBalans;
		
	}



}
