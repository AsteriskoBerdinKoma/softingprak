package praktika.partekatuak;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

public class ErreserbaItenarenBistaObserver extends UnicastRemoteObject implements RemoteObserver, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErreserbaItenarenBistaObserver() throws RemoteException{
		super();
	}
	
	@Override
	public void update(RemoteObservable o, Object arg) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	

}
