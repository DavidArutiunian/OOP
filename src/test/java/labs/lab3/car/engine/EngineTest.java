package labs.lab3.car.engine;

import labs.lab3.car.car.CarState;
import labs.lab3.car.car.CarStateException;
import labs.lab3.car.transmission.Gear;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("SameParameterValue")
public class EngineTest {
    private CarState state = new CarState();
    private Engine engine = new Engine(state);

    @Before
    public void setUp() {
        state = new CarState();
        engine = new Engine(state);
    }

    @Test
    public void engineIsOffOnInit() {
        assertEquals(EngineState.OFF, state.getEngineState());
    }

    @Test
    public void setEngineStateOn() throws EngineIsOnException {
        turnOnEngine();
        assertEquals(EngineState.ON, state.getEngineState());
    }

    @Test
    public void setEngineStateOff() throws EngineIsOffException, EngineIsOnException {
        turnOnEngine();
        engine.off();
        assertEquals(EngineState.OFF, state.getEngineState());
    }

    @Test
    public void throwsIfAlreadyOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOnException.class, engine::on);
    }

    @Test
    public void throwsIfAlreadyOff() {
        assertThrows(EngineIsOffException.class, () -> engine.off());
    }

    @Test
    public void throwsIfOffOnSpeed() throws EngineIsOnException, CarStateException {
        turnOnEngine();
        setSpeedAndGear(Gear.FIRST, 10);
        assertThrows(EngineIsOffException.class, () -> engine.off());
    }

    @Test
    public void throwsIfOffOnGear() throws EngineIsOnException, CarStateException {
        turnOnEngine();
        setSpeedAndGear(Gear.FIRST, 10);
        assertThrows(EngineIsOffException.class, () -> engine.off());
    }

    private void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }

    private void setSpeedAndGear(Gear gear, double speed) throws CarStateException {
        state.setTransmissionGear(gear);
        state.setCarSpeed(speed);
    }
}
