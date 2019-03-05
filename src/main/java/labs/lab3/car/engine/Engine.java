package labs.lab3.car.engine;

public class Engine {
    private EngineState state = EngineState.OFF;

    public EngineState getState() {
        return state;
    }

    public void on() throws EngineIsOn {
        if (state == EngineState.ON) {
            throw new EngineIsOn("Engine is already on!");
        }
        state = EngineState.ON;
    }

    public void off() throws EngineIsOff {
        if (state == EngineState.OFF) {
            throw new EngineIsOff("Engine is already off!");
        }
        state = EngineState.OFF;
    }
}
