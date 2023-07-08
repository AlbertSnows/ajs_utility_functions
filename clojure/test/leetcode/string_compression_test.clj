(ns leetcode.string-compression-test
  (:require [clojure.test :refer :all]
            [leetcode.string-compression :refer :all]
            [main.helpers :refer :all]))

(deftest compress-string-test
	(testing "strings are compressing correctly"
		(let [test-cases [{:params   [["a" "a" "b" "b" "c" "c" "c"]]
											 :expected {:a 2 :b 2 :c 3}}
											{:params   [["a"]]
											 :expected {:a 1}}
											{:params   [["a" "b" "b" "b" "b" "b" "b" "b" "b" "b" "b" "b" "b"]]
											 :expected {:a 1 :b 12}}]
					passed (all-tests-passed? compress-string test-cases)]
			passed)))
