package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;


import Interface.DataBaseInterface;
import db.DataBaseManager;
import src.ApplicatieLogica;
import src.Stichting;

	public class mainMenu {
		Stichting newStichting;
		DataBaseInterface db;
		ApplicatieLogica resetLogic = new ApplicatieLogica();

		public mainMenu(Stichting newStichting, DataBaseInterface db) {
			this.newStichting = newStichting;
			this.db = db;
			Shell dialog = null;
			
			Display mainDisplay = new Display();
			Shell mainShell = new Shell(mainDisplay);
			mainShell.setMinimumSize(new Point(150, 40));
			mainShell.setSize(324, 409);
						mainShell.setLayout(null);
			
			Button orderButton = new Button(mainShell, SWT.PUSH);
			orderButton.setBounds(92, 195, 120, 39);
			orderButton.setText("Order-overzicht");
						
			//Listener top open the orderscreen
			orderButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				mainShell.dispose();
				OrderScreen startOrderMenu = new OrderScreen(newStichting, db);
				
			}
			});
			Button customerButton = new Button(mainShell, SWT.PUSH);
			customerButton.setBounds(92, 240, 120, 39);
			customerButton.setText("Klantenoverzicht");
			
			//Listener to open the customerscreen
			customerButton.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					mainShell.dispose();
					CustomerScreen startCustomerMenu = new CustomerScreen(newStichting, db);
					
				}
			});
			Button supplierButton = new Button(mainShell, SWT.PUSH);
			supplierButton.setBounds(92, 285, 120, 39);
			supplierButton.setText("Leverancieroverzicht");
			
			Menu menu = new Menu(mainShell, SWT.BAR);
			mainShell.setMenuBar(menu);
			
			MenuItem mntmInstellingen = new MenuItem(menu, SWT.CASCADE);
			mntmInstellingen.setText("Instellingen");
			
			Menu menu_1 = new Menu(mntmInstellingen);
			mntmInstellingen.setMenu(menu_1);
			
			
			//Method to recreate the XML-file containing the StichtingNaam en balans. Unfortunately this function is not fully implemented yet since choosing a location causes an error
			//TODO: Fix error when choosing a new location
			MenuItem mntmStichtingGegevensResetten = new MenuItem(menu_1, SWT.NONE);
			mntmStichtingGegevensResetten.setText("Stichting gegevens resetten");
			mntmStichtingGegevensResetten.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					boolean done = resetLogic.resetXML();
					if (done == true) {
						return;
								}
				}
			});
			new MenuItem(menu_1, SWT.SEPARATOR);
			
			//Method to reset the database. This can only be executed by an admin, therefore it is protected with a password
			//TODO: Implement warning that password is incorrect. Now it just closes without informing the user that reset has been sucessfull.
			MenuItem mntmDatabaseResetten = new MenuItem(menu_1, SWT.NONE);
			mntmDatabaseResetten.setText("DataBase Resetten");
			mntmDatabaseResetten.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					final Shell dialog  = new Shell(mainShell, SWT.DIALOG_TRIM);
					dialog.setLayout(new RowLayout());
					dialog.setSize(200, 100);
					final Text textPassword = new Text(dialog, SWT.BORDER|SWT.PASSWORD);
					textPassword.setBounds(154, 439, 146, 21);
					final Button okButton = new Button(dialog, SWT.PUSH);
					okButton.setText("Valideren");
					Label lblWarning = new Label(dialog, SWT.NONE);
					lblWarning.setText("Incorrect wachtwoord");
					lblWarning.setVisible(false);
					
					final String password="ResetDeDatabase";
					
					okButton.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String getPass = textPassword.getText();
								if(getPass.equals(password)) {
									resetDataBase();
									dialog.close();
									System.out.println("resetting database...");
									return;
								} else {
									dialog.close();
							}
						}
					});
					dialog.open();
				}
			});
			
			
			//Listener to open the supplierscreen
			supplierButton.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					mainShell.dispose();
					SupplierScreen startSupplierMenu = new SupplierScreen(newStichting, db);
				}
			});
	
			
			mainShell.open();
			while(!mainShell.isDisposed()) {
				if(!mainDisplay.readAndDispatch()) 
					mainDisplay.sleep();
				}
			mainDisplay.dispose();
		}
		//method to reset the database
		public void resetDataBase() {
			resetLogic.disposeAndCreateDB();
		}
	}

