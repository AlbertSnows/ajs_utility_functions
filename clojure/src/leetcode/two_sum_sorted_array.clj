(ns two-sum-sorted-array)

(defn check-target [n target]
     (fn [{:keys [left right]}]
       (let [left-v (get n left)
             right-v (get n right)
             sum (+ left-v right-v)
             ran-out-of-numbers? (>= left right)
             matches-target? (= sum target)
             too-small? (> target sum)
             too-large? (> sum target)
             updated-left (inc left)
             updated-right (dec right)
             answer
             (cond
               ran-out-of-numbers? {:status "failed"}
               matches-target? {:status "success"
                                :left left :right right}
               too-large? {:status "searching"
                           :left left :right updated-right}
               too-small? {:status "searching"
                           :left updated-left
                           :right right}
               :else {:status "searching"
                      :left updated-left
                      :right updated-right})]
         answer)))

(def search-for-matchers
  (fn [n target]
    (loop [state {:left 0 :right (dec (count n))}]
      (if (contains? #{"success" "failed"} (:status state))
        state
        (recur ((check-target n target) state))))))

(defn find-two-sum-for-target [n target]
  (let [match-result (search-for-matchers n target)
        answer (case (:status match-result)
                 "success" [(inc (:left match-result)) (inc (:right match-result))]
                 [-1 -1])]
    answer))


(comment 
  (find-two-sum-for-target [2 7 11 15] 9)
 	(find-two-sum-for-target [2 3 4] 9)
 	(find-two-sum-for-target [2 3 4] 6)
		)