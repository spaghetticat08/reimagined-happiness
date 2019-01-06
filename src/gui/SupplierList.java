package gui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import Interface.DataBaseInterface;
import db.DataBaseManager;
import src.ApplicatieLogica;
import src.Leverancier;
import src.Stichting;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

public class SupplierList {
	Stichting newStichting;
	DataBaseInterface db;
	ApplicatieLogica newLogic = new ApplicatieLogica();
	
	private  Text textNaam;
	private  Text textAdres;
	private  Text textPlaats;
	private  Text textEmail;
	private  Text textTelefoonNr;
	private  Text textOpmerking;
	
	private List supplierList;
	private Text textContact;
	private Text textWebsite;
	private Text textLand;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public SupplierList(Stichting newStichting, DataBaseInterface db) {
		//todo: put if-statement in to check if window has already been opened
		//if yes, center the existing one, else create new one
		this.newStichting = newStichting;
		this.db = db;
		
		Display display = Display.getDefault();
		Shell supplierShell = new Shell();
		supplierShell.setSize(845, 603);
		supplierShell.setText("SWT Application");
		supplierShell.setLayout(null);
		
		Menu menu = new Menu(supplierShell, SWT.BAR);
		supplierShell.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Navigeer naar...");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmAdministratieoverzicht = new MenuItem(menu_1, SWT.RADIO);
		mntmAdministratieoverzicht.setText("Administratie-overzicht");
		mntmAdministratieoverzicht.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				supplierShell.dispose();
				addOrder startOrderMenu = new addOrder(newStichting, db);
			}
		});
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton.setSelection(true);
		mntmNewRadiobutton.setText("Leverancieroverzicht");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton_1.setText("Klanten");
		mntmNewRadiobutton_1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				supplierShell.dispose();
				CustomerList2 startCustomerMenu = new CustomerList2(newStichting, db);
				
			}
		});
		
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
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmExporterenNaar = new MenuItem(menu_2, SWT.NONE);
		mntmExporterenNaar.setText("Exporteren naar...");

		//Make list
		
		supplierList = new List(supplierShell, SWT.BORDER);
				
		supplierList.setBounds(10, 36, 246, 478);
				
		String[] listOfSupplierNames = getSupplierNames();
		supplierList.setItems(listOfSupplierNames);
		supplierList.addListener(SWT.Selection, event -> onListItemSelect(supplierList));
		
		Label label = new Label(supplierShell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(262, 0, 15, 534);
		
		textNaam = new Text(supplierShell, SWT.BORDER);
		textNaam.setBounds(393, 37, 144, 21);
		
		Label lblNewLabel = new Label(supplierShell, SWT.NONE);
		lblNewLabel.setBounds(283, 40, 55, 15);
		lblNewLabel.setText("Naam");
		
		Label lblNewLabel_1 = new Label(supplierShell, SWT.NONE);
		lblNewLabel_1.setBounds(283, 96, 55, 15);
		lblNewLabel_1.setText("Adres");
		
		textAdres = new Text(supplierShell, SWT.BORDER);
		textAdres.setBounds(393, 93, 233, 21);
		
		Label lblNewLabel_2 = new Label(supplierShell, SWT.NONE);
		lblNewLabel_2.setBounds(283, 123, 55, 15);
		lblNewLabel_2.setText("Plaats");
		
		Label lblEmailadres = new Label(supplierShell, SWT.NONE);
		lblEmailadres.setBounds(283, 204, 76, 15);
		lblEmailadres.setText("E-mailadres");
		
		textPlaats = new Text(supplierShell, SWT.BORDER);
		textPlaats.setBounds(393, 120, 144, 21);
		
		textEmail = new Text(supplierShell, SWT.BORDER);
		textEmail.setBounds(393, 201, 205, 21);
		
		Label lblTelefoonnummer = new Label(supplierShell, SWT.NONE);
		lblTelefoonnummer.setBounds(283, 231, 110, 15);
		lblTelefoonnummer.setText("Telefoonnummer");
		
		textTelefoonNr = new Text(supplierShell, SWT.BORDER);
		textTelefoonNr.setBounds(393, 228, 144, 21);
		
		Label lblOpmerkingen = new Label(supplierShell, SWT.NONE);
		lblOpmerkingen.setBounds(283, 312, 88, 15);
		lblOpmerkingen.setText("Opmerkingen");
		
		textOpmerking = new Text(supplierShell, SWT.BORDER);
		textOpmerking.setBounds(393, 309, 205, 160);
		
		Button btnOpslaan = new Button(supplierShell, SWT.NONE);
		btnOpslaan.setBounds(393, 490, 101, 25);
		btnOpslaan.setText("Opslaan");
		
		textContact = new Text(supplierShell, SWT.BORDER);
		textContact.setBounds(393, 66, 144, 21);
		
		Label lblContactpersoon = new Label(supplierShell, SWT.NONE);
		lblContactpersoon.setText("Contactpersoon");
		lblContactpersoon.setBounds(283, 69, 110, 15);
		
		textWebsite = new Text(supplierShell, SWT.BORDER);
		textWebsite.setBounds(393, 255, 144, 21);
		
		Label lblWebsite = new Label(supplierShell, SWT.NONE);
		lblWebsite.setText("Website");
		lblWebsite.setBounds(283, 258, 55, 15);
		
		textLand = new Text(supplierShell, SWT.BORDER);
		textLand.setBounds(393, 147, 144, 21);
		
		Label lblLand = new Label(supplierShell, SWT.NONE);
		lblLand.setText("Land (Invullen \r\nindien niet NL)");
		lblLand.setBounds(283, 150, 88, 46);
		
		Button btnToevoegen = new Button(supplierShell, SWT.NONE);
		btnToevoegen.setText("Toevoegen...");
		btnToevoegen.setBounds(283, 490, 101, 25);
		btnToevoegen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				addSupplier();
			}
		});
		
		Button btnVerwijderen = new Button(supplierShell, SWT.NONE);
		btnVerwijderen.setText("Verwijderen");
		btnVerwijderen.setBounds(500, 490, 101, 25);
		btnVerwijderen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				deleteSupplier();
			supplierShell.requestLayout();
			}
		});
		
		/*Button btnLeegmaken = new Button(supplierShell, SWT.NONE);
		btnLeegmaken.setText("Leegmaken");
		btnLeegmaken.setBounds(607, 490, 101, 25);
		*/
		
		supplierShell.open();
		supplierShell.layout();
		while (!supplierShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public String[] getSupplierNames() {
		//create a query to get the whole list of customers and return only the names
		String[] listOfSupplierNames = newStichting.leverancierNames(newStichting, db);
		return listOfSupplierNames;
	}

	public void onListItemSelect(List supplierList) {
		int indexNo = supplierList.getFocusIndex();
		Leverancier infoLev = newLogic.getLevInfo(indexNo, newStichting, db);
		textNaam.setText(infoLev.getLeverancierNaam());
		textContact.setText(infoLev.getContactPersoon());
		textAdres.setText(infoLev.getLeverancierAdres());
		textPlaats.setText(infoLev.getLeverancierPlaats());
		textLand.setText(infoLev.getLand());
		textEmail.setText(infoLev.getLeverancierEmailadres());
		textTelefoonNr.setText(infoLev.getLeverancierTelNr());
		textWebsite.setText(infoLev.getWebsite());
		textOpmerking.setText(infoLev.getLeverancierOpmerking());
	}
	
	public void addSupplier() {
		String levNaam = textNaam.getText();
		String contactPersoon = textContact.getText();
		String levAdres = textAdres.getText();
		String levPlaats = textPlaats.getText();
		String land = textLand.getText();
		String levEmail = textEmail.getText();
		String levTelefoonNr = textTelefoonNr.getText();
		String website = textWebsite.getText();
		String levOpmerking = textOpmerking.getText();
		
		newLogic.insertLeverancier(db, levNaam, contactPersoon, levAdres, levPlaats, land, levEmail, levTelefoonNr, website, levOpmerking);
		
		String[] listOfSupplierNames = getSupplierNames();
		supplierList.setItems(listOfSupplierNames);
	}
	public void deleteSupplier() {
		int indexNo = supplierList.getFocusIndex();
		newLogic.prepLevForDelete(indexNo, newStichting, db);
		//TODO: refresh supplierlist
		String[] listOfSupplierNames = getSupplierNames();
		supplierList.setItems(listOfSupplierNames);
		
		
	}
}
