package labs.lab3.car.transmission;

import labs.lab3.car.engine.Engine;

import java.util.EnumMap;
import java.util.Map;

class State {
    private final Engine engine;
    private final Map<Gear, Condition> conditions = new EnumMap<>(Gear.class);
    private Gear gear = Gear.NEUTRAL;

    State(final Engine engine) {
        this.engine = engine;
        conditions.put(Gear.REVERSE, new Condition(0, 0));
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

    private void setGear(final Gear nextGear) {
        gear = nextGear;
    }

    void setGear(final Gear nextGear, final double speed) throws IllegalStateChangeException {
        if (engine.isOff()) {
            setGear(nextGear);
            return;
        }
        if (!conditions.get(nextGear).test(speed)) {
            throw new IllegalStateChangeException("Cannot change gear!");
        }
        setGear(nextGear);
    }
}
