/*
Вариант 1 – парсер URL-ов – 80 баллов
Разработайте функцию
bool ParseURL(string const& url, Protocol &  protocol, int & port, string & host, string & document),
выполняющую разбор строки url, и извлечение из нее информации об используемом протоколе, номере порта, имени хоста и имени документа.
Валидным (допустимым) URL-ом программа должна считать строку в следующем формате:
протокол://хост[:порт][/документ], где
протокол – http, https или ftp (в любом регистре),
порт – положительное число от 1 до 65535
хост – непустая строка
(в квадратных скобках указаны опциональные элементы URL-а)
Если порт не указан, то он должен считаться равным номеру порта по умолчанию для данного протокола (для HTTP – это 80, для HTTPS – 443, для FTP – 21).
Разработайте на ее основе программу, распознающую допустимые URL-строки в каждой вводимой пользователем строке ввода и выводящую в стандартный поток вывода информацию о каждом URL-е в следующем формате:
<Исходный URL>
HOST: <хост>
PORT: <порт>
DOC: <документ>
*/

package labs.lab2.url_parser;

import lib.io.BaseInputOutput;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            final var scanner = new Scanner(System.in);
            final var input = BaseInputOutput.read(scanner);
            final var parser = new URLParser(input);
            System.out.println(parser.parse().print());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
