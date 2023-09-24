package software.coley.observables;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ValueTests {
	@Nested
	class Integers {
		@Test
		void testIntNegateBind() {
			ObservableInteger intA = new ObservableInteger(1);
			ObservableNumber<?> intB = intA.mapNegate();
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert negation
			assertEquals(1, intA.getValue());
			assertEquals(-1, intB.getValue());
			intA.setValue(10);
			assertEquals(10, intA.getValue());
			assertEquals(-10, intB.getValue());
		}

		@Test
		void testIntMultiplyBind() {
			ObservableInteger intA = new ObservableInteger(1);
			ObservableNumber<?> intB = intA.mapMultiply(5);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert multiplication
			assertEquals(1, intA.getValue());
			assertEquals(5, intB.getValue());
			intA.setValue(10);
			assertEquals(10, intA.getValue());
			assertEquals(50, intB.getValue());
		}

		@Test
		void testIntCompareBind() {
			ObservableInteger intA = new ObservableInteger(1);
			ObservableNumber<?> intB = intA.mapCompare(2);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert comparison
			assertEquals(1, intA.getValue());
			assertEquals(-1, intB.getValue());
			intA.setValue(2);
			assertEquals(2, intA.getValue());
			assertEquals(0, intB.getValue());
			intA.setValue(3);
			assertEquals(3, intA.getValue());
			assertEquals(1, intB.getValue());
		}

		@Test
		void testIntSubtractBind() {
			ObservableInteger intA = new ObservableInteger(10);
			ObservableNumber<?> intB = intA.mapSubtract(5);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert subtraction
			assertEquals(10, intA.getValue());
			assertEquals(5, intB.getValue());
			intA.setValue(100);
			assertEquals(100, intA.getValue());
			assertEquals(95, intB.getValue());
		}

		@Test
		void testIntAdditionBind() {
			ObservableInteger intA = new ObservableInteger(5);
			ObservableNumber<?> intB = intA.mapAdd(5);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert addition
			assertEquals(5, intA.getValue());
			assertEquals(10, intB.getValue());
			intA.setValue(95);
			assertEquals(95, intA.getValue());
			assertEquals(100, intB.getValue());
		}

		@Test
		void testIntDivideBind() {
			ObservableInteger intA = new ObservableInteger(100);
			ObservableNumber<?> intB = intA.mapDivide(5);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert division
			assertEquals(100, intA.getValue());
			assertEquals(20, intB.getValue());
			intA.setValue(95);
			assertEquals(95, intA.getValue());
			assertEquals(19, intB.getValue());
		}

		@Test
		void testIntRemainderBind() {
			ObservableInteger intA = new ObservableInteger(0);
			ObservableNumber<?> intB = intA.mapRemainder(10);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert remainder
			assertEquals(0, intA.getValue());
			assertEquals(0, intB.getValue());
			intA.setValue(1);
			assertEquals(1, intA.getValue());
			assertEquals(1, intB.getValue());
			intA.setValue(10);
			assertEquals(10, intA.getValue());
			assertEquals(0, intB.getValue());
			intA.setValue(11);
			assertEquals(11, intA.getValue());
			assertEquals(1, intB.getValue());
		}

		@Test
		void testIntAndBind() {
			ObservableInteger intA = new ObservableInteger(0b11111);
			ObservableNumber<?> intB = intA.mapAnd(0b10101);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert bitwise and
			assertEquals(0b11111, intA.getValue());
			assertEquals(0b10101, intB.getValue());
			intA.setValue(0b01010);
			assertEquals(0b01010, intA.getValue());
			assertEquals(0b00000, intB.getValue());
		}

		@Test
		void testIntOrBind() {
			ObservableInteger intA = new ObservableInteger(0b10101);
			ObservableNumber<?> intB = intA.mapOr(0b01010);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert bitwise or
			assertEquals(0b10101, intA.getValue());
			assertEquals(0b11111, intB.getValue());
			intA.setValue(0b00000);
			assertEquals(0b00000, intA.getValue());
			assertEquals(0b01010, intB.getValue());
		}

		@Test
		void testIntXorBind() {
			ObservableInteger intA = new ObservableInteger(0b10101);
			ObservableNumber<?> intB = intA.mapXor(0b01010);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert bitwise xor
			assertEquals(0b10101, intA.getValue());
			assertEquals(0b11111, intB.getValue());
			intA.setValue(0b00000);
			assertEquals(0b00000, intA.getValue());
			assertEquals(0b01010, intB.getValue());
			intA.setValue(0b11111);
			assertEquals(0b11111, intA.getValue());
			assertEquals(0b10101, intB.getValue());
		}

		@Test
		void testIntShiftLeftBind() {
			ObservableInteger intA = new ObservableInteger(0b00100);
			ObservableNumber<?> intB = intA.mapShiftLeft(1);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert bitwise shl
			assertEquals(0b00100, intA.getValue());
			assertEquals(0b01000, intB.getValue());
			intA.setValue(0);
			assertEquals(0, intA.getValue());
			assertEquals(0, intB.getValue());
		}

		@Test
		void testIntShiftRightBind() {
			ObservableInteger intA = new ObservableInteger(0b00100);
			ObservableNumber<?> intB = intA.mapShiftRight(1);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert bitwise shl
			assertEquals(0b00100, intA.getValue());
			assertEquals(0b00010, intB.getValue());
			intA.setValue(0);
			assertEquals(0, intA.getValue());
			assertEquals(0, intB.getValue());
			intA.setValue(-1); // sign copied
			assertEquals(-1, intA.getValue());
			assertEquals(-1 >> 1, intB.getValue());
		}

		@Test
		void testIntShiftRightUnsignedBind() {
			ObservableInteger intA = new ObservableInteger(0b00100);
			ObservableNumber<?> intB = intA.mapShiftRightUnsigned(1);
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> intB.bindTo(intA));
			// Assert bitwise shl
			assertEquals(0b00100, intA.getValue());
			assertEquals(0b00010, intB.getValue());
			intA.setValue(0);
			assertEquals(0, intA.getValue());
			assertEquals(0, intB.getValue());
			intA.setValue(-1); // sign not copied
			assertEquals(-1, intA.getValue());
			assertEquals(-1 >>> 1, intB.getValue());
		}
	}

	@Nested
	class Booleans {
		@Test
		void testBooleanBind() {
			ObservableBoolean boolA = new ObservableBoolean(true);
			ObservableBoolean boolB = new ObservableBoolean(true);
			boolB.bindTo(boolA);
			// Changes on A should reflect in B
			assertTrue(boolA.getValue());
			assertTrue(boolB.getValue());
			assertDoesNotThrow(boolA::toggle);
			assertFalse(boolA.getValue());
			assertFalse(boolB.getValue());
			// Attempting to change B when it is bound should fail
			assertThrows(BoundValueSetException.class, boolB::toggle);
		}

		@Test
		void testBooleanNegatedBind() {
			ObservableBoolean boolA = new ObservableBoolean(true);
			ObservableBoolean boolB = boolA.negated();
			// Assert bind exists
			assertThrows(BoundTargetSetException.class, () -> boolB.bindTo(boolA));
			// Changes on A should reflect in B
			assertTrue(boolA.getValue());
			assertFalse(boolB.getValue());
			assertDoesNotThrow(boolA::toggle);
			assertFalse(boolA.getValue());
			assertTrue(boolB.getValue());
			// Attempting to change B when it is bound should fail
			assertThrows(BoundValueSetException.class, boolB::toggle);
			// Assert unbind allows B to be updated
			assertTrue(boolB.unbind(boolA));
			assertDoesNotThrow(boolB::toggle);
		}
	}

	@Nested
	class Strings {
		@Test
		void testMapIntToString() {
			ObservableInteger intA = new ObservableInteger(10);
			ObservableString stringA = intA.mapString();
			assertEquals("10", stringA.getValue());
			intA.setValue(20);
			assertEquals("20", stringA.getValue());
			assertEquals(20, stringA.mapNumber().getValue());
		}

		@Test
		void testMapDoubleToString() {
			ObservableDouble intA = new ObservableDouble(0.123456789);
			ObservableString stringA = intA.mapString();
			ObservableString stringB = intA.mapFormattedString("%.3f");
			assertEquals("0.123456789", stringA.getValue());
			assertEquals("0.123", stringB.getValue());
			intA.setValue(0.999999);
			assertEquals("0.999999", stringA.getValue());
			assertEquals("1.000", stringB.getValue());
			assertEquals(1.0, stringB.mapNumber().getValue());
		}
	}

	@Nested
	class Collections {
		@Test
		void testMapListSize() {
			ObservableCollection<String, List<String>> list = new ObservableCollection<>(ArrayList::new);
			ObservableInteger listSize = list.mapInt(List::size);
			assertEquals(0, listSize.getValue());
			// Add to list
			list.add("one");
			assertEquals(1, listSize.getValue());
			// Remove from list
			list.remove("one");
			assertEquals(0, listSize.getValue());
		}

		@Test
		void testMapListContains() {
			ObservableCollection<String, List<String>> list = new ObservableCollection<>(ArrayList::new);
			ObservableBoolean listSize = list.mapBoolean(l -> l.contains("target"));
			assertFalse(listSize.getValue());
			// Add bogus list
			list.add("not it");
			assertFalse(listSize.getValue());
			// Add target to list
			list.add("target");
			assertTrue(listSize.getValue());
			// Remove from list
			list.remove("target");
			assertFalse(listSize.getValue());
		}
	}

	@Nested
	class Maps {
		@Test
		void testMapMapSize() {
			ObservableMap<String, String, Map<String, String>> map = new ObservableMap<>(HashMap::new);
			ObservableInteger mapSize = map.mapInt(Map::size);
			assertEquals(0, mapSize.getValue());
			// Add to map
			map.put("one", "a");
			assertEquals(1, mapSize.getValue());
			// Remove from map
			map.remove("one");
			assertEquals(0, mapSize.getValue());
		}
	}
}
