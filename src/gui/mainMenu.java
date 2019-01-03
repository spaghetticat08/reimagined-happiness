package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
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
import org.eclipse.wb.swt.SWTResourceManager;

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
			orderButton.setText("Orders");
						
			//Orderbutton with eventlistener
			//todo: close mainmenu once submenu has been opened
			orderButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				mainShell.dispose();
				addOrder startOrderMenu = new addOrder(newStichting, db);
				
			}
			});
			Button customerButton = new Button(mainShell, SWT.PUSH);
			customerButton.setBounds(92, 240, 120, 39);
			customerButton.setText("Klantenoverzicht");
			
			//customerbutton with eventlistener
			//todo: close mainmenu once submenu has been opened
			customerButton.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					mainShell.dispose();
					CustomerList2 startCustomerMenu = new CustomerList2(newStichting, db);
					
				}
			});
			Button supplierButton = new Button(mainShell, SWT.PUSH);
			supplierButton.setBounds(92, 285, 120, 39);
			supplierButton.setText("Leverancieroverzicht");
			
			Label lblNewLabel = new Label(mainShell, SWT.NONE);
			lblNewLabel.setBounds(92, 53, 120, 124);
			lblNewLabel.setText("New Label");
			
			Menu menu = new Menu(mainShell, SWT.BAR);
			mainShell.setMenuBar(menu);
			
			MenuItem mntmInstellingen = new MenuItem(menu, SWT.CASCADE);
			mntmInstellingen.setText("Instellingen");
			
			Menu menu_1 = new Menu(mntmInstellingen);
			mntmInstellingen.setMenu(menu_1);
			
			MenuItem mntmDatabaseResetten = new MenuItem(menu_1, SWT.NONE);
			mntmDatabaseResetten.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					resetDataBase();
				}
					/*final Shell dialog  = new Shell(mainShell, SWT.DIALOG_TRIM);
					dialog.setLayout(new RowLayout());
					dialog.setSize(200, 100);
					final Text textPassword = new Text(dialog, SWT.BORDER);
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
							String getText = textPassword.getText();
							for (int i=0; 0<3; i++) {
								if (getText==password) {
									dialog.close();
									resetDataBase();
									System.out.println("resetting database...");
								}
								else {
									lblWarning.setVisible(true);
								}
								
							}
						dialog.close();	
						}
					});
					//dialog.pack();
					dialog.open();
				}*/
			});
			mntmDatabaseResetten.setText("DataBase Resetten");
			
			//Supplierbutton with eventlistener
			//todo: close mainmenu once submenu has been opened
			supplierButton.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					mainShell.dispose();
					SupplierList startSupplierMenu = new SupplierList(newStichting, db);
				}
			});
			
			//Make display for menu with orders
		
			//add listeners to buttons
			
			//run the event loop as long as the window is opened
			
			mainShell.open();
			while(!mainShell.isDisposed()) {
				if(!mainDisplay.readAndDispatch()) 
					mainDisplay.sleep();
				}
			mainDisplay.dispose();
		}
		
		public void resetDataBase() {
			resetLogic.disposeAndCreateDB();
		}
		
	}

