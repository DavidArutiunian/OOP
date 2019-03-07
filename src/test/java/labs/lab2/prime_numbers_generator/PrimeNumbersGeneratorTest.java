package labs.lab2.prime_numbers_generator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimeNumbersGeneratorTest {
    @Test
    public void testGeneratorWorks1() {
        final int upperBound = 100_000_000;
        final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGenerator(upperBound);
        final int[] numbers = primeNumbersGenerator.sieve().primes();
        final int expectedNumbersLength = 5_761_455;
        assertEquals(expectedNumbersLength, numbers.length);
    }

    @Test
    public void testGeneratorWorks2() {
        final int upperBound = 99_999_999;
        final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGenerator(upperBound);
        final int[] numbers = primeNumbersGenerator.sieve().primes();
        final int expectedNumbersLength = 5_761_455;
        assertEquals(expectedNumbersLength, numbers.length);
    }

    @Test
    public void testGeneratorWorks3() {
        final int upperBound = 100;
        final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGenerator(upperBound);
        final int[] numbers = primeNumbersGenerator.sieve().primes();
        final int expectedNumbersLength = 25;
        assertEquals(expectedNumbersLength, numbers.length);
    }

    @Test
    public void testGeneratorWorks4() {
        final int upperBound = 2;
        final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGenerator(upperBound);
        final int[] numbers = primeNumbersGenerator.sieve().primes();
        final int expectedNumbersLength = 1;
        assertEquals(expectedNumbersLength, numbers.length);
    }

    @Test
    public void testGeneratorThrows1() {
        final int upperBound = 0;
        assertThrows(IllegalArgumentException.class, () -> new PrimeNumbersGenerator(upperBound));
    }

    @Test
    public void testGeneratorThrows2() {
        final int upperBound = 1;
        assertThrows(IllegalArgumentException.class, () -> new PrimeNumbersGenerator(upperBound));
    }

    @Test
    public void testGeneratorThrows3() {
        final int upperBound = -1;
        assertThrows(IllegalArgumentException.class, () -> new PrimeNumbersGenerator(upperBound));
    }
}
