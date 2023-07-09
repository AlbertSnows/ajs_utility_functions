(ns leetcode.longest-one-deletion-subarray-test
	(:require [clojure.test :refer :all]
						[leetcode.longest-one-deletion-subarray :refer :all]
						[main.helpers :refer :all]))

(deftest compress-string-test
	(testing "strings are compressing correctly"
		(let [test-cases [{:params [{:max-length 0 :before-delete-length 0 :after-delete-length 0} 0]
											 :expected {:before-delete-length 0, :after-delete-length 0, :max-length 0}}
											{:params [{:max-length 0 :before-delete-length 0 :after-delete-length 0} 1]
											 :expected {:before-delete-length 0, :after-delete-length 1, :max-length 0}}
											{:params [{:max-length 0 :before-delete-length 0 :after-delete-length 1} 4]
											 :expected {:before-delete-length 1, :after-delete-length 0, :max-length 1}}
											{:params [{:max-length 0 :before-delete-length 2 :after-delete-length 1} 4]
											 :expected {:before-delete-length 1, :after-delete-length 0, :max-length 3}}]
					passed (all-tests-passed? (calc-next-longest-one-deletion-subarray [0 1 1 1 0 1 1 0 1]) test-cases)]
			passed)))

(comment
 ((calc-next-longest-one-deletion-subarray [0 1 1 1 0 1 1 0 1])
	{:max-length 0 :before-delete-length 2 :after-delete-length 1}
	4)
 )