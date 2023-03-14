package software.coley.observables;

import java.util.Objects;
import java.util.function.Function;

/**
 * Observable for a {@code char} value.
 *
 * @author Matt Coley
 */
public class ObservableCharacter extends AbstractObservable<Character> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableCharacter(char value) {
		super(value);
	}

	@Override
	protected void validateNewValue(Character newValue) {
		Objects.requireNonNull(newValue, "Character values cannot be null");
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param boundValueMapper
	 * 		Mapper used to map values for bindings.
	 * @param <I>
	 * 		Input mapping type.
	 */
	public <I> ObservableCharacter(char value, Function<I, Character> boundValueMapper) {
		super(value, boundValueMapper);
	}
}
