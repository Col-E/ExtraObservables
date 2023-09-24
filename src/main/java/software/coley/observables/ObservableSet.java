package software.coley.observables;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Observable for generic sets.
 *
 * @param <T>
 * 		Collection value type.
 * @param <S>
 * 		Set type.
 *
 * @author Matt Coley
 */
public class ObservableSet<T, S extends Set<T>> extends ObservableCollection<T, S> implements Set<T> {
	/**
	 * @param listConstructor
	 * 		Constructor to create new sets.
	 */
	public ObservableSet(Supplier<S> listConstructor) {
		super(listConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param collectionConstructor
	 * 		Constructor to create new sets.
	 */
	public ObservableSet(S value, Supplier<S> collectionConstructor) {
		super(value, collectionConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param boundValueMapper
	 * 		Mapper used to map values for sets.
	 * @param collectionConstructor
	 * 		Constructor to create new lists.
	 * @param <I>
	 * 		Input mapping type.
	 */
	public <I> ObservableSet(S value, Function<I, S> boundValueMapper, Supplier<S> collectionConstructor) {
		super(value, boundValueMapper, collectionConstructor);
	}
}
