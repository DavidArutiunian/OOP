package labs.lab3.car.transmission;

import labs.lab3.car.shared.StateMediator;

public class Transmission {
    private final StateMediator mediator;
    private final TransmissionState state;

    public Transmission(final TransmissionStateMediator mediator) {
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
