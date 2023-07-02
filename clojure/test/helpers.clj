(ns helpers
  (:require [functions.core :refer :all]))

(defn test-adjacent-sum
	[passed-so-far {:keys [case answer]}]
	(let [possible-answer (apply find-sums case)
				passed-test (= possible-answer answer)]
		(if passed-test
			true
			(reduced false))))

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
