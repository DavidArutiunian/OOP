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
    private String pathToFile = "dictionary.tsv";

    FileDictionaryProvider(final Dictionary dictionary) throws IOException {
        this.dictionary = dictionary;
        this.manager = new FileManager(pathToFile);
        this.manager.create();
    }

    FileDictionaryProvider(final Dictionary dictionary, final String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        this.dictionary = dictionary;
        this.manager = new FileManager(pathToFile);
        this.manager.create();
    }

    void load() throws IOException {
        try (final var input = new Scanner(manager.getFileInstance())) {
            while (input.hasNext()) {
                final String word = input.next();
                final String translation = input.next();
                dictionary.add(word, translation);
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
            dictionary.traverse((word, translations) -> {
                try {
                    for (final String translation : translations) {
                        writer.write(word);
                        writer.write(DELIMITER);
                        writer.write(translation);
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
