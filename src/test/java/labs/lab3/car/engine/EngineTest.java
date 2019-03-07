package labs.lab3.car.engine;

import labs.lab3.car.car.CarState;
import labs.lab3.car.car.CarStateException;
import labs.lab3.car.transmission.Gear;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
    public void engineIsOn() throws EngineIsOnException {
        turnOnEngine();
        assertEquals(EngineState.ON, state.getEngineState());
    }

    @Test
    public void engineIsOff() throws EngineIsOffException, EngineIsOnException {
        turnOnEngine();
        engine.off();
        assertEquals(EngineState.OFF, state.getEngineState());
    }

    @Test
    public void engineThrowsIfAlreadyOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOnException.class, engine::on);
    }

    @Test
    public void engineThrowsIfAlreadyOff() {
        assertThrows(EngineIsOffException.class, () -> engine.off());
    }

    @Test
    public void engineThrowsIfOffOnSpeed() throws EngineIsOnException, CarStateException {
        turnOnEngine();
        setSpeedAndGear(Gear.FIRST, 10);
        assertThrows(EngineIsOffException.class, () -> engine.off());
    }

    @Test
    public void engineThrowsIfOffOnGear() throws EngineIsOnException, CarStateException {
        turnOnEngine();
        setSpeedAndGear(Gear.FIRST, 10);
        assertThrows(EngineIsOffException.class, () -> engine.off());
    }

    private void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }

    private void setSpeedAndGear(final Gear gear, final double speed) throws CarStateException {
        state.setTransmissionGear(gear);
        state.setCarSpeed(speed);
    }
}
