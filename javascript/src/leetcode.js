import { range } from "./core.js";

const cut_size_meets_k = (k) => (cut_length) => (ribbons) => {
	const count_valid_cuts = (valid_cuts, ribbon) => {
		const valid_cuts_on_this_ribbon = Math.floor(ribbon/cut_length);
		const updated_valid_cuts = valid_cuts + valid_cuts_on_this_ribbon;
		return updated_valid_cuts;
	};
	const answer = ribbons.reduce(count_valid_cuts, 0) >= k;
	return answer;
};

const calculate_ribbons = (ribbons, k) => {
	const max_ribbon_length = Math.max(...ribbons);
	const options = range(1)(max_ribbon_length);
	let left = 1;
	let right = max_ribbon_length;
	const check_if_cut_works = cut_size_meets_k(k);
	let answer = 0;
	while(left <= right) {
		const mid = Math.floor((left+right)/2);
		const cut_works = check_if_cut_works(mid)(ribbons);
		if(cut_works) {
			answer = mid;
			left = mid + 1;
		} else {
			right = mid - 1;
		}
	}
	
	return answer;
};
console.log(calculate_ribbons([7, 5, 9], 3));
console.log(calculate_ribbons([7, 5, 9], 4));
console.log(calculate_ribbons([5, 7, 9], 22));
