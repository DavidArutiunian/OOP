package labs.lab2.mini_dictionary;

import java.io.IOException;

interface EventLoop {
    void run(final Dictionary dictionary) throws IOException;

    void stop();
}
