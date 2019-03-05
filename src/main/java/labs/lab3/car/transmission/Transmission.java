package labs.lab3.car.transmission;

import labs.lab3.car.engine.EngineState;

public class Transmission implements SetGearWithSpeed, SetGearWithEngine, GetGear {
    private final TransmissionState state = new TransmissionState();

    @Override
    public Gear getGear() {
        return state.getGear();
    }

    @Override
    public void setGear(final Gear nextGear, final double currentSpeed) throws IllegalTransmissionStateChange {
        state.setGear(nextGear, currentSpeed);
    }

    @Override
    public void setGear(final Gear nextGear, final EngineState engineState) throws IllegalTransmissionStateChange {
        state.setGear(nextGear, engineState);
    }
}
