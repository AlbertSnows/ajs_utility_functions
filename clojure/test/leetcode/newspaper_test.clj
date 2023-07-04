(ns leetcode.newspaper-test
	(:require [clojure.test :refer :all]
						[leetcode.newspaper :refer :all]
						[main.helpers :refer :all]))

(def default-test-case
	["The quick" "brown foxjumps over" "thelazy dog yo!"])

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
					zipped-data (zip-vectors
												{:key :params :values test-cases}
												{:key :expected :values expected})
					results-with-expected (reduce (run-test-cases make-newspaper) [] zipped-data)
					check-results-with-expected #(and %1 (= (:expected %2) (:result %2)))
					all-tests-passed (reduce check-results-with-expected true results-with-expected)
					passed (is (true? all-tests-passed))]
			passed)))

(deftest can-make-newspaper?-test
	(testing "if I can make a newspaper"
		(let [width 12
					vec-of-vec-of-words [(flatten-phrases ["aaa b" "c" "d d d d d d d d d"])
															 (flatten-phrases ["d d d d d d d d d d d d d d d"])]

					can-make-all-newspapers?
					(reduce #(and %1 (can-make-newspaper? %2 width)) true vec-of-vec-of-words)
					passed (is (true? can-make-all-newspapers?))]
			passed)))

(deftest flatten-phrases-test
	(testing "if I can flatten phrases correctly"
		(let [test-cases [(flatten-phrases ["aaa b" "c" "d d d d d d d d d"])
											(flatten-phrases ["d d d d d d d d d d d d d d d"])]
					expected [["aaa" "b" "c" "d" "d" "d" "d" "d" "d" "d" "d" "d"]
										["d" "d" "d" "d" "d" "d" "d" "d" "d" "d" "d" "d" "d" "d" "d"]]
					cases-match-expected
					(reduce #(and %1 (= (test-cases %2) (expected %2))) true (range 0 (count test-cases)))
					passed (is (true? cases-match-expected))]
			passed)))

(deftest try-making-a-newspaper-test
	(testing "an attempt to make a newspaper"
		(let [vec-of-phrases [["aaaaaaaaaaaaaaaaaaaaa b"]
													["uwu" "wuw"]]
					expected [{:error "Cannot fit largest word into newspaper. Give smaller words or larger width."}
										["********" "*uwu wuw*" "********"]]
					width 8
					try-results (reduce #(conj %1 (try-making-a-newspaper %2 width)) [] vec-of-phrases)
					cases-match-expected
					(reduce
						#(and %1 (= (try-results %2) (expected %2)))
						true
						(range 0 (count vec-of-phrases)))
					passed (is (true? cases-match-expected))]
			passed)))