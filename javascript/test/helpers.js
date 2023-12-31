// Functions written specifically for testing core functions go here
import { swap, addIndex, reduce, reduced, join } from "ramda"; // https://ramdajs.com/docs/
import { short_circuit_reduce } from "../../../src/languages/js/core.js";
const swap_array_contents_rambda = (array) => (left_side_index) => {
	const index_of_last_element = array.length - 1;
	const right_side_index = index_of_last_element - left_side_index;
	const updated_array = swap(right_side_index, left_side_index, array);
	return updated_array;
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


const reverse_sentence_ramda = (phrase) => {
	const phrase_as_array = phrase.split(" ");
	const half_array_length = phrase_as_array.length / 2;
	const reduce_while_with_index = addIndex(reduce);
	const answer = reduce_while_with_index(
		(acc, val, idx) => {
			return idx >= half_array_length
				? reduced(acc)
				: swap_array_contents(acc)(idx);
		},
		phrase_as_array,
		phrase_as_array);
	return join(" ", answer);
};

const reverse_sentence = (phrase) => {
	const phrase_as_array = phrase.split(" ");
	const half_array_length = phrase_as_array.length / 2;
	const answer = 
        short_circuit_reduce(swap_array_contents)((_) => (_) => (idx) => idx >= half_array_length)(phrase_as_array)(phrase_as_array);
	return answer.join(" ", answer);
};

export { reverse_sentence };
