package labs.lab3.car.car;

import labs.lab3.car.Conditional;

import java.util.function.Predicate;

class Condition implements Conditional<Double> {
    private final Predicate<Double> predicate;

    Condition(final Predicate<Double> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(final Double speed) {
        return predicate.test(speed);
    }
}
