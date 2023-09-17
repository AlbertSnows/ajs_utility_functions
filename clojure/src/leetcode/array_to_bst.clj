(ns array-to-bst)


;; Example 1:

;; Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
;; Output: 6
;; Explanation: The LCA of nodes 2 and 8 is 6.

(defn build-node [value left right]
  {:right right :left left :value value})

(def build-bst (fn [n index]
                 (let [root (build-node (get n index) nil nil)
                       left (if (> index (count n)) nil (recur n (* 2 (inc index))))
                       right (recur n (* 2 (+ index 2)))
                       root-w-left (assoc root :left left)
                       root-w-right (assoc root-w-left :right right)
                       answer root-w-right]
                   answer)))

(comment 
  
  (let [n [6,2,8,0,4,7,9,nil,nil,3,5]
        indexes (range 0 (quot (count n) 2))
        
        result (reduce build-bst {} indexes)
        answer indexes]
    answer)

  
  
  )