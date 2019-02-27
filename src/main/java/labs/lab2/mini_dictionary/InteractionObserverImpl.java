package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class InteractionObserverImpl implements InteractionObserver {
    private final FileDictionaryProvider provider;

    InteractionObserverImpl(final FileDictionaryProvider provider) {
        this.provider = provider;
    }

    @Override
    public void onExit(final Runnable callback) {
        callback.run();
        System.out.println("Изменения сохранены. До свидания.");
    }

    @Override
    public void onEmptyInput() {
        System.out.println("Пусто. Введите заново.");
    }

    @Override
    public void onUnknownWord(final String word) {
        System.out.println("Неизвестое слово \"" + word + "\". Введите перевод или пустую строку для отказа.");
        final var scanner = new Scanner(System.in);
        final String translation = scanner.nextLine();
        if (translation.isEmpty()) {
            System.out.println("Слово \"" + word + "\" проигорировано.");
        } else if (translation.contains(",")) {
            final String[] splitted = translation.split(",");
            for (final String string : splitted) {
                provider.getDictionary().add(word, string.trim());
            }
            System.out.println("Слово(а) \"" + translation + "\" добавлено(ы) в словарь.");
        } else {
            provider.getDictionary().add(word, translation);
            System.out.println("Слово(а) \"" + translation + "\" добавлено(ы) в словарь.");
        }
    }

    @Override
    public void onSaveDict() throws IOException {
        System.out.println("В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.");
        Scanner inputY = new Scanner(System.in);
        String inY = inputY.nextLine();
        if (Objects.equals(inY, "Y") || Objects.equals(inY, "y")) {
            provider.save();
        }
    }

    @Override
    public void onPrintWord(final String word) {
        System.out.println(provider.getDictionary().get(word));
    }
}
