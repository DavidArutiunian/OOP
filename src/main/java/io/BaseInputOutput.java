package io;

import java.io.File;
import java.io.IOException;

public class BaseInputOutput {
    protected BaseInputOutput() {
    }

    public static void validate(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IOException("Empty args!");
        }
    }

    public static void validate(final File file) throws IOException {
        if (!file.exists() || !file.canRead()) {
            throw new IOException("Cannot read file \"" + file.getPath() + "\"!");
        }
    }
}
