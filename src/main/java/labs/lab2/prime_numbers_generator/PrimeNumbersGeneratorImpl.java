package labs.lab2.prime_numbers_generator;

import java.util.Arrays;
import java.util.stream.IntStream;

class PrimeNumbersGeneratorImpl implements PrimeNumbersGenerator {
    private boolean[] primes;

    PrimeNumbersGeneratorImpl(int n) {
        primes = new boolean[n + 1];
        sieve();
    }

    /**
     * Perform Sieve of Eratosthenes algorithm.
     * Fills 'primes' array of booleans.
     */
    private void sieve() {
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
    }

    @Override
    public int[] primes() {
        return IntStream.range(0, primes.length).filter(value -> primes[value]).toArray();
    }
}
