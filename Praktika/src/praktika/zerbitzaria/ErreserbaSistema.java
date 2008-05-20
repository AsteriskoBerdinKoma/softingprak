package praktika.zerbitzaria;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.Date;

import praktika.partekatuak.Erreserba;
import praktika.partekatuak.ErreserbaInterface;
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

	public ErreserbaSistema() throws RemoteException {
		aDB = new AplikazioDatuBase();
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
			java.rmi.registry.LocateRegistry.createRegistry(1099); // RMIREGISTRY
																	// jaurtitzearen
																	// baliokidea

			// Urruneko zerbitzua erregistratu
			Naming.rebind(zerbitzuIzena, zerbitzariObj);
			// "//IPHelbidea:PortuZenb/zerbitzuIzena"
			// EZ DABIL rmiregistry izen zerbitzaria localhost-en EZ BADAGO
			// } catch (Exception e) {
			// System.out
			// .println("Errorea zerbitzaria jaurtitzean" + e.toString());
			// }
			System.out.println("Zerbitzaria hasieratua");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out
					.println(e.toString()
							+ "\nSuposatzen dugu errorea dela rmiregistry aurretik jaurti delako ");
		}
	}
	
	@Override
	public void notifyConnection() throws ServerNotActiveException, RemoteException{
		System.out.println(getClientHost() + " konektatu da.");
	}

	@Override
	public void notifyDesconnection() throws RemoteException,
			ServerNotActiveException {
		System.out.println(getClientHost() + " deskonektatu da.");		
	}

}
