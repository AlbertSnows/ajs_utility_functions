package org.leetcode;
// import java string?

import org.functions.data_structures.ArrayDequeHelpers;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.function.BiFunction;

public class remove_stars_from_string {
	private remove_stars_from_string() {
		throw new IllegalStateException("Not an instantiable class.");
	}
//
//	You are given a string s, which contains stars *.
//
//	In one operation, you can:
//
//	Choose a star in s.
//	Remove the closest non-star character to its left, as well as remove the star itself.
//
//	Return the string after all stars have been removed.
//
//	Note:
//
//	The input will be generated such that the operation is always possible.
//	It can be shown that the resulting string will always be unique.
//
//
//
//	Example 1:
//
//	Input: s = "leet**cod*e"
//	Output: "lecoe"
//	Explanation: Performing the removals from left to right:
//	- The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
//	- The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
//	- The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
//	There are no more stars, so we return "lecoe".
//
//	Example 2:
//
//	Input: s = "erase*****"
//	Output: ""
//	Explanation: The entire string is removed, so we return an empty string.


//(ns leetcode.remove-stars-from-a-string
//(:require [clojure.string :refer [join]]))
//
//(defn check-for-star [new-phrase char]
//(let [char-is-star (= char \*)
//updated-phrase (if char-is-star (pop new-phrase) (conj new-phrase char))
//answer updated-phrase]
//answer))

	public static ArrayDeque<Character> check_for_star(ArrayDeque<Character> new_phrase, Character current_char) {
		final var is_char_a_star = current_char == '*';
		final var updated_phrase = is_char_a_star
						? ArrayDequeHelpers.popFromDeque(new_phrase)
						: ArrayDequeHelpers.pushToDeque(new_phrase, current_char);
		return updated_phrase;
	}

	public static <V, C extends Collection<V>, A> A reduce(BiFunction<A, V, A> reducer, A accumulator, C collection) {
		A result = accumulator;
		for (V value : collection) {
			result = reducer.apply(result, value);
		}
		return result;
	}

	public static String remove_stars(String phrase) {
		final var phrase_as_char_array = phrase.chars().mapToObj(ch -> (char) ch).toList();
		final BiFunction<ArrayDeque<Character>, Character, ArrayDeque<Character>> check_for_star = remove_stars_from_string::check_for_star;
		final var answer_as_char_queue = reduce(check_for_star, new ArrayDeque<>(), phrase_as_char_array);
		final var answer = reduce(StringBuilder::append, new StringBuilder(), answer_as_char_queue);
		return answer.reverse().toString();
	}
}

