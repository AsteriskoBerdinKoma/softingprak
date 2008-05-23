package praktika.bezeroa;

import java.awt.Font;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;

import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

/**
 * Lehen mailarekin(Bista)lan egiten duen klasea.
 */

public class ErreserbaItenarenBista extends JPanel {
	private JLabel label;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Kontrolak
	private JLabel etiketaKopurua = new JLabel("Kopurua     ");

	private JLabel etiketaDeskripzioa = new JLabel("Deskribapena ");

	private JLabel etiketaPrezioa = new JLabel("Prezioa       ");

	private JLabel etiketaLuzapena = new JLabel("Luzapena   ");

	//
	private JTextField testuEremuaKopurua = new JTextField("1");

	private JTextField textFieldDeskribapena = new JTextField();

	private JTextField testuEremuaPrezioa = new JTextField();

	private JTextField testuEremuaLuzapena = new JTextField();

	public ErreserbaItenarenBista(ErreserbaInterface erreserbaSistema) {
		try {
			UrrunekoBegiralea remErrItBista = new UrrunekoBegiralea();
			erreserbaSistema.addObserver(remErrItBista);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFont(new Font("Arial", Font.PLAIN, 12));
		// testu eremuak irakurtzeko bakarrik aldatu
		testuEremuaKopurua.setEditable(false);
		textFieldDeskribapena.setEditable(false);
		testuEremuaPrezioa.setEditable(false);
		testuEremuaLuzapena.setEditable(false);
		setBorder(new TitledBorder(null, "Azken irteeraren laburpena",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		// row 1
		final GroupLayout groupLayout = new GroupLayout((JComponent) this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.TRAILING)
				.addGroup(
						groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(testuEremuaKopurua,
										GroupLayout.DEFAULT_SIZE, 110,
										Short.MAX_VALUE).addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(getLabel()).addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(testuEremuaPrezioa,
										GroupLayout.DEFAULT_SIZE, 109,
										Short.MAX_VALUE).addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(textFieldDeskribapena,
										GroupLayout.DEFAULT_SIZE, 116,
										Short.MAX_VALUE).addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(testuEremuaLuzapena,
										GroupLayout.DEFAULT_SIZE, 105,
										Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap().addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								getLabel()).addComponent(testuEremuaKopurua,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								testuEremuaPrezioa, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								textFieldDeskribapena,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								testuEremuaLuzapena,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addGap(317, 317,
						317)));
		groupLayout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { testuEremuaKopurua,
						testuEremuaLuzapena, testuEremuaPrezioa,
						textFieldDeskribapena });
		setLayout(groupLayout);
	}

	private class UrrunekoBegiralea extends UnicastRemoteObject implements
			RemoteObserver, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UrrunekoBegiralea() throws RemoteException {
			super();
		}

		@Override
		public void update(RemoteObservable o, Object arg)
				throws RemoteException {
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			// if (erreserbaSistema.getLoturaErreserba() != null) {
			// } else {
			testuEremuaKopurua.setText("");
			textFieldDeskribapena.setText("");
			testuEremuaPrezioa.setText("");
			testuEremuaLuzapena.setText("");
			// }

		}

	}

	/**
	 * @return
	 */
	protected JLabel getLabel() {
		if (label == null) {
			label = new JLabel("@");
		}
		return label;
	}

}
