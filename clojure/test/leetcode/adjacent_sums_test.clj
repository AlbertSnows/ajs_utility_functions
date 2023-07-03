(ns leetcode.adjacent-sums-test
	(:require
	 [clojure.test :refer :all]
	 [leetcode.adjacent-sums :refer :all]))

(defn test-an-adjacent-sum-case
	[_ {:keys [case answer]}]
	(let [possible-answer (apply find-sums case)
				passed-test (= possible-answer answer)]
		(if passed-test
			true
			(reduced false))))

(deftest adjacent-sums-test
	(testing "Test that ensures ajacent sums at a high level works.
						More tests should be/have been written for this btw."
		(let [test-cases [{:case [[1 2 3 4] [4 5 6] 9] :answer 4}
											{:case [[1 1 1 1] [1 1 1] 2] :answer 17}
											{:case [[6 5 4 5 6] [1 2 3 2 1] 12] :answer 9}]
					passed (is (true? (reduce test-an-adjacent-sum-case true test-cases)))]
			passed)))