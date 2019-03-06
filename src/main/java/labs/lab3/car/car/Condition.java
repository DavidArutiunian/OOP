package labs.lab3.car.car;

import java.util.function.Predicate;

class Condition {
    private final Predicate<Double> predicate;
    private final CarStateException exception;

    Condition(final Predicate<Double> predicate, final CarStateException exception) {
        this.predicate = predicate;
        this.exception = exception;
    }

    boolean test(final double speed) {
        return predicate.test(speed);
    }

    CarStateException getException() {
        return exception;
    }
}
