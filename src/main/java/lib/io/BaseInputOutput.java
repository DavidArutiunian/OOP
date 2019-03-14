package lib.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BaseInputOutput {
    public static final String DELIMITER = " ";

    protected BaseInputOutput() {
    }

    public static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty args!");
        }
    }

    public static void validate(String[] args, int size) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty args!");
        }
        if (args.length < size) {
            throw new IllegalArgumentException("Some args missing!");
        }
    }

    public static void validate(File file) throws FileNotFoundException {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("Cannot read file \"" + file.getPath() + "\"!");
        }
    }

    public static String read(Scanner in) throws IOException {
        final String input = in.nextLine();
        if (input.length() == 0) {
            throw new IOException("Empty input!");
        }
        return input;
    }

    public static void print(List<Float> items) {
        items.forEach(item -> System.out.printf("%.3f%s", item, DELIMITER));
        System.out.println();
    }

    public static void print(String line) {
        System.out.println(line);
    }
}
