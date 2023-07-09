(ns leetcode.max-vowel-substring)

(def vowels #{\a \e \i \o \u})

(defn check-vowel [max-length]
	(fn [{:keys [current found]} char]
		(let [is-vowel (contains? vowels char)
					updated-current-length (if is-vowel (inc current) 0)
					max-found (max found updated-current-length)
					reached-max (= max-length max-found)
					updated-state (if reached-max (reduced {:found max-found})
													{:current updated-current-length
													 :found   max-found})]
			updated-state)))

(defn calc-max-vowel-substring [phrase max-length]
	(let [phrase-as-char-vec (vec phrase)
				answer (reduce
								 (check-vowel max-length)
								 {:current 0 :found 0}
								 phrase-as-char-vec)]
		answer))

(comment
	(calc-max-vowel-substring "abciiidef" 3)
	(calc-max-vowel-substring "aeiou" 2)
	(calc-max-vowel-substring "leetcode" 3)
	)

;Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
;
;Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
;
;
;
;Example 1:
;
;Input: s = "abciiidef", k = 3
;Output: 3
;Explanation: The substring "iii" contains 3 vowel letters.
;
;Example 2:
;
;Input: s = "aeiou", k = 2
;Output: 2
;Explanation: Any substring of length 2 contains 2 vowels.
;
;Example 3:
;
;Input: s = "leetcode", k = 3
;Output: 2
;Explanation: "lee", "eet" and "ode" contain 2 vowels.
