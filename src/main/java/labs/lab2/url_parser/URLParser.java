package labs.lab2.url_parser;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

class URLParser {
    private static final int MAX_PORT = 65535;

    private final String text;

    private String host;
    private int port;
    @Nullable
    private String doc;
    @Nullable
    private String hash;

    URLParser(final String text) {
        this.text = text;
    }

    URLParser parse() throws IOException, URISyntaxException {
        final var url = new URL(text.trim());
        final var uri = new URI(text.trim());
        host = url.getHost();
        port = getPortIfNotFound(url.getProtocol(), url.getPort());
        doc = url.getFile().replaceFirst("/", "");
        hash = uri.getFragment();
        return this;
    }

    String print() {
        return text + '\n' +
            "HOST: " + host + '\n' +
            "PORT: " + port +
            printIfNotNull("\nDOC: ", doc, printIfNotNull("#", hash));
    }

    private int getPortIfNotFound(String protocol, int port) throws IOException {
        if (port != -1) {
            if (port > MAX_PORT) {
                throw new IOException("Unsupported port \"" + port + "\"");
            }
            return port;
        }
        if (Protocol.HTTP.is(protocol)) {
            return Protocol.HTTP.getPort();
        } else if (Protocol.HTTPS.is(protocol)) {
            return Protocol.HTTPS.getPort();
        } else if (Protocol.FTP.is(protocol)) {
            return Protocol.FTP.getPort();
        } else {
            throw new IOException("Unknown protocol \"" + protocol + "\"");
        }
    }

    private String printIfNotNull(String prefix, String string, String postfix) {
        if (string != null && !string.isEmpty()) {
            return prefix + string + postfix;
        } else {
            return "";
        }
    }

    private String printIfNotNull(String prefix, String string) {
        if (string != null && !string.isEmpty()) {
            return prefix + string;
        } else {
            return "";
        }
    }
}
