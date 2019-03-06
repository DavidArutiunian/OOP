package labs.lab3.car.car;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;
import labs.lab3.car.transmission.Transmission;

class Car {
    private final Engine engine = new Engine();
    private final Transmission transmission = new Transmission(engine);
    private final State state = new State(transmission, engine);

    Gear getGear() {
        return transmission.getGear();
    }

    void setGear(final Gear nextGear) throws IllegalStateChangeException {
        transmission.setGear(nextGear, state.getSpeed());
    }

    double getSpeed() {
        return state.getSpeed();
    }

    void setSpeed(final double nextSpeed) throws CarStateException {
        state.setSpeed(nextSpeed);
    }

    void turnOffEngine() throws EngineIsOffException {
        engine.off();
    }

    void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }
}
