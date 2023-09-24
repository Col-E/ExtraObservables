package software.coley.observables;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListenerTests {
	@Test
	void testAddAndRemove() {
		ObservableInteger ob = new ObservableInteger(0);
		ChangeListener<Integer> listener = (observable, oldValue, newValue) -> {
		};
		ob.addChangeListener(listener);
		assertTrue(ob.removeChangeListener(listener), "Couldn't remove listener");
		assertFalse(ob.removeChangeListener(listener), "Should only need to remove once");
	}

	@Test
	void testAddAndRemoveAsync() {
		ObservableInteger ob = new ObservableInteger(0);
		ChangeListener<Integer> listener = (observable, oldValue, newValue) -> {
		};
		ob.addAsyncChangeListener(listener, Executors.newSingleThreadExecutor());
		assertTrue(ob.removeChangeListener(listener), "Couldn't remove listener");
		assertFalse(ob.removeChangeListener(listener), "Should only need to remove once");
	}

	@Test
	void testAsyncListenerIsAsync() {
		AtomicInteger counter = new AtomicInteger();
		ObservableInteger ob = new ObservableInteger(0);
		ChangeListener<Integer> listener = (observable, oldValue, newValue) -> {
			try {
				Thread.sleep(1000);
				counter.getAndIncrement();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		};

		// The async listener waits a second before incrementing.
		// Doing 100 updates and checking immediately after should still occur way before the counter has caught up.
		ob.addAsyncChangeListener(listener, Executors.newCachedThreadPool());
		int iterations = 100;
		for (int i = 0; i < iterations; i++) {
			ob.add(1);
		}
		assertTrue(counter.get() < iterations, "The delayed async listener calls should not have caught up yet");
	}
}
