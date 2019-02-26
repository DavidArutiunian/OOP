package labs.lab2.mini_dictionary;

import java.io.IOException;

class DispatcherImpl implements Dispatcher {
    private final InteractionObserver observer;

    DispatcherImpl(InteractionObserver observer) {
        this.observer = observer;
    }

    @Override
    public void dispatch(final InteractionEvents event) throws IOException {
        if (event == InteractionEvents.EMPTY_INPUT) {
            observer.onEmptyInput();
        } else if (event == InteractionEvents.SAVE_DICT) {
            observer.onSaveDict();
        }
    }

    @Override
    public void dispatch(final InteractionEvents event, final Runnable callback) {
        if (event == InteractionEvents.EXIT) {
            observer.onExit(callback);
        }
    }

    @Override
    public void dispatch(final InteractionEvents event, final String input) {
        if (event == InteractionEvents.PRINT_WORD) {
            observer.onPrintWord(input);
        } else if (event == InteractionEvents.UNKNOWN_WORD) {
            observer.onUnknownWord(input);
        }
    }
}
