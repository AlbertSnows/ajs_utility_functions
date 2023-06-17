(ns test.helpers)

(defn setup-check-fizzbuzz [div-by-3 div-by-5]
  (fn [accumulator number]
    (let [is-div-by-3 (div-by-3 number)
          is-div-by-5 (div-by-5 number)
          answer
          (cond
            (and is-div-by-3 is-div-by-5) "FizzBuzz"
            is-div-by-5 "Buzz"
            is-div-by-3 "Fizz"
            :else (str number))]
      (conj accumulator answer))))

(def default-fizzbuzz
  (let [div-by-x (fn [divisor] (fn [fizzbuzz-number] (= 0 (mod fizzbuzz-number divisor))))
        div-by-3 (div-by-x 3)
        div-by-5 (div-by-x 5)]
    (setup-check-fizzbuzz div-by-3 div-by-5)))

(defn fizzbuzz [n] (reduce default-fizzbuzz [] (range 1 (+ n 1))))
