package lab1.task1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided!");
            return;
        }
        var length = args.length - 1; // the last is output
        List<File> files = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            try {
                files.add(new FileManager(args[i]).create());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        final var output = new FileManager(args[args.length - 1]);
        try {
            final var join = new Join(files, output.create());
            join.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
