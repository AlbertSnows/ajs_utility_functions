// Functions written specifically for testing core functions go here
import { swap, reduceWhile, addIndex } from "ramda"; // https://ramdajs.com/docs/

// given an array of size n
// given an index where the max value is n/2
const swap_array_contents = (array) => (left_side_index) => {
  const index_of_last_element = array.length - 1; // 8
  const right_side_index = index_of_last_element - left_side_index;
  const updated_array = swap(right_side_index, left_side_index, array);
  return updated_array;
};

const solve151 = (phrase) => {
  const phrase_as_array = phrase.split(" ");
  const half_array_length = phrase_as_array.length / 2;
  const answer = addIndex(reduceWhile)(
    (acc, val, idx) => idx <= half_array_length,
    (acc, val, idx) => swap_array_contents(acc)(idx),
    phrase_as_array, phrase_as_array);
  return answer;
};
console.log(solve151("the sky is blue"));

export { solve151 };
