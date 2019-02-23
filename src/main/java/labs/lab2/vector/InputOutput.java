package labs.lab2.vector;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class InputOutput {
    private static char SPACE = ' ';

    static List<Float> parse(final String[] args) throws IOException {
        List<Float> parsed = new ArrayList<>();
        for (String arg : args) {
            if (!NumberUtils.isCreatable(arg)) {
                throw new IOException("Cannot parse \"" + arg + "\"!");
            }
            parsed.add(Float.parseFloat(arg));
        }
        Collections.sort(parsed);
        return parsed;
    }

    static String[] read(final Scanner in) throws IOException {
        final String input = in.nextLine();
        if (input == null) {
            throw new IOException("Empty input!");
        }
        if (input.length() == 0) {
            throw new IOException("Empty input!");
        }
        return input.split(" ");
    }

    static void print(final List<Float> items) {
        items.forEach(item -> System.out.printf("%.3f%c", item, SPACE));
        System.out.println();
    }
}
