package praktika.zerbitzaria;

import java.rmi.RemoteException;
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
	
	private Erreserba LoturaErreserba;

	public ErreserbaSistema() throws RemoteException{

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
	public void sartuTurista(String izena, String helbidea, String telefonoa) throws RemoteException {

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
	 * @throws RemoteException 
	 */
	public void submit() throws RemoteException {

		System.out.println("Datubasera bidali da");
		LoturaErreserba = null;
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}
}
