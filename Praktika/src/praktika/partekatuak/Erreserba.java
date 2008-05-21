package praktika.partekatuak;

import java.io.Serializable;
import java.util.Calendar;

public class Erreserba implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int erreserbaZenbakia;
	private Calendar data;
	private int pertsonaKopurua;
	private int baieztapenZenbakia;
	private int irteeraKodea;
	private String ukapenArrazoiak;
	
	public Erreserba() {
		super();
	}

	public Erreserba(int erreserbaZenbakia, Calendar data, int pertsonaKopurua, int baieztapenZenbakia, int irteeraKodea) {
		super();
		this.erreserbaZenbakia = erreserbaZenbakia;
		this.data = data;
		this.pertsonaKopurua = pertsonaKopurua;
		this.baieztapenZenbakia = baieztapenZenbakia;
		this.irteeraKodea = irteeraKodea;
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

	public void setBaieztapenZenbakia(int baieztapenZenbakia) {
		this.baieztapenZenbakia = baieztapenZenbakia;
	}
	
	public void ukatu(String arrazoiak){
		this.baieztapenZenbakia = -1;
		this.ukapenArrazoiak = arrazoiak;
	}

	public int getIrteeraKodea() {
		return irteeraKodea;
	}

	public void setIrteeraKodea(int irteeraKodea) {
		this.irteeraKodea = irteeraKodea;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
