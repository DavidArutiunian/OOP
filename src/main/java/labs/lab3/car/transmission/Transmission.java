package labs.lab3.car.transmission;

public class Transmission {
    private final TransmissionMediator mediator;
    private final TransmissionState state;

    public Transmission(final TransmissionMediator mediator) {
        this.mediator = mediator;
        this.state = new TransmissionState(mediator);
    }

    public void setGear(final Gear nextGear) throws IllegalStateChangeException {
        state.setGear(nextGear);
    }

    public void testConditionsForSpeed(final double speed) throws IllegalStateChangeException {
        state.testConditionsForGearAndSpeed(mediator.getTransmissionGear(), speed);
    }
}
