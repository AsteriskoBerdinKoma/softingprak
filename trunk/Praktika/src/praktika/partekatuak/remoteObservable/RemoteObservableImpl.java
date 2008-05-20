package praktika.partekatuak.remoteObservable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * This class represents an observable object, or "data" in the model-view
 * paradigm. It can be subclassed to represent an object that the application
 * wants to have observed.
 * <p>
 * An observable object can have one or more observers. An observer may be any
 * object that implements interface <tt>Observer</tt>. After an observable
 * instance changes, an application calling the <code>Observable</code>'s
 * <code>notifyObservers</code> method causes all of its observers to be
 * notified of the change by a call to their <code>update</code> method.
 * <p>
 * The order in which notifications will be delivered is unspecified. The
 * default implementation provided in the Observable class will notify Observers
 * in the order in which they registered interest, but subclasses may change
 * this order, use no guaranteed order, deliver notifications on separate
 * threads, or may guarantee that their subclass follows this order, as they
 * choose.
 * <p>
 * Note that this notification mechanism is has nothing to do with threads and
 * is completely separate from the <tt>wait</tt> and <tt>notify</tt>
 * mechanism of class <tt>Object</tt>.
 * <p>
 * When an observable object is newly created, its set of observers is empty.
 * Two observers are considered the same if and only if the <tt>equals</tt>
 * method returns true for them.
 * 
 * @author Chris Warth
 * @version 1.39, 11/17/05
 * @see java.util.Observable#notifyObservers()
 * @see java.util.Observable#notifyObservers(java.lang.Object)
 * @see java.util.Observer
 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
 * @since JDK1.0
 */
public class RemoteObservableImpl extends UnicastRemoteObject implements
		RemoteObservable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean changed = false;
	private Vector<RemoteObserver> obs;

	/**
	 * Construct an Observable with zero Observers.
	 * 
	 * @throws RemoteException
	 */

	public RemoteObservableImpl() throws RemoteException {
		super();
		obs = new Vector<RemoteObserver>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.partekatuak.remoteObservable.RemoteObservable2#addObserver(praktika.partekatuak.remoteObservable.RemoteObserver)
	 */
	public synchronized void addObserver(RemoteObserver o)
			throws RemoteException {
		if (o == null)
			throw new NullPointerException();
		if (!obs.contains(o)) {
			obs.addElement(o);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.partekatuak.remoteObservable.RemoteObservable2#deleteObserver(praktika.partekatuak.remoteObservable.RemoteObserver)
	 */
	public synchronized void deleteObserver(RemoteObserver o)
			throws RemoteException {
		obs.removeElement(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.partekatuak.remoteObservable.RemoteObservable2#notifyObservers()
	 */
	public void notifyObservers() throws RemoteException {
		notifyObservers(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.partekatuak.remoteObservable.RemoteObservable2#notifyObservers(java.lang.Object)
	 */
	public void notifyObservers(Object arg) throws RemoteException {
		/*
		 * a temporary array buffer, used as a snapshot of the state of current
		 * Observers.
		 */
		Object[] arrLocal;

		synchronized (this) {
			/*
			 * We don't want the Observer doing callbacks into arbitrary code
			 * while holding its own Monitor. The code where we extract each
			 * Observable from the Vector and store the state of the Observer
			 * needs synchronization, but notifying observers does not (should
			 * not). The worst result of any potential race-condition here is
			 * that: 1) a newly-added Observer will miss a notification in
			 * progress 2) a recently unregistered Observer will be wrongly
			 * notified when it doesn't care
			 */
			if (!changed)
				return;
			arrLocal = obs.toArray();
			clearChanged();
		}

		for (int i = arrLocal.length - 1; i >= 0; i--)
			((RemoteObserver) arrLocal[i]).update(this, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.partekatuak.remoteObservable.RemoteObservable2#deleteObservers()
	 */
	public synchronized void deleteObservers() throws RemoteException {
		obs.removeAllElements();
	}

	/**
	 * Marks this <tt>Observable</tt> object as having been changed; the
	 * <tt>hasChanged</tt> method will now return <tt>true</tt>.
	 */
	protected synchronized void setChanged() {
		changed = true;
	}

	/**
	 * Indicates that this object has no longer changed, or that it has already
	 * notified all of its observers of its most recent change, so that the
	 * <tt>hasChanged</tt> method will now return <tt>false</tt>. This
	 * method is called automatically by the <code>notifyObservers</code>
	 * methods.
	 * 
	 * @see java.util.Observable#notifyObservers()
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	protected synchronized void clearChanged() {
		changed = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.partekatuak.remoteObservable.RemoteObservable2#hasChanged()
	 */
	public synchronized boolean hasChanged() throws RemoteException {
		return changed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see praktika.partekatuak.remoteObservable.RemoteObservable2#countObservers()
	 */
	public synchronized int countObservers() throws RemoteException {
		return obs.size();
	}
}