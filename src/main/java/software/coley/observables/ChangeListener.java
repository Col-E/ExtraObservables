package software.coley.observables;

/**
 * Listener notified when {@link AbstractObservable#getValue()} is changed.
 *
 * @param <T>
 * 		Observable object type.
 *
 * @author Matt Coley
 */
@FunctionalInterface
public interface ChangeListener<T> {
	/**
	 * Called when {@link AbstractObservable#getValue()} changes.
	 *
	 * @param observable
	 * 		The {@code AbstractObservable} changed.
	 * @param oldValue
	 * 		The old value.
	 * @param newValue
	 * 		The new value.
	 */
	void changed(AbstractObservable<? extends T> observable, T oldValue, T newValue);
}

