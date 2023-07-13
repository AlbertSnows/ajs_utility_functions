package org.ajs_utility_functions;
import org.ajs_utility_functions.main_again.core;

import java.util.Arrays;

import static java.lang.System.out;

public class entry {
    public static void main(String[] args) {

        out.println("Hello, World!");
        core.example("trying to call example...");
        out.println("Called example, now printing string args: " + Arrays.toString(args));

    }
}
