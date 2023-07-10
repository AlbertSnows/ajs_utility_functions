(ns leetcode.longest-one-deletion-subarray)

(defn -main [& args]
	"hello world")

(defn calc-next-longest-one-deletion-subarray [nums]
	(fn [{:keys [max-length before-delete-length after-delete-length]} index]
		(let [number (nth nums index)
					is-one? (= number 1)
					updated-max-length (if is-one?
															 max-length
															 (max max-length (+ before-delete-length after-delete-length)))
					updated-before-delete-length
					(if is-one? before-delete-length after-delete-length)
					updated-after-delete-length (if is-one? (inc after-delete-length) 0)
					updated-state {:before-delete-length updated-before-delete-length
												 :after-delete-length updated-after-delete-length
												 :max-length updated-max-length}]
			updated-state)))
(defn find-longest-one-deletion-subarray [nums]
	(let [state {:max-length 0
							 :before-delete-length 0
							 :after-delete-length 0}
				result (reduce (calc-next-longest-one-deletion-subarray nums) state (range 0 (count nums)))
				possible-answer (max (:max-length result) (+ (:before-delete-length result) (:after-delete-length result)))
				before-length (:before-delete-length result)
				answer (if (= before-length 0) (dec possible-answer) possible-answer)]
		answer))

(comment
 (find-longest-one-deletion-subarray [1 1 0 1])
 (find-longest-one-deletion-subarray [0 1 1 1 0 1 1 0 1])
 (find-longest-one-deletion-subarray [1 1 1])
 )

;Longest Subarray of 1's After Deleting One Element

;Given a binary array nums, you should delete one element from it.
;
; Return the size of the longest non-empty subarray containing only 1's in the resulting array.
; Return 0 if there is no such subarray.
;
;Example 1:
;
;Input: nums = [1,1,0,1]
;Output: 3
;Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
;
;Example 2:
;
;Input: nums = [0,1,1,1,0,1,1,0,1]
;Output: 5
;Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
;
;Example 3:
;
;Input: nums = [1,1,1]
;Output: 2
;Explanation: You must delete one element.
