package labs.lab3.car;

import labs.lab3.car.engine.EngineIsOff;

public interface SetSpeed {
    void setSpeed(final double nextSpeed) throws IllegalSpeedChange, EngineIsOff;
}
