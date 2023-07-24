#pragma once
std::variant<int, std::string> fizzBuzzLogic(int number);
std::vector<std::variant<int, std::string>> wrapper(std::vector<std::variant<int, std::string>>& s, auto number);
void fizzBuzz(int n);