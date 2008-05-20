package praktika.bezeroa;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import praktika.partekatuak.ErreserbaInterface;

/**
 * Erreserbak kontrolatzeko klaseak.
 */

public class ErreserbaKontroladorea extends JPanel implements ActionListener,
		ItemListener {
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

	private JButton botoiaSartuIrteera = new JButton("Sartu Erreserba");

	private JButton botoiaSartuTurista = new JButton("Sartu Turista");

	private JButton botoiaSartuBidali = new JButton("Bidali");

	private JButton botoiaSartuEzeztatu = new JButton("Ezeztatu");

	//
	String[] a = { "Mendizabal Bidaiak", "Izotz Txangoak", "sw" };

	private JComboBox comboErreserbaAgentea = new JComboBox(a);

	private JComboBox comboPertsonaKopurua = new JComboBox();

	String[] b = { "4EgunParis", "10EgunItalia", "7EgunLondon" };

	private JComboBox comboIrteerarenEzaugarriak = new JComboBox(b);

	private JComboBox comboIrteeraData = new JComboBox();

	//
	private JTextField testuEremuaBaieztapenZenbakia = new JTextField();

	private JTextField testuEremuaIzena = new JTextField();

	private JTextField testuEremuaHelbidea = new JTextField();

	private JTextField testuEremuaTelefonoa = new JTextField();

	private ErreserbaInterface LoturaErreserbaSistema;

	public ErreserbaKontroladorea(ErreserbaInterface erreserbaSistema) {
		this.LoturaErreserbaSistema = erreserbaSistema;
		//
		Iterator iterator;

		setLayout(new GridLayout(8, 3, 25, 25));
		setFont(new Font("Arial", Font.PLAIN, 12));
		setBackground(Color.lightGray);
		setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		// errenkada 1
		add(etiketaErreserbaAgentea);
		add(comboErreserbaAgentea);
		add(new JLabel("            "));
		// errenkada 2
		add(etiketaPertsonaKopurua);
		add(comboPertsonaKopurua);
		add(botoiaErreserbaBerria);
		// errenkada 3
		add(etiketaIrteerarenEzaugarriak);
		add(comboIrteerarenEzaugarriak);
		add(new JLabel("            "));
		// errenkada 4
		add(etiketaIrteeraData);
		add(comboIrteeraData);
		add(new JLabel("            "));
		// errenkada 5
		add(etiketaBaieztapenZenbakia);
		add(testuEremuaBaieztapenZenbakia);
		add(botoiaSartuIrteera);
		// errenkada 6
		add(etiketaIzena);
		add(testuEremuaIzena);
		add(botoiaSartuTurista);
		// errenkada 7
		add(etiketaHelbidea);
		add(testuEremuaHelbidea);
		add(botoiaSartuBidali);
		// errenkada 8
		add(etiketaTelefonoa);
		add(testuEremuaTelefonoa);
		add(botoiaSartuEzeztatu);
		// Gehitu event listener-ak
		botoiaErreserbaBerria.addActionListener(this);
		botoiaSartuIrteera.addActionListener(this);
		comboIrteerarenEzaugarriak.addItemListener(this);
		botoiaSartuTurista.addActionListener(this);
		botoiaSartuBidali.addActionListener(this);
		botoiaSartuEzeztatu.addActionListener(this);
		// Botoiak ipini
		botoiaErreserbaBerria.setEnabled(true);
		botoiaSartuIrteera.setEnabled(false);
		botoiaSartuTurista.setEnabled(false);
		botoiaSartuBidali.setEnabled(false);
		botoiaSartuEzeztatu.setEnabled(false);
		//
		// Pertsona Kopurua aukera laukia
		for (int i = 1; i <= 10; i++) {
			comboPertsonaKopurua.addItem(Integer.toString(i));
		}

	}

	public void actionPerformed(ActionEvent event) {
		try {
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			DateFormat dataFormat = DateFormat.getDateInstance();
			if (event.getSource() == botoiaErreserbaBerria) {
				// Sarrera
				String strNumberInParty = (String) comboPertsonaKopurua
						.getSelectedItem();
				int pertsonaKopurua = Integer.parseInt(strNumberInParty);
				String irteeraHandizkariarenIdentifikatzailea = (String) comboErreserbaAgentea
						.getSelectedItem();
				// Ereduak sartu
				LoturaErreserbaSistema.erreserbaBerria(pertsonaKopurua,
						irteeraHandizkariarenIdentifikatzailea);

				// Botoiak ipini
				botoiaErreserbaBerria.setEnabled(false);
				botoiaSartuIrteera.setEnabled(true);
				botoiaSartuTurista.setEnabled(false);
				botoiaSartuBidali.setEnabled(false);
				botoiaSartuEzeztatu.setEnabled(false);
			}
			if (event.getSource() == botoiaSartuIrteera) {
				Date irteeraData = null;
				// Sarrera
				String zerbitzuarenKodea = (String) comboIrteerarenEzaugarriak
						.getSelectedItem();
				String strIrteeraData = (String) comboIrteeraData
						.getSelectedItem();
				try {
					irteeraData = dataFormat.parse(strIrteeraData);
				} catch (ParseException e) {
				}
				try {
					int baieztapenZenbakia = Integer
							.parseInt(testuEremuaBaieztapenZenbakia.getText());
					// Ereduak sartu
					LoturaErreserbaSistema.sartuIrteera(baieztapenZenbakia,
							zerbitzuarenKodea, irteeraData);
				} catch (NumberFormatException numberFormatException) {
					JOptionPane
							.showMessageDialog(new JFrame(),
									"Mesedez zenbaki oso bat sartu",
									"Sarrera errore mezua",
									JOptionPane.WARNING_MESSAGE);
				}
				// Botoiak ipini
				botoiaErreserbaBerria.setEnabled(false);
				botoiaSartuIrteera.setEnabled(true);
				botoiaSartuTurista.setEnabled(true);
				botoiaSartuBidali.setEnabled(false);
				botoiaSartuEzeztatu.setEnabled(false);
			}
			if (event.getSource() == botoiaSartuTurista) {
				// Sarrera
				String izena = testuEremuaIzena.getText();
				String helbidea = testuEremuaHelbidea.getText();
				String telefonoa = testuEremuaTelefonoa.getText();
				// Ereduak sartu
				LoturaErreserbaSistema.sartuTurista(izena, helbidea, telefonoa);
				// Botoiak ipini
				botoiaErreserbaBerria.setEnabled(false);
				botoiaSartuIrteera.setEnabled(false);
				botoiaSartuTurista.setEnabled(false);
				botoiaSartuBidali.setEnabled(true);
				botoiaSartuEzeztatu.setEnabled(true);

			}
			if (event.getSource() == botoiaSartuBidali) {
				// Ereduak sartu
				LoturaErreserbaSistema.submit();
				// Botoiak ipini
				botoiaErreserbaBerria.setEnabled(true);
				botoiaSartuIrteera.setEnabled(false);
				botoiaSartuTurista.setEnabled(false);
				botoiaSartuBidali.setEnabled(false);
				botoiaSartuEzeztatu.setEnabled(false);
				// Testu eremuak ezabatu
				testuEremuaBaieztapenZenbakia.setText("");
				testuEremuaIzena.setText("");
				testuEremuaHelbidea.setText("");
				testuEremuaTelefonoa.setText("");
			}
			if (event.getSource() == botoiaSartuEzeztatu) {
				// Ereduak sartu
				LoturaErreserbaSistema.ezeztatu();
				// Botoiak ipini
				botoiaErreserbaBerria.setEnabled(true);
				botoiaSartuIrteera.setEnabled(false);
				botoiaSartuTurista.setEnabled(false);
				botoiaSartuBidali.setEnabled(false);
				botoiaSartuEzeztatu.setEnabled(false);
				// Testu eremuak ezabatu
				testuEremuaBaieztapenZenbakia.setText("");
				testuEremuaIzena.setText("");
				testuEremuaHelbidea.setText("");
				testuEremuaTelefonoa.setText("");
			}
		} catch (RemoteException e) {
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
		// Combo boxa ezabatu
		comboIrteeraData.removeAllItems();

		comboIrteeraData.addItem("Dec 5, 2006 ");
		comboIrteeraData.addItem("Dec 12, 2006");
		comboIrteeraData.addItem("Dec 15, 2006");
	}
}
