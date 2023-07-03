(ns leetcode.reverse-vowels
	(:require [clojure.string :refer [join]]))

; Given a word, swap all vowels in the word in the following manner
; For a given vowel V, swap it with the furthest vowel on the other
; side of the word that hasn't been used until each vowel has been swapped
; once.
; e.g. aaaTeee => eeeTaaa
; racecar => racecar
; screaming => scriameng

(def example-word "wooden")                                 ; => weodon

(def vowels [\A \a \E \e \I \i \O \o \U \u])

(defn add-if-vowel [char-vec]
	(fn [vowels-in-word index]
		(let [char (char-vec index)
					is-vowel? (some #(= char %) vowels)
					updated-vowels (if is-vowel?
													 (conj vowels-in-word {:vowel char :index index})
													 vowels-in-word)]
			updated-vowels)))

(defn pair-left-and-right-vowels [number-of-vowels pairs vowels-in-word]
	(fn [paired index]
		(let [right-side-beginning-index (- number-of-vowels pairs)
					left-vowels (subvec vowels-in-word 0 pairs)
					right-vowels (subvec vowels-in-word right-side-beginning-index number-of-vowels)
					left-vowel-data (left-vowels index)
					right-vowel-data (right-vowels index)
					pair {:l-vowel (:vowel left-vowel-data)
								:l-index (:index left-vowel-data)
								:r-vowel (:vowel right-vowel-data)
								:r-index (:index right-vowel-data)}
					updated-pairs (conj paired pair)]
			updated-pairs)))

(defn swap-pair-indexes [swapped {:keys [l-index r-index] :as pair}]
	(let [updated-pair (assoc pair :l-index r-index :r-index l-index)
				updated-swapped (conj swapped updated-pair)]
		updated-swapped))
(defn reverse-vowels [word]
	(let [char-vec (vec word)
				word-count (count char-vec)
				indexes (range 0 word-count)
				vowels-in-word (reduce (add-if-vowel char-vec) [] indexes)
				number-of-vowels (count vowels-in-word)
				pairs (int (Math/floor (/ number-of-vowels 2)))
				paired-vowels (reduce (pair-left-and-right-vowels number-of-vowels pairs vowels-in-word) [] (range 0 pairs))
				swapped-paired-vowels (reduce swap-pair-indexes [] paired-vowels)
				swap-letters
				(fn [char-vec {:keys [l-vowel l-index r-vowel r-index]}]
					(assoc char-vec l-index l-vowel r-index r-vowel))
				updated-vec (reduce swap-letters char-vec swapped-paired-vowels)
				word-with-reversed-vowels (join updated-vec)]
		word-with-reversed-vowels))
(comment
	(reverse-vowels "wooden")
	)