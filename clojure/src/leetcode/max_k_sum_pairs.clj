(ns leetcode.max-k-sum-pairs)

(defn -main [& args]
	"hello world")

(defn check-for-pairs [k]
	(fn [{:keys [singles pairs-found]} number]
		(let [remainder (- k number)
					has-partner (contains? singles remainder)
					updated-state (if has-partner
													{:singles (disj singles remainder)
													 :pairs-found (inc pairs-found)}
													{:singles (conj singles number)
													 :pairs-found pairs-found})]
			updated-state)))

(defn find-max-k-sum-pairs [nums k]
	(let [state {:singles #{}
							 :pairs-found 0}
				result (reduce (check-for-pairs k) state nums)
				pairs-found (:pairs-found result)]
		pairs-found))

(comment
 ((check-for-pairs 12) [3 1 3 4 3] 7)

 )

; [ 0 6 1 5 2 4 3 3 ] 6
; [ a b c d ]
; is this possible? I don't think so due to identity
; [ a b ] => can't remove a b
; [ a d ] => [] can remove [ b c ] then [ a d ]

;You are given an integer array nums and an integer k.
;
;In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
;
;Return the maximum number of operations you can perform on the array.
;
;
;
;Example 1:
;
;Input: nums = [1,2,3,4], k = 5
;Output: 2
;Explanation: Starting with nums = [1,2,3,4]:
;- Remove numbers 1 and 4, then nums = [2,3]
;- Remove numbers 2 and 3, then nums = []
;There are no more pairs that sum up to 5, hence a total of 2 operations.
;
;Example 2:
;
;Input: nums = [3,1,3,4,3], k = 6
;Output: 1
;Explanation: Starting with nums = [3,1,3,4,3]:
;- Remove the first two 3's, then nums = [1,4,3]
;There are no more pairs that sum up to 6, hence a total of 1 operation.
;
;
;
;Constraints:
;
;1 <= nums.length <= 105
;1 <= nums[i] <= 109
;1 <= k <= 109
