package labs.lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class EventLoopImpl implements EventLoop {
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
            final String in = input.nextLine();
            if (in.isEmpty()) {
                dispatcher.dispatch(InteractionEvents.EMPTY_INPUT);
            } else if (Objects.equals(in, "...")) {
                dispatcher.dispatch(InteractionEvents.SAVE_DICT);
                dispatcher.dispatch(InteractionEvents.EXIT, this::stop);
            } else if (dictionary.contains(in)) {
                dispatcher.dispatch(InteractionEvents.PRINT_WORD, in);
            } else {
                dispatcher.dispatch(InteractionEvents.UNKNOWN_WORD, in);
            }
        }
    }

    @Override
    public void stop() {
        stop = true;
    }
}
