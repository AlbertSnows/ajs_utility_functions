// helpful run code plugin: https://marketplace.visualstudio.com/items?itemName=formulahendry.code-runner

import { multiply } from "../../../src/languages/js/core.js";
import { map } from "ramda"; // https://ramdajs.com/docs/
import { assert, expect } from "chai";
import { describe } from "mocha";

describe("simple multiplication test", () => {
    const multiply_test = map(multiply(6), [1, 2, 3]);
    expect(multiply_test).to.deep.equal([6, 12, 18]);
});

describe("simple addition test", () => {
  assert.equal(3, 1 + 2);
  assert.notEqual(0, 1);
});