package src;

import java.util.ArrayList;

import db.DataBaseManager;
import gui.mainMenu;
import src.Klant;

public class main {
	
	public static void main(String[] args) {
		Stichting newStichting = new Stichting("Yoga4Life", 10.00);
		DataBaseManager db = new DataBaseManager();
		mainMenu startMainMenu = new mainMenu(newStichting, db);
		
			
		/*DataBaseManager db = new DataBaseManager();
		Klant testKlant = new Klant("Bertha de Vrouw", "Stationsstraat 22 1071VL", "Amstelveen", "Berthaheeftmail@hotmail.com", "06-2769423", "Bertha is nog 50 euro verschuldigd", 2 );
		testKlant.insertTestData(testKlant);
		Stichting newStichting = new Stichting("Yoga4Life", 510.20);
		String[] newList = newStichting.customerNames(newStichting,db);
		System.out.println(newList[0]);
		*/
	}
	

}
