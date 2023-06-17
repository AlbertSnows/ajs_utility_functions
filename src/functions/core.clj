(ns functions.core)

;const reduce_number = (n) => (fn, initial_value, short_circuit = false) => {
;let accumulator = initial_value;
;if(short_circuit !== false) {
;for (let i = 1; i <= n; i++) {
;if(short_circuit(accumulator, i)) {
;return accumulator;
;}
;accumulator = fn(accumulator, i);
;}
;} else {
;for (let i = 1; i <= n; i++) {
;accumulator = fn(accumulator, i);
;}
;}
;
;return accumulator;
;};
;
;const find_first_true = (list_of_states) => {
;
;};
;

;((reduce_number n) check-fizz-buzz [])

(defn find-first-true [states]
  "Given a data structure in the form [{:context <bool> :value <data>} ...],
   returns the value of the first map whose context is evaluate to true.
   Returns nil if none are true. You can pass a map with a context of true to use a default value."
  (reduce #(println %
             ;if
             ;(true? (:context %))
             ;(:value %)
             ;(reduced (:value %))
             ;nil
             )
          states)
  )
