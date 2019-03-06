package labs.lab3.car.car;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineState;
import labs.lab3.car.shared.Conditional;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.Transmission;

import java.util.LinkedHashMap;
import java.util.Map;

class CarState {
    private final Map<CarStateException, Conditional<Double>> conditions = new LinkedHashMap<>();
    private double speed = 0;

    CarState(final Transmission transmission, final Engine engine) {
        conditions.put(
            new EngineIsOffException("Engine is OFF!"),
            new Condition(nextSpeed -> engine.getState() == EngineState.ON)
        );
        conditions.put(
            new IllegalSpeedChangeException("Speed can be set only closer to zero on " + Gear.NEUTRAL.name() + " gear!"),
            new Condition(nextSpeed -> transmission.getGear() != Gear.NEUTRAL || (nextSpeed >= 0 && nextSpeed <= Math.abs(speed)))
        );
    }

    double getSpeed() {
        return speed;
    }

    void setSpeed(final double nextSpeed) throws CarStateException {
        for (final var condition : conditions.entrySet()) {
            if (!condition.getValue().test(nextSpeed)) {
                throw condition.getKey();
            }
        }
        speed = nextSpeed;
    }
}
