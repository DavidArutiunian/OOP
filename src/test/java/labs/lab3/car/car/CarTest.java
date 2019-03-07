package labs.lab3.car.car;

import labs.lab3.car.engine.EngineIsOffException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CarTest {
    private static final double DELTA = 0.0005;
    private Car car = new Car();

    @Before
    public void setUp() {
        car = new Car();
    }

    @Test
    public void carCantTurnEngineOnIfAlreadyOn() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(EngineIsOnException.class, car::turnOnEngine);
    }

    @Test
    public void carCantTurnEngineOffIfAlreadyOff() {
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
    }

    @Test
    public void carCantSetSpeedIfEngineOff() {
        assertThrows(EngineIsOffException.class, () -> car.setSpeed(10));
        assertEquals(0, car.getSpeed(), DELTA);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void carTransmissionIsNeutralOnInit() {
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void carCantIncreaseSpeedOnNeutral() throws Exception {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(15);
        assertThrows(IllegalSpeedChangeException.class, () -> car.setSpeed(20));
    }

    @Test
    public void carCanDecreaseSpeedOnNeutral() throws CarStateException, IllegalStateChangeException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(15);
        car.setSpeed(10);
        assertEquals(10, car.getSpeed(), DELTA);
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void carSpeedIsZeroOnInit() {
        assertEquals(0, car.getSpeed(), DELTA);
    }

    @Test
    public void carTransmissionFailsWhenSpeedConditionFails1() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.SECOND));
    }

    @Test
    public void carTransmissionFailsWhenSpeedConditionFails2() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.THIRD));
    }

    @Test
    public void carTransmissionFailsWhenSpeedConditionFails3() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FOURTH));
    }

    @Test
    public void carTransmissionFailsWhenSpeedConditionFails4() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FIFTH));
    }

    @Test
    public void carSetSecondGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(20);
        car.setGear(Gear.SECOND);
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void carSetSecondGearThrows() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(19);
        car.setGear(Gear.FIRST);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.SECOND));
        assertEquals(Gear.FIRST, car.getGear());
    }

    @Test
    public void carSetThirdGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(30);
        car.setGear(Gear.THIRD);
        assertEquals(Gear.THIRD, car.getGear());
    }

    @Test
    public void carSetThirdGearThrows() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(29);
        car.setGear(Gear.SECOND);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.THIRD));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void carSetFourthGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(50);
        car.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, car.getGear());
    }

    @Test
    public void carSetFourthGearThrows() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(39);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FOURTH));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void carSetFifthGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(50);
        car.setGear(Gear.FIFTH);
        assertEquals(Gear.FIFTH, car.getGear());
    }

    @Test
    public void carSetFifthGearThrows() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnSecondGearAndLaveOnNeutralGear(49);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.FIFTH));
        assertEquals(Gear.SECOND, car.getGear());
    }

    @Test
    public void carCantSetReverseGearAgainWhenMovingReverse() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        assertThrows(IllegalStateChangeException.class, () -> car.setGear(Gear.REVERSE));
        assertEquals(Gear.NEUTRAL, car.getGear());
    }

    @Test
    public void carCantOffEngineOnNonZeroSpeedAndNonNeutralGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(10);
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
        assertEquals(Gear.NEUTRAL, car.getGear());
        setGear(Gear.FIRST);
        setSpeed(0);
        assertThrows(EngineIsOffException.class, car::turnOffEngine);
        assertEquals(Gear.FIRST, car.getGear());
    }

    @Test
    public void carCantSetPositiveSpeedOnMovingReverseOnNeutralGear() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        assertThrows(IllegalSpeedChangeException.class, () -> car.setSpeed(1));
    }

    @Test
    public void carCanSetNegativeSpeedOnMovingReverseOnNeutralGear1() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        car.setSpeed(-5);
    }

    @Test
    public void carCanSetNegativeSpeedOnMovingReverseOnNeutralGear2() throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnReverseGearAndLeaveOnNeutralGear(-10);
        car.setSpeed(0);
    }

    private void turnOnEngine() throws EngineIsOnException {
        car.turnOnEngine();
    }

    private void setSpeed(final double speed) throws IllegalStateChangeException, CarStateException {
        car.setSpeed(speed);
    }

    private void setGear(final Gear gear) throws IllegalStateChangeException {
        car.setGear(gear);
    }

    private void setSpeedOnReverseGearAndLeaveOnNeutralGear(final double speed) throws EngineIsOnException, IllegalStateChangeException, CarStateException {
        turnOnEngine();
        setGear(Gear.REVERSE);
        setSpeed(speed);
        setGear(Gear.NEUTRAL);
    }

    private void setSpeedOnFirstGearAndLeaveOnNeutralGear(final double speed) throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        turnOnEngine();
        setGear(Gear.FIRST);
        setSpeed(speed);
        setGear(Gear.NEUTRAL);
    }

    private void setSpeedOnSecondGearAndLaveOnNeutralGear(final double speed) throws IllegalStateChangeException, CarStateException, EngineIsOnException {
        setSpeedOnFirstGearAndLeaveOnNeutralGear(30);
        setGear(Gear.SECOND);
        setSpeed(speed);
    }
}
