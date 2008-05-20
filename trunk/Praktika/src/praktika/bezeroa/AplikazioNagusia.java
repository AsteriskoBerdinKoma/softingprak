package praktika.bezeroa;

import java.awt.BorderLayout;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import praktika.partekatuak.ErreserbaInterface;
import praktika.zerbitzaria.ErreserbaSistema;

public class AplikazioNagusia extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String zerbitzuIzena = "ErreserbaSistema";
	
	private String host = "localhost";
	
	private ErreserbaInterface urrunekoErreserba;

	/**
	 * Aplikazio nagusiaren hasierako klasea
	 * ======================================
	 */
	private ErreserbaSistema LoturaErreserbaSistema;

	public AplikazioNagusia(String izenburuBat) {
		// Eraikitzaileen edukiontzia
		super();
		try {
		setTitle(izenburuBat);
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.setProperty("java.security.policy", "client.policy");
		// Ereduak sortu
			this.setLocationRelativeTo(null);
			setRemoteServer();
			LoturaErreserbaSistema = new ErreserbaSistema();

		// Bistak sortu
		ErreserbaKontroladorea erreserbaKontroladorea = new ErreserbaKontroladorea(
				LoturaErreserbaSistema);
		ErreserbaBistarenLaburpena erreserbarenBistaSummary = new ErreserbaBistarenLaburpena(
				LoturaErreserbaSistema);
		ErreserbaItenarenBista erreserbaItenarenBista = new ErreserbaItenarenBista(
				LoturaErreserbaSistema);
		ErreserbarenBista erreserbarenBista = new ErreserbarenBista(
				LoturaErreserbaSistema);
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
		getContentPane().add(erreserbarenBistaSummary, BorderLayout.NORTH);
		getContentPane().add(erreserbaKontroladorea, BorderLayout.CENTER);
		getContentPane().add(erreserbarenBista, BorderLayout.EAST);
		getContentPane().add(erreserbaItenarenBista, BorderLayout.SOUTH);

		this.setVisible(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRemoteServer(){
		try {
			// Assingn security manager
			if (System.getSecurityManager() == null)
				System.setSecurityManager(new RMISecurityManager());

			String zerbIzena = "rmi://" + host  + "/" + zerbitzuIzena;
			// "rmi://IP_Helbidea:PortuZenb/ZerbitzuarenIzena"

			urrunekoErreserba = (ErreserbaInterface) Naming.lookup(zerbIzena);
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
			JOptionPane jop = new JOptionPane(
					"Ezin da zerbitzaria aurkitu",
					JOptionPane.ERROR_MESSAGE);
			jop.createDialog("Zerbitzari Ezezaguna").setVisible(true);
		} catch (Exception ex) {
			JOptionPane jop = new JOptionPane(
					"Errore ezezagun bat suertatu da",
					JOptionPane.ERROR_MESSAGE);
			jop.createDialog("Errore ezezaguna").setVisible(true);
		}
	}

//	public static void main(String[] args) {
//		new AplikazioNagusia("Erreserba Sistema");
//	}
}