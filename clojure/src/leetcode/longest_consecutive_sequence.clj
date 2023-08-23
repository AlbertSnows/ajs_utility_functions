(ns longest-consecutive-sequence)

(defn find-longest-consecutive-sequence [n]
  (let [sn (set n)
        beginnings (filter #(not (contains? sn (- % 1))) sn)
        has-next? #(contains? sn %)
        count-consecutives
        (fn [start]
          (loop [state {:count 0 :value start}]
            (if (not (has-next? (:value state)))
              (:count state)
              (recur {:count (inc (:count state))
                      :value (inc (:value state))}))))
        consecutives (map count-consecutives beginnings)
        max-count (apply max consecutives)
        answer max-count]
    answer))

(comment 
  (find-longest-consecutive-sequence [100,4,200,1,3,2])
 	)