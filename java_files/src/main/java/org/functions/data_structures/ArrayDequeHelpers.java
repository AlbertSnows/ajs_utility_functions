package org.functions.data_structures;

import org.immutables.value.Value;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

@Value.Immutable
public class ArrayDequeHelpers {
	public static <U, T extends Deque<U>> @org.jetbrains.annotations.NotNull T popFromDeque(@NotNull T deque) {
		deque.pop();
		return deque;
	}

	@Contract("_, _ -> param1")
	public static <T> @NotNull ArrayDeque<T> pushToDeque(@NotNull ArrayDeque<T> deque, T element) {
		deque.push(element);
		return deque;
	}
}
