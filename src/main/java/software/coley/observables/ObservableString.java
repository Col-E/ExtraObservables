package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a String values.
 *
 * @author Matt Coley
 */
public class ObservableString extends ObservableObject<String> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableString(String value) {
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
	public <I> ObservableString(String value, Function<I, String> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
