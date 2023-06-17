(ns functions.core
  (:require [java-time.api :as t]))

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
;const check_fizz_buzz = (fizz_buzz_results, number) => {
;const div_by_3 = number % 3 === 0;
;const div_by_5 = number % 5 === 0;
;const fizz_buzz_states = [
;{ context: div_by_5 && div_by_3, value: "FizzBuzz" },
;{ context: div_by_3, value: "Fizz" },
;{ context: div_by_5, value: "Buzz" }];
;return fizz_buzz_results.push(find_first_true(fizz_buzz_states));
;};

((reduce_number n) check-fizz-buzz [])
