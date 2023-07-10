(ns leetcode.adjacent-sums)

(defn add-possible-sums-to-current-number [current-number numbers-to-sum]
	(fn [sums amount-to-take]
		(let [new-sum (reduce + current-number (take amount-to-take numbers-to-sum))
					sums-with-new-number-added (conj sums new-sum)]
			sums-with-new-number-added)))

(defn sum-right-elements [list]
	(fn [sums index]
		(let [current-number (list index)
					data-to-calculate-sums (subvec list (+ 1 index))
					new-sums (reduce (add-possible-sums-to-current-number current-number data-to-calculate-sums) [] (range 0 (count data-to-calculate-sums)))
					sums-with-sums-from-current-number-added (concat sums new-sums)]
			sums-with-sums-from-current-number-added)))

(defn calc-sums [list]
	(let [indexes (range (count list))
				total-sums-for-list (reduce (sum-right-elements list) [] indexes)]
		total-sums-for-list))

(defn calc-final-sums [existing-sums]
	(fn [number]
		(let [final-sums-for-number (map #(+ % number) existing-sums)]
			final-sums-for-number)))

; Suppose you have three pieces of data. array A, array B, and total T.
; A and B are both arrays of integers in some arbitrary form [ 3 24 542 65 ...]
; T is just some number, like 69.
; Challenge: Find all possible sums between A and B that sum to T under the following rules:
; - At least one number must be used from A and B
; - If you want to use more than one number in an array, it must be adjacent to another number you're using. e.g. [ 1 2 3 ] valid combinations: 1 2 3, 1 2, 2 3, 1 2 3 | invalid: 1 3
; - If you use multiple numbers from A, you cannot use multiple numbers from B, and vice versa. e.g. A:[1 2 3], B:[4 5] invalid: A:1,2 + B:4,5
(defn find-sums [first-list second-list total]
	(let [first-sums (calc-sums first-list)
				second-sums (calc-sums second-list)
				final-first-sums (map (calc-final-sums first-sums) second-list)
				final-second-sums (map (calc-final-sums second-sums) first-list) ; vec of vecs
				all-possible-valid-sums (flatten [final-first-sums final-second-sums])
				answer (count (filter #(= % total) all-possible-valid-sums))]
		answer))

(comment
	(find-sums
		[1 2 3 4]
		[4 5 6]
		9)
	)