(ns test.core
  (:require [clojure.test :refer :all]
            [functions.core :refer :all]
            [test.helpers :refer :all]))

(deftest
  fizzbuzz-test
  (let [test-data [{:n 3 :result ["1", "2", "Fizz"]}
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