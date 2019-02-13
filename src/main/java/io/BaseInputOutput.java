package io;

import java.io.File;
import java.io.FileNotFoundException;

public class BaseInputOutput {
    protected BaseInputOutput() {
    }

    public static void validate(final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty args!");
        }
    }

    public static void validate(final String[] args, final int size) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty args!");
        }
        if (args.length < size) {
            throw new IllegalArgumentException("Some args missing!");
        }
    }

    public static void validate(final File file) throws FileNotFoundException {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("Cannot read file \"" + file.getPath() + "\"!");
        }
    }
}
