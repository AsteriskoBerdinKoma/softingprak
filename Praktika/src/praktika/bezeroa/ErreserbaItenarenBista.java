package praktika.bezeroa;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.ErreserbaItenarenBistaObserver;
import praktika.partekatuak.ErreserbaKontroladoreaObserver;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

/**
 * Lehen mailarekin(Bista)lan egiten duen klasea.
 */

public class ErreserbaItenarenBista extends JPanel {
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
			ErreserbaItenarenBistaObserver remErrItBista = new ErreserbaItenarenBistaObserver(this);
			erreserbaSistema.addObserver(remErrItBista);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setLayout(new GridLayout(1, 5, 25, 25));
		setFont(new Font("Arial", Font.PLAIN, 12));
		setBackground(Color.lightGray);
		setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		// row 1
		add(testuEremuaKopurua);
		add(new JLabel("@       "));
		add(testuEremuaPrezioa);
		add(textFieldDeskribapena);
		add(testuEremuaLuzapena);
		// testu eremuak irakurtzeko bakarrik aldatu
		testuEremuaKopurua.setEditable(false);
		textFieldDeskribapena.setEditable(false);
		testuEremuaPrezioa.setEditable(false);
		testuEremuaLuzapena.setEditable(false);
	}

}
