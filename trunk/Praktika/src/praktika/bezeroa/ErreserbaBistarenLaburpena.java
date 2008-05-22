package praktika.bezeroa;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import praktika.partekatuak.ErreserbaBistarenLaburpenaObserver;
import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

public class ErreserbaBistarenLaburpena extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Kontrolak
	private JLabel etiketaData = new JLabel("Erreserbaren Data  ");

	private JLabel etiketaAgentearenIzena = new JLabel("Erreserbaren Agentea ");

	private JLabel etiketaErreserbaZenbakia = new JLabel(
			"Erreserbaren Zenbakia");

	private JLabel etiketaGuztira = new JLabel("Guztira         ");

	private JTextField testuEremuaData = new JTextField();

	private JTextField testuEremuaAgentearenIzena = new JTextField();

	private JTextField testuEremuaErreserbarenZenbakia = new JTextField();

	private JTextField testuEremuaGuztira = new JTextField();

	public ErreserbaBistarenLaburpena(ErreserbaInterface erreserbaSistema) {
		try {
			UrrunekoBegiralea remErrBisLab = new UrrunekoBegiralea();
			erreserbaSistema.addObserver(remErrBisLab);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new GridLayout(2, 4, 25, 25));
		setFont(new Font("Arial", Font.PLAIN, 12));
		setBackground(Color.lightGray);
		setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		// row 1
		add(etiketaData);
		add(testuEremuaData);
		add(etiketaAgentearenIzena);
		add(testuEremuaAgentearenIzena);
		// row 2
		add(etiketaErreserbaZenbakia);
		add(testuEremuaErreserbarenZenbakia);
		add(etiketaGuztira);
		add(testuEremuaGuztira);
		// testu eremuak irakurtzeko bakarrik aldatu
		testuEremuaData.setEditable(false);
		testuEremuaAgentearenIzena.setEditable(false);
		testuEremuaErreserbarenZenbakia.setEditable(false);
		testuEremuaGuztira.setEditable(false);
	}
	
	private class UrrunekoBegiralea extends UnicastRemoteObject implements RemoteObserver, Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public UrrunekoBegiralea() throws RemoteException{
			super();
		}
		@Override
		public void update(RemoteObservable o, Object arg) throws RemoteException {
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			DateFormat dataFormat = DateFormat.getDateInstance();
			//ErreserbaInterface erreserbaSistema = (ErreserbaInterface) observable;
			//
			//	if (erreserbaSistema.getLoturaErreserba() != null) {
			//	} else {
					testuEremuaData.setText("");
					testuEremuaGuztira.setText("");
					testuEremuaAgentearenIzena.setText("");
					testuEremuaErreserbarenZenbakia.setText("");
			//	}
		}
	}
}
