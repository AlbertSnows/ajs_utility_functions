(ns product-of-array)

(defn calc-exclusive-products [ints]
  (let [size (count ints)
        prefix-prod (reductions * ints)
        postfix-prod (reductions * (reverse ints))
        get-product (fn [prod-coll index]
                      (if (neg-int? index) 1 (nth prod-coll index)))
        combine-products #(* (get-product prefix-prod (- % 1))
                             (get-product postfix-prod (- size (+ % 2))))
        answer (map combine-products (range size))]
    answer))

(comment
  (calc-exclusive-products [2 2 3 4 5])
  )
