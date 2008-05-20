package praktika.zerbitzaria;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import praktika.partekatuak.Erreserba;
import praktika.partekatuak.remoteObservable.RemoteObservableImpl;

/**
 * Bigarren mailako antolatzeko klasea
 */

public class ErreserbaSistema extends RemoteObservableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	public static final String DATABASE_URL = "jdbc:odbc:Bidaiak";
	public static final String USERNAME = "";
	public static final String PASSWORD = "";

	/**
	 * Zerbitzuak izango duen izena.
	 */
	public static final String zerbitzuIzena = "ErreserbaSistema";

	private Connection kon;
	private boolean connectedToDatabase = false;

	private Erreserba LoturaErreserba;

	public ErreserbaSistema() throws RemoteException {
		try {
			Class.forName(JDBC_DRIVER);
			kon = DriverManager.getConnection(DATABASE_URL);
			System.out.println("Driverra Kargatuta.");
			connectedToDatabase = true;
		} catch (SQLException e) {
			System.out.println("Ezin izan da datu basearekin konexioa ezarri");
		} catch (ClassNotFoundException e) {
			System.out.println("Ez da " + JDBC_DRIVER + " driverra aurkitu");
		}
	}

	/**
	 * Datu basearekin konexioa irekita badago itxi egiten du.
	 * 
	 * @throws SQLException
	 * 
	 * @throws SQLException
	 */
	public void disconnect() throws SQLException {
		if (connectedToDatabase) {
			kon.close();
			connectedToDatabase = false;
		}
	}

	public void ezeztatu() throws RemoteException {
		// Erreserba sortu
		LoturaErreserba = null;
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	/**
	 * Hemen metodoaren deskribapena sartu.
	 * 
	 * @param zerbitzuarenKodea
	 *            java.lang.String
	 * @param eskeinitakoData
	 *            java.util.Date
	 * @throws RemoteException
	 */
	public void sartuIrteera(int baieztapenZenbakia, String irteerarenKodea,
			Date eskeinitakoData) throws RemoteException {
		// Irtera bilatu eta erreserba bat eskatu

		System.out.println("Irteeraren kodea  " + irteerarenKodea + " data "
				+ eskeinitakoData);
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	/**
	 * Hemen metodoaren deskribapena sartu.
	 * 
	 * @param izena
	 *            java.lang.String
	 * @param helbidea
	 *            java.lang.String
	 * @param telefonoa
	 *            java.lang.String
	 * @throws RemoteException
	 */
	public void sartuTurista(String izena, String helbidea, String telefonoa)
			throws RemoteException {

		System.out.println("Turistaren izena " + izena);
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}

	public Erreserba getLoturaErreserba() {
		return LoturaErreserba;
	}

	/**
	 * 
	 * @return total double
	 * @throws RemoteException
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

	public void setLoturaErreserba(Erreserba newLoturaErreserba) {
		LoturaErreserba = newLoturaErreserba;
	}

	/**
	 * Hemen metodoaren deskribapena sartu
	 * 
	 * @throws RemoteException
	 */
	public void submit() throws RemoteException {

		System.out.println("Datubasera bidali da");
		LoturaErreserba = null;
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
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
			System.out.println("objektua jaurtia");
			try {
				java.rmi.registry.LocateRegistry.createRegistry(1099); // RMIREGISTRY
				// jaurtitzearen baliokidea
			} catch (Exception e) {
				System.out
						.println(e.toString()
								+ "\nSuposatzen dugu errorea dela rmiregistry aurretik jaurti delako ");
			}
			// Urruneko zerbitzua erregistratu
			Naming.rebind(zerbitzuIzena, zerbitzariObj);
			// "//IPHelbidea:PortuZenb/zerbitzuIzena"
			// EZ DABIL rmiregistry izen zerbitzaria localhost-en EZ BADAGO
			// } catch (Exception e) {
			// System.out
			// .println("Errorea zerbitzaria jaurtitzean" + e.toString());
			// }
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
