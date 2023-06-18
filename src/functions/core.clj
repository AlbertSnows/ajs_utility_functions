(ns functions.core)




;((reduce_number n) check-fizz-buzz [])

(defn reduce-int-elsewhere
  "An implementation of reduce-int but more explicit.
  The purpose is exclusively for reference for other languages."
  [n]
  (fn [fn initial-value short-circuit]
    ;  let accumulator = initial_value;
    ;  if(short_circuit !== false) {
    ;  for (let i = 1; i <= n; i++) {
    ;  if(short_circuit(accumulator, i)) {
    ;  return accumulator;
    ;  }
    ;  accumulator = fn(accumulator, i);
    ;  }
    ;  } else {
    ;  for (let i = 1; i <= n; i++) {
    ;  accumulator = fn(accumulator, i);
    ;  }
    ;  }

    ;accumulator
    )

  )

(defn reduce-int
  "An absurdly lazy way to reduce over an integer. Note that it reduces over a list.
   Not recommended unless you're doing this a lot. Just use range."
  ([n f] (reduce f (range 0 n)))
  ([n f init] (reduce f init (range 0 n))))

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
