package labs.lab2.mini_dictionary;

import java.io.IOException;

interface InteractionObserver {
    void onExit(final Runnable callback);

    void onEmptyInput();

    void onUnknownWord(final String word);

    void onSaveDict() throws IOException;

    void onPrintWord(final String word);
}
