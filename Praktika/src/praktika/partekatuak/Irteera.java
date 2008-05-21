package praktika.partekatuak;

import java.util.Calendar;

public class Irteera {

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

	protected final int getIrteerarenKodea() {
		return irteerarenKodea;
	}

	protected final void setIrteerarenKodea(int irteerarenKodea) {
		this.irteerarenKodea = irteerarenKodea;
	}

	protected final String getEzaugarriak() {
		return ezaugarriak;
	}

	protected final void setEzaugarriak(String ezaugarriak) {
		this.ezaugarriak = ezaugarriak;
	}

	protected final int getPertsonaKopMax() {
		return pertsonaKopMax;
	}

	protected final void setPertsonaKopMax(int pertsonaKopMax) {
		this.pertsonaKopMax = pertsonaKopMax;
	}

	protected final int getAgenteKodea() {
		return agenteKodea;
	}

	protected final void setAgenteKodea(int agenteKodea) {
		this.agenteKodea = agenteKodea;
	}

	protected final double getPrezioa() {
		return prezioa;
	}

	protected final void setPrezioa(double prezioa) {
		this.prezioa = prezioa;
	}

	protected final Calendar getData() {
		return data;
	}

	protected final void setData(Calendar data) {
		this.data = data;
	}

}
