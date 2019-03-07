package labs.lab2.url_parser;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class URLParserTest {
    @Test
    public void testParserThrows1() {
        final String input = "habr";
        final var parser = new URLParser(input);
        assertThrows(MalformedURLException.class, parser::parse);
    }

    @Test
    public void testParserThrows2() {
        final String input = "ws://unknown.protocol.com";
        final var parser = new URLParser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void testParserThrows3() {
        final String input = "http://";
        final var parser = new URLParser(input);
        assertThrows(URISyntaxException.class, parser::parse);
    }

    @Test
    public void testParserThrows4() {
        final String input = "habr.com";
        final var parser = new URLParser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void testParserThrows5() {
        final String input = "http://habr.com:70000";
        final var parser = new URLParser(input);
        assertThrows(IOException.class, parser::parse);
    }

    @Test
    public void testParserThrows6() {
        final String input = "http://habr.com:-2/test.pdf";
        final var parser = new URLParser(input);
        assertThrows(MalformedURLException.class, parser::parse);
    }

    @Test
    public void testParserWorks1() throws IOException, URISyntaxException {
        final String input = "http://www.mysite.com/docs/document1.html?page=30&lang=en#title";
        final var parser = new URLParser(input);
        final String expected = "http://www.mysite.com/docs/document1.html?page=30&lang=en#title\n" +
            "HOST: www.mysite.com\n" +
            "PORT: 80\n" +
            "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void testParserWorks2() throws IOException, URISyntaxException {
        final String input = "https://raw.githubusercontent.com/IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML";
        final var parser = new URLParser(input);
        final String expected = "https://raw.githubusercontent.com/IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML\n" +
            "HOST: raw.githubusercontent.com\n" +
            "PORT: 443\n" +
            "DOC: IntersectAustralia/acdata/master/sample_files/Panalytical.XRDML";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void testParserWorks3() throws IOException, URISyntaxException {
        final String input = "https://habr.com";
        final var parser = new URLParser(input);
        final String expected = "https://habr.com\n" +
            "HOST: habr.com\n" +
            "PORT: 443";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void testParserWorks4() throws IOException, URISyntaxException {
        final String input = "ftp://www.mysite.com/docs/document1.html?page=30&lang=en#title";
        final var parser = new URLParser(input);
        final String expected = "ftp://www.mysite.com/docs/document1.html?page=30&lang=en#title\n" +
            "HOST: www.mysite.com\n" +
            "PORT: 21\n" +
            "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }

    @Test
    public void testParserWorks5() throws IOException, URISyntaxException {
        final String input = "ftp://www.mysite.com:1337/docs/document1.html?page=30&lang=en#title";
        final var parser = new URLParser(input);
        final String expected = "ftp://www.mysite.com:1337/docs/document1.html?page=30&lang=en#title\n" +
            "HOST: www.mysite.com\n" +
            "PORT: 1337\n" +
            "DOC: docs/document1.html?page=30&lang=en#title";
        assertEquals(expected, parser.parse().print());
    }
}
