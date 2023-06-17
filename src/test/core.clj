(ns test.core
  (:require [clojure.test :refer :all]
            [functions.core :refer :all]))

(defn setup-check-fizzbuzz [div-by-3 div-by-5]
  (fn [accumulator number]
    (let [is-div-by-3 (div-by-3 number)
          is-div-by-5 (div-by-5 number)
          answer
          (cond
            (and is-div-by-3 is-div-by-5) "FizzBuzz"
            is-div-by-5 "Buzz"
            is-div-by-3 "Fizz"
            :else (str number))]
      (conj accumulator answer))))

(def default-fizzbuzz
  (let [div-by-x (fn [divisor] (fn [fizzbuzz-number] (= 0 (mod fizzbuzz-number divisor))))
        div-by-3 (div-by-x 3)
        div-by-5 (div-by-x 5)]
    (setup-check-fizzbuzz div-by-3 div-by-5)))

(defn fizzbuzz [n] (reduce default-fizzbuzz [] (range 1 (+ n 1))))

(deftest
  fizzbuzz-test
  (let [test-data [{:n 3 :result ["1","2","Fizz"]}
                   {:n 5 :result ["1","2","Fizz","4","Buzz"]}
                   {:n 15 :result ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]}]
        fizzbuzz-test-passed (fn [set] (= (:result set) (fizzbuzz (:n set))))
        array-of-results (map fizzbuzz-test-passed test-data)
        all-tests-passed (map #(is (true? %)) array-of-results)]
    all-tests-passed))


(comment
  (deftest sanity-success-test
    (is (= 4 (+ 2 2))))

  (deftest sanity-fail-test
    (is (= 6 (+ 3 2)))))