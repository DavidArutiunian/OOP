package labs.lab3.car;

import labs.lab3.car.engine.EngineIsOff;
import labs.lab3.car.engine.EngineIsOn;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalTransmissionStateChange;
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
        assertThrows(EngineIsOn.class, car::turnOnEngine);
    }

    @Test
    public void testCantTurnEngineOffIfAlreadyOff() {
        try {
            car.turnOffEngine();
        } catch (Exception e) {
            // noop
        }
        assertThrows(EngineIsOff.class, car::turnOffEngine);
    }

    @Test
    public void testCantSetSpeedIfEngineOff() throws EngineIsOff, IllegalTransmissionStateChange {
        car.setGear(Gear.NEUTRAL);
        car.turnOffEngine();
        assertThrows(EngineIsOff.class, () -> car.setSpeed(10));
        assertEquals(0, car.getSpeed(), DELTA);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void testTransmissionIsNeutralOnInit() {
        final var car = new Car();
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void testCantIncreaseSpeedOnNeutral() throws IllegalSpeedChange, IllegalTransmissionStateChange, EngineIsOff {
        car.setSpeed(15);
        car.setGear(Gear.NEUTRAL);
        assertThrows(IllegalSpeedChange.class, () -> car.setSpeed(20));
    }

    @Test
    public void testCanDecreaseSpeedOnNeutral() throws IllegalSpeedChange, IllegalTransmissionStateChange, EngineIsOff {
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
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.SECOND));
    }

    @Test
    public void testTransmissionFailsWhenSpeedConditionFails2() {
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.THIRD));
    }

    @Test
    public void testTransmissionFailsWhenSpeedConditionFails3() {
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.FOURTH));
    }

    @Test
    public void testTransmissionFailsWhenSpeedConditionFails4() {
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.FIFTH));
    }

    @Test
    public void testSetSecondGear() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(20);
        car.setGear(Gear.SECOND);
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void testSetSecondGearThrows() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(19);
        car.setGear(Gear.FIRST);
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.SECOND));
        assertEquals(Gear.FIRST, car.getGear());
    }

    @Test
    public void testSetThirdGear() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(30);
        car.setGear(Gear.THIRD);
        assertEquals(Gear.THIRD, car.getGear());
    }

    @Test
    public void testSetThirdGearThrows() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(29);
        car.setGear(Gear.SECOND);
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.THIRD));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void testSetFourthGear() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(40);
        car.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, car.getGear());
    }

    @Test
    public void testSetFourthGearThrows() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(39);
        car.setGear(Gear.THIRD);
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.FOURTH));
        assertEquals(Gear.THIRD, car.getGear());
    }

    @Test
    public void testSetFifthGear() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(50);
        car.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, car.getGear());
    }

    @Test
    public void testSetFifthGearThrows() throws IllegalTransmissionStateChange, IllegalSpeedChange, EngineIsOff {
        car.setSpeed(49);
        car.setGear(Gear.FOURTH);
        assertThrows(IllegalTransmissionStateChange.class, () -> car.setGear(Gear.FIFTH));
        assertEquals(Gear.FOURTH, car.getGear());
    }
}
