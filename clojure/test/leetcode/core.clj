(ns leetcode.core
	(:require
	 [clojure.test :refer :all]
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