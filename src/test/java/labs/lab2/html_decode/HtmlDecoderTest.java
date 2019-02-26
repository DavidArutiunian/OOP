package labs.lab2.html_decode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlDecoderTest {
    @Test
    public void testDecodeWorks1() {
        final String input = "Cat &lt;says&gt; &quot;Meow&quot;. M&amp;M&apos;s";
        final String expected = "Cat <says> \"Meow\". M&M's";
        final var htmlEntityMap = new HtmlEntityMapImpl();
        final var decoder = new HtmlDecoder(input);
        final String output = decoder.decode(htmlEntityMap);
        assertEquals(expected, output);
    }

    @Test
    public void testDecodeWorks2() {
        final String input = "&quot;&quot;&quot;Hello, &apos;World&apos;!&quot;&quot;&quot;&quot;";
        final String expected = "\"\"\"Hello, 'World'!\"\"\"\"";
        final var htmlEntityMap = new HtmlEntityMapImpl();
        final var decoder = new HtmlDecoder(input);
        final String output = decoder.decode(htmlEntityMap);
        assertEquals(expected, output);
    }

    @Test
    public void testDecodeWork3() {
        final String input = "&apos;2&apos; &lt;&gt; &quot;4&quot; &amp;&amp; 0";
        final String expected = "'2' <> \"4\" && 0";
        final var htmlEntityMap = new HtmlEntityMapImpl();
        final var decoder = new HtmlDecoder(input);
        final String output = decoder.decode(htmlEntityMap);
        assertEquals(expected, output);
    }

    @Test
    public void testDecodeWork4() {
        final String input = "&quot;&apos;&lt;&gt;&amp;&lt;&gt;&apos;&quot;";
        final String expected = "\"'<>&<>'\"";
        final var htmlEntityMap = new HtmlEntityMapImpl();
        final var decoder = new HtmlDecoder(input);
        final String output = decoder.decode(htmlEntityMap);
        assertEquals(expected, output);
    }
}
