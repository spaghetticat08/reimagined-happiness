package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;
import java.awt.Panel;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JRootPane;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import db.DataBaseManager;
import db.QueriesDB;
import src.Order;
import src.Stichting;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.List;
public class addOrder {
	private  Table orderTable;
	private  Text text_1;
	private  Text text_2;
	private  Text text_3;
	private  Text text_4;
	private  Text text_5;
	private  Text text_6;
	private  Text text_7;
	private  Text text_8;
	private  Text text_9;
	
	Stichting newStichting;
	/**
	 * Launch the application.
	 * @param args
	 */
	public addOrder(Stichting newStichting) {
		this.newStichting = newStichting;
		Display display = Display.getDefault();
		Shell orderShell = new Shell();
		orderShell.setSize(951, 675);
		
		orderTable = new Table(orderShell, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		orderTable.setBounds(56, 68, 765, 282);
		orderTable.setHeaderVisible(true);
		orderTable.setLinesVisible(true);
		
		TableColumn tblclmnOrderNumber = new TableColumn(orderTable, SWT.NONE);
		tblclmnOrderNumber.setWidth(151);
		tblclmnOrderNumber.setText("Ordernummer");
		
		TableColumn tblclmnDate = new TableColumn(orderTable, SWT.NONE);
		tblclmnDate.setWidth(100);
		tblclmnDate.setText("Datum Order");
		
		TableColumn tblclmnPrice = new TableColumn(orderTable, SWT.NONE);
		tblclmnPrice.setWidth(100);
		tblclmnPrice.setText("Totaalprijs");
		
		TableItem tableItem = new TableItem(orderTable, SWT.NONE);
		tableItem.setText("New TableItem");
	
	
		
		Label lblCurrentBalanse = new Label(orderShell, SWT.NONE);
		lblCurrentBalanse.setBounds(639, 37, 94, 25);
		lblCurrentBalanse.setText("Huidige Balans");
		
		Text text = new Text(orderShell, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(740, 34, 81, 25);
		
		
		
		Menu menu = new Menu(orderShell, SWT.BAR);
		orderShell.setMenuBar(menu);
		
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
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmVerversen = new MenuItem(menu_2, SWT.NONE);
		mntmVerversen.setText("Verversen...");
		
		Label label = new Label(orderShell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 370, 915, 9);
		
		text_1 = new Text(orderShell, SWT.BORDER);
		text_1.setBounds(139, 385, 267, 21);
		
		Label lblNewLabel = new Label(orderShell, SWT.NONE);
		lblNewLabel.setBounds(56, 391, 55, 15);
		lblNewLabel.setText("New Label");
		
		Label lblNewLabel_1 = new Label(orderShell, SWT.NONE);
		lblNewLabel_1.setBounds(56, 418, 55, 15);
		lblNewLabel_1.setText("New Label");
		
		text_2 = new Text(orderShell, SWT.BORDER);
		text_2.setBounds(139, 412, 267, 21);
		
		Label lblNewLabel_2 = new Label(orderShell, SWT.NONE);
		lblNewLabel_2.setBounds(56, 443, 55, 15);
		lblNewLabel_2.setText("New Label");
		
		text_3 = new Text(orderShell, SWT.BORDER);
		text_3.setBounds(139, 437, 267, 21);
		
		Label lblNewLabel_3 = new Label(orderShell, SWT.NONE);
		lblNewLabel_3.setBounds(56, 470, 55, 15);
		lblNewLabel_3.setText("New Label");
		
		text_4 = new Text(orderShell, SWT.BORDER);
		text_4.setBounds(139, 464, 267, 21);
		
		Label lblNewLabel_4 = new Label(orderShell, SWT.NONE);
		lblNewLabel_4.setBounds(56, 497, 55, 15);
		lblNewLabel_4.setText("New Label");
		
		text_5 = new Text(orderShell, SWT.BORDER);
		text_5.setBounds(139, 491, 267, 21);
		
		text_6 = new Text(orderShell, SWT.BORDER);
		text_6.setBounds(139, 518, 267, 21);
		
		text_7 = new Text(orderShell, SWT.BORDER);
		text_7.setBounds(139, 545, 267, 21);
		
		text_8 = new Text(orderShell, SWT.BORDER);
		text_8.setBounds(139, 572, 267, 21);
		
		text_9 = new Text(orderShell, SWT.BORDER);
		text_9.setBounds(554, 385, 267, 154);
		
		Label label_1 = new Label(orderShell, SWT.NONE);
		label_1.setText("New Label");
		label_1.setBounds(56, 524, 55, 15);
		
		Label label_2 = new Label(orderShell, SWT.NONE);
		label_2.setText("New Label");
		label_2.setBounds(56, 551, 55, 15);
		
		Label label_3 = new Label(orderShell, SWT.NONE);
		label_3.setText("New Label");
		label_3.setBounds(56, 578, 55, 15);
		
		Label label_4 = new Label(orderShell, SWT.NONE);
		label_4.setText("New Label");
		label_4.setBounds(467, 385, 55, 15);
		
		Button btnOpslaan = new Button(orderShell, SWT.NONE);
		btnOpslaan.setBounds(727, 581, 94, 25);
		btnOpslaan.setText("Opslaan");
		
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

	
	//tableItem.setText(new String[] {"1", "2", "3"});
	
	
}
