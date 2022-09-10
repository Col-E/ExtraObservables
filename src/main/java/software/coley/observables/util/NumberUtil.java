package software.coley.observables.util;

import software.coley.observables.*;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * General number parsing and other operations.
 *
 * @author Matt Coley
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class NumberUtil {
	private static final Map<Class<? extends Number>, BiFunction<Number, Function<Object, ? extends Number>, ? extends ObservableNumber>> map = new IdentityHashMap<>();

	/**
	 * @param input
	 * 		Text input that represents a number.
	 * 		<ul>
	 * 		<li>Numbers ending with {@code F} are parsed as {@link Float}.</li>
	 * 		<li>Numbers ending with {@code D} are parsed as {@link Double}.</li>
	 * 		<li>Numbers ending with {@code L} are parsed as {@link Long}.</li>
	 * 		</ul>
	 *
	 * @return Number parsed from text.
	 * Can be an {@link Integer}, {@link Long}, {@link Float}, or {@link Double}.
	 */
	@SuppressWarnings("")
	public static Result parse(String input) {
		String text = input.trim().toUpperCase();
		Result value;
		if (text.indexOf('.') > 0) {
			value = parseDecimal(text);
		} else {
			if (text.endsWith("L") && text.startsWith("0X"))
				value = new Result(Long.parseLong(text.substring(2, text.indexOf("L")), 16), Long.class);
			else if (text.endsWith("L"))
				value = new Result(Long.parseLong(text.substring(0, text.indexOf("L"))), Long.class);
			else if (text.startsWith("0X"))
				value = new Result(Integer.parseInt(text.substring(2), 16), Integer.class);
			else if (text.endsWith("F"))
				value = new Result(Float.parseFloat(text.substring(0, text.indexOf("F"))), Float.class);
			else if (text.endsWith("D") || text.contains("."))
				value = new Result(Double.parseDouble(text.substring(0, text.indexOf("D"))), Double.class);
			else
				value = new Result(Integer.parseInt(text), Integer.class);
		}
		return value;
	}

	/**
	 * @param text
	 * 		Text input that represents a decimal number.
	 *
	 * @return Decimal number, either {@link Float} or {@link Double}.
	 */
	private static Result parseDecimal(String text) {
		if (text.endsWith("F"))
			return new Result(Float.parseFloat(text.substring(0, text.indexOf("F"))), Float.class);
		else if (text.endsWith("D") || text.contains("."))
			return new Result(Double.parseDouble(text.substring(0, text.indexOf("D"))), Double.class);
		else
			return new Result(Double.parseDouble(text), Double.class);
	}

	/**
	 * Compare two numeric values, regardless of their type.
	 *
	 * @param right
	 * 		First value.
	 * @param left
	 * 		Second value.
	 *
	 * @return Comparison of {@code X.compare(left, right)}
	 */
	public static Result cmp(Number left, Number right) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (right instanceof Double || left instanceof Double) {
			return new Result(Double.compare(left.doubleValue(), right.doubleValue()), Integer.class);
		} else if (right instanceof Float || left instanceof Float) {
			return new Result(Float.compare(left.floatValue(), right.floatValue()), Integer.class);
		} else if (right instanceof Long || left instanceof Long) {
			return new Result(Long.compare(left.longValue(), right.longValue()), Integer.class);
		} else {
			return new Result(Integer.compare(left.intValue(), right.intValue()), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Difference value.
	 */
	public static Result sub(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Double || first instanceof Double) {
			return new Result(first.doubleValue() - second.doubleValue(), Double.class);
		} else if (second instanceof Float || first instanceof Float) {
			return new Result(first.floatValue() - second.floatValue(), Float.class);
		} else if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() - second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() - second.intValue(), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Sum value.
	 */
	public static Result add(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Double || first instanceof Double) {
			return new Result(first.doubleValue() + second.doubleValue(), Double.class);
		} else if (second instanceof Float || first instanceof Float) {
			return new Result(first.floatValue() + second.floatValue(), Float.class);
		} else if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() + second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() + second.intValue(), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Product value.
	 */
	public static Result mul(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Double || first instanceof Double) {
			return new Result(first.doubleValue() * second.doubleValue(), Double.class);
		} else if (second instanceof Float || first instanceof Float) {
			return new Result(first.floatValue() * second.floatValue(), Float.class);
		} else if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() * second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() * second.intValue(), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Divided value.
	 */
	public static Result div(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Double || first instanceof Double) {
			return new Result(first.doubleValue() / second.doubleValue(), Double.class);
		} else if (second instanceof Float || first instanceof Float) {
			return new Result(first.floatValue() / second.floatValue(), Float.class);
		} else if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() / second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() / second.intValue(), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Remainder value.
	 */
	public static Result rem(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Double || first instanceof Double) {
			return new Result(first.doubleValue() % second.doubleValue(), Double.class);
		} else if (second instanceof Float || first instanceof Float) {
			return new Result(first.floatValue() % second.floatValue(), Float.class);
		} else if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() % second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() % second.intValue(), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Value where matching bits remain.
	 */
	public static Result and(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() & second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() & second.intValue(), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Value where all active bits remain.
	 */
	public static Result or(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() | second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() | second.intValue(), Integer.class);
		}
	}

	/**
	 * @param first
	 * 		First value.
	 * @param second
	 * 		Second value.
	 *
	 * @return Value where non-matching bits remain.
	 */
	public static Result xor(Number first, Number second) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (second instanceof Long || first instanceof Long) {
			return new Result(first.longValue() ^ second.longValue(), Long.class);
		} else {
			return new Result(first.intValue() ^ second.intValue(), Integer.class);
		}
	}

	/**
	 * @param value
	 * 		Numeric value.
	 *
	 * @return Negated value.
	 */
	public static Result neg(Number value) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (value instanceof Double) {
			return new Result(-value.doubleValue(), Double.class);
		} else if (value instanceof Float) {
			return new Result(-value.floatValue(), Float.class);
		} else if (value instanceof Long) {
			return new Result(-value.longValue(), Long.class);
		} else {
			return new Result(-value.intValue(), Integer.class);
		}
	}

	/**
	 * @param value
	 * 		Numeric value.
	 * @param shift
	 * 		Value to shift by.
	 *
	 * @return Shifted value.
	 */
	public static Result shiftLeft(Number value, Number shift) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (value instanceof Long) {
			return new Result(value.longValue() << shift.longValue(), Long.class);
		} else {
			return new Result(value.intValue() << shift.intValue(), Integer.class);
		}
	}

	/**
	 * @param value
	 * 		Numeric value.
	 * @param shift
	 * 		Value to shift by.
	 *
	 * @return Shifted value.
	 */
	public static Result shiftRight(Number value, Number shift) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (value instanceof Long) {
			return new Result(value.longValue() >> shift.longValue(), Long.class);
		} else {
			return new Result(value.intValue() >> shift.intValue(), Integer.class);
		}
	}

	/**
	 * @param value
	 * 		Numeric value.
	 * @param shift
	 * 		Value to shift by.
	 *
	 * @return Shifted value.
	 */
	public static Result shiftRightU(Number value, Number shift) {
		// Check for widest types first, go down the type list to narrower types until reaching int.
		if (value instanceof Long) {
			return new Result(value.longValue() >>> shift.longValue(), Long.class);
		} else {
			return new Result(value.intValue() >>> shift.intValue(), Integer.class);
		}
	}

	static {
		map.put(Byte.class, (value, valueMapper) -> new ObservableByte(value.byteValue(), (Function<Object, Byte>) valueMapper));
		map.put(Double.class, (value, valueMapper) -> new ObservableDouble(value.doubleValue(), (Function<Object, Double>) valueMapper));
		map.put(Float.class, (value, valueMapper) -> new ObservableFloat(value.floatValue(), (Function<Object, Float>) valueMapper));
		map.put(Integer.class, (value, valueMapper) -> new ObservableInteger(value.intValue(), (Function<Object, Integer>) valueMapper));
		map.put(Long.class, (value, valueMapper) -> new ObservableLong(value.longValue(), (Function<Object, Long>) valueMapper));
		map.put(Short.class, (value, valueMapper) -> new ObservableShort(value.shortValue(), (Function<Object, Short>) valueMapper));
	}

	/**
	 * Wrapper for operations to yield value and type.
	 */
	public static class Result {
		private final Number value;
		private final Class<? extends Number> type;

		/**
		 * @param value
		 * 		Result value.
		 * @param type
		 * 		Result value type.
		 */
		public Result(Number value, Class<? extends Number> type) {
			this.value = value;
			this.type = type;
		}

		/**
		 * @param valueMapper
		 * 		Mapper to use for bound observable.
		 * @param <I>
		 * 		Input type.
		 * @param <N>
		 * 		Output type.
		 *
		 * @return Observable number of the result value.
		 */
		@SuppressWarnings({"rawtypes", "unchecked"})
		public <I extends Number, N extends Number> ObservableNumber<N> toObservable(Function<I, N> valueMapper) {
			return map.get(type).apply(value, (Function) valueMapper);
		}

		/**
		 * @return Result value.
		 */
		public Number getValue() {
			return value;
		}

		/**
		 * @return Result value type.
		 */
		public Class<? extends Number> getType() {
			return type;
		}
	}
}
