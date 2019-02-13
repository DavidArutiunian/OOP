package lab1.task3;

class Matrix {
    private int rows;
    private int cols;
    private double[][] data;

    Matrix(double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    Matrix(final int cols, final int rows) {
        this.rows = cols;
        this.cols = rows;
        data = new double[cols][rows];
    }

    int getRows() {
        return rows;
    }

    double[][] getData() {
        return data;
    }


    int getCols() {
        return cols;
    }

    void setValueAt(int row, int col, double value) {
        data[row][col] = value;
    }

    double getValueAt(int row, int col) {
        return data[row][col];
    }

    boolean isSquare() {
        return rows == cols;
    }

    int size() {
        if (isSquare()) {
            return rows;
        }
        return -1;
    }

    Matrix multiply(double constant) {
        var matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setValueAt(i, j, data[i][j] * constant);
            }
        }
        return matrix;
    }
}
