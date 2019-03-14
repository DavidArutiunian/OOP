package labs.lab2.mini_dictionary;

import java.io.IOException;

public interface EventLoopDelegate {
    void onEmptyInput();

    boolean onFinishWord() throws IOException;

    void onInputWord(String word);

    void onExit();
}
