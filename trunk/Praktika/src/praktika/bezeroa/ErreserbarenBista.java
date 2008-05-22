package praktika.bezeroa;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import praktika.partekatuak.Erreserba;
import praktika.partekatuak.ErreserbaInterface;
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
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
					.addContainerGap())
		);
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

//	public void update(RemoteObservable observable, Object objektua) {
//		itenak.clear();
//		//
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
//		DateFormat dataFormat = DateFormat.getDateInstance();
//		ErreserbaInterface erreserbaSistema = (ErreserbaInterface) observable;
//
//	}
	
	private class UrrunekoBegiralea extends UnicastRemoteObject implements RemoteObserver, Serializable {

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
			itenak.clear();
			if(objektua.getClass() == Erreserba.class){
				int baiZenb = ((Erreserba) objektua).getBaieztapenZenbakia();
				if(baiZenb != -1)
					itenak.addElement("Erreserba baieztatua " + baiZenb + " baieztapen zenbakiarekin.");
				else{
					itenak.addElement("Erreserba ezeztatua izan da:");
					itenak.addElement(((Erreserba) objektua).getUkapenArrazoiak());
				}
			}
			//
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			DateFormat dataFormat = DateFormat.getDateInstance();
			ErreserbaInterface erreserbaSistema = (ErreserbaInterface) observable;
		}
		
	}
}
