(ns leetcode.newspaper
  (:require [clojure.test :refer :all]
            [leetcode.newspaper :refer :all]))
(deftest newspaper-test
  (testing "Test that newspaper at the top level works"
    (let [test-cases [{:case [[1 2 3 4] [4 5 6] 9] :answer 4}
                      {:case [[1 1 1 1] [1 1 1] 2] :answer 17}
                      {:case [[6 5 4 5 6] [1 2 3 2 1] 12] :answer 9}]
          passed (is (true? (reduce #(and %1 %2) true test-cases)))]
      passed)))