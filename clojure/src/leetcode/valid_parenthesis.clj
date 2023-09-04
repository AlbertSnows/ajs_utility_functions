(ns valid-parenthesis)

;; Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

;; An input string is valid if:

;;     Open brackets must be closed by the same type of brackets.
;;     Open brackets must be closed in the correct order.
;;     Every close bracket has a corresponding open bracket of the same type.

 

;; Example 1:

;; Input: s = "()"
;; Output: true

;; Example 2:

;; Input: s = "()[]{}"
;; Output: true

;; Example 3:

;; Input: s = "(]"
;; Output: false


(comment 
  
  (let [phrase "([])"
        phrase-as-char-array (vec (char-array phrase))
        openers #{\( \{ \[}
        closer-to-opener {\) \( \} \{ \] \[}
        match-parens (fn [stack paren]
                       (let [is-opener? (contains? openers paren)
                             most-recent-opener (peek stack)
                             opener-for-current-paren
                             (if (not is-opener?)
                               (get closer-to-opener paren)
                               nil)
                             matching-openers? (= most-recent-opener opener-for-current-paren)
                             answer
                             (cond
                               is-opener? (conj stack paren)
                               matching-openers? (pop stack)
                               :else (reduced '("bad parens!")))]
                         answer))
        result (reduce match-parens '() phrase-as-char-array)
        answer result]
    answer)
  )