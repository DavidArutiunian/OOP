package labs.lab2.mini_dictionary;

import lib.io.FileManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

class DictionaryStore implements ISavableDictionary {
    private static final String DELIMITER = "\t";
    private static final String EOLN = "\n";

    private final FileManager manager;
    private String filename = "dictionary.tsv";

    DictionaryStore() throws IOException {
        this.manager = new FileManager(filename);
        this.manager.create();
    }

    DictionaryStore(String filename) throws IOException {
        this.filename = filename;
        this.manager = new FileManager(filename);
        this.manager.create();
    }

    Dictionary load() throws IOException {
        final var dictionary = new Dictionary();
        try (final var input = new Scanner(manager.getFileInstance())) {
            while (input.hasNext()) {
                final String word = input.next();
                final String translation = input.next();
                dictionary.add(word, translation);
            }
        } catch (NoSuchElementException e) {
            throw new IOException("Словарь повреждён!");
        }
        return dictionary;
    }

    @Override
    public void save(Dictionary dictionary) throws IOException {
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
