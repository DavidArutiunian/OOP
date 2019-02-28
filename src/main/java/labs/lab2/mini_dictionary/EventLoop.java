package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Scanner;

class EventLoop {
    private final Scanner input;
    private final EventLoopDelegate delegate;
    private boolean stop = false;

    EventLoop(final Scanner input, EventLoopDelegate delegate) {
        this.input = input;
        this.delegate = delegate;
    }

    void run() throws IOException {
        while (!stop) {
            final String word = input.nextLine();
            delegate.onEmptyInput(word);
            delegate.onFinishWord(word, this::stop);
            delegate.onInputWord(word);
        }
    }

    private void stop() {
        stop = true;
    }
}
