package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a {@code long} value.
 *
 * @author Matt Coley
 */
public class ObservableLong extends ObservableNumber<Long> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableLong(long value) {
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
	public <I> ObservableLong(long value, Function<I, Long> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
