// Example closure implementation. If you want mutable state, you need to do a deep copy to avoid referential problems.

// Scope in theory should allow you to quickly define a closure for a function
// given initial state and a desired function (that expects to be a closure)
// this function will pass that into your inner function and return whatever is the result
// which...should be a function probably. 
const scope = (initial_state, inner_function) => {
  let outer_context = initial_state
  return inner_function(outer_context);
};

// given an array, index, and number will update at index in array
// by taking the current value and adding the number to it. 
// returns a deep copy, useful(?) for closure antics
const add_number_at_index = (array) => (index, number) => {
  const number_at_index = number[index]
  const new_number = number_at_index + number
  array[index] = new_number
  return structuredClone(array)
};

module.exports = {
  scope,
  add_number_at_index
};
