package praktika.bezeroa;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import praktika.partekatuak.Erreserba;
import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.TuristaNotifikazioa;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

/**
 * Aplikazioaren lehen mailaren(Bista) klasea
 */

public class ErreserbarenBista extends JPanel {
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
			UrrunekoBegiralea remErrBis = new UrrunekoBegiralea();
			erreserbaSistema.addObserver(remErrBis);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itenak = new DefaultListModel();
		lista = new JList(itenak);
		lista.setVisibleRowCount(24);
		JScrollPane scrollPane;
		scrollPane = new JScrollPane(lista);
		final GroupLayout groupLayout = new GroupLayout((JComponent) this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								156, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								358, Short.MAX_VALUE).addContainerGap()));
		setLayout(groupLayout);
	}

	/**
	 * Eguneratu bista.
	 * 
	 * @param observable
	 *            java.util.Observable
	 * @param objektua
	 *            java.lang.Object
	 */

	// public void update(RemoteObservable observable, Object objektua) {
	// itenak.clear();
	// //
	// NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
	// DateFormat dataFormat = DateFormat.getDateInstance();
	// ErreserbaInterface erreserbaSistema = (ErreserbaInterface) observable;
	//
	// }
	private class UrrunekoBegiralea extends UnicastRemoteObject implements
			RemoteObserver, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected UrrunekoBegiralea() throws RemoteException {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void update(RemoteObservable observable, Object objektua)
				throws RemoteException {
			if (objektua instanceof Erreserba) {
				Erreserba e = (Erreserba) objektua;
				if (AplikazioNagusia.getUnekoErreserbaZenbakia() == e
						.getErreserbaZenbakia()) {
					if (!e.isSartuta()) {
						itenak.clear();
						int baiZenb = e.getBaieztapenZenbakia();
						itenak.addElement("Erreserbari esleitutako zenbakia "
								+ e.getErreserbaZenbakia() + ".");
						if (e.isBaieztatua())
							itenak.addElement("Erreserba baieztatua " + baiZenb
									+ " baieztapen zenbakiarekin.");
						else {
							itenak.addElement("Erreserba ezeztatua izan da:");
							itenak.addElement(((Erreserba) objektua)
									.getUkapenArrazoiak());
						}
					} else
						itenak.addElement("Erreserba satua izan da.");
				}
			} else if (objektua instanceof TuristaNotifikazioa) {
				TuristaNotifikazioa tn = (TuristaNotifikazioa) objektua;
				if (AplikazioNagusia.getUnekoErreserbaZenbakia() == tn
						.getErreserbaZenbakia()) {
					itenak.addElement(tn.getMezua());
					if (tn.getLibreKop() == 0)
						itenak
								.addElement("Ezin dira turista gehiago sartu. Egin click bukatu botoian erreserba honekin bukatzeko.");
				}
			}
			//
			// NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			// DateFormat dataFormat = DateFormat.getDateInstance();
			// ErreserbaInterface erreserbaSistema = (ErreserbaInterface)
			// observable;
		}
	}
}
