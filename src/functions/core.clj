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

(defn get-value-if-in-correct-context [_ state]
  "Given a context-value map (i.e. a data structure in the form {:context <bool> :value <data>}),
   returns the corresponding value and short circuits. Nil otherwise.
   Expects to be used in a reducer."
  (let [correct-context (true? (:context state))
        result (if correct-context (reduced (:value state)) nil)]
    result))

(defn find-first-true [states]
  "Given an array of context-value states, returns the value of the first map whose context is evaluate to true.
   Returns nil if none are true. You can pass a map with a context of true to use as a default value.
   See get-value-if-in-correct-context for more details about implementation."
  (let [result (reduce get-value-if-in-correct-context nil states)] result))
