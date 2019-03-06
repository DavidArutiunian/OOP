package labs.lab3.car.engine;

import labs.lab3.car.transmission.Gear;

public class Engine {
    private EngineState state = EngineState.OFF;

    public EngineState getState() {
        return state;
    }

    public void on() throws EngineIsOnException {
        if (state == EngineState.ON) {
            throw new EngineIsOnException("Engine is already on!");
        }
        state = EngineState.ON;
    }

    public void off(final Gear gear, final double speed) throws EngineIsOffException {
        if (state == EngineState.OFF) {
            throw new EngineIsOffException("Engine is already off!");
        }
        if (speed != 0) {
            throw new EngineIsOffException("Cannot OFF engine when moving!");
        }
        if (gear != Gear.NEUTRAL) {
            throw new EngineIsOffException("Cannot OFF engine on non-neutral gear!");
        }
        state = EngineState.OFF;
    }
}
