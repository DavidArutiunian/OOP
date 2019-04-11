package labs.lab3.car.car;

import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("SameParameterValue")
public class CarTest {
    private static final double EPS = 10e-15;
    private Car car = new Car();

    @Before
    public void setUp() {
        car = new Car();
    }

    @Test
    public void throwsOnTurnEngineOnIfAlreadyOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOnException.class, car::turnOnEngine);
    }

    @Test
    public void throwsOnTurnEngineOffIfAlreadyOff() {
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
    }

    @Test
    public void throwsOnSetSpeedIfEngineOff() {
        assertThrows(EngineIsOffException.class, () -> car.setSpeed(10));
        assertEquals(0, car.getSpeed(), EPS);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void setTransmissionNeutralOnInit() {
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void throwsOnIncreaseSpeedOnNeutral() throws Exception {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(15);
        assertThrows(IllegalSpeedChangeException.class, () -> car.setSpeed(20));
    }

    @Test
    public void setDecreasingSpeedOnNeutralGear() throws CarStateException, IllegalStateChangeException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(15);
        car.setSpeed(10);
        assertEquals(10, car.getSpeed(), EPS);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void speedIsZeroOnInit() {
        assertEquals(0, car.getSpeed(), EPS);
    }

    @Test
    public void throwsIfSettingSecondGearOnInitialStateEngineOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.SECOND));
    }

    @Test
    public void throwsIfSettingThirdGearOnInitialStateEngineOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.THIRD));
    }

    @Test
    public void throwsIfSettingFourthGearOnInitialStateEngineOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FOURTH));
    }

    @Test
    public void throwsIfSettingFifthGearOnInitialStateEngineOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FIFTH));
    }

    @Test
    public void setSecondGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(20);
        car.setGear(Gear.SECOND);
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void throwsWhenSetSecondGearOnAboveRangeSpeed() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(19);
        car.setGear(Gear.FIRST);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.SECOND));
        assertEquals(Gear.FIRST, car.getGear());
    }

    @Test
    public void setThirdGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(30);
        car.setGear(Gear.THIRD);
        assertEquals(Gear.THIRD, car.getGear());
    }

    @Test
    public void throwsWhenSetThirdGearOnAboveRangeSpeed() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(29);
        car.setGear(Gear.SECOND);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.THIRD));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void setFourthGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(50);
        car.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, car.getGear());
    }

    @Test
    public void throwsWhenSetFourthGearOnAboveRangeSpeed() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(39);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FOURTH));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void setFifthGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(50);
        car.setGear(Gear.FIFTH);
        assertEquals(Gear.FIFTH, car.getGear());
    }

    @Test
    public void throwsWhenSetFifthGearOnAboveRangeSpeed() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(49);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FIFTH));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void throwsOnSetReverseGearAgainWhenMovingReverse() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.REVERSE));
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void throwsOnSetOffEngineOnNonZeroSpeedAndNonNeutralGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(10);
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
        assertEquals(Gear.NEUTRAL, car.getGear());
        setGear(Gear.FIRST);
        setSpeed(0);
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
        assertEquals(Gear.FIRST, car.getGear());
    }

    @Test
    public void throwsOnSetPositiveSpeedOnMovingReverseOnNeutralGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        assertThrows(IllegalSpeedChangeException.class, () -> car.setSpeed(1));
    }

    @Test
    public void throwsOnSetNegativeSpeedOnMovingReverseOnNeutralGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        car.setSpeed(-5);
    }

    @Test
    public void throwsOnSetZeroSpeedOnMovingReverseOnNeutralGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        car.setSpeed(0);
    }

    @Test
    public void testWhenRunningBackwardsAt5ChangingTo4Works() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-5);
        setSpeed(-4);
        assertEquals(-4, car.getSpeed(), EPS);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    private void turnOnEngine() throws EngineIsOnException {
        car.turnOnEngine();
    }

    private void setSpeed(double speed) throws IllegalStateChangeException, CarStateException {
        car.setSpeed(speed);
    }

    private void setGear(Gear gear) throws IllegalStateChangeException {
        car.setGear(gear);
    }

    private void setSpeedOnReverseGearAndLeaveOnNeutralGear(double speed) throws EngineIsOnException, IllegalStateChangeException, CarStateException {
        turnOnEngine();
        setGear(Gear.REVERSE);
        setSpeed(speed);
        setGear(Gear.NEUTRAL);
    }

    private void setSpeedOnFirstGearAndLeaveOnNeutralGear(double speed) throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        turnOnEngine();
        setGear(Gear.FIRST);
        setSpeed(speed);
        setGear(Gear.NEUTRAL);
    }

    private void setSpeedOnSecondGearAndLaveOnNeutralGear(double speed) throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(30);
        setGear(Gear.SECOND);
        setSpeed(speed);
    }
}
