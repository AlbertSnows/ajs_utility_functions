(ns container-with-water)

;; You are given an integer array height of length n. 
;; There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height [i]) .

;; Find two lines that together with the x-axis form a container, such that the container contains the most water.

;; Return the maximum amount of water a container can store.

;; Notice that you may not slant the container.

;; Input: height = 
;; [1,8,6,2,5,4,8,3,7]
;; 

;; Output: 49
;; Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
;; In this case, the max area of water (blue section) the container can contain is 49.

;; Example 2:

;; Input: height = [1,1]
;; Output: 1

(defn setup-check-container [height]
  (fn [{:keys [left right max-volume]}]
    (let [left-wall (get height left)
          right-wall (get height right)
          distance (- right left)
          right-wall-is-larger-or-equal (>= right-wall left-wall)
          smaller-wall (if right-wall-is-larger-or-equal left-wall right-wall)
          container-size (* smaller-wall distance)
          updated-max-value (max max-volume container-size)
          updated-left-index (if right-wall-is-larger-or-equal (inc left) left)
          updated-right-index (if right-wall-is-larger-or-equal right (dec right))
          answer {:left updated-left-index
                  :right updated-right-index
                  :max-volume updated-max-value}]
      answer)))


(comment 
  
  (let [height [1,8,6,2,5,4,8,3,7]
        check-container (setup-check-container height)
        result (loop [state {:left 0 :right (dec (count height))
                             :max-volume 0}]
                 (if (>= (:left state) (:right state))
                   (:max-volume state)
                   (recur (check-container state))))
        answer result]
    answer)
    )