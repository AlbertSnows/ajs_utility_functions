(ns leetcode.max-consecutive-ones)

(defn -main [& args]
	"hello world")

(defn calc-current-consecutive [numbers]
	(fn [{:keys [available-swaps follower]} number]
		(let [is-one? (= 1 number)
					is-zero? (not is-one?)
					out-of-swaps? (< available-swaps 0)
					number-at-follower (nth numbers follower)
					follower-number-is-zero? (= 0 number-at-follower)
					should-decriment-available-swaps
					(and is-zero?
							 (or (not out-of-swaps?) (not follower-number-is-zero?)))
					should-incriment-available-swaps
					(and out-of-swaps? follower-number-is-zero? (not is-zero?))
					updated-available-swaps
					(cond
						should-decriment-available-swaps (dec available-swaps)
						should-incriment-available-swaps (inc available-swaps)
						:else available-swaps)
					updated-follower (if out-of-swaps? (inc follower) follower)
					answer {:follower updated-follower
									:available-swaps updated-available-swaps}]
			answer)))

(defn find-max-consecutive-ones [nums k]
	(let [state {:available-swaps k
							 :follower 0}
				result (reduce (calc-current-consecutive nums) state nums)
				answer (- (count nums) (inc (:follower result)))]
		answer))

(comment
 (find-max-consecutive-ones [1 1 1 0 0 0 1 1 1 1 0] 2)
 (find-max-consecutive-ones [0 0 1 1 0 0 1 1 1 0 1 1 0 0 0 1 1 1 1] 3)
 )

;Max Consecutive Ones III
;Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
;
;Example 1:
;
;Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
;Output: 6
;Explanation: [1,1,1,0,0,1,1,1,1,1,1]
;Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
;
;Example 2:
;
;Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
;Output: 10
;Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
;Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
