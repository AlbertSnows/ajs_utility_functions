(ns reorder-linked-list)

;; You are given the head of a singly linked-list. The list can be represented as:
;; L0 → L1 → … → Ln - 1 → Ln
;; Reorder the list to be on the following form:
;; L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
;; You may not modify the values in the list's nodes. Only nodes themselves may be changed.

;; Example 1:
;; Input: head = [1,2,3,4]
;; Output: [1,4,2,3]

;; Example 2:

;; Input: head = [1,2,3,4,5]
;; Output: [1,5,2,4,3]

(def build-alternate-end-linked-list
      (fn [{:keys [first-half second-half result first?]} _]
        (let [first-half-empty? (empty? first-half)
              next-value-to-append (if (and first? (not first-half-empty?))
                                     (first first-half)
                                     (first second-half))
              updated-result (conj result next-value-to-append)
              updated-first-half (if first? (rest first-half) first-half)
              updated-second-half (if first? second-half (rest second-half)) 
              answer {:first-half updated-first-half
                      :second-half updated-second-half
                      :result updated-result
                      :first? (not first?)}]
          answer)))


(comment 
  
  (let [head '(1 2 3 4)
        end (count head)
        head-as-vec (vec head)
        half-size (quot end 2)
        first-half (subvec head-as-vec 0 half-size)
        second-half (subvec head-as-vec half-size end)
        second-half-reversed (reverse second-half)
        indexes (range 0 end) 
        result (reduce build-alternate-end-linked-list
                       {:first-half first-half
                        :second-half second-half-reversed
                        :result '()
                        :first? true} indexes)
        answer (reverse (:result result))]
    answer)
  
  )