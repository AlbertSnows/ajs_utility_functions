(ns trapping-rain-water)

;; Given n non-negative integers representing an elevation map 
;; where the width of each bar is 1, compute how much water it can trap after raining.

 

;; Example 1:

;; Input: height = 
;; [0,1,0,2,1,0,1,3,2,1,2,1]

;; Output: 6
;; Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

;; Example 2:

;; Input: height = [4,2,0,3]

;; hashset of walls we've found so far 
;; tallest left wall 
;; when we hit a right wall that is in our set 
;; or taller than our left wall, stop 
;; we have a container 
;; run container fill
;; reset hash set
;; begin again 
;; left wall = tallest 

(comment 
  (let [height [0,1,0,2,1,0,1,3,2,1,2,1]
        indexes (range 0 (count height))
        set-max-wall-for-left-index
        (fn [{:keys [max-so-far max-sizes]} index]
          (let [new-left-wall (get height (dec index))
                larger? (> new-left-wall max-so-far)
                updated-max-left (if larger? new-left-wall max-so-far)
                updated-max-sizes (conj max-sizes updated-max-left)
                answer {:max-so-far updated-max-left
                        :max-sizes updated-max-sizes}]
            answer))
        max-left-wall-for-index (:max-sizes
                                 (reduce set-max-wall-for-left-index
                                         {:max-so-far 0 :max-sizes [0]}
                                         (rest indexes)))
        set-max-wall-for-right-index
        (fn [{:keys [max-so-far max-sizes]} index]
          (let [new-left-wall (get height (inc index))
                larger? (> new-left-wall max-so-far)
                updated-max-left (if larger? new-left-wall max-so-far)
                updated-max-sizes (conj max-sizes updated-max-left)
                answer {:max-so-far updated-max-left
                        :max-sizes updated-max-sizes}]
            answer))
        max-right-wall-for-index (vec 
                                  (reverse
                                   (:max-sizes
                                    (reduce set-max-wall-for-right-index
                                            {:max-so-far 0 :max-sizes [0]}
                                            (rest (reverse indexes))))))
        find-min-for-index
        (fn [mins index] 
          (conj mins (min (get max-left-wall-for-index index) 
                          (get max-right-wall-for-index index))))
        min-wall-for-index (reduce find-min-for-index [] indexes)
        get-diff-of-wall-height-and-container 
        (fn [index]
          (- (get min-wall-for-index index) (get height index)))
        remaining-water (map get-diff-of-wall-height-and-container indexes)
        valid-containers (filter #(> % 0) remaining-water)
        answer (apply + valid-containers)
        ]
    answer)
  )