package lab1.task1;

import java.io.*;
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
                e.printStackTrace();
            }
        });
    }

    private void copyfile(File input, File output) throws IOException {
        try (
            InputStream inputFile = new FileInputStream(input);
            OutputStream outputFile = new FileOutputStream(output, true)
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputFile.read(buffer)) > 0) {
                outputFile.write(buffer, 0, len);
            }
        }
    }
}
