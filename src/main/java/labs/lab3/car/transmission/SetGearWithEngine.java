package labs.lab3.car.transmission;

import labs.lab3.car.engine.EngineState;

public interface SetGearWithEngine {
    void setGear(final Gear nextGear, final EngineState engineState) throws IllegalTransmissionStateChange;
}
