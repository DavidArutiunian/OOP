package labs.lab3.car.transmission;

class TransmissionCondition {
    private double from;
    private double to;

    TransmissionCondition(final double from, final double to) {
        this.from = from;
        this.to = to;
    }

    boolean test(final double current) {
        if (current > to) {
            return false;
        }
        return !(current < from);
    }
}
