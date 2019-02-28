package labs.lab2.mini_dictionary;

import java.io.IOException;

public interface EventLoopDelegate {
    void onEmptyInput(final String word);

    void onFinishWord(final String word, final Runnable callback) throws IOException;

    void onInputWord(final String word);
}
