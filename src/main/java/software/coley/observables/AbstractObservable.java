package software.coley.observables;

import java.util.*;
import java.util.function.Function;

/**
 * Base type for observable implementations.
 *
 * @param <T>
 * 		Generic value type.
 *
 * @author Matt Coley
 */
public class AbstractObservable<T> implements Observable<T> {
	private final List<ChangeListener<T>> changeListeners = new ArrayList<>();
	@SuppressWarnings("rawtypes") // has to be raw for generic usage to compile, cannot use '?'
	private final Set<Observable> bindReceivers = Collections.newSetFromMap(new IdentityHashMap<>());
	private final Function<Object, T> boundValueMapper;
	private Observable<?> bindTarget;
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

	@Override
	public final T getValue() {
		return value;
	}

	@Override
	public final void setValue(T newValue) {
		if (bindTarget != null)
			throw new BoundValueSetException(this);
		validateNewValue(newValue);
		set(newValue);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends Observable<?>> S bindTo(Observable<?> observable) {
		Objects.requireNonNull(observable, "Observable target must not be null");
		if (bindTarget != null) {
			if (bindTarget != observable)
				return (S) this;
			throw new BoundTargetSetException(this);
		}
		bindTarget = observable;
		observable.getBoundReceivers().add(this);
		return (S) this;
	}

	@Override
	public boolean unbind(Observable<T> observable) {
		bindTarget = null;
		return observable.getBoundReceivers().remove(this);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Set<Observable> getBoundReceivers() {
		return bindReceivers;
	}

	@Override
	public Function<Object, T> getBoundValueMapper() {
		return boundValueMapper;
	}

	@Override
	public void addChangeListener(ChangeListener<T> listener) {
		Objects.requireNonNull(listener, "Listener must not be null");
		changeListeners.add(listener);
	}

	@Override
	public boolean removeChangeListener(ChangeListener<T> listener) {
		return changeListeners.remove(listener);
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
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void set(T newValue) {
		T oldValue = this.value;
		this.value = newValue;
		if (newValue != oldValue) {
			changeListeners.forEach(l -> l.changed(this, oldValue, newValue));
			bindReceivers.forEach(o -> {
				if (o instanceof AbstractObservable) {
					AbstractObservable ao = (AbstractObservable) o;
					ao.set(ao.map(newValue));
				} else {
					throw new UnsupportedOperationException("Receiver does not implement internal set/map operations");
				}
			});
		}
	}

	/**
	 * Passes the given value through the {@link #getBoundValueMapper() bound value mapper} if it exists.
	 * Otherwise, the input value is assumed to be of type {@code T}.
	 *
	 * @param value
	 * 		Input value.
	 *
	 * @return Mapped value.
	 */
	@SuppressWarnings("unchecked")
	private T map(Object value) {
		return boundValueMapper == null ? (T) value : boundValueMapper.apply(value);
	}
}
