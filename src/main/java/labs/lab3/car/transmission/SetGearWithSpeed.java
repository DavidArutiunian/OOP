package labs.lab3.car.transmission;

public interface SetGearWithSpeed {
    void setGear(final Gear nextGear, final double currentSpeed) throws IllegalTransmissionStateChange;
}
