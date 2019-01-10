package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;
import Interface.DataBaseInterface;
import src.ApplicatieLogica;
import src.Klant;
import src.Stichting;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CustomerList2 {
	private Text textNaam;
	private Text textAdres;
	private Text textPlaats;
	private Text textEmail;
	private Text textTelefoonNr;
	private Text textGebNr;
	private Text textOpmerkingen;
	String filter = null;
	
	private ArrayList<Klant> klantinfo;
	ApplicatieLogica newLogic = new ApplicatieLogica();
	
	Stichting newStichting;
	DataBaseInterface db;
	private List customerList;
	private Text textSearch;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public CustomerList2(Stichting newStichting, DataBaseInterface db) {
		this.newStichting = newStichting;
		this.db = db;
		Display display = Display.getDefault();
		Shell customerShell2 = new Shell();
		customerShell2.setSize(845, 603);
		customerShell2.setText("SWT Application");
		customerShell2.setLayout(null);

		
		Menu menu = new Menu(customerShell2, SWT.BAR);
		customerShell2.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Navigeer naar...");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmAdministratieoverzicht = new MenuItem(menu_1, SWT.RADIO);
		mntmAdministratieoverzicht.setText("Administratie-overzicht");
		mntmAdministratieoverzicht.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				customerShell2.dispose();
				addOrder startOrderMenu = new addOrder(newStichting, db);
			}
		});
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton.setText("Leverancieroverzicht");
		mntmNewRadiobutton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				customerShell2.dispose();
				SupplierList startSupplierMenu = new SupplierList(newStichting, db);
			}
		});
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton_1.setSelection(true);
		mntmNewRadiobutton_1.setText("Klantenoverzicht");
		
		
		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("Actie...");
		
		Menu menu_2 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_2);
		
		MenuItem mntmToevoegen = new MenuItem(menu_2, SWT.NONE);
		mntmToevoegen.setText("Toevoegen...");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmVerwijderen = new MenuItem(menu_2, SWT.NONE);
		mntmVerwijderen.setText("Verwijderen...");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmAanpassen = new MenuItem(menu_2, SWT.NONE);
		mntmAanpassen.setText("Aanpassen...");
		
		//Make list
		
		customerList = new List(customerShell2, SWT.BORDER | SWT.V_SCROLL);
		customerList.setBounds(10, 36, 246, 478);
		
		String[] listOfCustomerNames = getCustomerNames(null);
		customerList.setItems(listOfCustomerNames);
		customerList.addListener(SWT.Selection, event -> onListItemSelect(customerList, filter));

		Label label = new Label(customerShell2, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(317, 13, 15, 534);
				
		Label lblNewLabel = new Label(customerShell2, SWT.NONE);
		lblNewLabel.setBounds(338, 40, 55, 15);
		lblNewLabel.setText("Naam");
		
		textNaam = new Text(customerShell2, SWT.BORDER);
		textNaam.setBounds(454, 37, 144, 21);
		
		Label lblNewLabel_1 = new Label(customerShell2, SWT.NONE);
		lblNewLabel_1.setBounds(338, 67, 55, 15);
		lblNewLabel_1.setText("Adres");
		
		textAdres = new Text(customerShell2, SWT.BORDER);
		textAdres.setBounds(454, 64, 205, 21);
		
		Label lblNewLabel_2 = new Label(customerShell2, SWT.NONE);
		lblNewLabel_2.setBounds(338, 104, 55, 15);
		lblNewLabel_2.setText("Plaats");
		
		Label lblEmailadres = new Label(customerShell2, SWT.NONE);
		lblEmailadres.setBounds(338, 141, 76, 15);
		lblEmailadres.setText("E-mailadres");
		
		textPlaats = new Text(customerShell2, SWT.BORDER);
		textPlaats.setBounds(454, 101, 76, 21);
		
		textEmail = new Text(customerShell2, SWT.BORDER);
		textEmail.setBounds(454, 138, 144, 21);
		
		Label lblTelefoonnummer = new Label(customerShell2, SWT.NONE);
		lblTelefoonnummer.setBounds(338, 184, 110, 15);
		lblTelefoonnummer.setText("Telefoonnummer");
		
		textTelefoonNr = new Text(customerShell2, SWT.BORDER);
		textTelefoonNr.setBounds(454, 181, 144, 21);
		
		Label lblOpmerkingen = new Label(customerShell2, SWT.NONE);
		lblOpmerkingen.setBounds(338, 236, 88, 15);
		lblOpmerkingen.setText("Opmerkingen");
		
		textOpmerkingen = new Text(customerShell2, SWT.BORDER);
		textOpmerkingen.setBounds(454, 233, 205, 160);
		
		Button btnOpslaan = new Button(customerShell2, SWT.NONE);
		btnOpslaan.setBounds(442, 489, 101, 25);
		btnOpslaan.setText("Opslaan");
		btnOpslaan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				//TODO: change selected information
			}
		});
		
		Button btnToevoegen = new Button(customerShell2, SWT.NONE);	
		btnToevoegen.setBounds(338, 489, 101, 25);
		btnToevoegen.setText("Toevoegen...");
		btnToevoegen.addListener(SWT.Selection, new Listener(){
			@Override
			public void handleEvent(Event e) {
				addCustomer();
			}
		});
		
		Button btnVerwijderen = new Button(customerShell2, SWT.NONE);
		btnVerwijderen.setText("Verwijderen");
		btnVerwijderen.setBounds(546, 489, 101, 25);
		
		textGebNr = new Text(customerShell2, SWT.BORDER);
		textGebNr.setEditable(false);
		textGebNr.setBounds(454, 426, 144, 21);
		
		Label lblGebruikernummer = new Label(customerShell2, SWT.NONE);
		lblGebruikernummer.setText("Gebruikernummer");
		lblGebruikernummer.setBounds(338, 429, 101, 25);
		
		textSearch = new Text(customerShell2, SWT.BORDER);
		textSearch.setBounds(10, 10, 165, 21);
		
		Button btnSearch = new Button(customerShell2, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filter = textSearch.getText();
				String[] resultOfSearch = getCustomerNames(filter);
				customerList.setItems(resultOfSearch);
			}
		});
		btnSearch.setBounds(181, 10, 75, 22);
		btnSearch.setText("Zoeken...");
		
		Button btnClearSearch = new Button(customerShell2, SWT.NONE);
		btnClearSearch.setBounds(262, 10, 36, 21);
		btnClearSearch.setText("X");
		btnClearSearch.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
			textSearch.setText("");
			filter = null;
			String[] listOfCustomerNames = getCustomerNames(null);
			customerList.setItems(listOfCustomerNames);
			}
		});
		
		
		btnVerwijderen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				//TODO: make pop up for verification
				deleteCustomer();
				
			}
		});
		
		/*Button btnLeegmaken = new Button(customerShell2, SWT.NONE);
		btnLeegmaken.setText("Leegmaken");
		btnLeegmaken.setBounds(607, 489, 101, 25);
		btnLeegmaken.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				//TODO: clear all boxes from text
			}
		});
		*/
		
		customerShell2.open();
		customerShell2.layout();
		while (!customerShell2.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public String[] getCustomerNames(String filter) {
		//create a query to get the whole list of customers and return only the names
		String[] listOfCustomerNames = newLogic.getCustomerNames(newStichting, db, filter);
		return listOfCustomerNames;
	}
	
	public void onListItemSelect(List customerList, String filter) {
		int indexNo = customerList.getFocusIndex();
		Klant infoKlant = newLogic.getKlantInfo(indexNo, newStichting, db, filter);
		textNaam.setText(infoKlant.getKlantNaam());
		textAdres.setText(infoKlant.getKlantAdres());
		textPlaats.setText(infoKlant.getKlantPlaats());
		textEmail.setText(infoKlant.getKlantEmailadres());
		textTelefoonNr.setText(infoKlant.getKlantTelefoonnummer());
		textOpmerkingen.setText(infoKlant.getKlantOpmerking());
		textGebNr.setText(Integer.toString(infoKlant.getGebruikerNummer()));
	}
	
	//TODO: make checks for required fields and auto-generate GebruikerNummer
	public void addCustomer() {
		String klantNaam = textNaam.getText();
		String klantAdres = textAdres.getText();
		String klantPlaats = textPlaats.getText();
		String klantEmail = textEmail.getText();
		String klantTelefoonNr = textTelefoonNr.getText();
		String klantOpmerking = textOpmerkingen.getText();
		
		newLogic.insertKlant(db, klantNaam, klantAdres, klantPlaats, klantEmail, klantTelefoonNr, klantOpmerking);
		String[] listOfCustomerNames = getCustomerNames(null);
		customerList.setItems(listOfCustomerNames);	
		}
	
	public void deleteCustomer() {
		int indexNo = customerList.getFocusIndex();
		newLogic.prepKlantForDelete(indexNo, newStichting, db);
		customerList.removeAll();
		//TODO: maybe make a seperate function for reloading the list as this happens throughout several times
		String[] listOfCustomerNames = getCustomerNames(null);
		customerList.setItems(listOfCustomerNames);	
	}
	
	}