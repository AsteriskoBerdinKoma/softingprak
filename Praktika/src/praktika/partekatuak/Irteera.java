package praktika.partekatuak;

import java.io.Serializable;
import java.util.Calendar;

public class Irteera implements Serializable{

	private int irteerarenKodea;
	private String ezaugarriak;
	private int pertsonaKopMax;
	private int agenteKodea;
	private double prezioa;
	private Calendar data;

	public Irteera(int irteerarenKodea, String ezaugarriak, int pertsonaKopMax,
			int agenteKodea, double prezioa, Calendar data) {
		super();
		this.irteerarenKodea = irteerarenKodea;
		this.ezaugarriak = ezaugarriak;
		this.pertsonaKopMax = pertsonaKopMax;
		this.agenteKodea = agenteKodea;
		this.prezioa = prezioa;
		this.data = data;
	}

	public final int getIrteerarenKodea() {
		return irteerarenKodea;
	}

	public final void setIrteerarenKodea(int irteerarenKodea) {
		this.irteerarenKodea = irteerarenKodea;
	}

	public final String getEzaugarriak() {
		return ezaugarriak;
	}

	public final void setEzaugarriak(String ezaugarriak) {
		this.ezaugarriak = ezaugarriak;
	}

	public final int getPertsonaKopMax() {
		return pertsonaKopMax;
	}

	public final void setPertsonaKopMax(int pertsonaKopMax) {
		this.pertsonaKopMax = pertsonaKopMax;
	}

	public final int getAgenteKodea() {
		return agenteKodea;
	}

	public final void setAgenteKodea(int agenteKodea) {
		this.agenteKodea = agenteKodea;
	}

	public final double getPrezioa() {
		return prezioa;
	}

	public final void setPrezioa(double prezioa) {
		this.prezioa = prezioa;
	}

	public final Calendar getData() {
		return data;
	}

	public final void setData(Calendar data) {
		this.data = data;
	}

}
