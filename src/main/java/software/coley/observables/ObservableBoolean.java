package software.coley.observables;

import java.util.Objects;
import java.util.function.Function;

/**
 * Observable for a {@code boolean} value.
 *
 * @author Matt Coley
 */
public class ObservableBoolean extends AbstractObservable<Boolean> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableBoolean(boolean value) {
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
	public <I> ObservableBoolean(boolean value, Function<I, Boolean> boundValueMapper) {
		super(value, boundValueMapper);
	}

	@Override
	protected void validateNewValue(Boolean newValue) {
		Objects.requireNonNull(newValue, "Boolean values cannot be null");
	}

	/**
	 * Toggle {@code boolean} value.
	 */
	public void toggle() {
		setValue(!getValue());
	}

	/**
	 * @return Bound observable to the negated boolean value.
	 */
	public ObservableBoolean negated() {
		return mapBoolean(b -> !b);
	}
}
