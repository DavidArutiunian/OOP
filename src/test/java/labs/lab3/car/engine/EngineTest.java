package labs.lab3.car.engine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class EngineTest {
    private final Engine engine = new Engine();

    @Test
    public void testIsOffOnInit() {
        assertEquals(EngineState.OFF, engine.getState());
    }

    @Test
    public void testIsOn() throws EngineIsOnException {
        engine.on();
        assertEquals(EngineState.ON, engine.getState());
    }

    @Test
    public void testIsOff() throws EngineIsOffException {
        try {
            engine.on();
        } catch (Exception e) {
            // noop
        }
        engine.off();
        assertEquals(EngineState.OFF, engine.getState());
    }

    @Test
    public void testThrowsIfAlreadyOn() {
        try {
            engine.on();
        } catch (Exception e) {
            // noop
        }
        assertThrows(EngineIsOnException.class, engine::on);
    }

    @Test
    public void testThrowsIfAlreadyOff() {
        try {
            engine.off();
        } catch (Exception e) {
            // noop
        }
        assertThrows(EngineIsOffException.class, engine::off);
    }
}
