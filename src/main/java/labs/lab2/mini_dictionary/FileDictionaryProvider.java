package labs.lab2.mini_dictionary;

import lib.io.FileManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

class FileDictionaryProvider {
    private static final String DELIMITER = "\t";
    private static final String EOLN = "\n";
    private final Dictionary dictionary;
    private final FileManager manager;
    private String pathToDictFile = "dictionary.tsv";

    FileDictionaryProvider(final Dictionary dictionary) throws IOException {
        this.dictionary = dictionary;
        this.manager = new FileManager(pathToDictFile);
        this.manager.create();
    }

    FileDictionaryProvider(final Dictionary dictionary, final String pathToDictFile) throws IOException {
        this.pathToDictFile = pathToDictFile;
        this.dictionary = dictionary;
        this.manager = new FileManager(pathToDictFile);
        this.manager.create();
    }

    void load() throws IOException {
        try (final var scanner = new Scanner(manager.getFileInstance())) {
            while (scanner.hasNext()) {
                final String key = scanner.next();
                final String value = scanner.next();
                dictionary.add(key, value);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Словарь повреждён!");
            System.exit(-1);
        }
    }

    void save() throws IOException {
        final File file = manager.getFileInstance();
        Files.delete(Path.of(file.getAbsolutePath()));
        try (final var writer = new FileWriter(file)) {
            dictionary.traverse((key, values) -> {
                try {
                    for (final String value : values) {
                        writer.write(key);
                        writer.write(DELIMITER);
                        writer.write(value);
                        writer.write(EOLN);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
            writer.flush();
        }
    }
}
