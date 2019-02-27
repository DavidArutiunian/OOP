package labs.lab2.prime_numbers_generator;

import java.util.Arrays;
import java.util.stream.IntStream;

class PrimeNumbersGeneratorImpl implements PrimeNumbersGenerator {
    private static final int MIN_UPPER_BOUND = 2;

    private boolean[] primes;

    PrimeNumbersGeneratorImpl(int upperBound) {
        if (upperBound < MIN_UPPER_BOUND) {
            throw new IllegalArgumentException("Upper bound must be more then \"" + MIN_UPPER_BOUND + "\"");
        }
        primes = new boolean[upperBound + 1];
    }

    @Override
    public PrimeNumbersGenerator sieve() {
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i < primes.length; ++i) {
            if (primes[i]) {
                for (int j = 2; i * j < primes.length; ++j) {
                    primes[i * j] = false;
                }
            }
        }
        return this;
    }

    @Override
    public int[] primes() {
        return IntStream.range(0, primes.length).filter(value -> primes[value]).toArray();
    }
}
