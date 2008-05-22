package praktika.partekatuak;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;

import praktika.bezeroa.ErreserbarenBista;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

public class ErreserbarenBistaObserver extends UnicastRemoteObject implements RemoteObserver, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ErreserbarenBista gui;
	
	public ErreserbarenBistaObserver(ErreserbarenBista gui) throws RemoteException {
		super();
		this.gui = gui;
	}

	public void update(RemoteObservable observable, Object objektua)
			throws RemoteException {
		DefaultListModel items = gui.getItenak();
		items.clear();
		items.add(0, "Bai!!!");
		System.out.println("eginda");
		gui.setItenak(items);
		//
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		DateFormat dataFormat = DateFormat.getDateInstance();
		ErreserbaInterface erreserbaSistema = (ErreserbaInterface) observable;
	}
	
}
