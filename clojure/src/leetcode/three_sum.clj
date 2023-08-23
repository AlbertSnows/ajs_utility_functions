(ns three-sum
  (:require [two-sum-sorted-array :refer [find-two-sum-for-target]]))

(defn find-two-sum-for-n [sorted-list]
  (fn [{:keys [used three-sums]} fix-index]
    (let [;; 	used #{}
          ;; fix-index 0
          ;; three-sums []
          sorted-list
          (vec sorted-list)
          ;; (vec '(-4 -1 -1 0 1 2))
          fix (get sorted-list fix-index)
          _ (println ["b" fix-index fix])
          sublist (subvec sorted-list (inc fix-index))
          already-used? (contains? used fix)
          sum (find-two-sum-for-target sublist fix)
          found-two-sum? (not (empty? sum))
          three-sum (conj sum fix)
          updated-success-cases (conj three-sums three-sum)
          _ (println sublist)
          answer
     					;; fix-index
          (cond
            already-used? {:used used :three-sums three-sums}
            found-two-sum? {:used (conj used fix)
                            :three-sums updated-success-cases}
            :else {:used used :three-sums three-sums})]
      answer)
 			))

(comment
  (let [n [-1 0 1 2 -1 -4]
       	indexes (range 0 (count n))
        sorted-n (sort n)
        result (reduce (find-two-sum-for-n sorted-n)
                       {:used #{} :three-sums []}
                       indexes)
        answer result]
    answer)
  )