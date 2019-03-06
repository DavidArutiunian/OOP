package labs.lab3.car.car;

import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CarTest {
    private static final double DELTA = 0.0005;
    private final Car car = new Car();

    @Before
    public void setUp() {
        try {
            car.turnOnEngine();
        } catch (Exception e) {
            // noop
        }
        try {
            car.setGear(Gear.FIRST);
        } catch (Exception e) {
            // noop
        }
        try {
            car.setSpeed(0);
        } catch (Exception e) {
            // noop
        }
    }

    @After
    public void tearDown() {
        try {
            car.setSpeed(0);
        } catch (Exception e) {
            // noop
        }
        try {
            car.setGear(Gear.NEUTRAL);
        } catch (Exception e) {
            // noop
        }
        try {
            car.turnOffEngine();
        } catch (Exception e) {
            // noop
        }
    }

    @Test
    public void testCantTurnEngineOnIfAlreadyOn() {
        try {
            car.turnOnEngine();
        } catch (Exception e) {
            // noop
        }
        assertThrows(EngineIsOnException.class, car::turnOnEngine);
    }

    @Test
    public void testCantTurnEngineOffIfAlreadyOff() {
        try {
            car.turnOffEngine();
        } catch (Exception e) {
            // noop
        }
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
    }

    @Test
    public void testCantSetSpeedIfEngineOff() throws EngineIsOffException, IllegalStateChangeException {
        car.setGear(Gear.NEUTRAL);
        car.turnOffEngine();
        assertThrows(EngineIsOffException.class, () -> car.setSpeed(10));
        assertEquals(0, car.getSpeed(), DELTA);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void testTransmissionIsNeutralOnInit() {
        final var car = new Car();
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void testCantIncreaseSpeedOnNeutral() throws Exception {
        car.setSpeed(15);
        car.setGear(Gear.NEUTRAL);
        assertThrows(IllegalSpeedChangeException.class, () -> car.setSpeed(20));
    }

    @Test
    public void testCanDecreaseSpeedOnNeutral() throws CarStateException, IllegalStateChangeException {
        car.setSpeed(15);
        car.setGear(Gear.NEUTRAL);
        car.setSpeed(10);
        assertEquals(10, car.getSpeed(), DELTA);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void testSpeedIsZeroOnInit() {
        assertEquals(0, car.getSpeed(), DELTA);
    }

    @Test
    public void testTransmissionFailsWhenSpeedConditionFails1() {
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.SECOND));
    }

    @Test
    public void testTransmissionFailsWhenSpeedConditionFails2() {
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.THIRD));
    }

    @Test
    public void testTransmissionFailsWhenSpeedConditionFails3() {
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FOURTH));
    }

    @Test
    public void testTransmissionFailsWhenSpeedConditionFails4() {
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FIFTH));
    }

    @Test
    public void testSetSecondGear() throws IllegalStateChangeException, CarStateException {
        car.setSpeed(20);
        car.setGear(Gear.SECOND);
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void testSetSecondGearThrows() throws IllegalStateChangeException, CarStateException {
        car.setSpeed(19);
        car.setGear(Gear.FIRST);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.SECOND));
        assertEquals(Gear.FIRST, car.getGear());
    }

    @Test
    public void testSetThirdGear() throws IllegalStateChangeException, CarStateException {
        car.setSpeed(30);
        car.setGear(Gear.THIRD);
        assertEquals(Gear.THIRD, car.getGear());
    }

    @Test
    public void testSetThirdGearThrows() throws IllegalStateChangeException, CarStateException {
        car.setSpeed(29);
        car.setGear(Gear.SECOND);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.THIRD));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void testSetFourthGear() throws IllegalStateChangeException, CarStateException {
        car.setGear(Gear.FIRST);
        car.setSpeed(30);
        car.setGear(Gear.THIRD);
        car.setSpeed(40);
        car.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, car.getGear());
    }

    @Test
    public void testSetFourthGearThrows() throws IllegalStateChangeException, CarStateException {
        car.setGear(Gear.FIRST);
        car.setSpeed(30);
        car.setGear(Gear.THIRD);
        car.setSpeed(39);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FOURTH));
        assertEquals(Gear.THIRD, car.getGear());
    }

    @Test
    public void testSetFifthGear() throws IllegalStateChangeException, CarStateException {
        car.setGear(Gear.FIRST);
        car.setSpeed(30);
        car.setGear(Gear.THIRD);
        car.setSpeed(50);
        car.setGear(Gear.FIFTH);
        assertEquals(Gear.FIFTH, car.getGear());
    }

    @Test
    public void testSetFifthGearThrows() throws IllegalStateChangeException, CarStateException {
        car.setGear(Gear.FIRST);
        car.setSpeed(30);
        car.setGear(Gear.THIRD);
        car.setSpeed(49);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FIFTH));
        assertEquals(Gear.THIRD, car.getGear());
    }

    @Test
    public void testCantSetReverseGearAgainWhenMovingReverse() throws IllegalStateChangeException, CarStateException {
        car.setGear(Gear.REVERSE);
        car.setSpeed(-10);
        car.setGear(Gear.NEUTRAL);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.REVERSE));
        assertEquals(Gear.NEUTRAL, car.getGear());
        car.setSpeed(0);
        car.setGear(Gear.REVERSE);
        assertEquals(Gear.REVERSE, car.getGear());
    }

    @Test
    public void testCantOffEngineOnNonZeroSpeedAndNonNeutralGear() throws IllegalStateChangeException, CarStateException {
        car.setGear(Gear.FIRST);
        car.setSpeed(10);
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
        assertEquals(Gear.FIRST, car.getGear());
        car.setSpeed(0);
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
        assertEquals(Gear.FIRST, car.getGear());
        car.setGear(Gear.NEUTRAL);
        car.turnOffEngine();
    }
}
