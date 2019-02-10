package lab1.task1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

class FileManager {
    private final File file;

    FileManager(String pathToFile) {
        this.file = new File(pathToFile);
    }

    File create() throws IOException {
        if (!file.exists()) {
            final boolean isCreated = file.createNewFile();
            if (!isCreated) {
                throw new IOException("Cannot create input file!");
            }
        }
        return file;
    }

    void write(String string) throws IOException {
        final List<String> lines = Collections.singletonList(string);
        final Path path = Path.of(file.getAbsolutePath());
        Files.write(path, lines, Charset.forName("UTF-8"));
    }

    String read() throws IOException {
        final Path path = Path.of(file.getAbsolutePath());
        return Files.readString(path);
    }
}
