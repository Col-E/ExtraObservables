package software.coley.observables;

/**
 * Thrown when an {@link Observable} impl tries to update its value externally after being bound to another value.
 *
 * @author Matt Coley
 */
public class BoundValueSetException extends RuntimeException {
	private final Observable observable;

	/**
	 * @param observable
	 * 		Observable that attempted to update its value, but failed.
	 */
	public BoundValueSetException(Observable observable) {
		super("Cannot set value on observable since it has been bound to another value!");
		this.observable = observable;
	}

	/**
	 * @return Observable that attempted to update its value, but failed.
	 */
	public Observable getObservable() {
		return observable;
	}
}
