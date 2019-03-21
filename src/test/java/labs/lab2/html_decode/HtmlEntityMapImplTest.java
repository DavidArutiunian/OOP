package labs.lab2.html_decode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlEntityMapImplTest {
    private final HtmlEntityMap htmlEntityMap = new HtmlEntityMapImpl();

    @Test
    public void getDecodedQuot() {
        final String expected = "\"";
        final String actual = htmlEntityMap.getDecodedQuot();
        assertEquals(expected, actual);
    }

    @Test
    public void getDecodedApos() {
        final String expected = "\'";
        final String actual = htmlEntityMap.getDecodedApos();
        assertEquals(expected, actual);
    }

    @Test
    public void getDecodedLt() {
        final String expected = "<";
        final String actual = htmlEntityMap.getDecodedLt();
        assertEquals(expected, actual);
    }

    @Test
    public void getDecodedGt() {
        final String expected = ">";
        final String actual = htmlEntityMap.getDecodedGt();
        assertEquals(expected, actual);
    }

    @Test
    public void getDecodedAmp() {
        final String expected = "&";
        final String actual = htmlEntityMap.getDecodedAmp();
        assertEquals(expected, actual);
    }
}
