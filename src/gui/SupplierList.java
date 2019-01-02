package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import src.Stichting;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SupplierList {
	private  Table table;
	Stichting newStichting;

	/**
	 * Launch the application.
	 * @param args
	 */
	public SupplierList(Stichting newStichting) {
		//todo: put if-statement in to check if window has already been opened
		//if yes, center the existing one, else create new one
		this.newStichting = newStichting;
		
		Display display = Display.getDefault();
		Shell supplierShell = new Shell();
		supplierShell.setSize(885, 611);
		supplierShell.setText("SWT Application");
		supplierShell.setLayout(new FormLayout());
		
		table = new Table(supplierShell, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 3);
		fd_table.left = new FormAttachment(0, 3);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("naam");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("contactpersoon");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("adres");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("e-mailadres");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("telefoonnummer");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("website");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("opmerkingen");
		
		Menu menu = new Menu(supplierShell, SWT.BAR);
		supplierShell.setMenuBar(menu);
		
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
		
		MenuItem mntmExporterenNaar = new MenuItem(menu_2, SWT.NONE);
		mntmExporterenNaar.setText("Exporteren naar...");

		supplierShell.open();
		supplierShell.layout();
		while (!supplierShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
