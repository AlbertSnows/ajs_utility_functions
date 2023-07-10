(ns leetcode.find-close-strings)

(defn are-close-strings? [first-word second-word]           ; assuming same length
	(let [first-word-frequencies (frequencies first-word)
				second-word-frequencies (frequencies second-word)
				first-word-keys (set (keys first-word-frequencies))
				second-word-keys (set (keys second-word-frequencies))
				first-word-vals (sort (vals first-word-frequencies))
				second-word-vals (sort (vals second-word-frequencies))
				same-letters (= first-word-keys second-word-keys)
				same-frequencies (= second-word-vals first-word-vals)
				are-close? (and same-letters same-frequencies)
				answer are-close?]
		; can add (= count1 count2) logic to solve diff lengths
		answer))

(comment
 (are-close-strings? "acbb" "bcca")
 (are-close-strings? "cabbba" "abbccc")
 (are-close-strings? "aabbcc" "abbbbc")
 )
