package labs.lab3.car.car;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.Transmission;

import java.util.ArrayList;
import java.util.List;

class State {
    private final List<Condition> conditions = new ArrayList<>();
    private double speed = 0;

    State(final Transmission transmission, final Engine engine) {
        conditions.add(
            new Condition(
                nextSpeed -> engine.isOn(),
                new EngineIsOffException("Engine is not turned on!")
            )
        );
        conditions.add(
            new Condition(
                nextSpeed -> !transmission.is(Gear.NEUTRAL) || nextSpeed <= speed,
                new IllegalSpeedChangeException("Cannot increase speed on neutral gear!")
            )
        );
    }

    double getSpeed() {
        return speed;
    }

    void setSpeed(final double nextSpeed) throws CarStateException {
        for (final Condition condition : conditions) {
            if (!condition.test(nextSpeed)) {
                throw condition.getException();
            }
        }
        speed = nextSpeed;
    }
}
