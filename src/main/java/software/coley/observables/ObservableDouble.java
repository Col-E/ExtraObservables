package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a {@code double} value.
 *
 * @author Matt Coley
 */
public class ObservableDouble extends ObservableNumber<Double> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableDouble(double value) {
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
	public <I> ObservableDouble(double value, Function<I, Double> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
