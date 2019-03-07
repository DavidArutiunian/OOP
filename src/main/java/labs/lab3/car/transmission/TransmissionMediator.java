package labs.lab3.car.transmission;

import labs.lab3.car.car.SpeedMediator;
import labs.lab3.car.engine.EngineMediator;

public interface TransmissionMediator extends EngineMediator, SpeedMediator {
    Gear getTransmissionGear();

    void setTransmissionGear(final Gear nextGear);
}
