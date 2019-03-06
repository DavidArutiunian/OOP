package labs.lab3.car.transmission;

import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TransmissionTest {
    private final Engine engine = new Engine();
    private final Transmission transmission = new Transmission(engine);

    @Before
    public void setUp() throws EngineIsOnException {
        engine.on();
    }

    @After
    public void tearDown() throws EngineIsOffException {
        engine.off();
    }

    // NEUTRAL

    @Test
    public void testIsNeutralOnInit() {
        assertEquals(Gear.NEUTRAL, transmission.getGear());
    }

    @Test
    public void testNeutralGear() throws IllegalStateChangeException {
        transmission.setGear(Gear.NEUTRAL, 0);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, 10);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, 50);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, 150);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
        transmission.setGear(Gear.NEUTRAL, -20);
        assertEquals(Gear.NEUTRAL, transmission.getGear());
    }

    // FIRST

    @Test
    public void testFirstGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIRST, 0);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void testFirstGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIRST, 15);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void testFirstGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIRST, 30);
        assertEquals(Gear.FIRST, transmission.getGear());
    }

    @Test
    public void testFirstGearFails() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIRST, 31));
    }

    // SECOND

    @Test
    public void testSecondGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.SECOND, 20);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void testSecondGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.SECOND, 35);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void testSecondGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.SECOND, 50);
        assertEquals(Gear.SECOND, transmission.getGear());
    }

    @Test
    public void testSecondGearFails1() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND, 19));
    }

    @Test
    public void testSecondGearFails2() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND, 51));
    }

    // THIRD

    @Test
    public void testThirdGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.THIRD, 30);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void testThirdGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.THIRD, 45);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void testThirdGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.THIRD, 60);
        assertEquals(Gear.THIRD, transmission.getGear());
    }

    @Test
    public void testThirdGearFails1() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD, 29));
    }

    @Test
    public void testThirdGearFails2() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD, 61));
    }

    // FOURTH

    @Test
    public void testFourthGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.FOURTH, 40);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void testFourthGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.FOURTH, 75);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void testFourthGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.FOURTH, 90);
        assertEquals(Gear.FOURTH, transmission.getGear());
    }

    @Test
    public void testFourthGearFails1() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH, 39));
    }

    @Test
    public void testFourthGearFails2() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH, 91));
    }

    // FIFTH

    @Test
    public void testFifthGear1() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIFTH, 50);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void testFifthGear2() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIFTH, 100);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void testFifthGear3() throws IllegalStateChangeException {
        transmission.setGear(Gear.FIFTH, 150);
        assertEquals(Gear.FIFTH, transmission.getGear());
    }

    @Test
    public void testFifthGearFails1() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH, 49));
    }

    @Test
    public void testFifthGearFails2() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH, 151));
    }

    // REVERSE

    @Test
    public void testReverseGear() throws IllegalStateChangeException {
        transmission.setGear(Gear.REVERSE, 0);
        assertEquals(Gear.REVERSE, transmission.getGear());
    }

    @Test
    public void testReverseGearFails1() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, -21));
    }

    @Test
    public void testReverseGearFails2() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, 1));
    }

    @Test
    public void testReverseGearFails3() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, 20));
    }

    @Test
    public void testReverseGearFails4() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE, -1));
    }
}
