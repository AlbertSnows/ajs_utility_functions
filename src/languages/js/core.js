// Example closure implementation. If you want mutable state, you need to do a deep copy to avoid referential problems.
import { update } from "ramda"; // https://ramdajs.com/docs/

const multiply = x => y => x * y;

// given an array, index, and number will update at index in array
// by taking the current value and adding the number to it. 
// returns a deep copy, useful(?) for closure antics
const add_number_at_index = (number) => (index) => (array) => {
  const number_at_index = array[index]
  const new_number = number_at_index + number
  return update(index, new_number, array);
};


export {
  multiply,
  add_number_at_index
};
