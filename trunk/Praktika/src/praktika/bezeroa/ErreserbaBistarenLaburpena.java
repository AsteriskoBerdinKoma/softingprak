package praktika.bezeroa;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;

import praktika.partekatuak.Erreserba;
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

	private JLabel etiketaGuztira = new JLabel("Guztira");

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

		etiketaData.hashCode();
		// testu eremuak irakurtzeko bakarrik aldatu
		testuEremuaData.setEditable(false);

		etiketaAgentearenIzena.hashCode();
		testuEremuaAgentearenIzena.setEditable(false);

		etiketaErreserbaZenbakia.hashCode();
		testuEremuaErreserbarenZenbakia.setEditable(false);

		etiketaGuztira.hashCode();
		testuEremuaGuztira.setEditable(false);
		setBorder(new TitledBorder(null, "Erreserbaren Laburpena",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		// row 1
		// row 2
		final GroupLayout groupLayout = new GroupLayout((JComponent) this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																etiketaErreserbaZenbakia,
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																etiketaData,
																GroupLayout.Alignment.TRAILING))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																testuEremuaData,
																GroupLayout.DEFAULT_SIZE,
																95,
																Short.MAX_VALUE)
														.addComponent(
																testuEremuaErreserbarenZenbakia,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																95,
																Short.MAX_VALUE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																etiketaAgentearenIzena)
														.addComponent(
																etiketaGuztira))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																testuEremuaAgentearenIzena,
																GroupLayout.DEFAULT_SIZE,
																96,
																Short.MAX_VALUE)
														.addComponent(
																testuEremuaGuztira,
																GroupLayout.DEFAULT_SIZE,
																96,
																Short.MAX_VALUE))
										.addGap(14, 14, 14)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																etiketaData)
														.addComponent(
																testuEremuaData,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																etiketaAgentearenIzena)
														.addComponent(
																testuEremuaAgentearenIzena,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				etiketaErreserbaZenbakia)
																		.addComponent(
																				testuEremuaErreserbarenZenbakia,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				etiketaGuztira)
																		.addComponent(
																				testuEremuaGuztira,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(307, 307, 307)));
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
		public void update(RemoteObservable observable, Object objektua)
				throws RemoteException {
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			DateFormat dataFormat = DateFormat.getDateInstance();
			// ErreserbaInterface erreserbaSistema = (ErreserbaInterface)
			// observable;
			//
			// if (erreserbaSistema.getLoturaErreserba() != null) {
			// } else {
			if (objektua instanceof Erreserba) {
				Erreserba e = (Erreserba) objektua;
				if (AplikazioNagusia.getUnekoErreserbaZenbakia() == e
						.getErreserbaZenbakia()) {
					switch (e.getEgoera()) {
					case Erreserba.BAIEZTATUA:
						testuEremuaData.setText(dataFormat.format(e.getData()
								.getTime()));
						testuEremuaGuztira.setText(numberFormat.format(e
								.getPrezioa()));
						testuEremuaAgentearenIzena.setText(e.getAgenteIzena());
						testuEremuaErreserbarenZenbakia.setText(String
								.valueOf(e.getErreserbaZenbakia()));
						break;
					case Erreserba.BUKATUA:
						testuEremuaData.setText("");
						testuEremuaGuztira.setText("");
						testuEremuaAgentearenIzena.setText("");
						testuEremuaErreserbarenZenbakia.setText("");
						break;
					case Erreserba.EZEZTATUA:
						testuEremuaData.setText("");
						testuEremuaGuztira.setText("");
						testuEremuaAgentearenIzena.setText("");
						testuEremuaErreserbarenZenbakia.setText("");
						break;
					default:
						break;
					}
				}
			}
			// testuEremuaData.setText("");
			// testuEremuaGuztira.setText("");
			// testuEremuaAgentearenIzena.setText("");
			// testuEremuaErreserbarenZenbakia.setText("");
			// }
		}
	}
}
