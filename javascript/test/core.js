/* eslint-disable no-undef */
import { assert, expect } from "chai";
import { map } from "ramda"; // https://ramdajs.com/docs/
import {
	multiply,
	add_number_at_index,
	short_circuit_reduce,
	range
} from "../../../src/languages/js/core.js";
import { reverse_sentence } from "./helpers.js";
// import { mocha, it } from "mocha"; // there should be a way to do this reeeeeeeeeee

describe("This test is to ensure that short circuit reduce works correctly.", () => {
	it("should early exit when number is greater than 9 and give the correct value", () => {
		const sum_till_9 = short_circuit_reduce((acc) => (int) => (_) => acc + int)((acc) => (_) => (_) => acc > 9)(0)([1, 2, 3, 4, 5, 6]);
		expect(sum_till_9).to.equal(10);
	});
	it("should not exit early based on the given criteria", () => {
		const reducer = (acc) => (char) => (_) => acc + char;
		const reduced = (acc) => (_) => (_) => acc = undefined;
		const concat_string = short_circuit_reduce(reducer)(reduced)("")(["m", "e", "o", "w"]);
		expect(concat_string).to.equal("meow");
	});
});

describe("This test is for range. It just ensures range provides the expected value", () => {
	it("should return the correct array", () => {
		expect(range()(6)).deep.to.equal([0, 1, 2, 3, 4, 5]);
		expect(range(3)(5)).deep.to.equal([3, 4, 5, 6, 7]);
	});
});

// https://leetcode.com/problems/reverse-words-in-a-string/?envType=study-plan-v2&envId=leetcode-75
describe("151. Reverse Words in a String", () => {
	it("should reverse the words in a string", () => {
		// adding `it()` allows vscode to pick up these functions as a test
		const input = "the sky is blue";
		const output = reverse_sentence(input);
		expect(output).to.equal("blue is sky the");
	});
});

describe("add number at index in array", () => {
	it("should have the correct value at the specified array location", () => {
		const result = add_number_at_index(6)(1)([3, 2, 1]);
		expect(result).to.deep.equal([3, 8, 1]);
	});
});

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
