package labs.lab3.car.transmission;

import labs.lab3.car.Conditional;
import labs.lab3.car.engine.EngineState;

import java.util.EnumMap;
import java.util.Map;

class TransmissionState {
    private final Map<Gear, Conditional<Double>> conditions = new EnumMap<>(Gear.class);
    private Gear gear = Gear.NEUTRAL;

    TransmissionState() {
        conditions.put(Gear.REVERSE, new Condition(-20, 0));
        conditions.put(Gear.NEUTRAL, new Condition(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        conditions.put(Gear.FIRST, new Condition(0, 30));
        conditions.put(Gear.SECOND, new Condition(20, 50));
        conditions.put(Gear.THIRD, new Condition(30, 60));
        conditions.put(Gear.FOURTH, new Condition(40, 90));
        conditions.put(Gear.FIFTH, new Condition(50, 150));
    }

    Gear getGear() {
        return gear;
    }

    void setGear(final Gear nextGear, final EngineState engineState, final double speed) throws IllegalStateChangeException {
        if (engineState == EngineState.OFF) {
            gear = nextGear;
            return;
        }
        testConditionsForGearAndSpeed(nextGear, speed);
        if (nextGear == Gear.REVERSE && speed != 0) {
            throw new IllegalStateChangeException(nextGear.name() + " gear can be set only on 0 speed!");
        }
        gear = nextGear;
    }

    void testConditionsForGearAndSpeed(final Gear gear, final double speed) throws IllegalStateChangeException {
        if (!conditions.get(gear).test(speed)) {
            throw new IllegalStateChangeException("Incorrect speed for " + gear.name() + " gear range!");
        }
    }
}
