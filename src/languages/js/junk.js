// For stuff I don't feel like I can make use of, but don't want to delete.

// Scope in theory should allow you to quickly define a closure for a function
// given initial state and a desired function (that expects to be a closure)
// this function will pass that into your inner function and return whatever is the result
// which...should be a function probably. 
const scope = (initial_state, inner_function) => {
  let outer_context = initial_state
  return inner_function(outer_context);
};
