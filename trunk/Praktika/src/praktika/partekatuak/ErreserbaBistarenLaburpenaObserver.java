package praktika.partekatuak;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.NumberFormat;

import praktika.bezeroa.ErreserbaBistarenLaburpena;
import praktika.partekatuak.remoteObservable.RemoteObservable;
import praktika.partekatuak.remoteObservable.RemoteObserver;

public class ErreserbaBistarenLaburpenaObserver extends UnicastRemoteObject implements RemoteObserver, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErreserbaBistarenLaburpena gui;
	public ErreserbaBistarenLaburpenaObserver(ErreserbaBistarenLaburpena gui) throws RemoteException{
		super();
		this.gui = gui;
	}
	@Override
	public void update(RemoteObservable o, Object arg) throws RemoteException {
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
//		DateFormat dataFormat = DateFormat.getDateInstance();
//		ErreserbaInterface erreserbaSistema = (ErreserbaInterface) observable;
//		//
//		try {
//			if (erreserbaSistema.getLoturaErreserba() != null) {
//			} else {
//				testuEremuaData.setText("");
//				testuEremuaGuztira.setText("");
//				testuEremuaAgentearenIzena.setText("");
//				testuEremuaErreserbarenZenbakia.setText("");
//			}
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	

}
