package gui;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.SWT;
import java.util.ArrayList;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;


import Interface.DataBaseInterface;
import src.ApplicatieLogica;
import src.BetalingsMiddel;
import src.Klant;
import src.Leverancier;
import src.Order;
import src.Status;
import src.Stichting;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;

public class addOrder {
	private  Table orderTable;
	private Text textOrdernummer;
	private Text textArtikel;
	private Text textPrijs;
	private Combo textGebruiker;
	private Text textOmschrijving;
	boolean descending = false;
	
	String datum;
	Status orderStatus;
	BetalingsMiddel betaling;
	DataBaseInterface db;
	Stichting newStichting;
	private Text textDatum;
	
	ApplicatieLogica newLogic = new ApplicatieLogica();
	/**
	 * Launch the application.
	 * @param args
	 */
	public addOrder(Stichting newStichting, DataBaseInterface db) {
		this.newStichting = newStichting;
		this.db = db;
		Display display = Display.getDefault();
		Shell orderShell = new Shell();
		orderShell.setSize(951, 675);
		
		orderTable = new Table(orderShell, SWT.BORDER | SWT.FULL_SELECTION);
		orderTable.setBounds(56, 68, 765, 296);
		orderTable.setHeaderVisible(true);
		orderTable.setLinesVisible(true);
		//orderTable.addListener(SWT.Selection, event -> onOrderItemSelect(orderTable, comboStatus, comboBetaling));
		
		TableColumn tblclmnOrderNumber = new TableColumn(orderTable, SWT.NONE);
		tblclmnOrderNumber.setWidth(151);
		tblclmnOrderNumber.setText("Ordernummer");
		tblclmnOrderNumber.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				sortOrderNumbers();
			}
		});
		
		TableColumn tblclmnDate = new TableColumn(orderTable, SWT.NONE);
		tblclmnDate.setWidth(100);
		tblclmnDate.setText("Datum Order");
		tblclmnDate.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				sortDates();
			}
		});
		
		TableColumn tblclmnPrice = new TableColumn(orderTable, SWT.NONE);
		tblclmnPrice.setWidth(100);
		tblclmnPrice.setText("Bedrag");
		tblclmnPrice.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				sortOrderPrices();
			}
		});
		
		//Load all orders in the table
		loadAllOrders();
	
		Label lblCurrentBalanse = new Label(orderShell, SWT.NONE);
		lblCurrentBalanse.setBounds(639, 37, 94, 25);
		lblCurrentBalanse.setText("Huidige Balans");
		
		Text balancetext = new Text(orderShell, SWT.BORDER);
		balancetext.setEnabled(false);
		balancetext.setBounds(740, 34, 81, 25);
		
		Menu menu = new Menu(orderShell, SWT.BAR);
		orderShell.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Navigeer naar...");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmAdministratieoverzicht = new MenuItem(menu_1, SWT.RADIO);
		mntmAdministratieoverzicht.setSelection(true);
		mntmAdministratieoverzicht.setText("Administratie-overzicht");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton.setText("Leverancieroverzicht");
		mntmNewRadiobutton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				orderShell.dispose();
				SupplierList startSupplierMenu = new SupplierList(newStichting, db);
			}
		});
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton_1.setText("Klantenoverzicht");
		mntmNewRadiobutton_1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				orderShell.dispose();
				CustomerList2 startCustomerMenu = new CustomerList2(newStichting, db);
			}
		});

		
		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("Actie...");
		
		Menu menu_2 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_2);
		
		MenuItem mntmBalans = new MenuItem(menu_2, SWT.NONE);
		mntmBalans.setText("Balans aanpassen");
		
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmVerversen = new MenuItem(menu_2, SWT.NONE);
		mntmVerversen.setText("Verversen...");
		
		Label label = new Label(orderShell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 370, 915, 9);
		
		textOrdernummer = new Text(orderShell, SWT.BORDER);
		textOrdernummer.setEnabled(false);
		textOrdernummer.setBounds(154, 385, 267, 21);
		
		Label lblNewLabel = new Label(orderShell, SWT.NONE);
		lblNewLabel.setBounds(56, 388, 92, 15);
		lblNewLabel.setText("Ordernummer");
		
		Label lblNewLabel_1 = new Label(orderShell, SWT.NONE);
		lblNewLabel_1.setBounds(56, 415, 55, 15);
		lblNewLabel_1.setText("Artikel");
		
		textArtikel = new Text(orderShell, SWT.BORDER);
		textArtikel.setBounds(154, 412, 267, 21);
		
		Label lblNewLabel_2 = new Label(orderShell, SWT.NONE);
		lblNewLabel_2.setBounds(56, 440, 55, 15);
		lblNewLabel_2.setText("Datum");
		
		Label lblNewLabel_3 = new Label(orderShell, SWT.NONE);
		lblNewLabel_3.setBounds(56, 467, 55, 15);
		lblNewLabel_3.setText("Prijs");
		
		textPrijs = new Text(orderShell, SWT.BORDER);
		textPrijs.setBounds(154, 464, 267, 21);
		
		Label lblNewLabel_4 = new Label(orderShell, SWT.NONE);
		lblNewLabel_4.setBounds(56, 548, 92, 15);
		lblNewLabel_4.setText("Betalingsmiddel");
		
		textGebruiker = new Combo(orderShell, SWT.READ_ONLY);
		textGebruiker.setToolTipText("");
		textGebruiker.setBounds(154, 489, 267, 21);
		//getGebruikersList();
		
		String[] listGebruikers = getGebruikers(); 
		textGebruiker.setItems(listGebruikers);
		
				
		textOmschrijving = new Text(orderShell, SWT.BORDER);
		textOmschrijving.setBounds(554, 385, 267, 154);
		
		Label lblStatus = new Label(orderShell, SWT.NONE);
		lblStatus.setText("Status");
		lblStatus.setBounds(56, 518, 55, 24);
		
		Label lblGebruiker = new Label(orderShell, SWT.NONE);
		lblGebruiker.setText("Gebruiker Nummer");
		lblGebruiker.setBounds(56, 494, 55, 15);
		
		Label lblOmschrijving = new Label(orderShell, SWT.NONE);
		lblOmschrijving.setText("Omschrijving");
		lblOmschrijving.setBounds(468, 388, 81, 15);
		
		Button btnOpslaan = new Button(orderShell, SWT.NONE);
		btnOpslaan.setBounds(727, 581, 94, 25);
		btnOpslaan.setText("Opslaan");
		btnOpslaan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
			}
		});
		Button btnVerwijderen = new Button(orderShell, SWT.NONE);
		btnVerwijderen.setText("Verwijderen");
		btnVerwijderen.setBounds(627, 581, 94, 25);
		btnVerwijderen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				deleteOrder();
			}
		});
		/*Button btnLeegmaken = new Button(orderShell, SWT.NONE);
		btnLeegmaken.setText("Leegmaken");
		btnLeegmaken.setBounds(527, 581, 94, 25);
		btnLeegmaken.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
			}
		});*/
		
		Combo comboBetaling = new Combo(orderShell, SWT.READ_ONLY);
		//TODO: It would be nice if we can automatically iterate through the enum and add all items for future changes
		comboBetaling.setItems(new String[] {BetalingsMiddel.Bankoverschrijving.toString(), BetalingsMiddel.Contant.toString(), BetalingsMiddel.CreditCard.toString(), BetalingsMiddel.IDeal.toString(), BetalingsMiddel.Paypal.toString()});
		comboBetaling.setBounds(154, 545, 267, 25);
		comboBetaling.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				betaling = BetalingsMiddel.valueOf(comboBetaling.getText());
			}
		});
		
		Combo comboStatus = new Combo(orderShell, SWT.READ_ONLY);
		comboStatus.setItems(new String[] {Status.Voltooid.toString(), Status.Geannuleerd.toString(), Status.AfwachtingBetaling.toString().trim()});
		comboStatus.setVisibleItemCount(5);
		comboStatus.setBounds(154, 516, 267, 23);
		comboStatus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderStatus = Status.valueOf(comboStatus.getText());
			}
		});

		Button btnToevoegen = new Button(orderShell, SWT.NONE);
		btnToevoegen.setText("Toevoegen");
		btnToevoegen.setBounds(427, 581, 94, 25);
		btnToevoegen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				addNewOrder(balancetext, comboStatus, comboBetaling, textGebruiker);
			}
		});
		Button datumBtn = new Button(orderShell, SWT.PUSH);
		datumBtn.setBounds(340, 439, 81, 21);
		datumBtn.setText("Datum kiezen");
		
		textDatum = new Text(orderShell, SWT.BORDER);
		textDatum.setBounds(154, 439, 146, 21);
		
		orderTable.addListener(SWT.Selection, event -> onOrderItemSelect(orderTable, comboStatus, comboBetaling));
		
		datumBtn.addSelectionListener (new SelectionAdapter () {
		    public void widgetSelected (SelectionEvent e) {
		      final Shell dialog = new Shell (orderShell, SWT.DIALOG_TRIM);
		      dialog.setLayout (new GridLayout (3, false));

		      final DateTime calendar = new DateTime (dialog, SWT.CALENDAR | SWT.BORDER);
		      final DateTime date = new DateTime (dialog, SWT.DATE | SWT.SHORT);
		      
		      new Label (dialog, SWT.NONE);
		      new Label (dialog, SWT.NONE);
		      Button ok = new Button (dialog, SWT.PUSH);
		      ok.setText ("OK");
		      ok.setLayoutData(new GridData (SWT.FILL, SWT.CENTER, false, false));
		      ok.addSelectionListener (new SelectionAdapter () {
		      public void widgetSelected (SelectionEvent e) {
		    	  textDatum.setText(calendar.getDay ()  + "-" + (calendar.getMonth ()+1) + "-" + calendar.getYear ());
		          
		    	  dialog.close ();
		        }
		      });
		      dialog.setDefaultButton (ok);
		      dialog.pack ();
		      dialog.open ();
		    }
		  });
		
		mntmVerversen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				
			}
		});
		orderShell.open();
		while (!orderShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void addNewOrder(Text balancetext, Combo comboStatus, Combo comboBetaling, Combo textGebruiker) {
		//int ordernummer = Integer.valueOf(textOrdernummer.getText());
		String artikel = textArtikel.getText();
		Double prijs = Double.valueOf(textPrijs.getText());
		//int gebruikerNummer = Integer.valueOf(textGebruiker.getText());
		String omschrijving = textOmschrijving.getText();
		String datum = textDatum.getText();
		BetalingsMiddel betaling = BetalingsMiddel.valueOf(comboBetaling.getText());
		Status orderStatus = Status.valueOf(comboStatus.getText());
		String gebruiker = textGebruiker.getText();
		
		newLogic.insertOrder(newStichting, db, gebruiker, artikel, omschrijving, datum, prijs, betaling, orderStatus);
		Double newBalans = newStichting.calculateNewBalance(prijs, newStichting);
		balancetext.setText(Double.toString(newBalans));
		orderTable.removeAll();
		loadAllOrders();
	}
	
	public void loadAllOrders() {
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		for (Order order:orders) {
			TableItem newItem = new TableItem(orderTable, SWT.NONE);
			String[] tableItems = new String[] {Integer.toString(order.getOrdernummer()), order.getDatum(), Double.toString(order.getPrijs())};
			newItem.setText(tableItems);
		}
	}
	
	public void onOrderItemSelect(Table orderTable, Combo comboStatus, Combo comboBetaling) {
		int indexNo = orderTable.getSelectionIndex();
		System.out.println(indexNo);
		Order infoOrder = newLogic.getOrderInfo(indexNo, newStichting, db);
		
		textOrdernummer.setText(Integer.toString(infoOrder.getOrdernummer()));
		textArtikel.setText(infoOrder.getArtikel());
		textPrijs.setText(Double.toString(infoOrder.getPrijs()));
		textOmschrijving.setText(infoOrder.getOmschrijving());
		System.out.println(infoOrder.getOrderStatus().toString());
		comboStatus.setText(infoOrder.getOrderStatus().toString());
		comboBetaling.setText(infoOrder.getTypeBetaling().toString());
		
		Klant klant = infoOrder.getKlantNaam();
		Leverancier lev = infoOrder.getLeverancierNaam();
		
	}
	
	public void deleteOrder() {
		int indexNo = orderTable.getSelectionIndex();
		newLogic.prepOrderForDelete(indexNo, newStichting, db);
		//TODO: refresh list after deletion
		orderTable.removeAll();
		loadAllOrders();
	};
	
	public void sortOrderPrices() {
		//ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		ArrayList<Order> sortedOrders = newLogic.sortPrices(newStichting, db);
		orderTable.removeAll();
		for (Order order:sortedOrders) {
			TableItem newItem = new TableItem(orderTable, SWT.NONE);
			String[] tableItems = new String[] {Integer.toString(order.getOrdernummer()), order.getDatum(), Double.toString(order.getPrijs())};
			newItem.setText(tableItems);
		}
	}
	
	public void sortOrderNumbers() {
		ArrayList<Order> sortedOrders = newLogic.sortOrderNumbers(newStichting, db);
		orderTable.removeAll();
		for (Order order:sortedOrders) {
			TableItem newItem = new TableItem(orderTable, SWT.NONE);
			String[] tableItems = new String[] {Integer.toString(order.getOrdernummer()), order.getDatum(), Double.toString(order.getPrijs())};
			newItem.setText(tableItems);
		}
	}

	public void getGebruikersList() {
		textGebruiker.setData(newStichting.getKlanten(newStichting, db));
	}
	
	
	public String[] getGebruikers() {
		String [] listofGebruikers = newLogic.getGebruikers(newStichting, db);
		return listofGebruikers;
	}
	
	public void sortDates() {
	}
	
}
