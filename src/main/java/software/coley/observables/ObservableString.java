package software.coley.observables;

import software.coley.observables.util.NumberUtil;

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
	public <I> ObservableString(String value, Function<I, String> boundValueMapper) {
		super(value, boundValueMapper);
	}

	/**
	 * @return Number mapped from the current text of this value.
	 *
	 * @see NumberUtil#parse(String)
	 */
	public ObservableNumber<Number> mapNumber() {
		Function<String, Number> valueMapper = text -> NumberUtil.parse(text).getValue();
		ObservableNumber<Number> observable = new ObservableNumber<>(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}
}
