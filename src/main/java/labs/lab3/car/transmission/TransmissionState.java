package labs.lab3.car.transmission;

import labs.lab3.car.engine.EngineState;
import labs.lab3.car.shared.Conditional;

import java.util.EnumMap;
import java.util.Map;

class TransmissionState {
    private final Map<Gear, Conditional<Double>> conditions = new EnumMap<>(Gear.class);
    private final TransmissionMediator mediator;

    TransmissionState(final TransmissionMediator mediator) {
        this.mediator = mediator;
        conditions.put(Gear.REVERSE, new Condition(-20, 0));
        conditions.put(Gear.NEUTRAL, new Condition(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        conditions.put(Gear.FIRST, new Condition(0, 30));
        conditions.put(Gear.SECOND, new Condition(20, 50));
        conditions.put(Gear.THIRD, new Condition(30, 60));
        conditions.put(Gear.FOURTH, new Condition(40, 90));
        conditions.put(Gear.FIFTH, new Condition(50, 150));
    }

    void setGear(final Gear nextGear) throws IllegalStateChangeException {
        if (mediator.getEngineState() == EngineState.OFF && nextGear != Gear.NEUTRAL) {
            throw new IllegalStateChangeException("Cant set " + nextGear.name() + " when engine is " + mediator.getEngineState().name() + "!");
        }
        testConditionsForGearAndSpeed(nextGear, mediator.getCarSeed());
        if (nextGear == Gear.REVERSE && mediator.getCarSeed() != 0) {
            throw new IllegalStateChangeException(nextGear.name() + " gear can be set only on 0 speed!");
        }
        mediator.setTransmissionGear(nextGear);
    }

    void testConditionsForGearAndSpeed(final Gear gear, final double speed) throws IllegalStateChangeException {
        if (!conditions.get(gear).test(speed)) {
            throw new IllegalStateChangeException("Incorrect speed for " + gear.name() + " gear range!");
        }
    }
}
