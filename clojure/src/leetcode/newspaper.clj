(ns leetcode.newspaper
	(:require [clojure.string :refer [join split trim]]))

; Given an array of phrases, called a paragraph, and a width W make the following.
; Create a new paragraph P in the following form
; P should also be a paragraph
; The length of all phrases must size W
; P should begin and end with a sequence of asterisks
; All phrases must begin and end with an asterisk
; If a phrase exceedes W, then phrases should be continually moved to the next line
; until the current line is at or below W
; If a phrase is below W, spaces must be added to each side of the phrase until
; it is at size W. If you have an odd number of spaces, the remainder should be
; added to the right side.
; Thus, given an example paragraph below and W = 12, the output should look like this.
; ************
; *The quick *
; *  brown   *
; * foxjumps *
; *   over   *
; * thelazy  *
; * dog yo!  *
; ************
; Normally you would assume that any given string without spaces will not be greater than
; W-2, but I would not assume that here.
(def paragraph [" The quick  " "  brown foxjumps over " "  thelazy dog yo!"])

(defn zip-word-data [vec-of-words vec-of-word-counts]
	(fn [zipped index]
		(let [word (vec-of-words index)
					word-count (vec-of-word-counts index)
					package {:word word :word-count word-count}
					zipped-with-new-package (conj zipped package)]
			zipped-with-new-package)))

(defn handle-exceeded-width [reordered-words current-phrase word-count word]
	(let [reordered-words-with-new-phrase (conj reordered-words current-phrase)
				updated-state {:current-sum     word-count
											 :current-phrase  [word]
											 :reordered-words reordered-words-with-new-phrase}]
		updated-state))

(defn handle-below-or-at-width [current-phrase word current-sum word-count]
	(let [updated-current-phrase (conj current-phrase word)
				new-sum (+ current-sum word-count)
				updated-state {:current-sum    new-sum
											 :current-phrase updated-current-phrase}]
		updated-state))

(defn reorder-words [actual-width]
	(fn [{:keys [current-sum current-phrase reordered-words]} {:keys [word word-count]}]
		(let [new-current-sum (+ current-sum word-count)
					answer (if (> new-current-sum actual-width)
									 (handle-exceeded-width reordered-words current-phrase word-count word)
									 (merge
										 {:reordered-words reordered-words}
										 (handle-below-or-at-width current-phrase word current-sum word-count)))]
			answer)))

(defn add-phrase-count [phrases phrase]
	(let [phrase-with-count {:phrase phrase :count (count phrase)}
				phrases-with-new-phrase (conj phrases phrase-with-count)]
		phrases-with-new-phrase))

(defn add-asterisks-and-spaces [actual-width]
	(fn [final-phrases {:keys [phrase count]}]
		(let [space-to-fill (- actual-width count)
					left-spaces-count (int (Math/floor (/ space-to-fill 2)))
					right-spaces-count (int (Math/ceil (/ space-to-fill 2)))
					left-spaces (join (repeat left-spaces-count " "))
					right-spaces (join (repeat right-spaces-count " "))
					final-phrase (str "*" left-spaces phrase right-spaces "*")
					updated-final-phrases (conj final-phrases final-phrase)]
			updated-final-phrases)))
(defn make-newspaper [vec-of-words width]
	(let [actual-width (- width 2)
				vec-of-word-counts (into [] (map count vec-of-words))
				list-of-indexes (range 0 (count vec-of-word-counts))
				words-with-metadata (reduce (zip-word-data vec-of-words vec-of-word-counts) [] list-of-indexes)
				reordered-words-result
				(reduce (reorder-words actual-width) {:current-sum     0
																							:current-phrase  []
																							:reordered-words []} words-with-metadata)
				reordered-words (conj (:reordered-words reordered-words-result) (:current-phrase reordered-words-result))
				corrected-paragraph (map #(join " " %) reordered-words)
				phrases-with-count (reduce add-phrase-count [] corrected-paragraph)
				finalized-phrases (reduce (add-asterisks-and-spaces actual-width) [] phrases-with-count)
				string-of-asterisks (join (repeat width "*"))
				newspaper-paragraph (conj (reduce #(conj %1 %2) [string-of-asterisks] finalized-phrases) string-of-asterisks)]
		newspaper-paragraph))

(defn can-make-newspaper? [vec-of-words width] (not (some #(< width (count %)) vec-of-words)))

(defn flatten-phrases [phrases]
	(into [] (flatten (map #(split % #" ") phrases))))

(defn try-making-a-newspaper [phrases width]
	(let [trimmed-phrases (reduce #(conj %1 (trim %2)) [] phrases)
				vec-of-words (flatten-phrases trimmed-phrases)
				; if it has a word that's too big, hard exit
				result (if (can-make-newspaper? vec-of-words width)
								 (make-newspaper vec-of-words width)
								 {:error "Cannot fit largest word into newspaper. Give smaller words or larger width."})]
		result))

(comment
	(try-making-a-newspaper paragraph 12)
	)