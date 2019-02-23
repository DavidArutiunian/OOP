package labs.lab2.html_decode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlDecoderTest {
    @Test
    public void testDecodeWorks() {
        final String input = "Cat &lt;says&gt; &quot;Meow&quot;. M&amp;M&apos;s";
        final String expected = "Cat <says> \"Meow\". M&M's";
        final var htmlEntityMap = new HtmlEntityMapImpl();
        final var decoder = new HtmlDecoder(input);
        final String output = decoder.decode(htmlEntityMap);
        assertEquals(expected, output);
    }
}
