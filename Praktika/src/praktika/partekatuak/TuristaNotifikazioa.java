package praktika.partekatuak;

import java.io.Serializable;

public class TuristaNotifikazioa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Turista turista;
	private int zenbatgarrena;
	private int libreKop;
	private String mezua;

	public TuristaNotifikazioa(Turista turista, int zenbatgarrena,
			int libreKop, String mezua) {
		super();
		this.turista = turista;
		this.zenbatgarrena = zenbatgarrena;
		this.libreKop = libreKop;
		this.mezua = mezua;
	}

	public Turista getTurista() {
		return turista;
	}

	public int getZenbatgarrena() {
		return zenbatgarrena;
	}

	public int getLibreKop() {
		return libreKop;
	}

	public String getMezua() {
		return mezua;
	}

	public int getErreserbaZenbakia() {
		return turista.getErreserbaZenb();
	}

}
