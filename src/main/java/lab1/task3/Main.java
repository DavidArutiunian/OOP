package lab1.task3;

import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        try {
            InputOutput.validate(args);
            var file = new File(args[0]);
            InputOutput.validate(file);
            double[][] input = InputOutput.parse(file, InputOutput.MATRIX_SIZE);
            var matrix = new Matrix(input);
            var inverse = MatrixMathematics.inverse(matrix);
            InputOutput.print(inverse.getData());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
