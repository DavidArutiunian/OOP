package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class EventLoop {
    private static String TERMINAL_STRING = "...";

    private final Scanner input;
    private final EventLoopDelegate delegate;
    private boolean running = false;

    EventLoop(Scanner input, EventLoopDelegate delegate) {
        this.input = input;
        this.delegate = delegate;
    }

    void run() throws IOException {
        while (!running) {
            final String word = input.nextLine();
            if (word.isEmpty()) {
                delegate.onEmptyInput();
            } else if (Objects.equals(word, TERMINAL_STRING)) {
                boolean saved = delegate.onFinishWord();
                if (saved) {
                    delegate.onExit();
                    stop();
                }
            } else {
                delegate.onInputWord(word.toLowerCase().trim());
            }
        }
    }

    private void stop() {
        running = true;
    }
}
