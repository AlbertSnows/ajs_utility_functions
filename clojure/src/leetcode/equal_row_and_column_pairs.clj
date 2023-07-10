(ns leetcode.equal-row-and-column-pairs
	(:require [clojure.core.matrix :refer [transpose]]))

(defn -main [& args]
	"hello world")

(defn count-matching-vectors [matrix-to-compare]
	(fn [total-identical vector]
		(let [inc-if-equal #(if (= %2 vector) (inc %1) %1)
					identical-found (reduce inc-if-equal 0 matrix-to-compare)
					updated-total (+ total-identical identical-found)]
			updated-total)))

(defn count-equal-grid-pairs [vec-of-rows]
	(let [vec-of-columns (transpose vec-of-rows)
				matching (reduce (count-matching-vectors vec-of-columns) 0 vec-of-rows)
				result matching]
		result))

(comment
 (count-equal-grid-pairs [[3 2 1] [1 7 6] [2 7 7]])
 (count-equal-grid-pairs [[3, 1, 2, 2], [1, 4, 4, 5], [2, 4, 2, 2], [2, 4, 2, 2]])
 )
;Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
;
;A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
;
;
;
;Example 1:
;
;Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
;Output: 1
;Explanation: There is 1 equal row and column pair:
;- (Row 2, Column 1): [2,7,7]
;
;Example 2:
;
;Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
;Output: 3
;Explanation: There are 3 equal row and column pairs:
;- (Row 0, Column 0): [3,1,2,2]
;- (Row 2, Column 2): [2,4,2,2]
;- (Row 3, Column 2): [2,4,2,2]
