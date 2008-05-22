package praktika.bezeroa;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.server.ServerNotActiveException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import praktika.partekatuak.ErreserbaInterface;

public class AplikazioNagusia extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String zerbitzuIzena = "ErreserbaSistema";

	private String host = "localhost";

	private ErreserbaInterface urrunekoErreserba;

	private static int unekoErreserbaZenbakia;

	/**
	 * Aplikazio nagusiaren hasierako klasea
	 * ======================================
	 */
	// private ErreserbaSistema LoturaErreserbaSistema;
	public AplikazioNagusia(String izenburuBat) {
		// Eraikitzaileen edukiontzia
		super();
		setTitle(izenburuBat);
		setSize(700, 600);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					urrunekoErreserba.notifyDesconnection();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ServerNotActiveException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		System.setProperty("java.security.policy", "client.policy");
		// Ereduak sortu
		this.setLocationRelativeTo(null);
		setRemoteServer();
		// LoturaErreserbaSistema = new ErreserbaSistema();

		// Bistak sortu
		ErreserbaKontroladorea erreserbaKontroladorea = new ErreserbaKontroladorea(
				urrunekoErreserba);
		ErreserbaBistarenLaburpena erreserbarenBistaSummary = new ErreserbaBistarenLaburpena(
				urrunekoErreserba);
		ErreserbaItenarenBista erreserbaItenarenBista = new ErreserbaItenarenBista(
				urrunekoErreserba);
		ErreserbarenBista erreserbarenBista = new ErreserbarenBista(
				urrunekoErreserba);
		// Border erako osagaiak sortu
		erreserbaKontroladorea.setBorder(BorderFactory
				.createTitledBorder("Erreserba Eskaerak"));
		erreserbarenBistaSummary.setBorder(BorderFactory
				.createTitledBorder("Erreserbaren Laburpena"));
		erreserbaItenarenBista.setBorder(BorderFactory
				.createTitledBorder("Azken irteeraren laburpena"));
		erreserbarenBista.setBorder(BorderFactory
				.createTitledBorder("Erreserba"));
		//
		// Eraiki bista
		getContentPane().setLayout(new BorderLayout());
		// getContentPane().add(erreserbarenBistaSummary, BorderLayout.NORTH);
		getContentPane().add(erreserbaKontroladorea, BorderLayout.CENTER);
		getContentPane().add(erreserbarenBista, BorderLayout.EAST);
		// getContentPane().add(erreserbaItenarenBista, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public void setRemoteServer() {
		try {
			// Assingn security manager
			if (System.getSecurityManager() == null)
				System.setSecurityManager(new RMISecurityManager());

			String zerbIzena = "rmi://" + host + "/" + zerbitzuIzena;
			// "rmi://IP_Helbidea:PortuZenb/ZerbitzuarenIzena"

			urrunekoErreserba = (ErreserbaInterface) Naming.lookup(zerbIzena);
			urrunekoErreserba.notifyConnection();
			JOptionPane jop = new JOptionPane(
					"Zerbitzariarekin konexioa ondo ezarri da",
					JOptionPane.INFORMATION_MESSAGE);
			jop.createDialog("Konexioa ezarria").setVisible(true);
		} catch (ConnectException ex) {
			JOptionPane jop = new JOptionPane(
					"Ezin izan da zerbitzariarekin konexioa ezarri. Egiaztatu adierazitako \n helbidean zerbitzaria martxan dagoela.",
					JOptionPane.ERROR_MESSAGE);
			jop.createDialog("Errorea konexioa ezartzean").setVisible(true);
		} catch (UnknownHostException ex) {
			JOptionPane jop = new JOptionPane("Ezin da zerbitzaria aurkitu",
					JOptionPane.ERROR_MESSAGE);
			jop.createDialog("Zerbitzari Ezezaguna").setVisible(true);
		} catch (Exception ex) {
			JOptionPane jop = new JOptionPane(
					"Errore ezezagun bat suertatu da",
					JOptionPane.ERROR_MESSAGE);
			jop.createDialog("Errore ezezaguna").setVisible(true);
		}
	}

	protected static int getUnekoErreserbaZenbakia() {
		return unekoErreserbaZenbakia;
	}

	protected static void setUnekoErreserbaZenbakia(int unekoErreserbaZenbakia) {
		AplikazioNagusia.unekoErreserbaZenbakia = unekoErreserbaZenbakia;
	}
}