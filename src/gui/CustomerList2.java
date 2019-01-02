package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import db.DataBaseManager;
import src.Klant;
import src.Stichting;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class CustomerList2 {
	private Text textNaam;
	private Text textAdres;
	private Text textPlaats;
	private Text textEmail;
	private Text textTelefoonNr;
	private Text TextOpmerkingen;
	
	Stichting newStichting;
	DataBaseManager db;
	private List customerList;
	/**
	 * Launch the application.
	 * @param args
	 */
	public CustomerList2(Stichting newStichting, DataBaseManager db) {
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
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton.setText("Leveranciers");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton_1.setText("Klanten");
		
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
		
		customerList = new List(customerShell2, SWT.BORDER);
		
		customerList.setBounds(10, 36, 246, 478);
		
		String[] listOfCustomerNames = getCustomerNames();
		customerList.setItems(listOfCustomerNames);
		
		customerList.addMouseListener(new MouseAdapter() {
			public void showCustomerInformation(MouseEvent e) {
			}
		});
		
		Label label = new Label(customerShell2, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(262, 0, 15, 534);
				
		Label lblNewLabel = new Label(customerShell2, SWT.NONE);
		lblNewLabel.setBounds(283, 40, 55, 15);
		lblNewLabel.setText("Naam");
		
		textNaam = new Text(customerShell2, SWT.BORDER);
		textNaam.setBounds(393, 37, 144, 21);
		
		Label lblNewLabel_1 = new Label(customerShell2, SWT.NONE);
		lblNewLabel_1.setBounds(283, 72, 55, 15);
		lblNewLabel_1.setText("Adres");
		
		textAdres = new Text(customerShell2, SWT.BORDER);
		textAdres.setBounds(393, 69, 205, 21);
		
		Label lblNewLabel_2 = new Label(customerShell2, SWT.NONE);
		lblNewLabel_2.setBounds(283, 104, 55, 15);
		lblNewLabel_2.setText("Plaats");
		
		Label lblEmailadres = new Label(customerShell2, SWT.NONE);
		lblEmailadres.setBounds(283, 141, 76, 15);
		lblEmailadres.setText("E-mailadres");
		
		textPlaats = new Text(customerShell2, SWT.BORDER);
		textPlaats.setBounds(393, 101, 76, 21);
		
		textEmail = new Text(customerShell2, SWT.BORDER);
		textEmail.setBounds(393, 138, 144, 21);
		
		Label lblTelefoonnummer = new Label(customerShell2, SWT.NONE);
		lblTelefoonnummer.setBounds(283, 184, 110, 15);
		lblTelefoonnummer.setText("Telefoonnummer");
		
		textTelefoonNr = new Text(customerShell2, SWT.BORDER);
		textTelefoonNr.setBounds(393, 181, 144, 21);
		
		Label lblOpmerkingen = new Label(customerShell2, SWT.NONE);
		lblOpmerkingen.setBounds(283, 236, 88, 15);
		lblOpmerkingen.setText("Opmerkingen");
		
		TextOpmerkingen = new Text(customerShell2, SWT.BORDER);
		TextOpmerkingen.setBounds(393, 233, 205, 160);
		
		CLabel lblFoto = new CLabel(customerShell2, SWT.NONE);
		lblFoto.setBounds(604, 181, 133, 122);
		lblFoto.setText("Foto");
		
		Button btnOpslaan = new Button(customerShell2, SWT.NONE);
		btnOpslaan.setBounds(393, 489, 101, 25);
		btnOpslaan.setText("Opslaan");
		btnOpslaan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				//TODO: change selected information
			}
		});
		
		Button btnToevoegen = new Button(customerShell2, SWT.NONE);	
		btnToevoegen.setBounds(283, 489, 101, 25);
		btnToevoegen.setText("Toevoegen...");
		btnToevoegen.addListener(SWT.Selection, new Listener(){
			@Override
			public void handleEvent(Event e) {
				addCustomer();
			}
		});
		
		Button btnVerwijderen = new Button(customerShell2, SWT.NONE);
		btnVerwijderen.setText("Verwijderen");
		btnVerwijderen.setBounds(500, 489, 101, 25);
		btnVerwijderen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				//TODO: remove selected from database
			}
		});
		
		Button btnLeegmaken = new Button(customerShell2, SWT.NONE);
		btnLeegmaken.setText("Leegmaken");
		btnLeegmaken.setBounds(607, 489, 101, 25);
		btnLeegmaken.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				//TODO: clear all boxes from text
			}
		});
		
		customerShell2.open();
		customerShell2.layout();
		while (!customerShell2.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public String[] getCustomerNames() {
		//create a query to get the whole list of customers and return only the names
		String[] listOfCustomerNames = newStichting.customerNames(newStichting, db);
		return listOfCustomerNames;
	}
	
	public void showCustomerInformation() {
		//String[] customerData = newStichting.
	}
	
	//TODO: make checks for required fields and auto-generate GebruikerNummer
	public void addCustomer() {
		String klantNaam = textNaam.getText();
		String klantAdres = textAdres.getText();
		String klantPlaats = textPlaats.getText();
		String klantEmail = textEmail.getText();
		String klantTelefoonNr = textTelefoonNr.getText();
		String klantOpmerking = TextOpmerkingen.getText();
		
		//is it allowed to create a new object Klant here?
		//how do we create unique numbers?
		Klant newKlant = new Klant(klantNaam, klantAdres, klantPlaats, klantEmail, klantTelefoonNr, klantOpmerking, 0);	
		db.insertKlant(newKlant);
		
	}
	}