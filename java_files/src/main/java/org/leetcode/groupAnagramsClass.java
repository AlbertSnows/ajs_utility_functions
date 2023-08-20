package org.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class groupAnagramsClass {

	public static <K> Map<K, Integer> frequencies(K[] coll) {
		Map<K, Integer> frequencies = new HashMap<>();
		for (K element : coll)
			frequencies.put(element, frequencies.getOrDefault(element, 0) + 1);
		return frequencies;
	}

	public static <K> List<List<String>> groupAnagrams(String[] words) {
		// string.toCharArray()
		var chars_of_words = Stream.of(words).map(String::toCharArray).toList();
//		var char_frequencies = chars_of_words.stream().map(chars -> frequencies(chars));
		return new ArrayList<>();
	}

}
