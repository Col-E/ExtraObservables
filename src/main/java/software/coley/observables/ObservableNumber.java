package software.coley.observables;

import software.coley.observables.util.NumberUtil;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Observable for a {@link Number} value.
 *
 * @param <N>
 * 		Number type.
 *
 * @author Matt Coley
 */
public class ObservableNumber<N extends Number> extends AbstractObservable<N> {
	/**
	 * @param value
	 * 		Initial value.
	 */
	public ObservableNumber(N value) {
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
	public <I> ObservableNumber(N value, Function<I, N> boundValueMapper) {
		super(value, boundValueMapper);
		Objects.requireNonNull(value, "Numeric values cannot be null");
	}

	@Override
	protected void validateNewValue(N newValue) {
		Objects.requireNonNull(newValue, "Numeric values cannot be null");
	}

	/**
	 * Update the current value by adding the given number.
	 *
	 * @param value
	 * 		Value to increment by.
	 *
	 * @return The updated value.
	 */
	public N add(N value) {
		return setNumberValue(NumberUtil.add(getValue(), value).getValue());
	}

	/**
	 * Update the current value by subtracting the given number.
	 *
	 * @param value
	 * 		Value to decrement by.
	 *
	 * @return The updated value.
	 */
	public N subtract(N value) {
		return setNumberValue(NumberUtil.sub(getValue(), value).getValue());
	}

	/**
	 * Update the current value by multiplying by the given number.
	 *
	 * @param value
	 * 		Value to multiply by.
	 *
	 * @return The updated value.
	 */
	public N multiply(N value) {
		return setNumberValue(NumberUtil.mul(getValue(), value).getValue());
	}

	/**
	 * Update the current value by dividing by the given number.
	 *
	 * @param value
	 * 		Value to divide by.
	 *
	 * @return The updated value.
	 */
	public N divide(N value) {
		return setNumberValue(NumberUtil.div(getValue(), value).getValue());
	}

	/**
	 * Update the current value getting the modulo remainder with the given number.
	 *
	 * @param value
	 * 		Value to modulo by.
	 *
	 * @return The updated value.
	 */
	public N remainder(N value) {
		return setNumberValue(NumberUtil.rem(getValue(), value).getValue());
	}

	/**
	 * Update the current value by bitwise AND'ing with the given number.
	 *
	 * @param value
	 * 		Value to AND with.
	 *
	 * @return The updated value.
	 */
	public N and(N value) {
		return setNumberValue(NumberUtil.add(getValue(), value).getValue());
	}

	/**
	 * Update the current value by bitwise OR'ing with the given number.
	 *
	 * @param value
	 * 		Value to OR with.
	 *
	 * @return The updated value.
	 */
	public N or(N value) {
		return setNumberValue(NumberUtil.or(getValue(), value).getValue());
	}

	/**
	 * Update the current value by bitwise XOR'ing with the given number.
	 *
	 * @param value
	 * 		Value to XOR with.
	 *
	 * @return The updated value.
	 */
	public N xor(N value) {
		return setNumberValue(NumberUtil.xor(getValue(), value).getValue());
	}

	/**
	 * Update the current value by bitwise shifting left with the given number.
	 *
	 * @param value
	 * 		Value to shift by.
	 *
	 * @return The updated value.
	 */
	public N shiftLeft(N value) {
		return setNumberValue(NumberUtil.shiftLeft(getValue(), value).getValue());
	}

	/**
	 * Update the current value by bitwise shifting right with the given number.
	 *
	 * @param value
	 * 		Value to shift by.
	 *
	 * @return The updated value.
	 */
	public N shiftRight(N value) {
		return setNumberValue(NumberUtil.shiftRight(getValue(), value).getValue());
	}

	/**
	 * Update the current value by bitwise shifting right with the given number.
	 *
	 * @param value
	 * 		Value to shift by.
	 *
	 * @return The updated value.
	 */
	public N shiftRightUnsigned(N value) {
		return setNumberValue(NumberUtil.shiftRightU(getValue(), value).getValue());
	}

	/**
	 * Update the current value by negating it.
	 *
	 * @return The updated value.
	 */
	public N negate() {
		return setNumberValue(NumberUtil.neg(getValue()).getValue());
	}

	/**
	 * @return Observable {@code byte}, with mapped value from this observable.
	 */
	public ObservableByte mapAsByte() {
		Function<Number, Byte> valueMapper = Number::byteValue;
		return new ObservableByte(valueMapper.apply(getValue()), valueMapper).bindTo(this);
	}

	/**
	 * @return Observable {@code double}, with mapped value from this observable.
	 */
	public ObservableDouble mapAsDouble() {
		Function<Number, Double> valueMapper = Number::doubleValue;
		return new ObservableDouble(valueMapper.apply(getValue()), valueMapper).bindTo(this);
	}

	/**
	 * @return Observable {@code float}, with mapped value from this observable.
	 */
	public ObservableFloat mapAsFloat() {
		Function<Number, Float> valueMapper = Number::floatValue;
		return new ObservableFloat(valueMapper.apply(getValue()), valueMapper).bindTo(this);
	}

	/**
	 * @return Observable {@code int}, with mapped value from this observable.
	 */
	public ObservableInteger mapAsInt() {
		Function<Number, Integer> valueMapper = Number::intValue;
		return new ObservableInteger(valueMapper.apply(getValue()), valueMapper).bindTo(this);
	}

	/**
	 * @return Observable {@code long}, with mapped value from this observable.
	 */
	public ObservableLong mapLong() {
		Function<Number, Long> valueMapper = Number::longValue;
		return new ObservableLong(valueMapper.apply(getValue()), valueMapper).bindTo(this);
	}

	/**
	 * @return Observable {@code short}, with mapped value from this observable.
	 */
	public ObservableShort mapAsShort() {
		Function<Number, Short> valueMapper = Number::shortValue;
		return new ObservableShort(valueMapper.apply(getValue()), valueMapper).bindTo(this);
	}

	/**
	 * @param value
	 * 		Value to multiply by.
	 *
	 * @return Observable number, with mapped value from this observable.
	 */
	private ObservableNumber<? extends Number> mapFunction(N value, BiFunction<Number, Number, NumberUtil.Result> func) {
		Function<N, ? extends Number> valueMapper = in -> func.apply(in, value).getValue();
		ObservableNumber<? extends Number> observable = func.apply(getValue(), value).toObservable(valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @return Observable number, with mapped negated value from this observable.
	 */
	public ObservableNumber<? extends Number> mapNegate() {
		Function<N, ? extends Number> valueMapper = in -> NumberUtil.neg(in).getValue();
		ObservableNumber<? extends Number> observable = NumberUtil.neg(getValue()).toObservable(valueMapper);
		observable.bindTo(this);
		return observable;
	}

	/**
	 * @param value
	 * 		Value to compare to.
	 *
	 * @return Observable number, with mapped compared value from this observable.
	 */
	public ObservableNumber<? extends Number> mapCompare(N value) {
		return mapFunction(value, NumberUtil::cmp);
	}

	/**
	 * @param value
	 * 		Value to subtract by.
	 *
	 * @return Observable number, with mapped subtracted value from this observable.
	 */
	public ObservableNumber<? extends Number> mapSubtract(N value) {
		return mapFunction(value, NumberUtil::sub);
	}

	/**
	 * @param value
	 * 		Value to add by.
	 *
	 * @return Observable number, with mapped added value from this observable.
	 */
	public ObservableNumber<? extends Number> mapAdd(N value) {
		return mapFunction(value, NumberUtil::add);
	}

	/**
	 * @param value
	 * 		Value to multiply by.
	 *
	 * @return Observable number, with mapped multiplied value from this observable.
	 */
	public ObservableNumber<? extends Number> mapMultiply(N value) {
		return mapFunction(value, NumberUtil::mul);
	}

	/**
	 * @param value
	 * 		Value to divide by.
	 *
	 * @return Observable number, with mapped divided value from this observable.
	 */
	public ObservableNumber<? extends Number> mapDivide(N value) {
		return mapFunction(value, NumberUtil::div);
	}

	/**
	 * @param value
	 * 		Value to get remainder by.
	 *
	 * @return Observable number, with mapped remaining value from this observable.
	 */
	public ObservableNumber<? extends Number> mapRemainder(N value) {
		return mapFunction(value, NumberUtil::rem);
	}

	/**
	 * @param value
	 * 		Value to bitwise and with.
	 *
	 * @return Observable number, with mapped and'd value from this observable.
	 */
	public ObservableNumber<? extends Number> mapAnd(N value) {
		return mapFunction(value, NumberUtil::and);
	}

	/**
	 * @param value
	 * 		Value to bitwise or with.
	 *
	 * @return Observable number, with mapped or'd value from this observable.
	 */
	public ObservableNumber<? extends Number> mapOr(N value) {
		return mapFunction(value, NumberUtil::or);
	}

	/**
	 * @param value
	 * 		Value to bitwise xor with.
	 *
	 * @return Observable number, with mapped xor'd value from this observable.
	 */
	public ObservableNumber<? extends Number> mapXor(N value) {
		return mapFunction(value, NumberUtil::xor);
	}

	/**
	 * @param value
	 * 		Value to shift by.
	 *
	 * @return Observable number, with mapped left-shifted value from this observable.
	 */
	public ObservableNumber<? extends Number> mapShiftLeft(N value) {
		return mapFunction(value, NumberUtil::shiftLeft);
	}

	/**
	 * @param value
	 * 		Value to shift by.
	 *
	 * @return Observable number, with mapped right-shifted value from this observable.
	 */
	public ObservableNumber<? extends Number> mapShiftRight(N value) {
		return mapFunction(value, NumberUtil::shiftRight);
	}

	/**
	 * @param value
	 * 		Value to shift by.
	 *
	 * @return Observable number, with mapped right-shifted value from this observable.
	 */
	public ObservableNumber<? extends Number> mapShiftRightUnsigned(N value) {
		return mapFunction(value, NumberUtil::shiftRightU);
	}

	/**
	 * @param value
	 * 		New value to set.
	 *
	 * @return Value as correct generic type.
	 */
	@SuppressWarnings("unchecked")
	private N setNumberValue(Number value) {
		N actual = (N) value;
		setValue(actual);
		return actual;
	}
}
