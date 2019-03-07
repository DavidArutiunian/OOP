package labs.lab3.car.shared;

import labs.lab3.car.engine.EngineState;
import labs.lab3.car.transmission.Gear;

public interface StateMediator {
    Gear getTransmissionGear();

    EngineState getEngineState();

    double getCarSeed();
}
