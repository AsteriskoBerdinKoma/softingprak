package praktika.partekatuak.remoteObservable;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObservable extends Remote{

	/* (non-Javadoc)
	 * @see praktika.partekatuak.remoteObservable.RemoteObserver2#addObserver(praktika.partekatuak.remoteObservable.RemoteObserver)
	 */
	public void addObserver(RemoteObserver o);

	/* (non-Javadoc)
	 * @see praktika.partekatuak.remoteObservable.RemoteObserver2#deleteObserver(praktika.partekatuak.remoteObservable.RemoteObserver)
	 */
	public void deleteObserver(RemoteObserver o);

	/* (non-Javadoc)
	 * @see praktika.partekatuak.remoteObservable.RemoteObserver2#notifyObservers()
	 */
	public void notifyObservers() throws RemoteException;

	/* (non-Javadoc)
	 * @see praktika.partekatuak.remoteObservable.RemoteObserver2#notifyObservers(java.lang.Object)
	 */
	public void notifyObservers(Object arg) throws RemoteException;

	/* (non-Javadoc)
	 * @see praktika.partekatuak.remoteObservable.RemoteObserver2#deleteObservers()
	 */
	public void deleteObservers();

	/* (non-Javadoc)
	 * @see praktika.partekatuak.remoteObservable.RemoteObserver2#hasChanged()
	 */
	public boolean hasChanged();

	/* (non-Javadoc)
	 * @see praktika.partekatuak.remoteObservable.RemoteObserver2#countObservers()
	 */
	public int countObservers();

}