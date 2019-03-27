package labs.lab6.equation_roots;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int MAX_NUMBER_OF_ARGUMENTS = 5;

    public static void main(String[] args) {
        try {
            val equation = new EquationRoot4();
            val scanner = new Scanner(System.in);
            var counter = 0;

            val arguments = new ArrayList<Double>();

            while (scanner.hasNextDouble() || counter > MAX_NUMBER_OF_ARGUMENTS) {
                arguments.add(scanner.nextDouble());
                counter++;
            }

            if (counter < MAX_NUMBER_OF_ARGUMENTS) {
                throw new IllegalArgumentException("Not enough arguments!");
            }

            equation.solve(
                arguments.get(EEquationArgs.A.getValue()),
                arguments.get(EEquationArgs.B.getValue()),
                arguments.get(EEquationArgs.C.getValue()),
                arguments.get(EEquationArgs.D.getValue()),
                arguments.get(EEquationArgs.E.getValue())
            );

            val roots = equation.getRoots();
            roots.forEach(x -> System.out.println("x = " + x));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @AllArgsConstructor
    private enum EEquationArgs {
        A(0),
        B(1),
        C(2),
        D(3),
        E(4);
        @Getter
        private final int value;
    }
}
