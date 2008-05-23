package praktika.partekatuak;

import java.io.Serializable;
import java.util.Calendar;

public class Erreserba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int BERRIA = 0;
	public static final int BAIEZTATUA = 1;
	public static final int UKATUA = 2;
	public static final int SARTUTA = 3;
	public static final int EZEZTATUA = 4;
	public static final int BUKATUA = 5;

	private int erreserbaZenbakia;
	private Calendar data;
	private int pertsonaKopurua;
	private int baieztapenZenbakia;
	private int irteeraKodea;
	private String ukapenArrazoiak;
	private String agenteIzena;
	private double prezioa;
	private int egoera;
	private boolean baieztatua;
	private boolean sartuta;
	private boolean ezeztatuta;
	private boolean bukatuta;

	public Erreserba() {
		super();
	}

	public Erreserba(int erreserbaZenbakia, Calendar data, int pertsonaKopurua,
			int baieztapenZenbakia, int irteeraKodea) {
		super();
		this.erreserbaZenbakia = erreserbaZenbakia;
		this.data = data;
		this.pertsonaKopurua = pertsonaKopurua;
		this.baieztapenZenbakia = baieztapenZenbakia;
		this.irteeraKodea = irteeraKodea;
		this.ukapenArrazoiak = "";
		this.agenteIzena = "";
		this.prezioa = 0;
		this.egoera = Erreserba.BERRIA;
		this.baieztatua = false;
		this.sartuta = false;
		this.ezeztatuta = false;
		this.bukatuta = false;
	}

	public int getErreserbaZenbakia() {
		return erreserbaZenbakia;
	}

	public void setErreserbaZenbakia(int erreserbaZenbakia) {
		this.erreserbaZenbakia = erreserbaZenbakia;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getPertsonaKopurua() {
		return pertsonaKopurua;
	}

	public void setPertsonaKopurua(int pertsonaKopurua) {
		this.pertsonaKopurua = pertsonaKopurua;
	}

	public int getBaieztapenZenbakia() {
		return baieztapenZenbakia;
	}

	public void baieztatu(int baieztapenZenbakia) {
		this.baieztapenZenbakia = baieztapenZenbakia;
		this.ukapenArrazoiak = "";
		this.baieztatua = true;
		this.egoera = Erreserba.BAIEZTATUA;
	}

	public void ukatu(String arrazoiak) {
		this.baieztapenZenbakia = -1;
		this.ukapenArrazoiak = arrazoiak;
		this.baieztatua = false;
		this.egoera = Erreserba.UKATUA;
	}

	public int getIrteeraKodea() {
		return irteeraKodea;
	}

	public void setIrteeraKodea(int irteeraKodea) {
		this.irteeraKodea = irteeraKodea;
	}

	public String getUkapenArrazoiak() {
		return ukapenArrazoiak;
	}

	public final boolean isBaieztatua() {
		return baieztatua;
	}

	public final boolean isSartuta() {
		return sartuta;
	}

	public final void sartu() {
		this.sartuta = true;
		this.egoera = Erreserba.SARTUTA;
	}

	public void ezeztatu() {
		this.sartuta = false;
		this.ezeztatuta = true;
		this.egoera = Erreserba.EZEZTATUA;
	}

	public boolean isEzeztatuta() {
		return ezeztatuta;
	}

	public boolean isBukatuta() {
		return bukatuta;
	}

	public void bukatu() {
		this.bukatuta = true;
		this.egoera = Erreserba.BUKATUA;
	}

	public int getEgoera() {
		return egoera;
	}

	public String getAgenteIzena() {
		return agenteIzena;
	}

	public void setAgenteIzena(String agenteIzena) {
		this.agenteIzena = agenteIzena;
	}

	public double getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(double prezioa) {
		this.prezioa = prezioa;
	}
}
