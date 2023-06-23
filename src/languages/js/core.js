// Example closure implementation. If you want mutable state, you need to do a deep copy to avoid referential problems.

const multiply = x => y => x * y;

export {
  multiply
};
