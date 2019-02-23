/*
Задание 1. Основы работы с контейнером vector. 20 баллов
Ознакомьтесь с возможностями класса vector библиотеки STL, а также с основными алгоритмами STL.
Разработайте программу, выполняющую считывание массива чисел с плавающей запятой, разделяемых пробелами, из стандартного потока ввода в vector, обрабатывающую его согласно заданию Вашего варианта и выводящую в стандартный поток полученный массив (разделенный пробелами). В программе должны быть выделены функции, выполняющие считывание массива, его обработку  и вывод результата.
Для тестирования разрабатываемых функций должны быть разработаны тесты, проверяющие корректность их работы на некотором разумном наборе входных параметров.
Вариант     Выводимое значение
    1           Прибавить к каждому элементу массива среднее арифметическое его положительных элементов
    2           Каждый элемент массива должен быть умножен на минимальный элемент исходного массива
    3           Умножить элементы массива, делящиеся на 3 без остатка, на среднее арифметическое элементов массива, делящихся на 2 без остатка
    4           Разделить элементы массива на половину максимального элемента
    5           Умножить каждый отрицательный элемент массива на произведение максимального и минимального элементов исходного массива
    6           Умножить каждый элемент массива на максимальный элемент исходного массива и разделить на минимальный элемент исходного массива
    7           Прибавить к каждому элементу массива сумму трех минимальных элементов массива
    8           Элементы, стоящие на четных позициях массива умножить на 2, а из элементов, стоящих на нечетных позициях, вычесть сумму всех неотрицательных элементов
Т.к. вещественные числа представляются в памяти лишь приблизительно, необходимо при подсчете суммы цифр числа принимать в расчет лишь 3 знака после запятой.
Бонус 10 баллов за сортировку элементов массива в порядке возрастания
Бонус начисляется за вывод элементов массива в порядке возрастания их значений. При этом функция ProcessVector не должна выполнять сортировку элементов.
*/

package labs.lab2.vector;

import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            final Scanner in = new Scanner(System.in);
            final String[] arguments = InputOutput.read(in);
            final List<Float> input = InputOutput.parse(arguments);
            final var pv = new ProcessVector(input);
            final List<Float> output = pv.call(pv.getAverage());
            InputOutput.print(output);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
