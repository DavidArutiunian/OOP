package labs.lab3.car.transmission;

import labs.lab3.car.engine.EngineState;

public class Transmission {
    private final TransmissionState state = new TransmissionState();

    public Gear getGear() {
        return state.getGear();
    }

    public void setGear(final Gear nextGear, final double currentSpeed) throws IllegalTransmissionStateChange {
        state.setGear(nextGear, currentSpeed);
    }

    public void setGear(final Gear nextGear, final EngineState engineState) throws IllegalTransmissionStateChange {
        state.setGear(nextGear, engineState);
    }
}
