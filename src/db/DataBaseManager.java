package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLEngineResult.Status;

import Interface.DataBaseInterface;
import src.BetalingsMiddel;
import src.Klant;
import src.Leverancier;
import src.Order;

public class DataBaseManager implements DataBaseInterface {
	
	 final String JDBC_DRIVER	= "org.h2.Driver";
	 final String DB_URL = "jdbc:h2:~/ShowCaseDB";
	//DB credentials
	 final String USER = "sa";
	 final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	
	
	public DataBaseManager() {	
	}
	
	public void createDatabase() {
		try {
			CreateDB newDB = new CreateDB();
			newDB.createNewDB(newDB);
			
		} catch (Exception e) {
		}
	}
	
	/*temporary method to test db
	 * public void insertData(Klant objKlant) {
		try {
			InsertDataDB tempDataObj = new InsertDataDB();
			tempDataObj.createInsertQuery(objKlant);
		} catch (Exception e) {	
		}
	}*/
	
	public void insertKlant(Klant newKlant) {
		InsertDataDB insertKlantDb = new InsertDataDB();
		insertKlantDb.createInsertKlant(newKlant);
	}
	
	public void insertLeverancier(Leverancier newLev) {
		InsertDataDB insertLevDb = new InsertDataDB();
		insertLevDb.createInsertLev(newLev);
	}
	
	public void insertOrder(Order newOrder) {
		InsertDataDB insertOrder = new InsertDataDB();
		insertOrder.createInsertOrder(newOrder);
	}
	
	public ResultSet getOrders() {
		ResultSet rsOrders = null;
		QueriesDB orderQuery = new QueriesDB();
		rsOrders = orderQuery.getAllOrders();
		
		return rsOrders;
		
	}
	public ResultSet getLeveranciers() {
		ResultSet rsLeveranciers = null;
		QueriesDB LeverancierQuery = new QueriesDB();
		rsLeveranciers = LeverancierQuery.getAllLeveranciers();
		
		return rsLeveranciers;
	}
	
	public ResultSet getCustomers() {
		ResultSet rsCustomers = null;
		QueriesDB getCustomerQuery = new QueriesDB();
		rsCustomers= getCustomerQuery.getAllCustomers();
		
		/*try {
			while (rsCustomers.next()) {
				System.out.println(rsCustomers.getString("naam"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return rsCustomers;	
	}

	
	}
		
	
	
	
	
	
	


