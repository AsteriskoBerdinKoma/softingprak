package praktika.partekatuak;

import java.io.Serializable;

public class Agentea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int agenteKodea;
	private String izena;

	public Agentea(int agenteKodea, String izena) {
		super();
		this.agenteKodea = agenteKodea;
		this.izena = izena;
	}

	public Agentea() {
		// TODO Auto-generated constructor stub
	}

	public int getAgenteKodea() {
		return agenteKodea;
	}

	public void setAgenteKodea(int agenteKodea) {
		this.agenteKodea = agenteKodea;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

}
