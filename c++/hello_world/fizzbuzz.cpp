#pragma once
using namespace std;
#include <iostream>
#include <vector>
#include <iterator>
#include <variant>
#include "fizzbuzz.h"

// Polyfill of something
template <typename T>
class something {
public:
    // Constructor to initialize the view with the start and end values
    explicit something(T start, T end) : start_(start), end_(end) {}

    // Custom iterator for the view
    class iterator {
    public:
        explicit iterator(T value) : value_(value) {}

        T operator*() const { return value_; }
        iterator& operator++() { ++value_; return *this; }
        bool operator!=(const iterator& other) const { return value_ != other.value_; }

    private:
        T value_;
    };

    iterator begin() const { return iterator(start_); }
    iterator end() const { return iterator(end_); }

private:
    T start_;
    T end_;
};

template <typename T, typename I, typename F>
I reduce(const something<T>& list, I initial_value, F func) {
    I result = initial_value;
    for (const auto& item : list) {
        result = func(result, item);
    }
    return result;
}

// FizzBuzz function to perform the FizzBuzz logic
 std::variant<int, std::string> fizzBuzzLogic(int number) {
    if (number % 3 == 0 && number % 5 == 0) {
        return "fizzbuzz";
    }
    else if (number % 3 == 0) {
        return "fizz";;
    }
    else if (number % 5 == 0) {
        return "buzz";
    }
    else {       
        return number;
    }
}

// Wrapper lambda function for fizzBuzzLogic
std::vector<std::variant<int, std::string>> wrapper (auto s, auto number) {
    return s.push_back(fizzBuzzLogic(number));
}

/*
 * Complete the 'fizzBuzz' function below.
 *
 * The function accepts INTEGER n as parameter.
 */

void fizzBuzz(int n) {
    //std::cout << "in fizzbuzz" << std::endl;
    auto vect(something<int>(1, n + 1));
    auto result = reduce(vect, std::vector<std::variant<int, std::string>>(), [](auto list, auto number) {
        auto answer_for_number = fizzBuzzLogic(number);
        list.push_back(answer_for_number);
        return                          list;
        });
    for (const auto& item : result) {
        if (std::holds_alternative<int>(item)) {
            std::cout << std::get<int>(item) << std::endl;
        }
        else if (std::holds_alternative<std::string>(item)) {
            std::cout << std::get<std::string>(item) << std::endl;
        }
    }
}
