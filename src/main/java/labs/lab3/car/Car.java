package labs.lab3.car;

import labs.lab3.car.engine.*;
import labs.lab3.car.transmission.*;

class Car implements SetGear, GetGear, SetSpeed, GetSpeed, TurnOffEngine, TurnOnEngine {
    private double speed = 0;
    private Engine engine = new Engine();
    private Transmission transmission = new Transmission();

    @Override
    public Gear getGear() {
        return transmission.getGear();
    }

    @Override
    public void setGear(final Gear nextGear) throws IllegalTransmissionStateChange {
        if (engine.getState() == EngineState.ON) {
            transmission.setGear(nextGear, speed);
        } else {
            transmission.setGear(nextGear, EngineState.OFF);
        }
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double nextSpeed) throws IllegalSpeedChange, EngineIsOff {
        if (engine.getState() == EngineState.OFF) {
            throw new EngineIsOff("Engine is not turned on!");
        }
        if (transmission.getGear() == Gear.NEUTRAL && nextSpeed > speed) {
            throw new IllegalSpeedChange("Cannot increase speed on neutral gear!");
        }
        speed = nextSpeed;
    }

    @Override
    public void turnOffEngine() throws EngineIsOff {
        engine.off();
    }

    @Override
    public void turnOnEngine() throws EngineIsOn {
        engine.on();
    }
}
