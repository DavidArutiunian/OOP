package labs.lab2.url_parser;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class URLParserTest {
    @Test
    public void parseThrowsIfInputIsIncorrect() {
        final String input = "habr";
        final var parser = new URLParser(input);
        assertThrows(MalformedURLException.class, parser::parse);
    }

    @Test
    public void parseThrowsOnUnknownProtocol() {
        final String input = "ws://unknown.protocol.com";
        final var parser = new URLParser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void parseThrowsOnIncorrectUrl() {
        final String input = "http://";
        final var parser = new URLParser(input);
        assertThrows(URISyntaxException.class, parser::parse);
    }

    @Test
    public void parseThrowsIfNoProtocol() {
        final String input = "habr.com";
        final var parser = new URLParser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void parseThrowsIfPortIsMore65535() {
        final String input = "http://habr.com:70000";
        final var parser = new URLParser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void parseThrowsIfPortIsNegative() {
        final String input = "http://habr.com:-2/test.pdf";
        final var parser = new URLParser(input);
        assertThrows(MalformedURLException.class, parser::parse);
    }

    @Test
    public void parseWorksCorrectlyMediumSize() throws IOException, URISyntaxException {
        final String input = "http://www.mysite.com/docs/document1.html?page=30&lang=en#title";
        final var parser = new URLParser(input);
        final String expected = "http://www.mysite.com/docs/document1.html?page=30&lang=en#title\n" +
            "HOST: www.mysite.com\n" +
            "PORT: 80\n" +
            "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void parseWorksCorrectlyLongSize() throws IOException, URISyntaxException {
        final String input = "https://raw.githubusercontent.com/IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML";
        final var parser = new URLParser(input);
        final String expected = "https://raw.githubusercontent.com/IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML\n" +
            "HOST: raw.githubusercontent.com\n" +
            "PORT: 443\n" +
            "DOC: IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void parseWorksCorrectlyShortSize() throws IOException, URISyntaxException {
        final String input = "https://habr.com";
        final var parser = new URLParser(input);
        final String expected = "https://habr.com\n" +
            "HOST: habr.com\n" +
            "PORT: 443";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void parseWorksCorrectlyWithQueries() throws IOException, URISyntaxException {
        final String input = "ftp://www.mysite.com/docs/document1.html?page=30&lang=en#title";
        final var parser = new URLParser(input);
        final String expected = "ftp://www.mysite.com/docs/document1.html?page=30&lang=en#title\n" +
            "HOST: www.mysite.com\n" +
            "PORT: 21\n" +
            "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void parseWorksCorrectlyWithQueriesAndCustomPort() throws IOException, URISyntaxException {
        final String input = "ftp://www.mysite.com:1337/docs/document1.html?page=30&lang=en#title";
        final var parser = new URLParser(input);
        final String expected = "ftp://www.mysite.com:1337/docs/document1.html?page=30&lang=en#title\n" +
            "HOST: www.mysite.com\n" +
            "PORT: 1337\n" +
            "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }
}
