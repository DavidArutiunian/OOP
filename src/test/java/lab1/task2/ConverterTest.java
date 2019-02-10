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

    @Test
    public void testValidationWorks() {
        {
            final var cases = Arrays.asList(new Object[][]{
                {"1G", 16},
                {"1F", 15}
            });
            cases.forEach((final Object[] item) -> {
                final var value = (String) item[0];
                final var radix = (int) item[1];
                Assert.assertFalse(Converter.isValueValid(value, radix));
            });
        }
        {
            final var cases = Arrays.asList(new Object[][]{
                {"1F", 16},
                {"1E", 15}
            });
            cases.forEach((final Object[] item) -> {
                final var value = (String) item[0];
                final var radix = (int) item[1];
                Assert.assertTrue(Converter.isValueValid(value, radix));
            });
        }
    }
}
