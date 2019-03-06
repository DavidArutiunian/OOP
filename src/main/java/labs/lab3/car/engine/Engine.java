package labs.lab3.car.engine;

public class Engine {
    private EngineState state = EngineState.OFF;

    EngineState getState() {
        return state;
    }

    public void on() throws EngineIsOnException {
        if (state == EngineState.ON) {
            throw new EngineIsOnException("Engine is already on!");
        }
        state = EngineState.ON;
    }

    public void off() throws EngineIsOffException {
        if (state == EngineState.OFF) {
            throw new EngineIsOffException("Engine is already off!");
        }
        state = EngineState.OFF;
    }

    public boolean isOn() {
        return state == EngineState.ON;
    }

    public boolean isOff() {
        return state == EngineState.OFF;
    }
}
