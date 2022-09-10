package software.coley.observables;

import java.util.function.Function;

/**
 * Observable for a {@code byte} value.
 *
 * @author Matt Coley
 */
public class ObservableByte extends ObservableNumber<Byte> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableByte(byte value) {
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
	public <I> ObservableByte(byte value, Function<I, Byte> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
