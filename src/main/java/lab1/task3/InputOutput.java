package lab1.task3;

import io.BaseInputOutput;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;

class InputOutput extends BaseInputOutput {
    public static int MATRIX_SIZE = 3;
    private static String DELIMITER = "\t";

    public InputOutput() {
        super();
    }

    static double[][] parse(final File file, final int size) throws IOException {
        var input = new FileInputStream(file);
        double[][] matrix;
        try (var reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            String[] array;
            matrix = new double[size][size];
            int i = 0;
            int j = 0;
            while ((line = reader.readLine()) != null) {
                array = line.split(DELIMITER);
                for (String num : array) {
                    if (!NumberUtils.isCreatable(num)) {
                        throw new IOException("Incorrect matrix!");
                    }
                    matrix[i][j] = Double.parseDouble(num);
                    j++;
                }
                i++;
                if (i != MATRIX_SIZE || j != MATRIX_SIZE) {
                    throw new IOException("Incorrect matrix size!");
                }
                j = 0;
            }
        }
        return matrix;
    }

    static void print(final double[][] matrix) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                System.out.printf("%.3f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
