package praktika.partekatuak;

public class TuristaNotifikazioa {

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
