package labs.lab2.prime_numbers_generator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimeNumbersGeneratorImplTest {
    @Test
    public void testGenerator1() {
        final int upperBound = 100_000_000;
        final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGeneratorImpl(upperBound);
        final int[] numbers = primeNumbersGenerator.primes();
        final int expectedNumbersLength = 5_761_455;
        assertEquals(expectedNumbersLength, numbers.length);
    }

    @Test
    public void testGenerator2() {
        final int upperBound = 99_999_999;
        final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGeneratorImpl(upperBound);
        final int[] numbers = primeNumbersGenerator.primes();
        final int expectedNumbersLength = 5_761_455;
        assertEquals(expectedNumbersLength, numbers.length);
    }

    @Test
    public void testGenerator3() {
        final int upperBound = 100;
        final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGeneratorImpl(upperBound);
        final int[] numbers = primeNumbersGenerator.primes();
        final int expectedNumbersLength = 25;
        assertEquals(expectedNumbersLength, numbers.length);
    }
}
