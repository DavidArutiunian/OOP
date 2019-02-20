package lab1.task1;

import io.BaseInputOutput;
import io.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            BaseInputOutput.validate(args);
            var length = args.length - 1; // the last is output
            List<File> files = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                files.add(new FileManager(args[i]).getFileInstance());
            }
            final var output = new FileManager(args[args.length - 1]);
            final var join = new Join(files, output.getFileInstance());
            join.process();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
