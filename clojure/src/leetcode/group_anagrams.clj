(ns group-anagrams)

;; Given an array of strings strs, group the anagrams together. You can return the answer in any order.

;; An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
;; typically using all the original letters exactly once.



;; Example 1:

;; Input: strs = ["eat","tea","tan","ate","nat","bat"]
;; Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

;; Example 2:

;; Input: strs = [""]
;; Output: [[""]]

;; Example 3:

;; Input: strs = ["a"]
;; Output: [["a"]]

(defn get-anagram-frequencies [words]
  (let [str-freqs (map frequencies words)
       	;; don't need to sort, but can make it easier to reason about for educational reasons
        ;; sorted-string-freqs (map #(into (sorted-map) %) str-freqs)
        answer (frequencies str-freqs)]
    answer))

(comment
  

 	)