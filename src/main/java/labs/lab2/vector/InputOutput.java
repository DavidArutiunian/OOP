package labs.lab2.vector;

import lib.io.BaseInputOutput;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InputOutput extends BaseInputOutput {
    private InputOutput() {
        super();
    }

    static List<Float> parse(final String[] args) throws IOException {
        List<Float> parsed = new ArrayList<>();
        for (String arg : args) {
            if (!NumberUtils.isCreatable(arg)) {
                throw new IOException("Cannot parse \"" + arg + "\"!");
            }
            parsed.add(Float.parseFloat(arg));
        }
        Collections.sort(parsed);
        return parsed;
    }
}
