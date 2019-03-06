package labs.lab3.car.car;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.engine.EngineState;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;
import labs.lab3.car.transmission.Transmission;

class Car {
    private double speed = 0;
    private Engine engine = new Engine();
    private Transmission transmission = new Transmission();

    Gear getGear() {
        return transmission.getGear();
    }

    void setGear(final Gear nextGear) throws IllegalStateChangeException {
        if (engine.getState() == EngineState.ON) {
            transmission.setGear(nextGear, speed);
        } else {
            transmission.setGear(nextGear, EngineState.OFF);
        }
    }

    double getSpeed() {
        return speed;
    }

    void setSpeed(double nextSpeed) throws IllegalSpeedChangeException, EngineIsOffException {
        if (engine.getState() == EngineState.OFF) {
            throw new EngineIsOffException("Engine is not turned on!");
        }
        if (transmission.getGear() == Gear.NEUTRAL && nextSpeed > speed) {
            throw new IllegalSpeedChangeException("Cannot increase speed on neutral gear!");
        }
        speed = nextSpeed;
    }

    void turnOffEngine() throws EngineIsOffException {
        engine.off();
    }

    void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }
}
