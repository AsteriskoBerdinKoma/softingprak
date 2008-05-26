package praktika.bezeroa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.server.ServerNotActiveException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

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
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
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
		});
		System.setProperty("java.security.policy", "client.policy");
		// Ereduak sortu
		this.setLocationRelativeTo(null);
		setRemoteServer();
		// LoturaErreserbaSistema = new ErreserbaSistema();

		// Bistak sortu
		ErreserbaKontroladorea erreserbaKontroladorea;
		erreserbaKontroladorea = new ErreserbaKontroladorea(urrunekoErreserba);
		ErreserbaBistarenLaburpena erreserbarenBistaSummary = new ErreserbaBistarenLaburpena(
				urrunekoErreserba);
		ErreserbaItenarenBista erreserbaItenarenBista = new ErreserbaItenarenBista(
				urrunekoErreserba);
		ErreserbarenBista erreserbarenBista;
		erreserbarenBista = new ErreserbarenBista(urrunekoErreserba);
		// Border erako osagaiak sortu
		// erreserbaKontroladorea.setBorder(BorderFactory
		// .createTitledBorder("Erreserba Eskaerak"));
		erreserbarenBistaSummary.setBorder(BorderFactory
				.createTitledBorder("Erreserbaren Laburpena"));
		erreserbaItenarenBista.setBorder(BorderFactory
				.createTitledBorder("Azken irteeraren laburpena"));
		erreserbarenBista.setBorder(BorderFactory
				.createTitledBorder("Erreserba"));
		//
		// Eraiki bista
		// getContentPane().add(erreserbarenBistaSummary, BorderLayout.NORTH);
		// getContentPane().add(erreserbaItenarenBista, BorderLayout.SOUTH);

		this.setVisible(true);

		ErreserbaBistarenLaburpena erreserbaBistarenLaburpena;
		erreserbaBistarenLaburpena = new ErreserbaBistarenLaburpena(
				urrunekoErreserba);
		final GroupLayout groupLayout = new GroupLayout(
				(JComponent) getContentPane());
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
																erreserbaBistarenLaburpena,
																GroupLayout.DEFAULT_SIZE,
																725,
																Short.MAX_VALUE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				erreserbaKontroladorea,
																				GroupLayout.PREFERRED_SIZE,
																				468,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				erreserbarenBista,
																				GroupLayout.PREFERRED_SIZE,
																				251,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(erreserbaBistarenLaburpena,
								GroupLayout.PREFERRED_SIZE, 91,
								GroupLayout.PREFERRED_SIZE).addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(erreserbarenBista,
												GroupLayout.DEFAULT_SIZE, 295,
												Short.MAX_VALUE).addComponent(
												erreserbaKontroladorea,
												GroupLayout.DEFAULT_SIZE, 295,
												Short.MAX_VALUE))
						.addContainerGap()));
		getContentPane().setLayout(groupLayout);
		pack();
	}

	public void setRemoteServer() {
		SelectHost frame = new SelectHost();
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
		while (frame.isVisible())
			;
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

	public class SelectHost extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JTextField hostTextField;

		/**
		 * Create the dialog
		 */
		public SelectHost() {
			super();
			setTitle("Zerbitzaria aukeratu");

			JLabel zerbitzariarenHelbideaLabel;
			zerbitzariarenHelbideaLabel = new JLabel();
			zerbitzariarenHelbideaLabel.setText("Zerbitzariaren helbidea:");

			hostTextField = new JTextField();
			hostTextField.setText("localhost");

			JButton adosButton;
			adosButton = new JButton();
			adosButton.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					String s = hostTextField.getText();
					if (s != null) {
						host = s;
						setVisible(false);
					}
				}
			});
			adosButton.setText("Ados");
			final GroupLayout groupLayout = new GroupLayout(
					(JComponent) getContentPane());
			groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(
					groupLayout.createSequentialGroup().addContainerGap()
							.addComponent(zerbitzariarenHelbideaLabel)
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(hostTextField,
									GroupLayout.DEFAULT_SIZE, 116,
									Short.MAX_VALUE).addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(adosButton).addContainerGap()));
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
																	zerbitzariarenHelbideaLabel)
															.addComponent(
																	adosButton)
															.addComponent(
																	hostTextField,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE)));
			getContentPane().setLayout(groupLayout);
			pack();
			//
		}
	}
}