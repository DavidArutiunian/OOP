package labs.lab2.url_parser;

import java.io.IOException;
import java.net.URISyntaxException;

public interface URLParser {
    URLParser parse() throws IOException, URISyntaxException;

    String print() throws IOException;
}
