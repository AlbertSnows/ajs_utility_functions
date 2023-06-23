// Example closure implementation. If you want mutable state, you need to do a deep copy to avoid referential problems.
import { update } from "ramda"; // https://ramdajs.com/docs/

const multiply = (x) => (y) => x * y;

// given an array, index, and number will update at index in array
// by taking the current value and adding the number to it.
// returns a deep copy, useful(?) for closure antics
const add_number_at_index = (number) => (index) => (array) => {
	const number_at_index = array[index];
	const new_number = number_at_index + number;
	return update(index, new_number, array);
};

const swap_array_contents = (array) => (_) =>  (left_side_index) => {
	const index_of_last_element = array.length - 1;
	const right_side_index = index_of_last_element - left_side_index;
	const rsv = array[right_side_index];
	const lsv = array[left_side_index];
	array[right_side_index] = lsv;
	array[left_side_index] = rsv;
	return array;
};

// This is a curried, short-circuit version of reduce
// The only difference is short_circuit, which expects
// a function in the form (acc) => (val) => (idx) => bool
// When the bool evaluates to true, it exits the function immediately. 
// Note: only useful when you don't have access to lodash or ramda or something
const short_circuit_reduce = f => short_circuit => init => coll => {
	const r_handler = (acc, val, idx, itr) => {
		const exit_early = short_circuit(acc)(val)(idx);
		if(exit_early) {
			itr.splice(1);
			return acc;
		}
		return f(acc)(val)(idx);
	};
	return coll.slice(0).reduce(r_handler, init);
};



export { multiply, add_number_at_index, short_circuit_reduce };
