package labs.lab3.car.transmission;

import labs.lab3.car.shared.Conditional;

class Condition implements Conditional<Double> {
    private final double from;
    private final double to;

    Condition(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean test(Double current) {
        return current >= from && current <= to;
    }
}
