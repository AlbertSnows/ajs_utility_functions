(ns leetcode.newspaper
	(:require [clojure.test :refer :all]
						[leetcode.newspaper :refer :all]))

(def default-test-case ["The quick" "brown foxjumps over" "thelazy dog yo!"])

(deftest zip-word-data-test
	(testing "Testing that zipping data correctly works given the expected data."
		(let [vec-of-words (flatten-phrases default-test-case)
					vec-of-word-counts (into [] (map count vec-of-words))
					try-zipping (zip-word-data vec-of-words vec-of-word-counts)
					result (try-zipping [] 3)
					passed (is (= result [{:word "foxjumps" :word-count 8}]))]
			passed)))

(deftest handle-exceeded-width-test
	(testing "Testing that the state updates correctly when we've exceeded the width"
		(let [expected {:current-sum 3, :current-phrase ["foo"], :reordered-words [["a" "b"] ["c" "d"]]}
					params [[["a" "b"]] ["c" "d"] 3 "foo"]
					result (apply handle-exceeded-width params)
					passed (is (= expected result))]
			passed)))

(deftest handle-below-or-at-width-test
	(testing "Testing that the state updates correctly when we're below the width"
		(let [expected {:current-sum 7, :current-phrase ["aaa" "eee" "boo"]}
					params [["aaa" "eee"] "boo" 4 3]
					result (apply handle-below-or-at-width params)
					passed (is (= expected result))]
			passed)))

(deftest reorder-words-test
	(testing "Testing that words are reordered correctly."
		(let [params-list
					[{:params
						[{:current-sum     5
							:current-phrase  ["foo"]
							:reordered-words [["a" "b"] ["c"]]}
						 {:word       "bar"
							:word-count 3}]
						:expected {:current-sum     3,
											 :current-phrase  ["bar"],
											 :reordered-words [["a" "b"] ["c"] ["foo"]]}}
					 {:params
						[{:current-sum     1
							:current-phrase  ["foo"]
							:reordered-words [["a" "b"] ["c"]]}
						 {:word       "bar"
							:word-count 3}]
						:expected {:current-sum     4,
											 :current-phrase  ["foo" "bar"],
											 :reordered-words [["a" "b"] ["c"]]}}]
					try-reordering (reorder-words 7)
					add-test-result #(conj %1 {:expected (:expected %2)
																		 :result   (apply try-reordering (:params %2))})
					result (reduce add-test-result [] params-list)
					passed (is (nil? (some #(not (= (:expected %) (:result %))) result)))]
			passed)))

(deftest add-phrase-count-test
	(testing "Testing that appending the phrase count works fine"
		(let [expected [{:phrase "ababa boo", :count 9} {:phrase "foo", :count 3}]
					params [[{:phrase "ababa boo" :count 9}] "foo"]
					result (apply add-phrase-count params)
					passed (is (= result expected))]
			passed)))

(deftest add-asterisks-and-spaces-test
	(testing "Testing adding spaces and asterisks."
		(let [params [["*final phrases*"] {:phrase "foo bar" :count 7}]
					try-adding-wrappers (add-asterisks-and-spaces 13)
					expected ["*final phrases*" "*   foo bar   *"]
					result (apply try-adding-wrappers params)
					passed (is (= result expected))]
			passed)))

(defn zip-vectors [left right keys]
	(let [indexes (range 0 (count left))
				combine-vectors
				(fn [combined index]
					(let [left-value (left index)
								right-value (right index)
								left-key (keys index)
								right
								result {()}
								]
						result))
				result (reduce combine-vectors [] indexes)
				]
		result))

(deftest make-newspaper-test
	(testing "Trying to make a newspaper."
		(let [vec-of-words (flatten-phrases default-test-case)
					expected [["************"
										 "*The quick *"
										 "*  brown   *"
										 "* foxjumps *"
										 "*   over   *"
										 "*thelazy dog*"
										 "*   yo!    *"
										 "************"]
										["********" "* The  *" "*quick *" "*brown *" "*foxjumps*" "* over *" "*thelazy*" "*dog yo!*" "********"]
										["***" "*a*" "*b c def*" "*g*" "*h*" "***"]
										["*************" "* aoeusnot  *" "*************"]]
					test-cases [[vec-of-words 12]
											[vec-of-words 8]
											[["a" "b c def" "g" "h"] 3]
											[["aoeusnot"] 13]]
					run-test-cases (fn [results case] (conj results (apply make-newspaper case)))
					result (reduce run-test-cases [] test-cases)
					passed
					result
					;(is (= result expected))
					]
			passed)))

(deftest newspaper-test
	(testing "Test that newspaper at the top level works"
		(let [test-cases [[" The quick  " "  brown foxjumps over " "  thelazy dog yo!"]
											""]
					passed (is (true? (reduce #(and %1 %2) true test-cases)))]
			passed)))