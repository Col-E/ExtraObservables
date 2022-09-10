package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a generic object values.
 *
 * @param <T>
 * 		Generic value type.
 *
 * @author Matt Coley
 */
public class ObservableObject<T> extends AbstractObservable<T> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableObject(T value) {
		this(value, null);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param boundValueMapper
	 * 		Mapper used to map values for bindings.
	 * @param <I>
	 * 		Input mapping type.
	 */
	public <I> ObservableObject(T value, Function<I, T> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
