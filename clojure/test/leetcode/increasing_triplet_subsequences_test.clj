(ns leetcode.increasing-triplet-subsequences-test
	(:require
	 [clojure.test :refer :all]
	 [leetcode.increasing-triplet-subsequences :refer :all]
	 [main.helpers :refer :all]))

(deftest increasing-triplet-subsequences-test
	(testing "that increasing-triplet-subsequences recuder works  fine"
		(let [test-cases [{:params   [{:number-of-incrimental-indices 0 :n-1 0} 1]
											 :expected {:number-of-incrimental-indices 1 :n-1 1}}
											{:params   [{:number-of-incrimental-indices 1 :n-1 1} 0]
											 :expected {:number-of-incrimental-indices 1 :n-1 0}}
											{:params   [{:number-of-incrimental-indices 1 :n-1 3} 2]
											 :expected {:number-of-incrimental-indices 1 :n-1 2}}
											{:params   [{:number-of-incrimental-indices 2 :n-1 4} 5]
											 :expected #(reduced? %)}
											{:params   [{:number-of-incrimental-indices 2 :n-1 4} 4]
											 :expected {:number-of-incrimental-indices 1 :n-1 4}}]
					result (reduce (run-test-cases add-incrimental-indices) [] test-cases)
					all-equal (reduce all-tests-passed? true result)
					passed (is (true? all-equal))]
			passed)))

(comment
	{:expected {:number-of-incrimental-indices 1, :n-1 1},
	 :result   {:number-of-incrimental-indices 1, :n-1 1}})