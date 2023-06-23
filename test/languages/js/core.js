
import { multiply, add_number_at_index } from "../../../src/languages/js/core.js";
import { map } from "ramda"; // https://ramdajs.com/docs/
import { assert, expect } from "chai";

describe("add number at index in array", () => {
    it("should have the correct value at the specified array location", () => {
        const result = add_number_at_index(6)(1)([3, 2, 1]);
        expect(result).to.deep.equal([3, 8, 1]);
    });
})

describe("simple multiplication test", () => {
    it("should do simple multiplication correctly", () => {
        const multiply_test = map(multiply(6), [1, 2, 3]);
        expect(multiply_test).to.deep.equal([6, 12, 18]);    
    });
});

describe("simple addition test", () => {
    it("should do simple addition correctly", () => {
        assert.equal(3, 1 + 2);
        assert.notEqual(0, 1);      
    });
});