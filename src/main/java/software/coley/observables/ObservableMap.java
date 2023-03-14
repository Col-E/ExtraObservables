package software.coley.observables;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Observable for generic maps.
 *
 * @author Matt Coley
 */
public class ObservableMap<K, V, M extends Map<K, V>> extends ObservableObject<M> implements Map<K, V> {
	private final Supplier<M> mapConstructor;

	/**
	 * @param mapConstructor
	 * 		Constructor to create new maps of the type {@code M}.
	 */
	public ObservableMap(Supplier<M> mapConstructor) {
		this(mapConstructor.get(), null, mapConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param mapConstructor
	 * 		Constructor to create new maps of the type {@code M}.
	 */
	public ObservableMap(M value, Supplier<M> mapConstructor) {
		this(value, null, mapConstructor);
	}

	/**
	 * @param value
	 * 		Initial value.
	 * @param boundValueMapper
	 * 		Mapper used to map values for bindings.
	 * @param mapConstructor
	 * 		Constructor to create new maps of the type {@code M}.
	 * @param <I>
	 * 		Input mapping type.
	 */
	public <I> ObservableMap(M value, Function<I, M> boundValueMapper, Supplier<M> mapConstructor) {
		super(value, boundValueMapper);
		this.mapConstructor = mapConstructor;
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
	public boolean containsKey(Object key) {
		return getValue().containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return getValue().containsValue(value);
	}

	@Override
	public V get(Object key) {
		return getValue().get(key);
	}

	@Override
	public V put(K key, V value) {
		M map = getValue();
		M newMap = mapConstructor.get();
		newMap.putAll(map);
		V replaced = newMap.put(key, value);
		setValue(newMap);
		return replaced;
	}

	@Override
	public V remove(Object key) {
		M map = getValue();
		M newMap = mapConstructor.get();
		newMap.putAll(map);
		V removed = newMap.remove(key);
		setValue(newMap);
		return removed;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		M map = getValue();
		M newMap = mapConstructor.get();
		newMap.putAll(map);
		newMap.putAll(m);
		setValue(newMap);
	}

	@Override
	public void clear() {
		setValue(mapConstructor.get());
	}

	@Override
	public Set<K> keySet() {
		return getValue().keySet();
	}

	@Override
	public Collection<V> values() {
		return getValue().values();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return getValue().entrySet();
	}
}
