package labs.lab1.join;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

class Join {
    private static final int BUFFER_SIZE = 1024;
    private final List<File> files;
    private final File output;

    Join(final List<File> files, final File output) {
        this.files = files;
        this.output = output;
    }

    void process() {
        files.forEach((File file) -> {
            try {
                copyfile(file, output);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }

    private void copyfile(File input, File output) throws IOException {
        try (
            var inputfile = new FileInputStream(input);
            var outputfile = new FileOutputStream(output, true)
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputfile.read(buffer)) > 0) {
                outputfile.write(buffer, 0, len);
            }
        }
    }
}
