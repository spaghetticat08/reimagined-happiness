package src;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import Interface.DataBaseInterface;
import db.DataBaseManager;
import gui.mainMenu;
import src.Klant;

public class main {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		StichtingGegevens xmlGegevens = new StichtingGegevens();
		//If we want to reinitialize the xml file
		
		Stichting newStichting = xmlGegevens.readXML("C:\\Users\\Britt\\eclipse-workspace\\ShowCase\\src\\src\\gegevens.xml");
		boolean succes = xmlGegevens.writeXML("C:\\Users\\Britt\\eclipse-workspace\\ShowCase\\src\\src\\gegevens.xml", "YogaYang", "120.25");
		DataBaseInterface db = new DataBaseManager();
		mainMenu startMainMenu = new mainMenu(newStichting, db);
		
		/*StichtingGegevens xmlGegevens = new StichtingGegevens();
		String xml = "C:\\Users\\Britt\\eclipse-workspace\\ShowCase\\src\\src\\gegevens.xml";
		 = xmlGegevens.readXML(xml);
		
		*/
			
		/*DataBaseManager db = new DataBaseManager();
		Klant testKlant = new Klant("Bertha de Vrouw", "Stationsstraat 22 1071VL", "Amstelveen", "Berthaheeftmail@hotmail.com", "06-2769423", "Bertha is nog 50 euro verschuldigd", 2 );
		testKlant.insertTestData(testKlant);
		Stichting newStichting = new Stichting("Yoga4Life", 510.20);
		String[] newList = newStichting.customerNames(newStichting,db);
		System.out.println(newList[0]);
		*/
	}
	

}
