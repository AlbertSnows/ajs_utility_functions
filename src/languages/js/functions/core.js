// helpful run code plugin: https://marketplace.visualstudio.com/items?itemName=formulahendry.code-runner
//todo: move above line to test
// Example closure implementation. If you want mutable state, you need to do a deep copy to avoid referential problems.
const scope = (initial_state, inner_function) => {
  let outer_context = initial_state
  return inner_function(outer_context);
};

const add_number_at_index = (array) => (index, number) => {
  const number_at_index = number[index]
  const new_number = number_at_index + number
  array[index] = new_number
  return structuredClone(array)
}

module.exports = {
  add_number_at_index
};
sss