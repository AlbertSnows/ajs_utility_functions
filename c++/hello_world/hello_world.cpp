using namespace std;
#include <iostream>
#include <iterator>
#include <vector>
#include <variant>
#include "fizzbuzz.h"
#include <string>


int main() {	
	//std::cout << "test" << std::endl;
	string a = "test";
	variant<int, string> result = a; // fizzBuzzLogic(3);
	cout << std::get<decltype(a)>(result) << std::endl;
	fizzBuzz(15);

	// ctrl k ctrl c
	//std::cout << "Hello, world!\n";
	//int first_number = 0;
	//int second_number = 0;
	//std::cout << "Enter integer 1: ";
	//std::cin >> first_number;
	//std::cout << "Enter integer 2: ";
	//std::cin >> second_number;
	//int sum = first_number + second_number;
	//std::cout << "Output: " << sum;
	//return 0;	
}						