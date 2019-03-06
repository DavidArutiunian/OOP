package labs.lab3.car.transmission;

import labs.lab3.car.Conditional;

class Condition implements Conditional<Double> {
    private final double from;
    private final double to;

    Condition(final double from, final double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean test(final Double current) {
        return current >= from && current <= to;
    }
}
