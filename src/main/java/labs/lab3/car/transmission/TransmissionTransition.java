package labs.lab3.car.transmission;

class TransmissionTransition implements GetGear {
    private final Gear gear;
    private final TransmissionCondition condition;

    TransmissionTransition(final Gear gear, final TransmissionCondition condition) {
        this.gear = gear;
        this.condition = condition;
    }

    boolean test(final double currentSpeed) {
        return condition.test(currentSpeed);
    }

    @Override
    public Gear getGear() {
        return gear;
    }
}
