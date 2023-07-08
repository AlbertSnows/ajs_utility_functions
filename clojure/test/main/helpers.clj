(ns main.helpers
	(:require [clojure.test :refer [function?]]
						[main.core :refer :all]))

(defn all-tests-passed? [_ {:keys [expected result] :as result-case}]
	(let [passed (if (function? expected) (expected result) (= expected result))
				result (if passed true (reduced result-case))]
		result))

(defn combine-vectors [left-values right-values left-keyword right-keyword]
	(fn [combined index]
		(let [left-value (left-values index)
					right-value (right-values index)
					new-map {left-keyword  left-value
									 right-keyword right-value}
					result (conj combined new-map)]
			result)))

(defn zip-vectors [left right]
	(let [left-keyword (:key left)
				left-values (:values left)
				right-keyword (:key right)
				right-values (:values right)
				indexes (range 0 (count left-values))
				result (reduce
								 (combine-vectors left-values right-values left-keyword right-keyword)
								 []
								 indexes)]
		result))

(defn run-test-cases [test-function]
	(fn [results {:keys [params expected]}]
		(let [result (apply test-function params)
					updated-map {:expected expected
											 :result   result}
					results-with-new-case (conj results updated-map)]
			results-with-new-case)))

(defn setup-check-fizzbuzz [div-by-3 div-by-5]
	(fn [fizzbuzz-answers number]
		(let [is-div-by-3? (div-by-3 number)
					is-div-by-5? (div-by-5 number)
					fizzbuzz-states [{:context (and is-div-by-3? is-div-by-5?) :value "FizzBuzz"}
													 {:context is-div-by-5? :value "Buzz"}
													 {:context is-div-by-3? :value "Fizz"}
													 {:context true :value (str number)}]
					answer-for-number (find-first-true fizzbuzz-states)]
			(conj fizzbuzz-answers answer-for-number))))

(def default-fizzbuzz
	(let [div-by-x (fn [divisor] (fn [fizzbuzz-number] (= 0 (mod fizzbuzz-number divisor))))
				div-by-3 (div-by-x 3)
				div-by-5 (div-by-x 5)
				check-fizzbuzz (setup-check-fizzbuzz div-by-3 div-by-5)]
		check-fizzbuzz))

(defn fizzbuzz [n]
	(let [answer (reduce default-fizzbuzz [] (range 1 (+ n 1)))] answer))
