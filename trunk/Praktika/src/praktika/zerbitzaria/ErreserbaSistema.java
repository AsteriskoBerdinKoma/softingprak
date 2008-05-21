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

import praktika.partekatuak.Erreserba;
import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.Irteera;
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

	private Erreserba LoturaErreserba;
	private AplikazioDatuBase aDB;

	private ZerbitzariaFrame frame;

	private boolean konektatua;

	public ErreserbaSistema() throws RemoteException {
		frame = new ZerbitzariaFrame();
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

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
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#ezeztatu()
	 */
	public void ezeztatu() throws RemoteException {
		// Erreserba sortu
		LoturaErreserba = null;
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#sartuIrteera(int,
	 *      java.lang.String, java.util.Date)
	 */
	public Vector<String> getErreserbaAgenteak(){
		Vector<String> vA = new Vector<String>();
		try {
			vA = aDB.getAgenteak();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vA;
	}
	
	public Vector<Irteera> getIrteerenEzaugarriak(String agIzena){
		Vector<Irteera> vIrtAgente = new Vector<Irteera>();
		try{
			vIrtAgente = aDB.irakurriIrteerak(agIzena);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vIrtAgente;
	}
	
	public void sartuIrteera(int baieztapenZenbakia, String irteerarenKodea,
			Date eskeinitakoData) throws RemoteException {
		// Irtera bilatu eta erreserba bat eskatu

		System.out.println("Irteeraren kodea  " + irteerarenKodea + " data "
				+ eskeinitakoData);
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#sartuTurista(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void sartuTurista(String izena, String helbidea, String telefonoa)
			throws RemoteException {

		System.out.println("Turistaren izena " + izena);
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#getLoturaErreserba()
	 */
	public Erreserba getLoturaErreserba() {
		return LoturaErreserba;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#erreserbaBerria(int,
	 *      java.lang.String)
	 */
	public void erreserbaBerria(int pertsonaKopurua,
			String erreserbaAgentearenIdentifikatzailea) throws RemoteException {
		// Erreserba sortu
		
		// Erreserba agentea bilatu eta erreserba antolatzeko eskatu
		System.out.println("Erreserba  " + pertsonaKopurua + " pertsonentzat  "
				+ erreserbaAgentearenIdentifikatzailea + " agentearekin ");
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#setLoturaErreserba(praktika.partekatuak.Erreserba)
	 */
	public void setLoturaErreserba(Erreserba newLoturaErreserba) {
		LoturaErreserba = newLoturaErreserba;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.zerbitzaria.ErreserbaInterface#submit()
	 */
	public void submit() throws RemoteException {

		System.out.println("Datubasera bidali da");
		LoturaErreserba = null;
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	@Override
	public void notifyConnection() throws ServerNotActiveException,
			RemoteException {
		frame.gehituBezeroa(getClientHost());
		frame.gehituEkintza(getCurrentTime() + ": " + getClientHost()
				+ " bezeroa konektatu da");
		System.out.println(getClientHost() + " konektatu da.");
	}

	@Override
	public void notifyDesconnection() throws RemoteException,
			ServerNotActiveException {
		frame.kenduBezeroa(getClientHost());
		frame.gehituEkintza(getCurrentTime() + ": " + getClientHost()
				+ " bezeroa deskonektatu da");
		System.out.println(getClientHost() + " deskonektatu da.");
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
