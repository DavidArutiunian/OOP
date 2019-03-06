package labs.lab3.car.transmission;

import labs.lab3.car.engine.EngineState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TransmissionState {
    private List<TransmissionTransition> transitions = new ArrayList<>(Arrays.asList(
        new TransmissionTransition(
            Gear.REVERSE,
            new TransmissionCondition(0, 0)
        ),
        new TransmissionTransition(
            Gear.NEUTRAL,
            new TransmissionCondition(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
        ),
        new TransmissionTransition(
            Gear.FIRST,
            new TransmissionCondition(0, 30)
        ),
        new TransmissionTransition(
            Gear.SECOND,
            new TransmissionCondition(20, 50)
        ),
        new TransmissionTransition(
            Gear.THIRD,
            new TransmissionCondition(30, 60)
        ),
        new TransmissionTransition(
            Gear.FOURTH,
            new TransmissionCondition(40, 90)
        ),
        new TransmissionTransition(
            Gear.FIFTH,
            new TransmissionCondition(50, 150)
        )
    ));
    private Gear gear = Gear.NEUTRAL;

    Gear getGear() {
        return gear;
    }

    void setGear(final Gear nextGear, final double currentSpeed) throws IllegalTransmissionStateChange {
        for (final TransmissionTransition transition : transitions) {
            if (transition.getGear() != nextGear) {
                continue;
            }
            if (!transition.test(currentSpeed)) {
                throw new IllegalTransmissionStateChange("Cannot change gear!");
            }
            gear = nextGear;
        }
    }

    void setGear(final Gear nextGear, final EngineState engineState) throws IllegalTransmissionStateChange {
        if (engineState == EngineState.ON) {
            throw new IllegalTransmissionStateChange("Speed is not provided!");
        }
        gear = nextGear;
    }
}
