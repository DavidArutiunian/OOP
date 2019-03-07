package labs.lab3.car.engine;

import labs.lab3.car.transmission.Gear;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class EngineTest {
    private Engine engine = new Engine();

    @Before
    public void setUp() {
        engine = new Engine();
    }

    @Test
    public void engineIsOffOnInit() {
        assertEquals(EngineState.OFF, engine.getState());
    }

    @Test
    public void engineIsOn() throws EngineIsOnException {
        turnOnEngine();
        assertEquals(EngineState.ON, engine.getState());
    }

    @Test
    public void engineIsOff() throws EngineIsOffException, EngineIsOnException {
        turnOnEngine();
        engine.off(Gear.NEUTRAL, 0);
        assertEquals(EngineState.OFF, engine.getState());
    }

    @Test
    public void engineThrowsIfAlreadyOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOnException.class, engine::on);
    }

    @Test
    public void engineThrowsIfAlreadyOff() {
        assertThrows(EngineIsOffException.class, () -> engine.off(Gear.NEUTRAL, 0));
    }

    @Test
    public void engineThrowsIfOffOnSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOffException.class, () -> engine.off(Gear.NEUTRAL, 10));
    }

    @Test
    public void engineThrowsIfOffOnGear() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOffException.class, () -> engine.off(Gear.FIRST, 0));
    }

    private void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }
}
