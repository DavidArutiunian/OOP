package labs.lab3.car.car;

import labs.lab3.car.shared.Conditional;

import java.util.function.Predicate;

class Condition implements Conditional<Double> {
    private final Predicate<Double> predicate;

    Condition(Predicate<Double> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(Double speed) {
        return predicate.test(speed);
    }
}
