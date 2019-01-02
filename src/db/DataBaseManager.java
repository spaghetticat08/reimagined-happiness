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

import src.BetalingsMiddel;
import src.Klant;
import src.Leverancier;
import src.LeverancierBuitenland;
import src.LeverancierNL;
import src.Order;

public class DataBaseManager {
	
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
	
	public void insertLevNL(LeverancierNL newLev) {
		InsertDataDB insertLevNLDb = new InsertDataDB();
		insertLevNLDb.createInsertNLLev(newLev);
	}
	
	public void insertLevBL(LeverancierBuitenland newLev) {
		InsertDataDB insertLevBLDb = new InsertDataDB();
		insertLevBLDb.createInsertLev(newLev);
	}
	
	public void getOrders() {
		try {
			
			
		} catch (Exception e) {}
		
	}
	public ResultSet getLeveranciers(DataBaseManager db) {
		ResultSet rsLeveranciers = null;
		QueriesDB getLeverancierQuery = new QueriesDB();
		rsLeveranciers = getLeverancierQuery.getAllLeveranciers(getLeverancierQuery);
		
		return rsLeveranciers;
	}
	
	public ResultSet getCustomers(DataBaseManager db) {
		ResultSet rsCustomers = null;
		QueriesDB getCustomerQuery = new QueriesDB();
		rsCustomers= getCustomerQuery.getAllCustomers(getCustomerQuery);
		
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
		
	
	
	
	
	
	


