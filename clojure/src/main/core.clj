(ns main.core)


(defn -main [& args]
      "I don't do anything. ")

(defn short-circuit-reduce
  "Normal reduce function except you pass a function in as short-circuit
   in the form (fn [acc value] ...).
   If the result is truthy, it immediately exits the function."
  [f initial-value short-circuit coll]
  (let [reducer (fn [accumulator value]
                  (if (short-circuit accumulator value)
                    (reduced accumulator)
                    (f accumulator value)))
        result (reduce reducer initial-value coll)]
    result))

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
