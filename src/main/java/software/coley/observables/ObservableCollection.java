package software.coley.observables;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Observable for generic collections.
 *
 * @param <T>
 * 		Collection value type.
 * @param <C>
 * 		Collection type.
 *
 * @author Matt Coley
 */
public class ObservableCollection<T, C extends Collection<T>> extends ObservableObject<C> implements Collection<T> {
	protected final Supplier<C> collectionConstructor;

	/**
	 * @param collectionConstructor
	 * 		Constructor to create new collections of the type {@code C}.
	 */
	public ObservableCollection(Supplier<C> collectionConstructor) {
		this(collectionConstructor.get(), null, collectionConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param collectionConstructor
	 * 		Constructor to create new collections of the type {@code C}.
	 */
	public ObservableCollection(C value, Supplier<C> collectionConstructor) {
		this(value, null, collectionConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param boundValueMapper
	 * 		Mapper used to map values for bindings.
	 * @param collectionConstructor
	 * 		Constructor to create new collections of the type {@code C}.
	 * @param <I>
	 * 		Input mapping type.
	 */
	public <I> ObservableCollection(C value, Function<I, C> boundValueMapper, Supplier<C> collectionConstructor) {
		super(value, boundValueMapper);
		this.collectionConstructor = collectionConstructor;
	}

	@Override
	public int size() {
		return getValue().size();
	}

	@Override
	public boolean isEmpty() {
		return getValue().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return getValue().contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return getValue().iterator();
	}

	@Override
	public boolean removeIf(Predicate<? super T> filter) {
		Objects.requireNonNull(filter);
		boolean removed = false;
		final Iterator<T> each = iterator();
		while (each.hasNext()) {
			if (filter.test(each.next())) {
				each.remove();
				removed = true;
			}
		}

		// Trigger update in listener by resetting value
		if (removed) {
			C copy = collectionConstructor.get();
			copy.addAll(getValue());
			setValue(copy);
		}
		return removed;
	}

	@Override
	public Object[] toArray() {
		return getValue().toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return getValue().toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return getValue().containsAll(c);
	}

	@Override
	public boolean add(T t) {
		C collection = getValue();
		C newCollection = collectionConstructor.get();
		newCollection.addAll(collection);
		boolean result = newCollection.add(t);
		setValue(newCollection);
		return result;
	}

	@Override
	public boolean remove(Object o) {
		C collection = getValue();
		C newCollection = collectionConstructor.get();
		newCollection.addAll(collection);
		boolean result = newCollection.remove(o);
		setValue(newCollection);
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		C collection = getValue();
		C newCollection = collectionConstructor.get();
		newCollection.addAll(collection);
		boolean result = newCollection.addAll(c);
		setValue(newCollection);
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		C collection = getValue();
		C newCollection = collectionConstructor.get();
		newCollection.addAll(collection);
		boolean result = newCollection.removeAll(c);
		setValue(newCollection);
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		C collection = getValue();
		C newCollection = collectionConstructor.get();
		newCollection.addAll(collection);
		boolean result = newCollection.retainAll(c);
		setValue(newCollection);
		return result;
	}

	@Override
	public void clear() {
		setValue(collectionConstructor.get());
	}
}
