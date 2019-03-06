package labs.lab3.car.transmission;

class Condition {
    private double from;
    private double to;

    Condition(final double from, final double to) {
        this.from = from;
        this.to = to;
    }

    boolean test(final Double current) {
        return current >= from && current <= to;
    }
}
