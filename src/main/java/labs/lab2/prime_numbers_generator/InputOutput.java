package labs.lab2.prime_numbers_generator;

import lib.io.BaseInputOutput;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

class InputOutput extends BaseInputOutput {
    private static final int MAX_UPPER_BOUND = 100_000_000;
    private static final int MIN_UPPER_BOUND = 0;

    static int parse(final String input) throws IOException {
        if (!NumberUtils.isCreatable(input)) {
            throw new IOException("Cannot parse \"" + input + "\" to int!");
        }
        return Integer.parseInt(input);
    }

    static void validate(final int upperBound) throws IOException {
        if (upperBound > MAX_UPPER_BOUND) {
            throw new IOException("\"" + upperBound + "\" exceeds max bound!");
        } else if (upperBound < MIN_UPPER_BOUND) {
            throw new IOException("\"" + upperBound + "\" exceeds min bound!");
        }
    }
}
