(ns leetcode.max-k-sum-pairs-test
	(:require
	 [clojure.test :refer :all]
	 [leetcode.max-k-sum-pairs :refer :all]
	 [main.helpers :refer :all]))

(deftest check-for-pairs-test
	(testing "that it correctly identifies pairs"
		(let [test-cases [{:params   [{:singles #{} :pairs-found 0} 7]
											 :expected {:singles #{7} :pairs-found 0}}
											{:params   [{:singles #{5} :pairs-found 0} 7]
											 :expected {:singles #{} :pairs-found 1}}
											{:params   [{:singles #{4 5 6} :pairs-found 3} 7]
											 :expected {:singles #{4 6} :pairs-found 4}}
											{:params   [{:singles #{4 6} :pairs-found 3} 7]
											 :expected {:singles #{4 6 7} :pairs-found 3}}
											{:params   [{:singles #{0 4 5 6} :pairs-found 3} 12]
											 :expected {:singles #{4 5 6} :pairs-found 4}}]
					passed (is (true? (all-tests-passed? (check-for-pairs 12) test-cases)))]
			passed)))

(comment
	((check-for-pairs 12) {:singles #{0 4 5 6} :pairs-found 3} 12)
	)