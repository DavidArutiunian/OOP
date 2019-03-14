package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class InteractionController implements EventLoopDelegate {
    private static final String SAVE_DICT_ACCEPT_CAPITAL = "Y";
    private static final String SAVE_DICT_ACCEPT_LOW = "y";

    private final Dictionary dictionary;
    private final ISavableDictionary store;

    InteractionController(Dictionary dictionary, ISavableDictionary store) {
        this.dictionary = dictionary;
        this.store = store;
    }

    @Override
    public void onExit() {
        System.out.println("Изменения сохранены. До свидания.");
    }

    @Override
    public void onEmptyInput() {
        System.out.println("Пусто. Введите заново.");
    }

    @Override
    public void onInputWord(String word) {
        if (dictionary.contains(word)) {
            System.out.println(dictionary.get(word));
            return;
        }
        System.out.println("Неизвестное слово \"" + word + "\". Введите перевод или пустую строку для отказа.");
        final var scanner = new Scanner(System.in);
        final String translation = scanner.nextLine();
        if (translation.isEmpty()) {
            System.out.println("Слово \"" + word + "\" проигнорировано.");
        } else if (translation.contains(",")) {
            final String[] translations = translation.split(",");
            for (final String translatedWord : translations) {
                dictionary.add(word, translatedWord.trim());
            }
            System.out.println("Слово(а) \"" + translation + "\" добавлено(ы) в словарь.");
        } else {
            dictionary.add(word, translation);
            System.out.println("Слово(а) \"" + translation + "\" добавлено(ы) в словарь.");
        }
    }

    @Override
    public boolean onFinishWord() throws IOException {
        System.out.println("В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.");
        final Scanner input = new Scanner(System.in);
        final String word = input.nextLine();
        if (Objects.equals(word, SAVE_DICT_ACCEPT_CAPITAL) || Objects.equals(word, SAVE_DICT_ACCEPT_LOW)) {
            store.save(dictionary);
            return true;
        } else {
            System.out.println("Изменения не сохранены.");
            return false;
        }
    }
}
