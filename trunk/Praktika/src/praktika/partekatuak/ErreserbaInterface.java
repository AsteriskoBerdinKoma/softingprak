package praktika.partekatuak;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.Date;
import java.util.Vector;

import praktika.partekatuak.remoteObservable.RemoteObservable;


public interface ErreserbaInterface extends RemoteObservable{

	public void erreserbaBerria(Erreserba erreserba) throws RemoteException;

	public void sartuIrteera(int baieztapenZenbakia, String zerbitzuarenKodea,
			Date irteeraData) throws RemoteException;

	public void sartuTurista(String izena, String helbidea, String telefonoa) throws RemoteException;

	public Vector<String> getErreserbaAgenteak() throws RemoteException;
	
	public Vector<Irteera> getIrteerenEzaugarriak(String agIzena) throws RemoteException;
	
	public void submit() throws RemoteException;

	public void ezeztatu() throws RemoteException;
	
	public void notifyConnection() throws RemoteException, ServerNotActiveException;

	public void notifyDesconnection() throws RemoteException, ServerNotActiveException;
}
