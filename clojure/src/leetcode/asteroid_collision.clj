(ns leetcode.asteroid-collision)

(defn collide-asteroids [initial-asteroids initial-current-asteroid]
	(loop [asteroids initial-asteroids
				 current-asteroid initial-current-asteroid]
		(let [previous-asteroid (if (empty? asteroids) 0 (peek asteroids))
					same-sign? (or (and (pos? current-asteroid) (pos? previous-asteroid))
												 (and (neg? current-asteroid) (neg? previous-asteroid)))
					previous-asteroid-is-bigger? (< (abs current-asteroid) (abs previous-asteroid))
					same-size? (= (abs current-asteroid) (abs previous-asteroid))]
			(cond
				(= asteroids []) (conj asteroids current-asteroid)
				same-sign? (conj asteroids current-asteroid)
				same-size? (pop asteroids)
				previous-asteroid-is-bigger? asteroids
				:else (recur (pop asteroids) current-asteroid)))))

(defn calc-asteroid-collisions [asteroids]
	(let [remaining-asteroids (reduce #(collide-asteroids %1 %2) [] asteroids)
				answer remaining-asteroids]
		answer))

(comment
 (calc-asteroid-collisions [5 10 -5])
 (calc-asteroid-collisions [8 -8])
 (calc-asteroid-collisions [10, 2, -5])
 )

;We are given an array asteroids of integers representing asteroids in a row.
;
;For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
;
;Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
;
;
;
;Example 1:
;
;Input: asteroids = [5,10,-5]
;Output: [5,10]
;Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
;
;Example 2:
;
;Input: asteroids = [8,-8]
;Output: []
;Explanation: The 8 and -8 collide exploding each other.
;
;Example 3:
;
;Input: asteroids = [10,2,-5]
;Output: [10]
;Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
