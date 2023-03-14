package software.coley.observables;

import java.util.Set;
import java.util.function.Function;

/**
 * Core observable type.
 *
 * @author Matt Coley
 */
public interface Observable<T> {
	/**
	 * @param newValue
	 * 		New value to assign.
	 */
	void setValue(T newValue);

	/**
	 * @return Current value.
	 */
	T getValue();

	/**
	 * @return {@code true} when {@link #getValue()} is not {@code null},
	 */
	default boolean hasValue() {
		return getValue() != null;
	}

	/**
	 * @param listener
	 * 		Lister to add to receive value changes.
	 */
	void addChangeListener(ChangeListener<T> listener);

	/**
	 * @param listener
	 * 		Lister to remove from receiving value changes.
	 *
	 * @return {@code true} on removal.
	 * {@code false} when no listener existed for this observable.
	 */
	boolean removeChangeListener(ChangeListener<T> listener);

	/**
	 * Bind this instance to the given observable.
	 *
	 * @param observable
	 * 		Other observable to bind to.
	 * @param <S>
	 * 		Self type.
	 *
	 * @return Self.
	 */
	<S extends Observable<?>> S bindTo(Observable<?> observable);

	/**
	 * @param observable
	 * 		Other observable to unbind from.
	 *
	 * @return {@code true} on removal.
	 * {@code false} when no bind existed for the given observable.
	 */
	boolean unbind(Observable<T> observable);

	/**
	 * @return Set of observable
	 */
	@SuppressWarnings("rawtypes")
	Set<Observable> getBoundReceivers();

	/**
	 * @return Mapper used to map values for mapping operations / bindings.
	 * May be {@code null} when this observable value is not a mapping of another value.
	 */
	Function<Object, T> getBoundValueMapper();

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 * @param <R>
	 * 		Type to map to.
	 *
	 * @return Mapped value, not boxed in an {@link AbstractObservable}.
	 */
	default <R> R unboxingMap(Function<T, R> valueMapper) {
		return valueMapper.apply(getValue());
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 * @param <R>
	 * 		Type to map to.
	 *
	 * @return Observable object of type, with mapped value from this observable.
	 */
	default <R> ObservableObject<R> mapObject(Function<T, R> valueMapper) {
		ObservableObject<R> observable = new ObservableObject<>(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @return Observable {@code String}, with mapped value from this observable.
	 */
	default ObservableString mapString() {
		return mapString(String::valueOf);
	}

	/**
	 * @param format
	 * 		Format for {@link String#format(String, Object...)}.
	 *
	 * @return Observable {@code String}, with mapped value from this observable.
	 */
	default ObservableString mapFormattedString(String format) {
		return mapString(v -> String.format(format, getValue()));
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code String}, with mapped value from this observable.
	 */
	default ObservableString mapString(Function<T, String> valueMapper) {
		ObservableString observable = new ObservableString(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code boolean}, with mapped value from this observable.
	 */
	default ObservableBoolean mapBoolean(Function<T, Boolean> valueMapper) {
		ObservableBoolean observable = new ObservableBoolean(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code byte}, with mapped value from this observable.
	 */
	default ObservableByte mapByte(Function<T, Byte> valueMapper) {
		ObservableByte observable = new ObservableByte(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code char}, with mapped value from this observable.
	 */
	default ObservableCharacter mapCharacter(Function<T, Character> valueMapper) {
		ObservableCharacter observable = new ObservableCharacter(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code double}, with mapped value from this observable.
	 */
	default ObservableDouble mapDouble(Function<T, Double> valueMapper) {
		ObservableDouble observable = new ObservableDouble(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code float}, with mapped value from this observable.
	 */
	default ObservableFloat mapFloat(Function<T, Float> valueMapper) {
		ObservableFloat observable = new ObservableFloat(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code int}, with mapped value from this observable.
	 */
	default ObservableInteger mapInt(Function<T, Integer> valueMapper) {
		ObservableInteger observable = new ObservableInteger(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code long}, with mapped value from this observable.
	 */
	default ObservableLong mapLong(Function<T, Long> valueMapper) {
		ObservableLong observable = new ObservableLong(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code short}, with mapped value from this observable.
	 */
	default ObservableShort mapShort(Function<T, Short> valueMapper) {
		ObservableShort observable = new ObservableShort(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}
}
