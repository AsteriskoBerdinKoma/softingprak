package praktika.partekatuak;

import java.rmi.RemoteException;
import java.util.Date;

import praktika.partekatuak.remoteObservable.RemoteObservable;


public interface ErreserbaInterface extends RemoteObservable{

	public void erreserbaBerria(int pertsonaKopurua,
			String irteeraHandizkariarenIdentifikatzailea) throws RemoteException;

	public Erreserba getLoturaErreserba();

	public void sartuIrteera(int baieztapenZenbakia, String zerbitzuarenKodea,
			Date irteeraData) throws RemoteException;

	public void sartuTurista(String izena, String helbidea, String telefonoa) throws RemoteException;

	public void submit() throws RemoteException;

	public void ezeztatu() throws RemoteException;
	
	

}
