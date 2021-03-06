package praktika.partekatuak;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.Vector;

import praktika.partekatuak.remoteObservable.RemoteObservable;

public interface ErreserbaInterface extends RemoteObservable {

	public void erreserbaBerria(Erreserba erreserba) throws RemoteException;

	public void sartuIrteera(Erreserba erreserba) throws RemoteException;

	public void sartuTurista(Turista turista) throws RemoteException;

	public Vector<Agentea> getErreserbaAgenteak() throws RemoteException;

	public Vector<Irteera> getIrteerenEzaugarriak(String agIzena)
			throws RemoteException;

	public void submit(Erreserba erreserba) throws RemoteException;

	public void ezeztatu(Erreserba erreserba) throws RemoteException;

	public void notifyConnection() throws RemoteException,
			ServerNotActiveException;

	public void notifyDesconnection() throws RemoteException,
			ServerNotActiveException;

	public int getHurrengoErreserbaZenb() throws RemoteException;
}
