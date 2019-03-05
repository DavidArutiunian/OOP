package labs.lab3.car.transmission;

public interface SetGear {
    void setGear(final Gear nextGear) throws IllegalTransmissionStateChange;
}
