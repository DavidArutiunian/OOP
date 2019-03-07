package labs.lab3.car.engine;

public interface EngineMediator {
    EngineState getEngineState();

    void setEngineState(final EngineState nextEngineState);
}
