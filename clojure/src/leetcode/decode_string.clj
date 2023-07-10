(ns leetcode.decode-string
	(:require [clojure.string :refer [join]]))

(defn split-phrase [split-phrases phrase]
	(let [split-by-num-and-group #"(\d+?)(\[(.*?)\])"
				[_ number _ content] (re-find split-by-num-and-group phrase)
				answer (conj split-phrases {:number (Integer/parseInt number)
																		:phrase content})]
		answer))

(defn apply-sequences [new-phrase {:keys [number phrase]}]
	(let [phrase-to-add (repeat number phrase)
				answer (str new-phrase (join phrase-to-add))]
		answer))

(defn decode-string [encoded-string]
	(let [split-into-char-groups #"\d+\[.*?\]"
				matching-sequences (re-seq split-into-char-groups encoded-string)
				sequences (reduce split-phrase [] matching-sequences)
				result (reduce apply-sequences "" sequences)
				answer result]
		answer)
	)

(comment
 (decode-string "3[a]2[bc]")
 (decode-string "3[a2[c]]")
 (decode-string "2[abc]3[cd]ef")
 )

;Given an encoded string, return its decoded string.
;
;The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
;
;You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
;
;The test cases are generated so that the length of the output will never exceed 105.
;
;
;
;Example 1:
;
;Input: s = "3[a]2[bc]"
;Output: "aaabcbc"
;
;Example 2:
;
;Input: s = "3[a2[c]]"
;Output: "accaccacc"
;
;Example 3:
;
;Input: s = "2[abc]3[cd]ef"
;Output: "abcabccdcdcdef"
