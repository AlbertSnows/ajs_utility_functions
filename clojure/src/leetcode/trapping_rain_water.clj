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
        set-max-wall-for-index 
        (fn [{:keys [max-so-far max-sizes]} index]
          (let [new-left-wall (get height (dec index))
                larger? (> new-left-wall max-so-far)
                updated-max-left (if larger? new-left-wall max-so-far)
                updated-max-sizes (conj max-sizes updated-max-left)
                answer {:max-so-far updated-max-left
                        :max-sizes updated-max-sizes}]
            answer))
        max-left-wall-for-index (reduce set-max-wall-for-index
                                        {:max-so-far 0 :max-sizes [0]}
                                        (rest indexes))
        ;; max-right-wall-for-index (reduce set-max-wall-for-index 
        ;;                                  {:max-so-far 0 :max-sizes []}
        ;;                                  (reverse indexes))
        ;; ;; containers (reduce check-containers seed indexes)
        answer max-left-wall-for-index]
    answer)
  )