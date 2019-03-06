package labs.lab3.car.transmission;

import labs.lab3.car.engine.Engine;

public class Transmission {
    private final State state;

    public Transmission(final Engine engine) {
        state = new State(engine);
    }

    public Gear getGear() {
        return state.getState();
    }

    public void setGear(final Gear nextGear, final double speed) throws IllegalStateChangeException {
        state.setGear(nextGear, speed);
    }
}
