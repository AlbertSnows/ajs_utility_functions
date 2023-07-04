(ns leetcode.reverse-vowels-test
	(:require
	 [clojure.test :refer :all]
	 [leetcode.reverse-vowels :refer :all]))

(deftest add-if-vowel-test
	(testing "if I add a vowel correctly"
		(let [char-vec [\w \o \a \d]
					test-cases [[[{}] 0]
											[[{:vowel \o :index 1}] 2]
											[[{:vowel \a :index 2}] 3]]
					expected [[{}]
										[{:vowel \o, :index 1} {:vowel \a, :index 2}]
										[{:vowel \a, :index 2}]]
					try-adding-vowel (add-if-vowel char-vec)
					result (reduce
									 #(conj %1 (apply try-adding-vowel %2))
									 []
									 test-cases)
					all-tests-passed (reduce
														 #(and %1 (= (result %2) (expected %2)))
														 true
														 (range 0 (count test-cases)))
					passed (is (true? all-tests-passed))]
			passed)))

(deftest swap-pair-indexes-test
	(testing "if we can swap pair indexes correctly"
		(let [result (swap-pair-indexes [{:l-index 9 :r-index 6}] {:l-index 0 :r-index 10})
					expected [{:l-index 9, :r-index 6} {:l-index 10, :r-index 0}]
					passed (is (true? (= result expected)))]
			passed)))

(deftest reverse-vowels-test
	(testing "if we can reverse vowels correctly"
		(let [result (reduce #(conj %1 (reverse-vowels %2)) [] ["wooden" "tool" "raid" "roaad"])
					expected ["weodon" "tool" "riad" "raaod"]
					passed (is (true? (= result expected)))]
			passed)))