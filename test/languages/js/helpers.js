// Functions written specifically for testing core functions go here
import { swap, reduceWhile, addIndex, __, reduce, reduced, join } from "ramda"; // https://ramdajs.com/docs/

const swap_array_contents = (array) => (left_side_index) => {
	const index_of_last_element = array.length - 1;
	const right_side_index = index_of_last_element - left_side_index;
	const updated_array = swap(right_side_index, left_side_index, array);
	return updated_array;
};

const reverse_sentence = (phrase) => {
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
		phrase_as_array // wrong
	);
	return join(" ", answer);
};

export { reverse_sentence };
