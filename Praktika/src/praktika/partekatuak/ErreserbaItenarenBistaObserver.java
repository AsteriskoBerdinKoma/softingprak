package praktika.partekatuak;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;

import praktika.bezeroa.ErreserbaItenarenBista;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

public class ErreserbaItenarenBistaObserver extends UnicastRemoteObject implements RemoteObserver, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErreserbaItenarenBista gui;

	public ErreserbaItenarenBistaObserver(ErreserbaItenarenBista gui) throws RemoteException{
		super();
		this.gui = gui;
	}
	
	@Override
	public void update(RemoteObservable o, Object arg) throws RemoteException {
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
//			if (erreserbaSistema.getLoturaErreserba() != null) {
//			} else {
//				testuEremuaKopurua.setText("");
//				textFieldDeskribapena.setText("");
//				testuEremuaPrezioa.setText("");
//				testuEremuaLuzapena.setText("");
//			}
		
	}
	

}
