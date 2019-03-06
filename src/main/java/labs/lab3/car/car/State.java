package labs.lab3.car.car;

import labs.lab3.car.Conditional;
import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.Transmission;

import java.util.LinkedHashMap;
import java.util.Map;

class State {
    private final Map<CarStateException, Conditional<Double>> conditions = new LinkedHashMap<>();
    private double speed = 0;

    State(final Transmission transmission, final Engine engine) {
        conditions.put(
            new EngineIsOffException("Engine is not turned on!"),
            new Condition(nextSpeed -> engine.isOn())
        );
        conditions.put(
            new IllegalSpeedChangeException("Cannot increase speed on neutral gear!"),
            new Condition(nextSpeed -> transmission.getGear() != Gear.NEUTRAL || nextSpeed <= speed)
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
