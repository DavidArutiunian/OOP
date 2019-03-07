package labs.lab3.car.engine;

import labs.lab3.car.transmission.Gear;

public class Engine {
    private final EngineStateMediator mediator;

    public Engine(final EngineStateMediator mediator) {
        this.mediator = mediator;
    }

    public void on() throws EngineIsOnException {
        if (mediator.getEngineState() == EngineState.ON) {
            throw new EngineIsOnException("Engine is already on!");
        }
        mediator.setEngineState(EngineState.ON);
    }

    public void off() throws EngineIsOffException {
        if (mediator.getEngineState() == EngineState.OFF) {
            throw new EngineIsOffException("Engine is already off!");
        }
        if (mediator.getCarSeed() != 0) {
            throw new EngineIsOffException("Cannot OFF engine when moving!");
        }
        if (mediator.getTransmissionGear() != Gear.NEUTRAL) {
            throw new EngineIsOffException("Cannot OFF engine on non-neutral gear!");
        }
        mediator.setEngineState(EngineState.OFF);
    }
}
