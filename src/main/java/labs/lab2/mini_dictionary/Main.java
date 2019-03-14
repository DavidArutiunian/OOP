/*
Вариант 2 – мини-словарь – 90 баллов
Разработайте программу-словарь, осуществляющую перевод слов и словосочетаний, поступающих со стандартного потока ввода, с английского языка на русский с использованием заданного файла словаря и выводящую результат перевода в стандартный поток вывода.
Если вводимое слово или словосочетание, отсутствует в словаре, программа должна попросить пользователя ввести перевод и запомнить его, в случае, если пользователь ввел непустую строку.
Для выхода из диалога с программой пользователь должен ввести строку, состоящую из трех точек. Перед выходом программа спрашивает о необходимости сохранить изменения в файле словаря, в том случае, если в словарь были добавлены фразы во время текущей сессии работы с программой.
Имя файла словаря передается программе с помощью параметра командной строки. Если файл словаря отсутствует, то программа должна считать его пустым и предложить сохранить словарь по окончании работы, если туда были добавлены фразы.
*/

package labs.lab2.mini_dictionary;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

class Main {
    public static void main(String... args) {
        try {
            final var dictionary = new Dictionary();
            final var provider = createDictionaryStore(args[0]);
            provider.load(dictionary);
            final var controller = new InteractionController(dictionary, provider);
            final var loop = new EventLoop(controller);
            loop.run();
        } catch (Exception e) {
            exception(e);
        }
    }

    private static DictionaryStore createDictionaryStore(@Nullable String filename) throws IOException {
        DictionaryStore provider;
        if (filename != null) {
            provider = new DictionaryStore(filename);
        } else {
            provider = new DictionaryStore();
        }
        return provider;
    }

    private static void exception(Exception e) {
        if (e.getMessage() == null) {
            System.err.println("Программная ошибка!");
        } else {
            System.err.println(e.getMessage());
        }
    }
}
