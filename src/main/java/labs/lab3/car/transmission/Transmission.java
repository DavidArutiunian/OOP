package labs.lab3.car.transmission;

import labs.lab3.car.engine.EngineState;

public class Transmission {
    private final State state = new State();

    public Gear getGear() {
        return state.getGear();
    }

    public void setGear(final Gear nextGear, final double speed) throws IllegalStateChangeException {
        state.setGear(nextGear, speed);
    }

    public void setGear(final Gear nextGear, final EngineState engineState) throws IllegalStateChangeException {
        state.setGear(nextGear, engineState);
    }
}
