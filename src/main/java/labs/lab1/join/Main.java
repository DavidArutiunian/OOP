/*
Вариант №5 – join – 50 баллов
Разработайте программу join.exe, выполняющую слияние содержимого нескольких входных бинарных файлов в выходной файл (отличный от входных).
Формат командной строки:
    join.exe <input file1> … <input file N> <output file>
Программа должна корректно обрабатывать ошибки, связанные с открытием входных и выходных файлов.
Размеры входных и выходных файлов таковы, что в оперативную память программы могут не поместиться целиком.
В комплекте с программой должны обязательно поставляться файлы, позволяющие проверить ее работу в автоматическом режиме.
*/

package labs.lab1.join;

import lib.io.BaseInputOutput;
import lib.io.FileManagerImpl;

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
                files.add(new FileManagerImpl(args[i]).getFileInstance());
            }
            final var output = new FileManagerImpl(args[args.length - 1]);
            final var join = new Join(files, output.getFileInstance());
            join.process();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
