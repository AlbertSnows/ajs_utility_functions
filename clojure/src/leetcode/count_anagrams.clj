(ns count-anagrams)


(defn count-anagrams [int-list]
  (let [convert-to-digits #(Character/digit % 10)
        as-digits (map #(map convert-to-digits (str %)) int-list)
        as-freq (vec (map frequencies as-digits))
        are-equal (fn [pivot]
                    (fn [num]
                      (let [is-anagram
                            (and (= (count pivot) (count num))
                                 (= pivot num))]
                        is-anagram)))
        add-anagrams-for-pivot
        (fn [total pivot-index]
          (let [pivot (nth as-freq pivot-index)
                nums-to-check (subvec as-freq (inc pivot-index))
                anagrams-for-pivot (filter (are-equal pivot) nums-to-check)
                answer (+ total (count anagrams-for-pivot))]
            answer))
        result (reduce add-anagrams-for-pivot 0 (range (count int-list)))
        answer   result]
    answer))
