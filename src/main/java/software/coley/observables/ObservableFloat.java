package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a {@code float} value.
 *
 * @author Matt Coley
 */
public class ObservableFloat extends ObservableNumber<Float> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableFloat(float value) {
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
	public <I> ObservableFloat(float value, Function<I, Float> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
