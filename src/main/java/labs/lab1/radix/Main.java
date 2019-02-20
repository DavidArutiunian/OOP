/*
Вариант №6 – radix – 100 баллов
Разработайте программу radix.exe, выполняющую перевод чисел из одной произвольной системы счисления в другую произвольную и запись результата в стандартный поток вывода.
Под произвольной системой счисления понимается система с основанием от 2 до 36.
Системы счисления с 11-ричной до 36-ричной должны использовать заглавные буквы латинского алфавита от A до Z для представления разрядов с 1010 до 3510.
Формат командной строки приложения:
    radix.exe <source notation> <destination notation> <value>
Например, следующим способом программа должна осуществлять перевод шестнадцатеричного числа 1F в его десятичное представление:
    radix.exe 16 10 1F
В конце строки, выводимой в стандартный поток вывода должен располагаться код \n.
Программа должна быть способна осуществлять перевод как положительных, так и отрицательных чисел, а также нуля.
Особое внимание уделите переводу максимальных и минимальных целых чисел на данной платформе (они должны преобразовываться корректно).
Программа должна корректно обрабатывать ошибки
*/

package labs.lab1.radix;

import lib.io.BaseInputOutput;

public class Main {
    public static void main(String[] args) {
        try {
            BaseInputOutput.validate(args, 3);
            final int from = Integer.parseInt(args[0]);
            final int to = Integer.parseInt(args[1]);
            if (!Converter.isRadixValid(from) || !Converter.isRadixValid(to)) {
                throw new ArithmeticException("Invalid radix!");
            }
            final String value = args[2];
            if (!Converter.isValueValid(value, from)) {
                throw new ArithmeticException("Value do not match source radix!");
            }
            final Converter converter = new Converter(value, from);
            System.out.println(converter.convert(to));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
