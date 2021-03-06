package labs.lab1.invert;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatrixMathematicsTest {
    private final static double EPS = 1e-3;

    @Test
    public void transposeOnTwoByTwoMatrix() {
        var matrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        var transposed = MatrixMathematics.transpose(matrix);
        assertEquals("1.03.02.04.0", transposed.getValueAt(0, 0) + "" + transposed.getValueAt(0, 1) + "" + transposed.getValueAt(1, 0) + "" + transposed.getValueAt(1, 1));
    }

    @Test
    public void transposeOnOneByTwoMatrix() {
        var matrix = new Matrix(new double[][]{{1, 2}});
        assertEquals(2, matrix.getCols());
        assertEquals(1, matrix.getRows());
        var transposed = MatrixMathematics.transpose(matrix);
        assertEquals("1.02.0", transposed.getValueAt(0, 0) + "" + transposed.getValueAt(1, 0));
        assertEquals(1, transposed.getCols());
        assertEquals(2, transposed.getRows());
    }

    @Test
    public void createsSubMatrix() {
        var matrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        var sub = MatrixMathematics.createSubMatrix(matrix, new ImmutablePair<>(0, 0));
        assertEquals("5.06.08.09.0", sub.getValueAt(0, 0) + "" + sub.getValueAt(0, 1) + "" + sub.getValueAt(1, 0) + "" + sub.getValueAt(1, 1));
        sub = MatrixMathematics.createSubMatrix(matrix, new ImmutablePair<>(0, 1));
        assertEquals("4.06.07.09.0", sub.getValueAt(0, 0) + "" + sub.getValueAt(0, 1) + "" + sub.getValueAt(1, 0) + "" + sub.getValueAt(1, 1));
        sub = MatrixMathematics.createSubMatrix(matrix, new ImmutablePair<>(1, 1));
        assertEquals("1.03.07.09.0", sub.getValueAt(0, 0) + "" + sub.getValueAt(0, 1) + "" + sub.getValueAt(1, 0) + "" + sub.getValueAt(1, 1));
    }

    @Test
    public void determinantOnOneByOneMatrix() {
        var matrix = new Matrix(new double[][]{{2}});
        double determinant = MatrixMathematics.determinant(matrix);
        assertTrue(Math.abs(determinant - 2) < EPS);
    }

    @Test
    public void determinantOnThreeByThreeMatrix() {
        var matrix = new Matrix(new double[][]{{-2, 2, 3}, {-1, 1, 3}, {2, 0, -1}});
        double determinant = MatrixMathematics.determinant(matrix);
        assertTrue(Math.abs(determinant - 6) < EPS);
    }

    @Test
    public void determinantOnFourByFourMatrix() {
        var matrix = new Matrix(new double[][]{{3, 2, 0, 1}, {4, 0, 1, 2}, {3, 0, 2, 1}, {9, 2, 3, 1}});
        double determinant = MatrixMathematics.determinant(matrix);
        assertTrue(Math.abs(determinant - 24) < EPS);
    }

    @Test
    public void cofactorOnThreeByThreeMatrix() {
        var matrix = new Matrix(new double[][]{{1, 2, 3}, {0, 4, 5}, {1, 0, 6}});
        var cofactor = MatrixMathematics.cofactor(matrix);
        assertTrue(Math.abs(24 - cofactor.getValueAt(0, 0)) < EPS);
        assertTrue(Math.abs(5 - cofactor.getValueAt(0, 1)) < EPS);
        assertTrue(Math.abs(-4 - cofactor.getValueAt(0, 2)) < EPS);
        assertTrue(Math.abs(-12 - cofactor.getValueAt(1, 0)) < EPS);
        assertTrue(Math.abs(3 - cofactor.getValueAt(1, 1)) < EPS);
        assertTrue(Math.abs(2 - cofactor.getValueAt(1, 2)) < EPS);
        assertTrue(Math.abs(-2 - cofactor.getValueAt(2, 0)) < EPS);
        assertTrue(Math.abs(-5 - cofactor.getValueAt(2, 1)) < EPS);
        assertTrue(Math.abs(4 - cofactor.getValueAt(2, 2)) < EPS);

    }

    @Test
    public void inverseOnThreeByThreeMatrix() {
        var matrix = new Matrix(new double[][]{{1, 2, 3}, {0, 4, 5}, {1, 0, 6}});
        var inverse = MatrixMathematics.inverse(matrix);
        assertTrue(Math.abs(12.0 / 11.0 - inverse.getValueAt(0, 0)) < EPS);
        assertTrue(Math.abs(-6.0 / 11.0 - inverse.getValueAt(0, 1)) < EPS);
        assertTrue(Math.abs(-1.0 / 11.0 - inverse.getValueAt(0, 2)) < EPS);
        assertTrue(Math.abs(5.0 / 22.0 - inverse.getValueAt(1, 0)) < EPS);
        assertTrue(Math.abs(3.0 / 22.0 - inverse.getValueAt(1, 1)) < EPS);
        assertTrue(Math.abs(-5.0 / 22.0 - inverse.getValueAt(1, 2)) < EPS);
        assertTrue(Math.abs(-2.0 / 11.0 - inverse.getValueAt(2, 0)) < EPS);
        assertTrue(Math.abs(1.0 / 11.0 - inverse.getValueAt(2, 1)) < EPS);
        assertTrue(Math.abs(2.0 / 11.0 - inverse.getValueAt(2, 2)) < EPS);

    }

    @Test
    public void inverseOnThreeByThreeMatrixWithNegatives() {
        var matrix = new Matrix(new double[][]{{3, 9, 2}, {0, 0, 0}, {-4, -5, 1}});
        var inverse = MatrixMathematics.inverse(matrix);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(isNaN(inverse.getValueAt(i, j)));
            }
        }
    }

    @Test
    public void inverseOnThreeByThreeMatrixWithNegativesAndFloats() {
        var matrix = new Matrix(new double[][]{{3, 4, 8}, {2.4, -1, 11}, {7, -3.2, 0}});
        var inverse = MatrixMathematics.inverse(matrix);
        assertTrue(0.086 - inverse.getValueAt(0, 0) < EPS);
        assertTrue(-0.063 + inverse.getValueAt(0, 1) < EPS);
        assertTrue(0.127 - inverse.getValueAt(0, 2) < EPS);
        assertTrue(0.189 - inverse.getValueAt(1, 0) < EPS);
        assertTrue(-0.137 + inverse.getValueAt(1, 1) < EPS);
        assertTrue(-0.034 + inverse.getValueAt(1, 2) < EPS);
        assertTrue(-0.002 + inverse.getValueAt(2, 0) < EPS);
        assertTrue(0.092 - inverse.getValueAt(2, 1) < EPS);
        assertTrue(-0.031 + inverse.getValueAt(2, 2) < EPS);
    }

    @Test
    public void inverseThrowsOnEmptyMatrix() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(new double[][]{}));
    }

    private boolean isNaN(double value) {
        return Double.isNaN(value) || Double.isInfinite(value);
    }
}
