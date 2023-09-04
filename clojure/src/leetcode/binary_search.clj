(ns binary-search)


(defn setup-binary-search [n h]
  (fn [{:keys [left right]}]
    (let [middle (quot (+ right left) 2)
          value (get n middle)
          equal? (= value h)
          greater? (> h value)
          lower? (> value h)
          new-left (if greater? (inc middle) left)
          new-right (if lower? (dec middle) right)
          answer (cond
                   equal? {:left middle :right middle}
                   greater? {:left new-left :right right}
                   :else {:left left :right new-right})]
      answer)))

(comment 
  
  (let [
        n [0 2 6]
        h 6
        end (count n)
        binary-search (setup-binary-search n h)
        result 
        (loop [state {:left 0
                      :right (dec end)}]
          (if (>= (:left state) (:right state)) 
            state
            (recur (binary-search state))))
        answer [result "... or nil"]]
    answer)
  )