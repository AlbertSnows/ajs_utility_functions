(ns leetcode.max-vowel-substring-test
	(:require [clojure.test :refer :all]
						[leetcode.max-vowel-substring :refer :all]
						[main.helpers :refer :all]))

(deftest compress-string-test
	(testing "strings are compressing correctly"
		(let [test-cases [{:params   [{:current 0 :found 0} \a]
											 :expected {:current 1 :found 1}}
											{:params   [{:current 0 :found 0} \x]
											 :expected {:current 0 :found 0}}
											{:params   [{:current 3 :found 3} \a]
											 :expected {:current 4 :found 4}}
											{:params   [{:current 3 :found 3} \x]
											 :expected #(reduced? %)}]
					passed (all-tests-passed? (check-vowel 3) test-cases)]
			passed)))

(comment
	((check-vowel 3) {:current 0 :found 0} \a)
	((check-vowel 3) {:current 0 :found 0} \b)
	((check-vowel 3) {:current 3 :found 3} \a)
	((check-vowel 3) {:current 3 :found 3} \b)

	)