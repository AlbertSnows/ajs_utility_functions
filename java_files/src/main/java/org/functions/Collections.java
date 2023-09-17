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

	/**
	 * @deprecated can this just be replaced with stream flatmap? investigate
	 */
	@Deprecated(since = "*shrugs*, still need to check if this is necessary first")
	public static <V> Stream<V> combine(@NotNull List<List<V>> collectionOfCollections) {
		return collectionOfCollections.stream()
						.flatMap(List::stream);
	}

	/**
	 * This is an eager implementation of firstTrueStateOf.
	 * firstTrueStateOf is just meant to provide access to a declarative alternative
	 * to if-else-if styles of coding
	 * It acts as a very rudimentary implementation of pattern matching.
	 * For an example of the spirit of what this function is trying to capture,
	 * look at an example in languages like scala:
	 * <a href="https://docs.scala-lang.org/tour/pattern-matching.html">PM in Scala</a>
	 *
	 * @param stateMap is a list of State (P)airs (tuples). For more information on state pairs
	 *                 refer to the documentation folder.
	 * @return will be the (V)alue with an associated state
	 */
	public static <V> @NotNull V firstTrueEagerStateOf(@NotNull List<Pair<Boolean, V>> stateMap) {
		for (Pair<Boolean, V> statePair : stateMap) {
			var value = statePair.getFirst();
			if (Boolean.TRUE.equals(value)) {
				return statePair.getSecond();
			}
		}
		throw new IllegalArgumentException("You must provide a default case as the last argument");
	}

	/**
	 * This is a lazy implementation of firstTrueStateOf (and the default)
	 * refer to eager above for more information about the motivation of this function
	 *
	 * @param stateMap is a list of Pairs where
	 *                 L is a *0 arity* (i.e. lazy) function that returns a bool
	 *                 R is a lazy function that returns a value identical to all other values in a list
	 * @return represents a lazy evaluation of what you want in state L, encompassed by R
	 * The goal of laziness is to represent values without actually doing the expensive
	 * calculations necessary to get them, which saves on computation.
	 * For more examples of laziness, refer to Vavr's documentation; there's also plenty
	 * of other resources online
	 * <a href="https://javadoc.io/doc/io.vavr/vavr/0.9.2/io/vavr/Lazy.html">Laziness</a>
	 */
	public static <V> @NotNull Lazy<V> firstTrueStateOf(@NotNull List<Pair<Lazy<Boolean>, Lazy<V>>> stateMap) {
		for (Pair<Lazy<Boolean>, Lazy<V>> statePair : stateMap) {
			var value = statePair.getFirst().get();
			if (Boolean.TRUE.equals(value)) {
				return statePair.getSecond();
			}
		}
		throw new IllegalArgumentException("You must provide a default case as the last argument");
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
