package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a {@code short} value.
 *
 * @author Matt Coley
 */
public class ObservableShort extends ObservableNumber<Short> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableShort(short value) {
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
	public <I> ObservableShort(short value, Function<I, Short> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
