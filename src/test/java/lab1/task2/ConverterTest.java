package lab1.task2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ConverterTest {
    private String value;
    private int from;
    private int to;
    private String expected;

    public ConverterTest(String value, int from, int to, String expected) {
        this.value = value;
        this.from = from;
        this.to = to;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"1F", 16, 10, "31"},
            {"-64D", 38, 14, "-3309"},
            {"0", 5, 7, "0"},
            {"AFZ621", 24, 32, "2GLHTH"}
        });
    }

    @Test
    public void testConverterWorks() {
        Converter converter = new Converter(value, from);
        Assert.assertEquals(converter.convert(to), expected);
    }
}
