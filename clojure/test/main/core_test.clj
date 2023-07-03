(ns main.core-test
	(:require
	 [clojure.test :refer :all]
	 [main.core :refer :all]
	 [main.helpers :refer :all]))

(deftest combine-vectors-test
	(testing "Ensure that vectors are combined correctly"
		(let [left-values [["a"] ["b" "c"]]
					right-values ["foo" "bar"]
					left-keyword :fiddle
					right-keyword :dee
					try-combining (combine-vectors left-values right-values left-keyword right-keyword)
					result (try-combining
									 [{:fiddle "da" :dee "do"}]
									 1)
					expected [{:fiddle "da", :dee "do"} {:fiddle ["b" "c"], :dee "bar"}]
					passed (is (true? (= result expected)))]
			passed)))

(deftest zip-vectors-test
	(testing "zipping vectors"
		(let [left {:key :foob :values [["aaa" "eee"]
																		["bbbb"]]}
					right {:key :boof :values [["reeeee"]
																		 ["eeeeep"]]}

					result (zip-vectors left right)
					expected [{:foob ["aaa" "eee"], :boof ["reeeee"]}
										{:foob ["bbbb"], :boof ["eeeeep"]}]
					passed (is (= result expected))]
			passed)))

(deftest run-test-cases-test
	(testing "Can run test cases correctly"
		(let [test-function (fn [first second] (str first second))
					try-running-test-cases (run-test-cases test-function)
					result (try-running-test-cases
									 [{:expected ["cat" "dog"]
										 :result   ["cat" "dog"]}]
									 {:params   ["foo" "bar"]
										:expected "foobar"})
					expected [{:expected ["cat" "dog"], :result ["cat" "dog"]}
										{:expected "foobar", :result "foobar"}]
					passed (is (= result expected))]
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