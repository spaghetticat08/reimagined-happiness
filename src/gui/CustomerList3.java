package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.widgets.Table;

public class CustomerList3 {

	/**
	 * Launch the application.
	 * @param args
	 */
	public  void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(805, 653);
		shell.setText("SWT Application");
		
		Tree tree = new Tree(shell, SWT.BORDER);
		tree.setBounds(0, 0, 85, 85);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
