package labs.lab3.car.car;

import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineState;
import labs.lab3.car.engine.EngineStateMediator;
import labs.lab3.car.shared.Conditional;
import labs.lab3.car.shared.StateMediator;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.TransmissionStateMediator;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarState implements StateMediator, TransmissionStateMediator, EngineStateMediator {
    private final Map<CarStateException, Conditional<Double>> conditions = new LinkedHashMap<>();
    private double speed = 0;
    private EngineState engineState = EngineState.OFF;
    private Gear gear = Gear.NEUTRAL;

    public CarState() {
        conditions.put(
            new EngineIsOffException("Engine is OFF!"),
            new Condition(nextSpeed -> engineState == EngineState.ON)
        );
        conditions.put(
            new IllegalSpeedChangeException("Speed can be set only closer to zero on " + Gear.NEUTRAL.name() + " gear!"),
            new Condition(nextSpeed -> {
                if (gear != Gear.NEUTRAL) {
                    return true;
                }
                if (speedsHaveSameSigns(speed, nextSpeed)) {
                    final var delta = Math.abs(speed) - Math.abs(nextSpeed);
                    return delta <= Math.abs(speed) && delta >= 0;
                }
                return false;
            })
        );
    }

    private boolean speedsHaveSameSigns(final double left, final double right) {
        return left * right >= 0;
    }

    @Override
    public EngineState getEngineState() {
        return engineState;
    }

    @Override
    public void setEngineState(final EngineState nextEngineState) {
        engineState = nextEngineState;
    }

    @Override
    public Gear getTransmissionGear() {
        return gear;
    }

    @Override
    public void setTransmissionGear(final Gear nextGear) {
        gear = nextGear;
    }

    @Override
    public double getCarSeed() {
        return speed;
    }

    public void setCarSpeed(double nextSpeed) throws CarStateException {
        for (final var condition : conditions.entrySet()) {
            if (!condition.getValue().test(nextSpeed)) {
                throw condition.getKey();
            }
        }
        speed = nextSpeed;
    }
}
