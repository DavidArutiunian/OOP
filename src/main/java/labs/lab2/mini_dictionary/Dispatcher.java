package labs.lab2.mini_dictionary;

import java.io.IOException;

interface Dispatcher {
    void dispatch(final InteractionEvents event) throws IOException;

    void dispatch(final InteractionEvents event, final Runnable callback);

    void dispatch(final InteractionEvents event, final String input);
}
