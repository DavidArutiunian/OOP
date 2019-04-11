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
            final var store = createDictionaryStore(args[0]);
            final var dictionary = store.load();
            final var controller = new InteractionController(dictionary, store);
            final var loop = new EventLoop(controller);
            loop.run();
        } catch (Exception e) {
            exception(e);
        }
    }

    private static DictionaryStore createDictionaryStore(@Nullable String filename) throws IOException {
        DictionaryStore store;
        if (filename != null) {
            store = new DictionaryStore(filename);
        } else {
            store = new DictionaryStore();
        }
        return store;
    }

    private static void exception(Exception e) {
        if (e.getMessage() == null) {
            System.err.println("Программная ошибка!");
        } else {
            System.err.println(e.getMessage());
        }
    }
}
