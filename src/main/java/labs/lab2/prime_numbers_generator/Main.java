package labs.lab2.prime_numbers_generator;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        try {
            InputOutput.validate(args, 1);
            final int upperBound = InputOutput.parse(args[0]);
            InputOutput.validate(upperBound);
            final PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGeneratorImpl(upperBound);
            System.out.println(Arrays.toString(primeNumbersGenerator.primes()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
