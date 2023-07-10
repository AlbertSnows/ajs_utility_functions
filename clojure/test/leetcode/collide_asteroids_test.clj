(ns leetcode.collide-asteroids-test
  (:require [clojure.test :refer :all]
            [leetcode.asteroid-collision :refer :all]
            [main.helpers :refer :all]))

(deftest collide-asteroids-test
	(testing "that asteroids collide correctly"
		(let [test-cases [{:params [[5 10] -5]
											 :expected [5 10]}
											{:params [[-5] -5]
											 :expected [-5 -5]}
											{:params [[5 10] -15]
											 :expected [-15]}
											{:params [[5 4 3 2 1] -5]
											 :expected []}]
					passed (all-tests-passed? collide-asteroids test-cases)]
			passed)))

(comment
 (collide-asteroids [5 10] -5)
 (collide-asteroids [-5] -5)
 (collide-asteroids [5 10] -15)
 (collide-asteroids [5 4 3 2 1] -5)
 )