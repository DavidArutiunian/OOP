package labs.lab3.car.transmission;

import labs.lab3.car.shared.StateMediator;

public interface TransmissionStateMediator extends StateMediator {
    void setTransmissionGear(final Gear gear);
}
