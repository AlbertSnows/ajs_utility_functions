package org.leetcode;

import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class remove_stars_from_string_test {

	@org.junit.jupiter.api.Test
	void example_add() {
		assertEquals(7, Integer.sum(2, 5));
	}


	@org.junit.jupiter.api.Test
	void check_for_star() {
		final var current_char = 'b';
		final var new_phrase = new ArrayDeque<Character>();
		final var result = remove_stars_from_string.check_for_star(new_phrase, current_char);
		assertEquals(new ArrayDeque<>(List.of(current_char)), result);
	}

	@org.junit.jupiter.api.Test
	void remove_stars() {
	}
}