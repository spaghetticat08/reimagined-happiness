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

import db.DataBaseManager;
import src.Stichting;

	public class mainMenu {
		Stichting newStichting;
		DataBaseManager db;

		public mainMenu(Stichting newStichting, DataBaseManager db) {
			this.newStichting = newStichting;
			this.db = db;
			Shell dialog = null;
			
			Display mainDisplay = new Display();
			Shell mainShell = new Shell(mainDisplay);
			mainShell.setMinimumSize(new Point(150, 40));
			mainShell.setSize(324, 373);
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
			lblNewLabel.setBounds(92, 42, 120, 124);
			lblNewLabel.setText("New Label");
			
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
	}

