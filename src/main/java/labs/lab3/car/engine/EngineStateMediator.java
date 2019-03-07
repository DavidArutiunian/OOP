package labs.lab3.car.engine;

import labs.lab3.car.shared.StateMediator;

public interface EngineStateMediator extends StateMediator {
    void setEngineState(final EngineState engineState);
}
