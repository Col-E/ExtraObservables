package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a {@code int} value.
 *
 * @author Matt Coley
 */
public class ObservableInteger extends ObservableNumber<Integer> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableInteger(int value) {
		super(value);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param boundValueMapper
	 * 		Mapper used to map values for bindings.
	 * @param <I>
	 * 		Input mapping type.
	 */
	public <I> ObservableInteger(int value, Function<I, Integer> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
