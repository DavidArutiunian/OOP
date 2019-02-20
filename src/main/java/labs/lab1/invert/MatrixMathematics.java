package labs.lab1.invert;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

class MatrixMathematics {
    private MatrixMathematics() {
    }

    static Matrix transpose(final Matrix matrix) {
        var transposed = new Matrix(matrix.getCols(), matrix.getRows());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                transposed.setValueAt(j, i, matrix.getValueAt(i, j));
            }
        }
        return transposed;
    }

    static Matrix inverse(final Matrix matrix) {
        return (transpose(cofactor(matrix)).multiply(1.0 / determinant(matrix)));
    }

    static double determinant(final Matrix matrix) {
        if (!matrix.isSquare())
            throw new ArithmeticException("Matrix must be square!");
        if (matrix.size() == 1) {
            return matrix.getValueAt(0, 0);
        }
        if (matrix.size() == 2) {
            return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
        }
        double sum = 0.0;
        for (int i = 0; i < matrix.getCols(); i++) {
            sum += inverse(i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, new ImmutablePair<>(0, i)));
        }
        return sum;
    }

    private static int inverse(final int i) {
        if (i % 2 == 0) {
            return 1;
        }
        return -1;
    }

    static Matrix createSubMatrix(final Matrix matrix, final Pair<Integer, Integer> exclude) {
        var mat = new Matrix(matrix.getRows() - 1, matrix.getCols() - 1);
        int r = -1;
        for (int i = 0; i < matrix.getRows(); i++) {
            if (i == exclude.getLeft()) {
                continue;
            }
            r++;
            int c = -1;
            for (int j = 0; j < matrix.getCols(); j++) {
                if (j == exclude.getRight()) {
                    continue;
                }
                mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
            }
        }
        return mat;
    }

    static Matrix cofactor(final Matrix matrix) {
        var mat = new Matrix(matrix.getRows(), matrix.getCols());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                mat.setValueAt(i, j, inverse(i) * inverse(j) * determinant(createSubMatrix(matrix, new ImmutablePair<>(i, j))));
            }
        }
        return mat;
    }
}
