(ns leetcode.max-consecutive-ones-test
	(:require [clojure.test :refer :all]
						[leetcode.max-consecutive-ones :refer :all]
						[main.helpers :refer :all]))

(deftest max-consecutive-ones-test
	(testing "that it calculates the next consecutive one correctly"
		(let [test-cases [{:params [{:available-swaps 3 :follower 0} 1]
											 :expected {:available-swaps 3 :follower 0}}
											{:params [{:available-swaps 3 :follower 4} 1]
											 :expected {:available-swaps 3 :follower 4}}
											{:params [{:available-swaps 3 :follower 4} 0]
											 :expected {:available-swaps 2 :follower 4}}
											{:params [{:available-swaps 0 :follower 3} 1]
											 :expected {:available-swaps 0 :follower 3}}
											{:params [{:available-swaps 0 :follower 4} 0]
											 :expected {:available-swaps -1 :follower 4}}
											{:params [{:available-swaps -1 :follower 4} 0]
											 :expected {:available-swaps -1 :follower 5}}]
					passed (all-tests-passed?
									(calc-current-consecutive [1 1 1 1 0 0 1 0 1 0 1 1 1 0])
									test-cases)]
			passed)))

(comment
 ((calc-current-consecutive [1 1 1 1 0 0 1 0 1 0 1 1 1 0])
	{:available-swaps -1 :follower 4}
	0)
 )