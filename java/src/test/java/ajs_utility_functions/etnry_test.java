package ajs_utility_functions;

import ajs_utility_functions.main_again.core_test;

import java.util.Arrays;

import static java.lang.System.out;

public class etnry_test {
    public static void main(String[] args) {

        out.println("Hello, World!");
        core_test.example("trying to call example...");
        out.println("Called example, now printing string args: " + Arrays.toString(args));

    }
}
