package praktika.bezeroa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;

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
		setBorder(new TitledBorder(null, "Erreserba",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));

		JButton garbituButton;
		garbituButton = new JButton();
		garbituButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				itenak.clear();
			}
		});
		garbituButton.setText("Garbitu");
		final GroupLayout groupLayout = new GroupLayout((JComponent) this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																scrollPane,
																GroupLayout.Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																466,
																Short.MAX_VALUE)
														.addComponent(
																garbituButton))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(
						GroupLayout.Alignment.TRAILING).addGroup(
						groupLayout.createSequentialGroup().addComponent(
								scrollPane, GroupLayout.DEFAULT_SIZE, 305,
								Short.MAX_VALUE).addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(garbituButton).addContainerGap()));
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
					switch (e.getEgoera()) {
					case Erreserba.BERRIA:
						itenak.addElement("Erreserba berria sortu da "
								+ e.getErreserbaZenbakia() + " zenbakiarekin.");
						break;
					case Erreserba.BAIEZTATUA:
						int baiZenb = e.getBaieztapenZenbakia();
						itenak.addElement(e.getErreserbaZenbakia()
								+ " zenbakidun erreserba baieztatua " + baiZenb
								+ " baieztapen zenbakiarekin.");
						break;
					case Erreserba.UKATUA:
						itenak.addElement(e.getErreserbaZenbakia()
								+ " zenbakidun erreserba ukatua izan da:");
						itenak.addElement(((Erreserba) objektua)
								.getUkapenArrazoiak());
						break;
					case Erreserba.SARTUTA:
						itenak.addElement(e.getErreserbaZenbakia()
								+ " zenbakidun erreserba satua izan da.");
						break;
					case Erreserba.EZEZTATUA:
						itenak.addElement(e.getErreserbaZenbakia()
								+ " zenbakidun erreserba ezeztatua izan da.");
						break;
					case Erreserba.BUKATUA:
						itenak.addElement(e.getErreserbaZenbakia()
								+ " zenbakidun erreserba bukatua izan da.");
						break;
					default:
						break;
					}
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
