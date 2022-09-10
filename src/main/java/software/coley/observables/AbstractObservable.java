package software.coley.observables;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Base type for observable implementations.
 *
 * @param <T>
 * 		Generic value type.
 *
 * @author Matt Coley
 */
public class AbstractObservable<T> implements Observable {
	private final List<ChangeListener<T>> changeListeners = new ArrayList<>();
	@SuppressWarnings("rawtypes") // has to be raw for generic usage to compile, cannot use '?'
	private final List<AbstractObservable> bindReceivers = new ArrayList<>();
	private final Function<Object, T> boundValueMapper;
	private AbstractObservable<?> bindTarget;
	private T value;

	/**
	 * @param value
	 * 		Initial value.
	 */
	public AbstractObservable(T value) {
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
	@SuppressWarnings("unchecked")
	public <I> AbstractObservable(T value, Function<I, T> boundValueMapper) {
		this.boundValueMapper = (Function<Object, T>) boundValueMapper;
		this.value = value;
	}

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
	@SuppressWarnings("unchecked")
	public <S extends AbstractObservable<?>> S bindTo(AbstractObservable<?> observable) {
		Objects.requireNonNull(observable, "Observable target must not be null");
		if (bindTarget != null) {
			if (bindTarget != observable)
				return (S) this;
			throw new BoundTargetSetException(this);
		}
		bindTarget = observable;
		observable.bindReceivers.add(this);
		return (S) this;
	}

	/**
	 * @param observable
	 * 		Other observable to unbind from.
	 *
	 * @return {@code true} on removal.
	 * {@code false} when no bind existed for the given observable.
	 */
	public boolean unbind(AbstractObservable<T> observable) {
		bindTarget = null;
		return observable.bindReceivers.remove(this);
	}

	/**
	 * @param listener
	 * 		Lister to add to receive value changes.
	 */
	public void addChangeListener(ChangeListener<T> listener) {
		Objects.requireNonNull(listener, "Listener must not be null");
		changeListeners.add(listener);
	}

	/**
	 * @param listener
	 * 		Lister to remove from receiving value changes.
	 *
	 * @return {@code true} on removal.
	 * {@code false} when no listener existed for this observable.
	 */
	public boolean removeChangeListener(ChangeListener<T> listener) {
		return changeListeners.remove(listener);
	}

	/**
	 * @param newValue
	 * 		New value to assign.
	 */
	public final void setValue(T newValue) {
		if (bindTarget != null)
			throw new BoundValueSetException(this);
		validateNewValue(newValue);
		set(newValue);
	}

	/**
	 * Validate if the value can be assigned.
	 *
	 * @param newValue
	 * 		Value to validate from {@link #setValue(Object)}.
	 */
	protected void validateNewValue(T newValue) {
		// no-op by default
	}

	/**
	 * Used internally to bypass {@link BoundValueSetException} check.
	 *
	 * @param newValue
	 * 		New value to assign.
	 */
	@SuppressWarnings("unchecked")
	private void set(T newValue) {
		T oldValue = this.value;
		this.value = newValue;
		if (newValue != oldValue) {
			changeListeners.forEach(l -> l.changed(this, oldValue, newValue));
			bindReceivers.forEach(o -> o.set(o.map(newValue)));
		}
	}

	/**
	 * @param value
	 * 		Input value.
	 *
	 * @return Mapped value.
	 */
	@SuppressWarnings("unchecked")
	private T map(Object value) {
		return boundValueMapper == null ? (T) value : boundValueMapper.apply(value);
	}

	/**
	 * @return {@code true} when {@link #getValue()} is not {@code null},
	 */
	public final boolean hasValue() {
		return value != null;
	}

	/**
	 * @return Current value.
	 */
	public final T getValue() {
		return value;
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 * @param <R>
	 * 		Type to map to.
	 *
	 * @return Observable object of type, with mapped value from this observable.
	 */
	public <R> ObservableObject<R> mapObject(Function<T, R> valueMapper) {
		ObservableObject<R> observable = new ObservableObject<>(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @return Observable {@code String}, with mapped value from this observable.
	 */
	public ObservableString mapString() {
		return mapString(String::valueOf);
	}

	/**
	 * @param format
	 * 		Format for {@link String#format(String, Object...)}.
	 *
	 * @return Observable {@code String}, with mapped value from this observable.
	 */
	public ObservableString mapFormattedString(String format) {
		return mapString(v -> String.format(format, getValue()));
	}

	/**
	 * @param valueMapper
	 * 		Mapping function to use.
	 *
	 * @return Observable {@code String}, with mapped value from this observable.
	 */
	public ObservableString mapString(Function<T, String> valueMapper) {
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
	public ObservableBoolean mapBoolean(Function<T, Boolean> valueMapper) {
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
	public ObservableByte mapByte(Function<T, Byte> valueMapper) {
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
	public ObservableCharacter mapCharacter(Function<T, Character> valueMapper) {
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
	public ObservableDouble mapDouble(Function<T, Double> valueMapper) {
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
	public ObservableFloat mapFloat(Function<T, Float> valueMapper) {
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
	public ObservableInteger mapInt(Function<T, Integer> valueMapper) {
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
	public ObservableLong mapLong(Function<T, Long> valueMapper) {
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
	public ObservableShort mapShort(Function<T, Short> valueMapper) {
		ObservableShort observable = new ObservableShort(valueMapper.apply(getValue()), valueMapper);
		observable.bindTo(this);
		return observable;
	}
}
