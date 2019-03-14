package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Scanner;

class EventLoop {
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
            delegate.onEmptyInput(word);
            delegate.onFinishWord(word, this::stop);
            delegate.onInputWord(word);
        }
    }

    private void stop() {
        running = true;
    }
}
