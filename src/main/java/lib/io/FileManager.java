package lib.io;

import java.io.File;
import java.io.IOException;

public interface FileManager {
    File getFileInstance() throws IOException;

    void create() throws IOException;

    FileManager write(String string) throws IOException;

    String read() throws IOException;
}
