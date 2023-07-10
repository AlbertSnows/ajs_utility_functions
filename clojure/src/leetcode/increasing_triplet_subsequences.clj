(ns leetcode.increasing-triplet-subsequences)
;Given an integer array nums, return true if there exists a triple of indices (i, j, k)
; such that i < j < k and nums[i] < nums[j] < nums[k].
; If no such indices exists, return false.
;
;Input: nums = [1,2,3,4,5]
;Output: true
;Explanation: Any triplet where i < j < k is valid.
;
;Input: nums = [5,4,3,2,1]
;Output: false
;Explanation: No triplet exists.
;
;Input: nums = [2,1,5,0,4,6]
;Output: true
;Explanation: The triplet (3, 4, 5) is valid because
; nums[3] == 0 < nums[4] == 4 < nums[5] == 6.

(defn add-incrimental-indices [{:keys [number-of-incrimental-indices n-1]} number]
	(let [starting-sequence? (= number-of-incrimental-indices 0)
				middle-success-sequence? (and
																	 (= number-of-incrimental-indices 1)
																	 (> number n-1))
				middle-fail-sequence? (and
																(= number-of-incrimental-indices 1)
																(<= number n-1))
				end-success-sequence? (and
																(= number-of-incrimental-indices 2)
																(> number n-1))
				end-fail-sequence? (and
														 (= number-of-incrimental-indices 2)
														 (<= number n-1))
				success-case {:number-of-incrimental-indices (inc number-of-incrimental-indices)
											:n-1                           number}
				fail-case {:number-of-incrimental-indices 1 :n-1 number}
				updated-state
				(cond
					starting-sequence? success-case
					middle-success-sequence? success-case
					middle-fail-sequence? fail-case
					end-success-sequence? (reduced 3)
					end-fail-sequence? fail-case)]
		updated-state))

(defn has-three-increasing-indices [nums]
	(let [starting-state {:number-of-incrimental-indices 0 :n-1 0}
				number-of-inc-indicies (= 3 (reduce add-incrimental-indices starting-state nums))
				answer number-of-inc-indicies]
		answer))

(comment
	(has-three-increasing-indices [1 2 3 4 5])
	(has-three-increasing-indices [5 4 3 2 1])
	(has-three-increasing-indices [2 1 5 0 4 6])
	)