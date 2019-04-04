package labs.lab2.prime_numbers_generator;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.IntStream;

@UtilityClass
class PrimeNumbersGenerator {
    private static final int MIN_UPPER_BOUND = 2;

    private static boolean[] sieve(int upperBound) {
        if (upperBound < MIN_UPPER_BOUND) {
            throw new IllegalArgumentException("Upper bound must be more then \"" + MIN_UPPER_BOUND + "\"");
        }
        final boolean[] sieve = new boolean[upperBound + 1];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;
        for (int i = 2; i < sieve.length; ++i) {
            if (sieve[i]) {
                for (int j = 2; i * j < sieve.length; ++j) {
                    sieve[i * j] = false;
                }
            }
        }
        return sieve;
    }

    static int[] primes(int upperBound) {
        final boolean[] sieve = sieve(upperBound);
        return IntStream.range(0, sieve.length).filter(value -> sieve[value]).toArray();
    }
}
