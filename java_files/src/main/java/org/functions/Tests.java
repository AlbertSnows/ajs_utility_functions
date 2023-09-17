package org.functions;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class tests {

	private tests() {
		throw new IllegalStateException("utility class");
	}

	public static Function<Object, Function<Object, Map<String, Object>>>
	make_test_case() {
		return expected -> test_case -> Map.of("expected", expected, "case", test_case);
	}

	public static <I, C, O>
	Function<
					Function<I, Function<C, O>>,
					Function<Supplier<I>,
									Function<List<Map<String, C>>, Object>>>
	run_test_cases() {
		return function_to_test -> initial_data -> test_cases -> {
			Object test_result = true;
			for (Map<String, C> test_case : test_cases) {
				var test_against_initial_data = function_to_test.apply(initial_data.get());
				var expected_result = test_case.get("expected");
				var case_to_test = test_case.get("case");
				var result = test_against_initial_data.apply(case_to_test);
				var test_failed = !result.equals(expected_result);
				if (test_failed) {
					test_result = result;
					break;
				}
			}
			return test_result;
		};
	}
}
