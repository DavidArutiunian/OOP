package labs.lab3.car.transmission;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOnException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TransmissionTest {
    private Engine engine = new Engine();
    private Transmission transmission = new Transmission();

    @Before
    public void setUp() {
        engine = new Engine();
        transmission = new Transmission();
    }

    // NEUTRAL

    @Test
    public void testIsNeutralOnInit() {
        assertEquals(Gear.NEUTRAL, transmission.getGear());
    }

    @Test
    public void testNeutralGear() throws IllegalStateChangeException {
        transmission.setGear(Gear.NEUTRAL, engine.getState(), 0);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, engine.getState(), 10);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, engine.getState(), 50);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, engine.getState(), 150);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, engine.getState(), -20);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
    }

    // FIRST

    @Test
    public void testFirstGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIRST, engine.getState(), 0);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void testFirstGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIRST, engine.getState(), 15);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void testFirstGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIRST, engine.getState(), 30);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void testFirstGearFails() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIRST, engine.getState(), 31));
    }

    // SECOND

    @Test
    public void testSecondGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.SECOND, engine.getState(), 20);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void testSecondGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.SECOND, engine.getState(), 35);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void testSecondGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.SECOND, engine.getState(), 50);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void testSecondGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND, engine.getState(), 19));
    }

    @Test
    public void testSecondGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND, engine.getState(), 51));
    }

    // THIRD

    @Test
    public void testThirdGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.THIRD, engine.getState(), 30);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void testThirdGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.THIRD, engine.getState(), 45);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void testThirdGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.THIRD, engine.getState(), 60);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void testThirdGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD, engine.getState(), 29));
    }

    @Test
    public void testThirdGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD, engine.getState(), 61));
    }

    // FOURTH

    @Test
    public void testFourthGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.FOURTH, engine.getState(), 40);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void testFourthGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.FOURTH, engine.getState(), 75);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void testFourthGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.FOURTH, engine.getState(), 90);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void testFourthGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH, engine.getState(), 39));
    }

    @Test
    public void testFourthGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH, engine.getState(), 91));
    }

    // FIFTH

    @Test
    public void testFifthGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIFTH, engine.getState(), 50);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void testFifthGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIFTH, engine.getState(), 100);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void testFifthGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIFTH, engine.getState(), 150);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void testFifthGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH, engine.getState(), 49));
    }

    @Test
    public void testFifthGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH, engine.getState(), 151));
    }

    // REVERSE

    @Test
    public void testReverseGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.REVERSE, engine.getState(), 0);
        assertEquals(Gear.REVERSE, transmission.getGear());
    }

    @Test
    public void testReverseGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), -21));
    }

    @Test
    public void testReverseGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), 1));
    }

    @Test
    public void testReverseGearFails3() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), 20));
    }

    @Test
    public void testReverseGearFails4() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), -1));
    }

    @Test
    public void testReverseGearFails5() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), -20));
    }

    private void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }
}
