package labs.lab3.car.car;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.engine.EngineState;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;
import labs.lab3.car.transmission.Transmission;

public class Car {
    private final CarState state = new CarState();
    private final Engine engine = new Engine(state);
    private final Transmission transmission = new Transmission(state);

    public Gear getGear() {
        return state.getTransmissionGear();
    }

    public void setGear(Gear nextGear) throws IllegalStateChangeException {
        transmission.setGear(nextGear);
    }

    public double getSpeed() {
        return state.getCarSeed();
    }

    public void setSpeed(double nextSpeed) throws CarStateException, IllegalStateChangeException {
        final var currentGear = state.getTransmissionGear();
        final var currentSpeed = state.getCarSeed();
        boolean isMovingReverseAndNeutralGear = currentSpeed < 0 && currentGear == Gear.NEUTRAL;
        boolean isReverseGear = currentGear == Gear.REVERSE;
        if (isReverseGear || isMovingReverseAndNeutralGear) {
            nextSpeed = nextSpeed * -1;
        }
        transmission.testConditionsForSpeed(nextSpeed);
        state.setCarSpeed(nextSpeed);
    }

    public void turnOffEngine() throws EngineIsOffException {
        engine.off();
    }

    public void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }

    public EngineState getEngineState() {
        return state.getEngineState();
    }
}
