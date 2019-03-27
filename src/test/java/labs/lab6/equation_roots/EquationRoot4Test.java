package labs.lab6.equation_roots;

import lombok.val;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquationRoot4Test {
    @Test
    public void equationHasAllRoots() throws WolframResponseException, CannotParseValueException, SolutionNotFoundException, WolframAppIdException, IOException {
        val equation = new EquationRoot4();
        equation.solve(1.0, 2.0, -41.0, -42.0, 360.0);
        val expected = Arrays.asList(-6.0, -4.0, 3.0, 5.0);
        val actual = equation.getRoots();
        assertThat(actual, is(expected));
    }

    @Test
    public void hasAllRootsWithOtherSetOfArguments() throws WolframResponseException, CannotParseValueException, SolutionNotFoundException, WolframAppIdException, IOException {
        val equation = new EquationRoot4();
        equation.solve(3.0, 6.0, -123.0, -126.0, 1080);
        val expected = Arrays.asList(-6.0, -4.0, 3.0, 5.0);
        val actual = equation.getRoots();
        assertThat(actual, is(expected));
    }


    @Test
    public void cannotSolveIsFirstArgumentIsZero() {
        val equation = new EquationRoot4();
        assertThrows(IllegalArgumentException.class, () -> equation.solve(0.0, 2.0, -41.0, -42.0, 360.0));
    }

    @Test
    public void throwsIfNotAllSolutionsAreReal() {
        val equation = new EquationRoot4();
        assertThrows(SolutionNotFoundException.class, () -> equation.solve(-3.0, 6.0, -123.0, -126.0, 1080));
    }
}
