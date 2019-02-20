/*
Вариант №2 – invert – 80 баллов
Разработайте приложение invert.exe, выполняющее инвертирование матрицы 3*3, т.е. нахождение обратной матрицы и выводящее коэффициенты результирующей матрицы в стандартный поток вывода.
Формат командной строки приложения:
    invert.exe <matrix file1>
Коэффициенты входной матрицы заданы во входном текстовом файле (смотрите файл matrix.txt в качестве иллюстрации)  в трех строках по 3 элемента.
Коэффициенты результирующей матрицы выводятся с точностью до 3 знаков после запятой.
Используйте двухмерные массивы для хранения коэффициентов матриц.
В комплекте с программой должны обязательно поставляться файлы, позволяющие проверить ее работу в автоматическом режиме.
*/

package labs.lab1.invert;

import java.io.File;

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
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
