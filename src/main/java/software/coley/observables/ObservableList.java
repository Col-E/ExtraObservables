package software.coley.observables;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Observable for generic lists.
 *
 * @param <T>
 * 		Collection value type.
 * @param <L>
 * 		List type.
 *
 * @author Matt Coley
 */
public class ObservableList<T, L extends List<T>> extends ObservableCollection<T, L> implements List<T> {
	/**
	 * @param listConstructor
	 * 		Constructor to create new lists.
	 */
	public ObservableList(Supplier<L> listConstructor) {
		super(listConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param collectionConstructor
	 * 		Constructor to create new lists.
	 */
	public ObservableList(L value, Supplier<L> collectionConstructor) {
		super(value, collectionConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param boundValueMapper
	 * 		Mapper used to map values for bindings.
	 * @param collectionConstructor
	 * 		Constructor to create new lists.
	 * @param <I>
	 * 		Input mapping type.
	 */
	public <I> ObservableList(L value, Function<I, L> boundValueMapper, Supplier<L> collectionConstructor) {
		super(value, boundValueMapper, collectionConstructor);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		L list = getValue();
		L newList = collectionConstructor.get();
		newList.addAll(list);
		boolean result = newList.addAll(c);
		setValue(newList);
		return result;
	}

	@Override
	public T get(int index) {
		return getValue().get(index);
	}

	@Override
	public T set(int index, T element) {
		L list = getValue();
		L newList = collectionConstructor.get();
		newList.addAll(list);
		T result = newList.set(index, element);
		setValue(newList);
		return result;
	}

	@Override
	public void add(int index, T element) {
		L list = getValue();
		L newList = collectionConstructor.get();
		newList.addAll(list);
		newList.add(index, element);
		setValue(newList);
	}

	@Override
	public T remove(int index) {
		L list = getValue();
		L newList = collectionConstructor.get();
		newList.addAll(list);
		T result = newList.remove(index);
		setValue(newList);
		return result;
	}

	@Override
	public int indexOf(Object o) {
		return getValue().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return getValue().lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return getValue().listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return getValue().listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return getValue().subList(fromIndex, toIndex);
	}
}
