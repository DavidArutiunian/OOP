package labs.lab3.car.car;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.engine.EngineState;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;
import labs.lab3.car.transmission.Transmission;

public class Car {
    private final Engine engine = new Engine();
    private final Transmission transmission = new Transmission();
    private final CarState state = new CarState(transmission, engine);

    public Gear getGear() {
        return transmission.getGear();
    }

    public void setGear(final Gear nextGear) throws IllegalStateChangeException {
        transmission.setGear(nextGear, engine.getState(), state.getSpeed());
    }

    public double getSpeed() {
        return state.getSpeed();
    }

    public void setSpeed(final double nextSpeed) throws CarStateException {
        state.setSpeed(nextSpeed);
    }

    public void turnOffEngine() throws EngineIsOffException {
        engine.off(transmission.getGear(), state.getSpeed());
    }

    public void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }

    public EngineState getEngineState() {
        return engine.getState();
    }
}
