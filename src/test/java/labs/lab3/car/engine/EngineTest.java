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
    public void testIsOffOnInit() {
        assertEquals(EngineState.OFF, engine.getState());
    }

    @Test
    public void testIsOn() throws EngineIsOnException {
        turnOnEngine();
        assertEquals(EngineState.ON, engine.getState());
    }

    @Test
    public void testIsOff() throws EngineIsOffException, EngineIsOnException {
        turnOnEngine();
        engine.off(Gear.NEUTRAL, 0);
        assertEquals(EngineState.OFF, engine.getState());
    }

    @Test
    public void testThrowsIfAlreadyOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOnException.class, engine::on);
    }

    @Test
    public void testThrowsIfAlreadyOff() {
        assertThrows(EngineIsOffException.class, () -> engine.off(Gear.NEUTRAL, 0));
    }

    @Test
    public void testThrowsIfOffOnSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOffException.class, () -> engine.off(Gear.NEUTRAL, 10));
    }

    @Test
    public void testThrowsIfOffOnGear() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOffException.class, () -> engine.off(Gear.FIRST, 0));
    }

    private void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }
}
