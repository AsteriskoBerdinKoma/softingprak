package praktika.bezeroa;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;

import praktika.partekatuak.Agentea;
import praktika.partekatuak.Erreserba;
import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.Irteera;
import praktika.partekatuak.Turista;
import praktika.partekatuak.TuristaNotifikazioa;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

/**
 * Erreserbak kontrolatzeko klaseak.
 */

public class ErreserbaKontroladorea extends JPanel implements ActionListener,
		ItemListener {
	private JTextField testuEremuaBaieztapenZenbakia;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Kontrolak
	private JLabel etiketaErreserbaAgentea = new JLabel("Erreserba Agentea");

	private JLabel etiketaPertsonaKopurua = new JLabel("Pertsona Kopurua");

	private JLabel etiketaIrteerarenEzaugarriak = new JLabel(
			"Irteeraren Ezaugarriak");

	private JLabel etiketaIrteeraData = new JLabel("Irteera Data");

	private JLabel etiketaBaieztapenZenbakia = new JLabel("Baieztapen Zenbakia");

	private JLabel etiketaIzena = new JLabel("Izena");

	private JLabel etiketaHelbidea = new JLabel("Helbidea");

	private JLabel etiketaTelefonoa = new JLabel("Telefonoa");

	// 
	private JButton botoiaErreserbaBerria = new JButton("Erreserba Berria");

	private JButton botoiaSartuErreserba = new JButton("Sartu Erreserba");

	private JButton botoiaSartuTurista = new JButton("Sartu Turista");

	private JButton botoiaBukatu = new JButton("Bukatu");

	private JButton botoiaEzeztatu = new JButton("Ezeztatu");

	// String[] a = { "Mendizabal Bidaiak", "Izotz Txangoak", "sw" };

	private JComboBox comboErreserbaAgentea;// = new JComboBox(a);

	private JComboBox comboPertsonaKopurua = new JComboBox();

	// String[] b = { "4EgunParis", "10EgunItalia", "7EgunLondon" };

	private JComboBox comboIrteerarenEzaugarriak;// = new JComboBox(b);

	private JComboBox comboIrteeraData;// = new JComboBox();

	//

	private JTextField testuEremuaIzena = new JTextField();

	private JTextField testuEremuaHelbidea = new JTextField();

	private JTextField testuEremuaTelefonoa = new JTextField();

	private Vector<Irteera> vIrteerak = new Vector<Irteera>();
	private Vector<Irteera> vIrteeraDistinct = new Vector<Irteera>();
	private Vector<Agentea> vAgenteak = new Vector<Agentea>();

	private Erreserba erreserba;

	boolean datuakGehituta = true;

	private ErreserbaInterface LoturaErreserbaSistema;

	public ErreserbaKontroladorea(ErreserbaInterface erreserbaSistema) {
		try {
			UrrunekoBegiralea remErrKontr = new UrrunekoBegiralea();
			erreserbaSistema.addObserver(remErrKontr);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.LoturaErreserbaSistema = erreserbaSistema;
		// Iterator iterator;
		DateFormat dataFormat = DateFormat.getDateInstance();

		etiketaIrteerarenEzaugarriak.hashCode();
		comboIrteerarenEzaugarriak = new JComboBox();

		etiketaIrteeraData.hashCode();
		comboIrteeraData = new JComboBox();

		etiketaErreserbaAgentea.hashCode();
		comboErreserbaAgentea = new JComboBox();
		try {
			vAgenteak = LoturaErreserbaSistema.getErreserbaAgenteak();
			for (Agentea a : vAgenteak) {
				comboErreserbaAgentea.addItem(a.getIzena());
			}
			comboErreserbaAgentea.setSelectedIndex(0);
			vIrteerak = LoturaErreserbaSistema.getIrteerenEzaugarriak(vAgenteak
					.firstElement().getIzena());
			String defaultChoice = vIrteerak.firstElement().getEzaugarriak();
			for (Irteera i : vIrteerak) {
				if (i.compareTo(vIrteeraDistinct) != 0) {
					comboIrteerarenEzaugarriak.addItem(i.getEzaugarriak());
					vIrteeraDistinct.add(i);
				}
				if (i.getEzaugarriak().equals(defaultChoice)) {
					comboIrteeraData.addItem(dataFormat.format(i.getData()
							.getTime()));
				}
			}
			comboIrteerarenEzaugarriak.setSelectedIndex(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFont(new Font("Arial", Font.PLAIN, 12));
		setBorder(new TitledBorder(null, "Erreserba Eskaerak",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		// errenkada 1
		// errenkada 2
		// errenkada 3
		// errenkada 4
		// errenkada 5

		etiketaPertsonaKopurua.hashCode();

		comboPertsonaKopurua.hashCode();
		// errenkada 6
		// errenkada 7
		// errenkada 8
		// Gehitu event listener-ak
		botoiaErreserbaBerria.addActionListener(this);

		etiketaBaieztapenZenbakia.hashCode();
		botoiaSartuErreserba.addActionListener(this);
		comboIrteerarenEzaugarriak.addItemListener(this);
		comboErreserbaAgentea.addItemListener(this);

		etiketaIzena.hashCode();

		testuEremuaIzena.hashCode();
		botoiaSartuTurista.addActionListener(this);

		etiketaHelbidea.hashCode();

		testuEremuaHelbidea.hashCode();
		botoiaBukatu.addActionListener(this);

		etiketaTelefonoa.hashCode();

		testuEremuaTelefonoa.hashCode();
		botoiaEzeztatu.addActionListener(this);
		// Botoiak ipini
		botoiaErreserbaBerria.setEnabled(true);
		botoiaSartuErreserba.setEnabled(false);
		botoiaSartuTurista.setEnabled(false);
		botoiaBukatu.setEnabled(false);
		botoiaEzeztatu.setEnabled(false);

		testuEremuaBaieztapenZenbakia = new JTextField();
		testuEremuaBaieztapenZenbakia.setEditable(false);
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
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																etiketaTelefonoa)
														.addComponent(
																etiketaHelbidea)
														.addComponent(
																etiketaIzena)
														.addComponent(
																etiketaErreserbaAgentea)
														.addComponent(
																etiketaPertsonaKopurua)
														.addComponent(
																etiketaIrteerarenEzaugarriak)
														.addComponent(
																etiketaIrteeraData)
														.addComponent(
																etiketaBaieztapenZenbakia))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																testuEremuaTelefonoa,
																GroupLayout.DEFAULT_SIZE,
																164,
																Short.MAX_VALUE)
														.addComponent(
																testuEremuaHelbidea,
																GroupLayout.DEFAULT_SIZE,
																164,
																Short.MAX_VALUE)
														.addComponent(
																testuEremuaIzena,
																GroupLayout.DEFAULT_SIZE,
																164,
																Short.MAX_VALUE)
														.addComponent(
																testuEremuaBaieztapenZenbakia,
																GroupLayout.DEFAULT_SIZE,
																164,
																Short.MAX_VALUE)
														.addComponent(
																comboErreserbaAgentea,
																0, 164,
																Short.MAX_VALUE)
														.addComponent(
																comboIrteerarenEzaugarriak,
																0, 164,
																Short.MAX_VALUE)
														.addComponent(
																comboIrteeraData,
																0, 164,
																Short.MAX_VALUE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				comboPertsonaKopurua,
																				0,
																				164,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																botoiaEzeztatu,
																GroupLayout.PREFERRED_SIZE,
																129,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																botoiaBukatu,
																GroupLayout.PREFERRED_SIZE,
																129,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																botoiaSartuTurista,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																129,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																botoiaSartuErreserba,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																129,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																botoiaErreserbaBerria,
																GroupLayout.PREFERRED_SIZE,
																129,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								etiketaErreserbaAgentea).addComponent(
								comboErreserbaAgentea,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								etiketaPertsonaKopurua).addComponent(
								comboPertsonaKopurua,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								botoiaErreserbaBerria)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								etiketaIrteerarenEzaugarriak).addComponent(
								comboIrteerarenEzaugarriak,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								etiketaIrteeraData).addComponent(
								comboIrteeraData, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								etiketaBaieztapenZenbakia).addComponent(
								testuEremuaBaieztapenZenbakia,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								botoiaSartuErreserba)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								testuEremuaIzena, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								etiketaIzena).addComponent(botoiaSartuTurista,
								GroupLayout.PREFERRED_SIZE, 26,
								GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								testuEremuaHelbidea,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								etiketaHelbidea).addComponent(botoiaBukatu,
								GroupLayout.PREFERRED_SIZE, 26,
								GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								testuEremuaTelefonoa,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(
								etiketaTelefonoa).addComponent(botoiaEzeztatu,
								GroupLayout.PREFERRED_SIZE, 26,
								GroupLayout.PREFERRED_SIZE)).addContainerGap(
						26, Short.MAX_VALUE)));
		groupLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { botoiaBukatu, botoiaErreserbaBerria,
						botoiaEzeztatu, botoiaSartuErreserba,
						botoiaSartuTurista });
		groupLayout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { botoiaBukatu, botoiaEzeztatu,
						botoiaSartuErreserba, botoiaSartuTurista });
		setLayout(groupLayout);
		//
		// Pertsona Kopurua aukera laukia
		for (int i = 1; i <= 10; i++) {
			comboPertsonaKopurua.addItem(Integer.toString(i));
		}

	}

	public void actionPerformed(ActionEvent event) {
		try {
			// NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			DateFormat dataFormat = DateFormat.getDateInstance();
			if (event.getSource() == botoiaErreserbaBerria) {
				// Sarrera
				erreserba = new Erreserba();
				String strNumberInParty = (String) comboPertsonaKopurua
						.getSelectedItem();
				int pertsonaKopurua = Integer.parseInt(strNumberInParty);
				String irteeraHandizkariarenIdentifikatzailea = (String) comboErreserbaAgentea
						.getSelectedItem();
				String selectIrteera = comboIrteerarenEzaugarriak
						.getSelectedItem().toString();
				Date data = dataFormat.parse(comboIrteeraData.getSelectedItem()
						.toString());
				Calendar cal = new GregorianCalendar();
				cal.setTime(data);
				int kodea = -1;
				for (Agentea a : vAgenteak) {
					if (a.getIzena().equals(
							irteeraHandizkariarenIdentifikatzailea)) {
						kodea = a.getAgenteKodea();
					}
				}
				boolean bidaiaDago = false;
				for (Irteera i : vIrteerak) {
					if (i.getEzaugarriak().equals(selectIrteera)
							&& i.getData().get(Calendar.YEAR) == cal
									.get(Calendar.YEAR)
							&& i.getData().get(Calendar.MONTH) == cal
									.get(Calendar.MONTH)
							&& i.getData().get(Calendar.DAY_OF_MONTH) == cal
									.get(Calendar.DAY_OF_MONTH)
							&& i.getAgenteKodea() == kodea) {
						int erZ = LoturaErreserbaSistema
								.getHurrengoErreserbaZenb();
						erreserba = new Erreserba(erZ, i.getData(),
								pertsonaKopurua, -1, i.getIrteerarenKodea());
						AplikazioNagusia.setUnekoErreserbaZenbakia(erZ);
						// Ereduak sartu
						LoturaErreserbaSistema.erreserbaBerria(erreserba);
						// Botoiak ipini
						// botoiaErreserbaBerria.setEnabled(false);
						// botoiaSartuErreserba.setEnabled(true);
						// botoiaSartuTurista.setEnabled(false);
						// botoiaBukatu.setEnabled(false);
						// botoiaEzeztatu.setEnabled(false);
						bidaiaDago = true;
						break;
					}
				}
				if (!bidaiaDago) {
					JOptionPane jop = new JOptionPane(
							"Emandako datuekin ez dago bidairik.\nSaiatu berrio.",
							JOptionPane.INFORMATION_MESSAGE);
					jop.createDialog("Oharra").setVisible(true);
				}

			}
			if (event.getSource() == botoiaSartuErreserba) {
				LoturaErreserbaSistema.sartuIrteera(erreserba);
				// Botoiak ipini
				// botoiaErreserbaBerria.setEnabled(false);
				// botoiaSartuErreserba.setEnabled(true);
				// botoiaSartuTurista.setEnabled(true);
				// botoiaBukatu.setEnabled(false);
				// botoiaEzeztatu.setEnabled(false);
			}
			if (event.getSource() == botoiaSartuTurista) {
				// Sarrera
				String izena = testuEremuaIzena.getText();
				String helbidea = testuEremuaHelbidea.getText();
				String telefonoa = testuEremuaTelefonoa.getText();
				// Ereduak sartu
				if (!izena.isEmpty() && !helbidea.isEmpty()
						&& !telefonoa.isEmpty()) {
					Turista turista = new Turista(izena, helbidea, telefonoa,
							erreserba.getErreserbaZenbakia());
					LoturaErreserbaSistema.sartuTurista(turista);
					// Botoiak ipini
					// botoiaErreserbaBerria.setEnabled(false);
					// botoiaSartuErreserba.setEnabled(false);
					// botoiaEzeztatu.setEnabled(true);
				} else {
					JOptionPane jop = new JOptionPane(
							"Turistaren datu guztiak sartu behar dira.\nSaiatu berrio.",
							JOptionPane.ERROR_MESSAGE);
					jop.createDialog("Turistaren datuak faltan").setVisible(
							true);
				}

			}
			if (event.getSource() == botoiaBukatu) {
				// Ereduak sartu
				LoturaErreserbaSistema.submit(erreserba);
				// Botoiak ipini
				// botoiaErreserbaBerria.setEnabled(true);
				// botoiaSartuErreserba.setEnabled(false);
				// botoiaSartuTurista.setEnabled(false);
				// botoiaBukatu.setEnabled(false);
				// botoiaEzeztatu.setEnabled(false);
				// Testu eremuak ezabatu
				testuEremuaBaieztapenZenbakia.setText("");
				testuEremuaIzena.setText("");
				testuEremuaHelbidea.setText("");
				testuEremuaTelefonoa.setText("");
			}
			if (event.getSource() == botoiaEzeztatu) {
				// Ereduak sartu
				LoturaErreserbaSistema.ezeztatu(erreserba);
				// Botoiak ipini
				// botoiaErreserbaBerria.setEnabled(true);
				// botoiaSartuErreserba.setEnabled(false);
				// botoiaSartuTurista.setEnabled(false);
				// botoiaBukatu.setEnabled(false);
				// botoiaEzeztatu.setEnabled(false);
				// Testu eremuak ezabatu
				testuEremuaBaieztapenZenbakia.setText("");
				testuEremuaIzena.setText("");
				testuEremuaHelbidea.setText("");
				testuEremuaTelefonoa.setText("");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gertaeraren trataera
	 * 
	 * @param itemStateChanged
	 *            java.awt.event.ItemEvent
	 */
	public void itemStateChanged(ItemEvent itemStateChanged) {

		DateFormat dataFormat = DateFormat.getDateInstance();
		if (itemStateChanged.getSource() == comboErreserbaAgentea) {
			datuakGehituta = false;
			comboIrteerarenEzaugarriak.removeAllItems();
			comboIrteeraData.removeAllItems();
			vIrteerak.removeAllElements();
			vIrteeraDistinct.removeAllElements();
			try {
				vIrteerak = LoturaErreserbaSistema
						.getIrteerenEzaugarriak(comboErreserbaAgentea
								.getSelectedItem().toString());
				System.out.println(comboErreserbaAgentea.getSelectedItem()
						.toString());
				String defaultChoice = vIrteerak.firstElement()
						.getEzaugarriak();

				for (Irteera i : vIrteerak) {
					if (i.compareTo(vIrteeraDistinct) != 0) {
						comboIrteerarenEzaugarriak.addItem(i.getEzaugarriak());
						vIrteeraDistinct.add(i);
					}
					if (i.getEzaugarriak().equals(defaultChoice)) {
						comboIrteeraData.addItem(dataFormat.format(i.getData()
								.getTime()));
					}
				}
				datuakGehituta = true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out
						.println("Urruneko zerbitzariarekin ezin izan da komunikatu");
			}
		} else if (itemStateChanged.getSource() == comboIrteerarenEzaugarriak
				&& datuakGehituta) {
			comboIrteeraData.removeAllItems();
			for (Irteera i : vIrteerak) {
				if (i.getEzaugarriak().equals(
						comboIrteerarenEzaugarriak.getSelectedItem().toString()
								.trim())) {
					comboIrteeraData.addItem(dataFormat.format(i.getData()
							.getTime()));
				}
			}
		}
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
			if (arg instanceof Erreserba) {
				erreserba = (Erreserba) arg;
				if (erreserba.getErreserbaZenbakia() == AplikazioNagusia
						.getUnekoErreserbaZenbakia()) {
					switch (erreserba.getEgoera()) {
					case Erreserba.BERRIA:
						botoiaErreserbaBerria.setEnabled(false);
						break;
					case Erreserba.BAIEZTATUA:
						testuEremuaBaieztapenZenbakia.setText(String
								.valueOf(erreserba.getBaieztapenZenbakia()));
						botoiaSartuErreserba.setEnabled(true);
						botoiaEzeztatu.setEnabled(true);
						break;
					case Erreserba.UKATUA:
						botoiaErreserbaBerria.setEnabled(true);
						botoiaSartuErreserba.setEnabled(false);
						botoiaSartuTurista.setEnabled(false);
						botoiaBukatu.setEnabled(false);
						botoiaEzeztatu.setEnabled(false);
						break;
					case Erreserba.SARTUTA:
						botoiaSartuErreserba.setEnabled(false);
						botoiaSartuTurista.setEnabled(true);
						botoiaEzeztatu.setEnabled(true);
						testuEremuaHelbidea.setEnabled(true);
						testuEremuaIzena.setEditable(true);
						testuEremuaTelefonoa.setEditable(true);
						break;
					case Erreserba.EZEZTATUA:
						botoiaErreserbaBerria.setEnabled(true);
						botoiaSartuErreserba.setEnabled(false);
						botoiaSartuTurista.setEnabled(false);
						botoiaBukatu.setEnabled(false);
						botoiaEzeztatu.setEnabled(false);
						break;
					case Erreserba.BUKATUA:
						botoiaErreserbaBerria.setEnabled(true);
						botoiaSartuErreserba.setEnabled(false);
						botoiaSartuTurista.setEnabled(false);
						botoiaBukatu.setEnabled(false);
						botoiaEzeztatu.setEnabled(false);
						break;
					default:
						botoiaErreserbaBerria.setEnabled(true);
						botoiaSartuErreserba.setEnabled(false);
						botoiaSartuTurista.setEnabled(false);
						botoiaBukatu.setEnabled(false);
						botoiaEzeztatu.setEnabled(false);
						break;
					}
				} else {
					System.out
							.println("Notifikazioa beste bezero batentzat zen.");
				}
			} else if (arg instanceof TuristaNotifikazioa) {
				TuristaNotifikazioa tn = (TuristaNotifikazioa) arg;
				if (tn.getErreserbaZenbakia() == AplikazioNagusia
						.getUnekoErreserbaZenbakia()) {
					botoiaBukatu.setEnabled(true);
					testuEremuaHelbidea.setText("");
					testuEremuaIzena.setText("");
					testuEremuaTelefonoa.setText("");
					if (tn.getLibreKop() == 0) {
						botoiaSartuTurista.setEnabled(false);
						System.out
								.println("Erreserbako turista guztien informazioa sartu da\n");
					} else if (tn.getLibreKop() == -1
							|| tn.getZenbatgarrena() == -1) {
						System.out.println("Errorea turista sartzean\n");
						botoiaBukatu.setEnabled(false);
					}
				}
			} else {
				System.out.println("Notifikazioa beste bezero batentzat zen.");
			}
		}
	}

}
