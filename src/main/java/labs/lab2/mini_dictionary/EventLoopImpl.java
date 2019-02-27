package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class EventLoopImpl implements EventLoop {
    private static final String TERMINAL_STRING = "...";

    private final Scanner input;
    private final Dispatcher dispatcher;
    private boolean stop = false;

    EventLoopImpl(final Scanner input, final Dispatcher dispatcher) {
        this.input = input;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run(final Dictionary dictionary) throws IOException {
        while (!stop) {
            final String word = input.nextLine().toLowerCase();
            if (word.isEmpty()) {
                dispatcher.dispatch(InteractionEvents.EMPTY_INPUT);
            } else if (Objects.equals(word, TERMINAL_STRING)) {
                dispatcher.dispatch(InteractionEvents.SAVE_DICT);
                dispatcher.dispatch(InteractionEvents.EXIT, this::stop);
            } else if (dictionary.contains(word)) {
                dispatcher.dispatch(InteractionEvents.PRINT_WORD, word);
            } else {
                dispatcher.dispatch(InteractionEvents.UNKNOWN_WORD, word);
            }
        }
    }

    @Override
    public void stop() {
        stop = true;
    }
}
