package praktika.partekatuak;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import praktika.bezeroa.ErreserbaKontroladorea;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

public class ErreserbaKontroladoreaObserver extends UnicastRemoteObject implements RemoteObserver, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErreserbaKontroladorea gui;
	
	public ErreserbaKontroladoreaObserver(ErreserbaKontroladorea gui) throws RemoteException{
		super();
		this.gui = gui;
	}

	/** Prints out the observable, item of interest, and the argument, if any.
	  */
	@Override
	public void update( RemoteObservable o, Object arg ) throws RemoteException
	{
		System.err.println( "RemoteObserverImpl.update: observable: "+o+ " arg: "+arg );
	}

	
}

