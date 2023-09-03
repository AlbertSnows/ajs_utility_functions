(ns tribonacci)


(comment
  (let [n 27
        f 3 ;; for nbonnaci
        seed {0 0 1 0 2 1}
        numbers-to-calc (range 3 (inc n))
        calc-tribonacci
        (fn [calculated number]
          (assoc calculated
                 number
                 (+ (get calculated (- number 3))
                    (get calculated (- number 2))
                    (get calculated (dec number)))))
        result (reduce calc-tribonacci seed numbers-to-calc)

        answer (sort result)]
    answer)
  )