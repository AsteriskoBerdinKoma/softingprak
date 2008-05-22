package praktika.zerbitzaria;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import praktika.partekatuak.Agentea;
import praktika.partekatuak.Erreserba;
import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.Irteera;
import praktika.partekatuak.Turista;
import praktika.partekatuak.TuristaNotifikazioa;
import praktika.partekatuak.remoteObservable.RemoteObservableImpl;

/**
 * Bigarren mailako antolatzeko klasea
 */

class ErreserbaSistema extends RemoteObservableImpl implements
		ErreserbaInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Zerbitzuak izango duen izena.
	 */
	public static final String zerbitzuIzena = "ErreserbaSistema";

	// private Erreserba LoturaErreserba;
	private AplikazioDatuBase aDB;

	private ZerbitzariaFrame frame;

	private boolean konektatua;

	private int unekoErreserbaZenbakia;
	private int unekoBaieztapenZenbakia;

	public ErreserbaSistema() throws RemoteException {
		frame = new ZerbitzariaFrame();
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				aDB.finalize();
				frame.gehituEkintza(getCurrentTime()
						+ ": Datu baseko konexioa itxita.");
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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		aDB = AplikazioDatuBase.instance();
		if (aDB.isConnectedToDatabase())
			frame.gehituEkintza(getCurrentTime() + ": Datu basera konektatua");
		else
			frame.gehituEkintza(getCurrentTime()
					+ ": Ezin izan da datu basera konexioa ezarri");
		try {
			unekoErreserbaZenbakia = aDB.getAzkenErreserbarenZenbakia();
			unekoBaieztapenZenbakia = aDB.getLastBaieztapenZenb();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#ezeztatu()
	 */
	public void ezeztatu(Erreserba erreserba) throws RemoteException {
		try {
			aDB.deleteErreserba(erreserba.getErreserbaZenbakia());
			erreserba.ezeztatu();
			// Bistak ohararazi
			setChanged();
			super.notifyObservers(erreserba);

			String client;
			try {
				client = getClientHost();
			} catch (ServerNotActiveException e) {
				client = "ezezaguna";
			}
			frame.gehituEkintza(getCurrentTime() + ": " + client
					+ " - Ezeztatu - Erreserba zenbakia: "
					+ erreserba.getErreserbaZenbakia());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#sartuIrteera(int,
	 *      java.lang.String, java.util.Date)
	 */
	public Vector<Agentea> getErreserbaAgenteak() {
		Vector<Agentea> vA = new Vector<Agentea>();
		try {
			vA = aDB.getAgenteak();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vA;
	}

	public Vector<Irteera> getIrteerenEzaugarriak(String agIzena) {
		Vector<Irteera> vIrtAgente = new Vector<Irteera>();
		try {
			vIrtAgente = aDB.irakurriIrteerak(agIzena);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vIrtAgente;
	}

	public void sartuIrteera(Erreserba erreserba) throws RemoteException {
		int pertsonaMax;
		try {
			pertsonaMax = aDB.getPertsonaMax(erreserba.getIrteeraKodea());
			int plazaLibreak = pertsonaMax
					- aDB.getErreserbatutakoPertsonaKop(erreserba
							.getIrteeraKodea());
			if (plazaLibreak < erreserba.getPertsonaKopurua())
				erreserba.ukatu("Ez daude plaza librerik, bakarrik "
						+ plazaLibreak + " plaza daude libre.");
			else if (aDB.sartuErreserba(erreserba) > 0)
				erreserba.sartu();
		} catch (SQLException e) {
			e.printStackTrace();
			erreserba.ukatu("Arazo bat egon da datu basea atzitzean");
		}
		// Bistak ohararazi
		setChanged();
		super.notifyObservers(erreserba);

		String client;
		try {
			client = getClientHost();
		} catch (ServerNotActiveException e) {
			client = "ezezaguna";
		}
		frame.gehituEkintza(getCurrentTime() + ": " + client
				+ " - Sartu irteera - Erreserba zenbakia: "
				+ erreserba.getErreserbaZenbakia());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#sartuTurista(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void sartuTurista(Turista turista) throws RemoteException {
		String mezua = "";
		int errPertsonaKop = -2;
		int turistaKop = -1;
		try {
			errPertsonaKop = aDB.getPertsonaKop(turista.getErreserbaZenb());
			turistaKop = aDB.getTuristaKop(turista.getErreserbaZenb());
			if (errPertsonaKop > turistaKop) {
				if (aDB.sartuTurista(turista) > 0) {
					turistaKop++;
					mezua = "Turista sartua izan da.";
				} else
					mezua = "Ezin izan da turista sartu.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mezua = "Ezin izan da turista sartu. Errore bat egon da datu basea atzitzerakoan.";
		}
		TuristaNotifikazioa tn = new TuristaNotifikazioa(turista, turistaKop,
				errPertsonaKop - turistaKop, mezua);
		setChanged();
		super.notifyObservers(tn);

		String client;
		try {
			client = getClientHost();
		} catch (ServerNotActiveException e) {
			client = "ezezaguna";
		}
		frame.gehituEkintza(getCurrentTime() + ": " + client
				+ " - Sartu turista - Erreserba zenbakia: "
				+ turista.getErreserbaZenb());
	}

	// public Erreserba getLoturaErreserba() {
	// return LoturaErreserba;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#erreserbaBerria(int,
	 *      java.lang.String)
	 */
	public void erreserbaBerria(Erreserba erreserba) throws RemoteException {
		setChanged();
		super.notifyObservers(erreserba);
		try {
			// Erreserba sortu
			int pertsonaMax = aDB.getPertsonaMax(erreserba.getIrteeraKodea());
			int plazaLibreak = pertsonaMax
					- aDB.getErreserbatutakoPertsonaKop(erreserba
							.getIrteeraKodea());
			if (plazaLibreak >= erreserba.getPertsonaKopurua()) {
				unekoBaieztapenZenbakia++;
				erreserba.baieztatu(unekoBaieztapenZenbakia);
			} else
				erreserba.ukatu("Ez daude plaza librerik, bakarrik "
						+ plazaLibreak + " plaza daude libre.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			erreserba.ukatu("Arazo bat egon da datu basea atzitzean");
		}
		// Bistak ohararazi
		setChanged();
		super.notifyObservers(erreserba);

		String client;
		try {
			client = getClientHost();
		} catch (ServerNotActiveException e) {
			client = "ezezaguna";
		}
		frame.gehituEkintza(getCurrentTime() + ": " + client
				+ " - Erreserba berria - Erreserba zenbakia: "
				+ erreserba.getErreserbaZenbakia() + ", Baieztapen zenbakia: "
				+ erreserba.getBaieztapenZenbakia());
	}

	public int getHurrengoErreserbaZenb() throws RemoteException {
		unekoErreserbaZenbakia++;
		return unekoErreserbaZenbakia;
	}

	// public void setLoturaErreserba(Erreserba newLoturaErreserba) {
	// LoturaErreserba = newLoturaErreserba;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#submit()
	 */
	public void submit(Erreserba erreserba) throws RemoteException {
		erreserba.bukatu();
		// Bistak ohararazi
		setChanged();
		super.notifyObservers(erreserba);

		String client;
		try {
			client = getClientHost();
		} catch (ServerNotActiveException e) {
			client = "ezezaguna";
		}
		frame.gehituEkintza(getCurrentTime() + ": " + client
				+ " - Submit - Erreserba zenbakia: "
				+ erreserba.getErreserbaZenbakia());
	}

	@Override
	public void notifyConnection() throws RemoteException {
		String client;
		try {
			client = getClientHost();
		} catch (ServerNotActiveException e) {
			client = "ezezaguna";
		}
		frame.gehituBezeroa(client);
		frame.gehituEkintza(getCurrentTime() + ": " + client
				+ " bezeroa konektatu da");
		System.out.println(client + " konektatu da.");
	}

	@Override
	public void notifyDesconnection() throws RemoteException {
		String client;
		try {
			client = getClientHost();
		} catch (ServerNotActiveException e) {
			client = "ezezaguna";
		}

		frame.kenduBezeroa(client);
		frame.gehituEkintza(getCurrentTime() + ": " + client
				+ " bezeroa deskonektatu da");
		System.out.println(client + " deskonektatu da.");
	}

	private static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	protected final boolean isKonektatua() {
		return konektatua;
	}

	protected final void setKonektatua(boolean konektatua) {
		this.konektatua = konektatua;
		if (konektatua)
			frame.gehituEkintza(getCurrentTime() + ": Zerbitzaria hasieratua");
		else
			frame.gehituEkintza(getCurrentTime()
					+ ": Ezin izan da zerbitzaria hasieratu");
	}

	/**
	 * UrrunekoNegozioLogika-ren zerbitzaria hasieratzen du zerbitzuIzena
	 * atributuan definitutako zerbitzu izenarekin.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("java.security.policy", "client.policy");
		// Assingn security manager
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		try {
			ErreserbaSistema zerbitzariObj = new ErreserbaSistema();
			try {
				java.rmi.registry.LocateRegistry.createRegistry(1099); // RMIREGISTRY
				// jaurtitzearen
				// baliokidea

				// Urruneko zerbitzua erregistratu
				Naming.rebind(zerbitzuIzena, zerbitzariObj);
				// "//IPHelbidea:PortuZenb/zerbitzuIzena"
				// EZ DABIL rmiregistry izen zerbitzaria localhost-en EZ BADAGO
				System.out.println("Zerbitzaria hasieratua");
				zerbitzariObj.setKonektatua(true);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				zerbitzariObj.setKonektatua(false);
			} catch (Exception e) {
				System.out
						.println(e.toString()
								+ "\nSuposatzen dugu errorea dela rmiregistry aurretik jaurti delako ");
				zerbitzariObj.setKonektatua(false);
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
