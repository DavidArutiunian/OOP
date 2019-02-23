/*
Вариант 5 – HTML Decode – 60 баллов
Разработайте функцию
    std::string HtmlDecode(std::string const& html),
выполняющую декодирование HTML-сущностей строки html, перечисленных в варианте 3, обратно в их символьное представление.
Разработайте на ее основе программу, выполняющую декодирование html-сущностей текста, поступающего со стандартного потока ввода, и выводящую результат в стандартный поток вывода.
Внимание, реализация данной функции должна иметь сложность близкую к O(N)
*/

package labs.lab2.html_decode;

import lib.io.BaseInputOutput;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            final var htmlEntityMap = new HtmlEntityMapImpl();
            final Scanner in = new Scanner(System.in);
            final String input = BaseInputOutput.read(in);
            final var decoder = new HtmlDecoder(input);
            final String output = decoder.decode(htmlEntityMap);
            BaseInputOutput.print(output);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
