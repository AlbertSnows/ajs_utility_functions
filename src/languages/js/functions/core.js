// helpful run code plugin: https://marketplace.visualstudio.com/items?itemName=formulahendry.code-runner

// Example closure implementation. If you want mutable state, you need to do a deep copy to avoid referential problems.
const outer = (a) => {
  const array = a
  const add_number_at_index = (index, number) => {
    const number_at_index = number[i]
    const new_number = number_at_index + number
    array[i] = new_number
    return structuredClone(array)
  }
  return add_number_at_index
};
