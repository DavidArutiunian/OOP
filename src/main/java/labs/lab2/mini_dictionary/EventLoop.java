package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class EventLoop {
    @SuppressWarnings("FieldCanBeLocal")
    private static final String TERMINAL_STRING = "...";

    private final EventLoopDelegate delegate;

    EventLoop(EventLoopDelegate delegate) {
        this.delegate = delegate;
    }

    void run() throws IOException {
        while (true) {
            final Scanner scanner = new Scanner(System.in);
            final String word = scanner.nextLine();
            if (word.isEmpty()) {
                delegate.onEmptyInput();
            } else if (Objects.equals(word, TERMINAL_STRING)) {
                boolean saved = delegate.onFinishWord();
                if (saved) {
                    delegate.onExit();
                    return;
                }
            } else {
                delegate.onInputWord(word.toLowerCase().trim());
            }
        }
    }
}
