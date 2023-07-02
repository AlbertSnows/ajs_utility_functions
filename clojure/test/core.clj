(ns core
	(:require [clojure.test :refer :all]
	 [functions.core :refer :all]
	 [main.leetcode.adjacent-sums :refer :all]
	 [test.helpers :refer :all]))


(deftest adjacent-sums-test
	(testing "Test that ensures ajacent sums at a high level works.
						More tests should be/have been written for this."
		(let [test-cases [{:case [[1 2 3 4] [4 5 6] 9] :answer 4}
											{:case [[1 1 1 1] [1 1 1] 2] :answer 17}
											{:case [[6 5 4 5 6] [1 2 3 2 1] 12] :answer 9}]
					passed (reduce test-adjacent-sum true test-cases)]
			passed)))

(deftest short-circuit-reduce-test
	(testing "Testing that a short-circuit reduce works correctly."
		(let [test-one (= 15 (short-circuit-reduce + 0 (fn [a _] (< 10 a)) (range 0 10)))
					test-two (= [0 6 12 18] (short-circuit-reduce
																		(fn [a _] (conj a (* 6 (+ (count a)))))
																		[]
																		(fn [_ n] (false? n))
																		[true true true true false true true]))
					tests-passed (is (true? (and test-one test-two)))]
			tests-passed)))

(deftest reduce-int-tests
	"Testing that reduce-int works correctly."
	(let [test-one (= 66 (reduce-int 12 +))
				test-two (= 114 (reduce-int 15 + 9))
				tests-passed (is (true? (and test-one test-two)))]
		tests-passed))

(deftest fizzbuzz-test
	"Tests three different datasets against fizzbuzz"
	(let [test-data [{:n 3 :result ["1", "2", "Fizz"]}
									 {:n 5 :result ["1", "2", "Fizz", "4", "Buzz"]}
									 {:n 15 :result ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"]}]
				fizzbuzz-test-passed (fn [set] (= (:result set) (fizzbuzz (:n set))))
				array-of-results (map fizzbuzz-test-passed test-data)
				all-tests-passed (map #(is (true? %)) array-of-results)]
		all-tests-passed))

(comment
	(deftest sanity-success-test
		"hey look a docstring"
		(is (= 4 (+ 2 2))))

	(deftest sanity-fail-test
		(is (= 6 (+ 3 2)))))