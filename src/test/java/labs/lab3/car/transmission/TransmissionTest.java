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
    public void transmissionIsNeutralOnInit() {
        assertEquals(Gear.NEUTRAL, transmission.getGear());
    }

    @Test
    public void transmissionNeutralGear() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
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

    @Test
    public void transmissionCantSetNeutralGearWhenEngineIsOff() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIRST, engine.getState(), 0));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND, engine.getState(), 0));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD, engine.getState(), 0));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH, engine.getState(), 0));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH, engine.getState(), 0));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), 0));
    }

    // FIRST

    @Test
    public void transmissionFirstGear1() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FIRST, engine.getState(), 0);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void transmissionFirstGear2() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FIRST, engine.getState(), 15);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void transmissionFirstGear3() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FIRST, engine.getState(), 30);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void transmissionFirstGearFails() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIRST, engine.getState(), 31));
    }

    // SECOND

    @Test
    public void transmissionSecondGear1() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.SECOND, engine.getState(), 20);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void transmissionSecondGear2() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.SECOND, engine.getState(), 35);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void transmissionSecondGear3() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.SECOND, engine.getState(), 50);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void transmissionSecondGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND, engine.getState(), 19));
    }

    @Test
    public void transmissionSecondGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND, engine.getState(), 51));
    }

    // THIRD

    @Test
    public void transmissionThirdGear1() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.THIRD, engine.getState(), 30);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void transmissionThirdGear2() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.THIRD, engine.getState(), 45);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void transmissionThirdGear3() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.THIRD, engine.getState(), 60);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void transmissionThirdGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD, engine.getState(), 29));
    }

    @Test
    public void transmissionThirdGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD, engine.getState(), 61));
    }

    // FOURTH

    @Test
    public void transmissionFourthGear1() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FOURTH, engine.getState(), 40);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void transmissionFourthGear2() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FOURTH, engine.getState(), 75);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void transmissionFourthGear3() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FOURTH, engine.getState(), 90);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void transmissionFourthGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH, engine.getState(), 39));
    }

    @Test
    public void transmissionFourthGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH, engine.getState(), 91));
    }

    // FIFTH

    @Test
    public void transmissionFifthGear1() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FIFTH, engine.getState(), 50);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void transmissionFifthGear2() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FIFTH, engine.getState(), 100);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void transmissionFifthGear3() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.FIFTH, engine.getState(), 150);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void transmissionFifthGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH, engine.getState(), 49));
    }

    @Test
    public void transmissionFifthGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH, engine.getState(), 151));
    }

    // REVERSE

    @Test
    public void transmissionReverseGear1() throws IllegalStateChangeException, EngineIsOnException {
        turnOnEngine();
        transmission.setGear(Gear.REVERSE, engine.getState(), 0);
        assertEquals(Gear.REVERSE, transmission.getGear());
    }

    @Test
    public void transmissionReverseGearFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), -21));
    }

    @Test
    public void transmissionReverseGearFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), 1));
    }

    @Test
    public void transmissionReverseGearFails3() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), 20));
    }

    @Test
    public void transmissionReverseGearFails4() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), -1));
    }

    @Test
    public void transmissionReverseGearFails5() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, engine.getState(), -20));
    }

    private void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }
}
