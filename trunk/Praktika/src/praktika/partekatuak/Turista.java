package praktika.partekatuak;

import java.io.Serializable;

public class Turista implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String izena;
	private String helbidea;
	private String telefonoa;
	private int erreserbaZenb;

	public Turista(String izena, String helbidea, String telefonoa,
			int erreserbaZenb) {
		super();
		this.izena = izena;
		this.helbidea = helbidea;
		this.telefonoa = telefonoa;
		this.erreserbaZenb = erreserbaZenb;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getHelbidea() {
		return helbidea;
	}

	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
	}

	public String getTelefonoa() {
		return telefonoa;
	}

	public void setTelefonoa(String telefonoa) {
		this.telefonoa = telefonoa;
	}

	public int getErreserbaZenb() {
		return erreserbaZenb;
	}
}
