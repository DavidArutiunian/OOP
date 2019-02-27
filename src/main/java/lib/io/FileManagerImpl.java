package lib.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FileManagerImpl implements FileManager {
    private final File file;

    public FileManagerImpl(String pathToFile) throws IOException {
        this.file = new File(pathToFile);
        getFileInstance();
    }

    public FileManagerImpl() {
        final var uuid = UUID.randomUUID();
        this.file = new File(uuid.toString());
    }

    @Override
    public File getFileInstance() throws IOException {
        create();
        return file;
    }

    @Override
    public void create() throws IOException {
        if (!file.exists()) {
            final boolean isCreated = file.createNewFile();
            if (!isCreated) {
                throw new IOException("Cannot create input file!");
            }
        }
    }

    @Override
    public FileManager write(String string) throws IOException {
        final List<String> lines = Collections.singletonList(string);
        Files.write(Path.of(file.getAbsolutePath()), lines, Charset.forName("UTF-8"));
        return this;
    }

    @Override
    public String read() throws IOException {
        return Files.readString(Path.of(file.getAbsolutePath()));
    }
}
