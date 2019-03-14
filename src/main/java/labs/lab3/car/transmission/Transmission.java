package labs.lab3.car.transmission;

import labs.lab3.car.shared.StateMediator;

public class Transmission {
    private final StateMediator mediator;
    private final TransmissionState state;

    public Transmission(TransmissionStateMediator mediator) {
        this.mediator = mediator;
        this.state = new TransmissionState(mediator);
    }

    public void setGear(Gear nextGear) throws IllegalStateChangeException {
        state.setGear(nextGear);
    }

    public void testConditionsForSpeed(double speed) throws IllegalStateChangeException {
        state.testConditionsForGearAndSpeed(mediator.getTransmissionGear(), speed);
    }
}
