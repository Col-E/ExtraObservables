package software.coley.observables;

/**
 * Thrown when an {@link Observable} impl tries to bind to another value but was already bound.
 *
 * @author Matt Coley
 */
public class BoundTargetSetException extends RuntimeException {
	private final Observable observable;

	/**
	 * @param observable
	 * 		Observable that attempted to update its bind target, but failed.
	 */
	public BoundTargetSetException(Observable observable) {
		super("Cannot set bind target on observable since it has been bound to another value!");
		this.observable = observable;
	}

	/**
	 * @return Observable that attempted to update its bind target, but failed.
	 */
	public Observable getObservable() {
		return observable;
	}
}
