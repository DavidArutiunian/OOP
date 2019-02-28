package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;

class EventLoopState implements EventLoopDelegate {
    private static final String TERMINAL_STRING = "...";

    private final InteractionController controller;

    EventLoopState(final InteractionController controller) {
        this.controller = controller;
    }

    @Override
    public void onEmptyInput(final String word) {
        if (!word.isEmpty()) {
            return;
        }
        controller.onEmptyInput();
    }

    @Override
    public void onFinishWord(final String word, final Runnable callback) throws IOException {
        if (!Objects.equals(word, TERMINAL_STRING)) {
            return;
        }
        boolean saved = controller.onSaveDict();
        if (!saved) {
            return;
        }
        controller.onExit(callback);
    }

    @Override
    public void onInputWord(final String word) {
        if (Objects.equals(word, TERMINAL_STRING)) {
            return;
        }
        final String normalizedWord = word.toLowerCase().trim();
        controller.onPrintWord(normalizedWord);
        if (word.isEmpty()) {
            return;
        }
        controller.onUnknownWord(normalizedWord);
    }
}
