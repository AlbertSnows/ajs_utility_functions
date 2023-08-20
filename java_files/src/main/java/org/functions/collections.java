package org.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

// TODO: unit tests
public class collections {

	private collections() {
		throw new IllegalStateException("Utility class");
	}

	public static <V, A> A reduce(BiFunction<A, V, A> reducer, A initial_value, Collection<V> collection) {
		A accumulator = initial_value;
		for (V value : collection) {
			accumulator = reducer.apply(accumulator, value);
		}
		return accumulator;
	}

	public static List<Integer> range(Integer size) {
		List<Integer> list_of_incrementing_numbers = IntStream.range(0, size).boxed().toList();
		return list_of_incrementing_numbers;
	}

	public static <K> Object search(Map<?, ?> map, Collection<K> keys) {
		Map<?, ?> current_map = map;
		Object current_value = null;
		for (K key : keys) {
			var new_value = current_map.get(key);
			if (new_value instanceof Map) {
				current_map = (Map<?, ?>) new_value;
			} else {
				current_value = new_value;
				break;
			}
		}
		return current_value;
	}

	// refer: https://clojuredocs.org/clojure.core/some
	public static @NotNull
	Function<Function<Object, Boolean>,
					Function<List<Object>,
									Object>>
	some() {
		return pred -> coll -> some_or_default().apply(pred).apply(null).apply(coll);
	}

	public static Function<Function<Object, Boolean>,
					Function<Object,
									Function<List<Object>,
													Object>>>
	some_or_default() {
		return pred -> default_value -> coll -> {
			var result = default_value;
			for (Object item : coll) {
				if (Boolean.TRUE.equals(pred.apply(item))) {
					result = item;
					break;
				}
			}
			return result;
		};
	}
}
