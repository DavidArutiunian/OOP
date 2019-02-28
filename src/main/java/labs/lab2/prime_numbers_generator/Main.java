/*
Вариант 4 – Генератор простых чисел – 100 баллов
Разработайте функцию std::set<int> GeneratePrimeNumbersSet(int upperBound), возвращающую множество всех простых чисел, не превышающих значения upperBound.
С ее использованием разработайте программу, выводящую в стандартный поток вывода элементы множества простых чисел, не превышающие значения, переданного приложению через обязательный параметр командной строки.
Максимальное значение верхней границы множества принять равным 100 миллионам.
Время построения множества простых чисел в указанном диапазоне на компьютере с 2GHz-процессором не должно превышать 10-12 секунд (программа будет запускаться в Release-конфигурации).
Примечание: наивный поиск простых чисел не позволит добиться указанной производительности. Используйте «Решето Эратосфена». Для предварительного просеивания воспользуйтесь vector<bool> (для хранения каждого элемента он использует 1 бит информации, т.к. на хранение признака простоты 100 миллионов чисел потребуется всего 12,5 мегабайт памяти)
*/

package labs.lab2.prime_numbers_generator;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        try {
            InputOutput.validate(args, 1);
            final int upperBound = InputOutput.parse(args[0]);
            InputOutput.validate(upperBound);
            final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGenerator(upperBound);
            System.out.println(Arrays.toString(primeNumbersGenerator.sieve().primes()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
