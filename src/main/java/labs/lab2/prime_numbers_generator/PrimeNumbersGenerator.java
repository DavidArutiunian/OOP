package labs.lab2.prime_numbers_generator;

public interface PrimeNumbersGenerator {
    /**
     * Perform Sieve of Eratosthenes algorithm.
     * Fills 'primes' array of booleans.
     */
    PrimeNumbersGenerator sieve();

    int[] primes();
}
