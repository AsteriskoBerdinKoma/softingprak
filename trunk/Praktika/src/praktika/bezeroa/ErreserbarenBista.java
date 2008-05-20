package praktika.bezeroa;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

/**
 * Aplikazioaren lehen mailaren(Bista) klasea
 */

public class ErreserbarenBista extends JPanel implements RemoteObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Kontrolak
	private DefaultListModel itenak; // new DefaultListModel();

	private JList lista; // new JList(itenak);

	// private JTextArea area = new JTextArea();

	public ErreserbarenBista(ErreserbaInterface erreserbaSistema) {
		try {
			erreserbaSistema.addObserver(this);
			//
			itenak = new DefaultListModel();
			lista = new JList(itenak);
			lista.setVisibleRowCount(24);
			this.add(new JScrollPane(lista));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Eguneratu bista.
	 * 
	 * @param observable
	 *            java.util.Observable
	 * @param objektua
	 *            java.lang.Object
	 */

	public void update(RemoteObservable observable, Object objektua) {
		itenak.clear();
		//
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		DateFormat dataFormat = DateFormat.getDateInstance();
		ErreserbaInterface erreserbaSistema = (ErreserbaInterface) observable;

	}
}
