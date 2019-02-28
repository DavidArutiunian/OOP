package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class InteractionController {
    private static final String SAVE_DICT_ACCEPT_CAPITAL = "Y";
    private static final String SAVE_DICT_ACCEPT_LOW = "y";

    private final Dictionary dictionary;
    private final FileDictionaryProvider provider;

    InteractionController(Dictionary dictionary, final FileDictionaryProvider provider) {
        this.dictionary = dictionary;
        this.provider = provider;
    }

    void onExit(final Runnable callback) {
        callback.run();
        System.out.println("Изменения сохранены. До свидания.");
    }

    void onEmptyInput(final String word) {
        System.out.println("Пусто. Введите заново.");
    }

    void onUnknownWord(final String word) {
        if (dictionary.contains(word)) {
            return;
        }
        System.out.println("Неизвестное слово \"" + word + "\". Введите перевод или пустую строку для отказа.");
        final var scanner = new Scanner(System.in);
        final String translation = scanner.nextLine();
        if (translation.isEmpty()) {
            System.out.println("Слово \"" + word + "\" проигнорировано.");
        } else if (translation.contains(",")) {
            final String[] splitted = translation.split(",");
            for (final String string : splitted) {
                dictionary.add(word, string.trim());
            }
            System.out.println("Слово(а) \"" + translation + "\" добавлено(ы) в словарь.");
        } else {
            dictionary.add(word, translation);
            System.out.println("Слово(а) \"" + translation + "\" добавлено(ы) в словарь.");
        }
    }

    boolean onSaveDict() throws IOException {
        System.out.println("В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.");
        Scanner inputY = new Scanner(System.in);
        String inY = inputY.nextLine();
        if (Objects.equals(inY, SAVE_DICT_ACCEPT_CAPITAL) || Objects.equals(inY, SAVE_DICT_ACCEPT_LOW)) {
            provider.save();
            return true;
        } else {
            System.out.println("Изменения не сохранены.");
            return false;
        }
    }

    void onPrintWord(final String word) {
        if (!dictionary.contains(word)) {
            return;
        }
        System.out.println(dictionary.get(word));
    }
}
