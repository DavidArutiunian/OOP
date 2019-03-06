package labs.lab3.car.car;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOff;
import labs.lab3.car.engine.EngineIsOn;
import labs.lab3.car.engine.EngineState;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalTransmissionStateChange;
import labs.lab3.car.transmission.Transmission;

public class Car {
    private double speed = 0;
    private Engine engine = new Engine();
    private Transmission transmission = new Transmission();

    Gear getGear() {
        return transmission.getGear();
    }

    void setGear(final Gear nextGear) throws IllegalTransmissionStateChange {
        if (engine.getState() == EngineState.ON) {
            transmission.setGear(nextGear, speed);
        } else {
            transmission.setGear(nextGear, EngineState.OFF);
        }
    }

    double getSpeed() {
        return speed;
    }

    void setSpeed(double nextSpeed) throws IllegalSpeedChange, EngineIsOff {
        if (engine.getState() == EngineState.OFF) {
            throw new EngineIsOff("Engine is not turned on!");
        }
        if (transmission.getGear() == Gear.NEUTRAL && nextSpeed > speed) {
            throw new IllegalSpeedChange("Cannot increase speed on neutral gear!");
        }
        speed = nextSpeed;
    }

    void turnOffEngine() throws EngineIsOff {
        engine.off();
    }

    void turnOnEngine() throws EngineIsOn {
        engine.on();
    }
}
