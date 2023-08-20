(ns valid-sudoku
  (:require [clojure.core.matrix :refer [transpose]]))

;; Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

;;     Each row must contain the digits 1-9 without repetition.
;;     Each column must contain the digits 1-9 without repetition.
;;     Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

;; Note:

;;     A Sudoku board (partially filled) could be valid but is not necessarily solvable.
;;     Only the filled cells need to be validated according to the mentioned rules.


(defn group-by-3b3 [s]
  (let [columns-grouped-by-3  (map #(vec (partition 3 %)) s)
        grouped-to-3b3 (vec (map vec (partition 3 columns-grouped-by-3)))
        group-indexes [0 1 2]
        build-square (fn [groups-row column-index]
                       (fn [square row-index]
                         (let [transposed-row (get-in groups-row [row-index column-index])
                               updated-square (conj square transposed-row)]
                           updated-square)))
        update-group-row (fn [groups-row]
                           (fn [new-groups column-index]
                             (let [square (reduce (build-square groups-row column-index) [] group-indexes)
                                   updated-row-groups (conj new-groups square)]
                               updated-row-groups)))
        reorder-by-groups (fn [new-group-rows groups-row] ;; 0
                            (let [updated-group-row (reduce (update-group-row groups-row) [] group-indexes)
                                  updated-group-rows (conj new-group-rows updated-group-row)]
                              updated-group-rows))
        reordered-by-groups (reduce reorder-by-groups [] grouped-to-3b3)
        unpartitioned-rows (apply concat reordered-by-groups)
        unpartitioned-columns (mapv #(apply concat %) unpartitioned-rows)
        sudoku-by-groups unpartitioned-columns]
    sudoku-by-groups))

(def valid-numbers #{"1" "2" "3" "4" "5" "6" "7" "8" "9"})

(defn is-valid-sudoku? [sudoku]
  (let [s-by-column (transpose sudoku)
        s-by-3b3 (group-by-3b3 sudoku)
        sudokus-to-check [sudoku s-by-column s-by-3b3]
        sudokus-with-only-numbers (map (fn [sudoku]
                                         (map (fn [sudoku-row]
                                                (filter #(contains? valid-numbers %) sudoku-row)) sudoku))
                                       sudokus-to-check)
        unique-collection? #(= (count %) (count (set %)))
        invalid-sudoku? (fn [sudoku] (some #(not (unique-collection? %)) sudoku))
        answer (not (some invalid-sudoku? sudokus-with-only-numbers))]
    answer))

(comment 
  
   )
   