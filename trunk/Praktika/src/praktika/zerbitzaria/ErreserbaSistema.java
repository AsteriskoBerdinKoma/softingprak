package praktika.zerbitzaria;

import java.util.Date;
import java.util.Observable;

import praktika.partekatuak.Erreserba;

/**
 * Bigarren mailako antolatzeko klasea
 */

public class ErreserbaSistema extends Observable {

	private Erreserba LoturaErreserba;

	public ErreserbaSistema() {

	}

	public void ezeztatu() {
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
	 */
	public void sartuIrteera(int baieztapenZenbakia, String irteerarenKodea,
			Date eskeinitakoData) {
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
	 */
	public void sartuTurista(String izena, String helbidea, String telefonoa) {

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
	 */
	public void erreserbaBerria(int pertsonaKopurua,
			String erreserbaAgentearenIdentifikatzailea) {
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
	 */
	public void submit() {

		System.out.println("Datubasera bidali da");
		LoturaErreserba = null;
		// Bistak ohararazi
		setChanged();
		super.notifyObservers();
	}
}
