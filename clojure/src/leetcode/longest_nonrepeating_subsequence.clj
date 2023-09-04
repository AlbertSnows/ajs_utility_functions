(ns longest-nonrepeating-subsequence)

;; Given a string s, find the length of the longest
;; substring
;; without repeating characters.

 

;; Example 1:

;; Input: s = "abcabcbb"
;; Output: 3
;; Explanation: The answer is "abc", with the length of 3.

;; Example 2:

;; Input: s = "bbbbb"
;; Output: 1
;; Explanation: The answer is "b", with the length of 1.

;; Example 3:

;; Input: s = "pwwkew"
;; Output: 3
;; Explanation: The answer is "wke", with the length of 3.
;; Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


(comment
  (let [phrase "abcabcbb"
        phrase-as-char-array (vec (char-array phrase))
        find-max-non-repeating-subsequence
        (fn [{:keys [follower unique-chars leader max-unique]}]
          (let [leader-char (get phrase-as-char-array leader)
                found-repeat? (contains? unique-chars leader-char)
                follower-char (get phrase-as-char-array follower)
                leader-equals-follower? (= leader-char follower-char)
                updated-unique-chars
                (cond 
                  (not found-repeat?) (conj unique-chars leader-char)
                  (= leader-char follower-char) unique-chars
                  :else (disj unique-chars follower-char))
                matching-chars-or-still-searching? 
                (or leader-equals-follower?
                    (contains? updated-unique-chars leader-char))
                update-follower? (and found-repeat?
                                      matching-chars-or-still-searching?)
                updated-follower (if update-follower? (inc follower) follower)
                update-leader? (or (not update-follower?) leader-equals-follower?)
                updated-leader (if update-leader? (inc leader) leader)
                updated-max (cond
                              (not found-repeat?) (max max-unique
                                                       (- updated-leader follower))
                              :else max-unique)
                answer {:follower updated-follower :leader updated-leader
                        :unique-chars updated-unique-chars :max-unique updated-max}]
            answer))
        result 
        (loop [state {:follower 0 :unique-chars #{} :leader 0 :max-unique 0}]
          (if (>= (:leader state) (count phrase-as-char-array))
            (:max state) 
            (recur (find-max-non-repeating-subsequence state))))
        answer result]
    answer)
  )