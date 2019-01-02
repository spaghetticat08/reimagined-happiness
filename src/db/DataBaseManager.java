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
	
	public void insertData(Klant objKlant) {
		try {
			InsertDataDB tempDataObj = new InsertDataDB();
			tempDataObj.createInsertQuery(objKlant);
		} catch (Exception e) {
			
		}
	}
	
	public void getOrders() {
		try {
			
			
		} catch (Exception e) {}
		
	}
	public void getSuppliers() {
		try {
			QueriesDB getSupplierQuery = new QueriesDB();
			getSupplierQuery.getAllSuppliers(getSupplierQuery);
			
		} catch (Exception e) {}
		
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
		
	
	
	
	
	
	


