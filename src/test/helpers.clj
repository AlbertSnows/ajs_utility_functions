(ns test.helpers
  (:require [functions.core :refer :all]))

(defn setup-check-fizzbuzz [div-by-3 div-by-5]
  (fn [accumulator number]
    (let [is-div-by-3 (div-by-3 number)
          is-div-by-5 (div-by-5 number)
          fizzbuzz-states [{:context (and is-div-by-3 is-div-by-5) :value "FizzBuzz"}
                           {:context is-div-by-5 :value "Buzz"}
                           {:context is-div-by-3 :value "Fizz"}
                           {:context true :value number}]
          _ (println fizzbuzz-states)
          answer (find-first-true fizzbuzz-states)]
      (conj accumulator answer))))

(def default-fizzbuzz
  (let [div-by-x (fn [divisor] (fn [fizzbuzz-number] (= 0 (mod fizzbuzz-number divisor))))
        div-by-3 (div-by-x 3)
        div-by-5 (div-by-x 5)]
    (setup-check-fizzbuzz div-by-3 div-by-5)))

(defn fizzbuzz [n] (reduce default-fizzbuzz [] (range 1 (+ n 1))))
