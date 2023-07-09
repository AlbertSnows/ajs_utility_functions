(ns leetcode.find-close-strings)

(defn are-close-strings? [first-word second-word]           ; assuming same length
	(let [grab-unique-chars #(set (vec %))
				unique-chars-in-first-word (grab-unique-chars first-word)
				unique-chars-in-second-word (grab-unique-chars second-word)
				are-equal? (= unique-chars-in-first-word unique-chars-in-second-word)
				answer are-equal?]
		; can add (= count1 count2) logic to solve diff lengths
		answer))
; todo: this solution is wrong, come back to later
; steps:
; compare unique letters
; compare letter counts
(comment
 (are-close-strings? "abc" "bca")
 (are-close-strings? "cabbba" "abbccc")
 )
