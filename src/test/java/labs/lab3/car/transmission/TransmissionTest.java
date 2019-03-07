package labs.lab3.car.transmission;

import labs.lab3.car.car.CarState;
import labs.lab3.car.car.CarStateException;
import labs.lab3.car.engine.Engine;
import labs.lab3.car.engine.EngineIsOnException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransmissionTest {
    private CarState state = new CarState();
    private Engine engine = new Engine(state);
    private Transmission transmission = new Transmission(state);

    @Before
    public void setUp() {
        state = new CarState();
        transmission = new Transmission(state);
        engine = new Engine(state);
    }

    // NEUTRAL

    @Test
    public void iNeutralOnInit() {
        assertEquals(Gear.NEUTRAL, state.getTransmissionGear());
    }

    @Test
    public void canSetNeutralGearOnAnySpeedEngineOn() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setGear(Gear.FIRST);
        setSpeed(30);
        transmission.setGear(Gear.NEUTRAL);
        assertEquals(Gear.NEUTRAL, state.getTransmissionGear());
        setGear(Gear.SECOND);
        setSpeed(50);
        transmission.setGear(Gear.NEUTRAL);
        assertEquals(Gear.NEUTRAL, state.getTransmissionGear());
        setGear(Gear.THIRD);
        setSpeed(60);
        transmission.setGear(Gear.NEUTRAL);
        assertEquals(Gear.NEUTRAL, state.getTransmissionGear());
        setGear(Gear.FOURTH);
        setSpeed(90);
        transmission.setGear(Gear.NEUTRAL);
        assertEquals(Gear.NEUTRAL, state.getTransmissionGear());
        setGear(Gear.FIFTH);
        setSpeed(150);
        transmission.setGear(Gear.NEUTRAL);
        assertEquals(Gear.NEUTRAL, state.getTransmissionGear());
    }

    @Test
    public void cantSetNeutralGearWhenEngineIsOff() {
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIRST));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.SECOND));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.THIRD));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FOURTH));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIFTH));
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.REVERSE));
    }

    // FIRST

    @Test
    public void setFirstGearOnMinRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedFirstGear(0);
        transmission.setGear(Gear.FIRST);
        assertEquals(Gear.FIRST, state.getTransmissionGear());
    }

    @Test
    public void setFirstGearOnAcceptableRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedFirstGear(15);
        transmission.setGear(Gear.FIRST);
        assertEquals(Gear.FIRST, state.getTransmissionGear());
    }

    @Test
    public void setFirstGearOnMaxRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedFirstGear(30);
        transmission.setGear(Gear.FIRST);
        assertEquals(Gear.FIRST, state.getTransmissionGear());
    }

    @Test
    public void firstGearThrowsOnAboveRangeSpeed() throws EngineIsOnException, IllegalStateChangeException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedSecondGear(31);
        assertThrows(IllegalStateChangeException.class, () -> transmission.setGear(Gear.FIRST));
    }

    // SECOND

    @Test
    public void setSecondGearOnMinRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedSecondGear(20);
        transmission.setGear(Gear.SECOND);
        assertEquals(Gear.SECOND, state.getTransmissionGear());
    }

    @Test
    public void setSecondGearOnAcceptableRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedSecondGear(30);
        transmission.setGear(Gear.SECOND);
        assertEquals(Gear.SECOND, state.getTransmissionGear());
    }

    @Test
    public void setSecondGearOnMaxRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedSecondGear(50);
        transmission.setGear(Gear.SECOND);
        assertEquals(Gear.SECOND, state.getTransmissionGear());
    }

    @Test
    public void secondGearThrowsOnBelowRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedSecondGear(19));
    }

    @Test
    public void secondGearThrowsOnAboveRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedSecondGear(51));
    }

    // THIRD

    @Test
    public void setThirdGearOnMinRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedSecondGear(30);
        transmission.setGear(Gear.THIRD);
        assertEquals(Gear.THIRD, state.getTransmissionGear());
    }

    @Test
    public void setThirdGearOnAcceptableRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedSecondGear(45);
        transmission.setGear(Gear.THIRD);
        assertEquals(Gear.THIRD, state.getTransmissionGear());
    }

    @Test
    public void setThirdGearOnMaxRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedThirdGear(60);
        transmission.setGear(Gear.THIRD);
        assertEquals(Gear.THIRD, state.getTransmissionGear());
    }

    @Test
    public void thirdGearThrowsOnBelowRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedThirdGear(29));
    }

    @Test
    public void thirdGearThrowsOnAboveRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedThirdGear(61));
    }

    // FOURTH

    @Test
    public void setFourthGearOnMinRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedSecondGear(40);
        transmission.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, state.getTransmissionGear());
    }

    @Test
    public void setFourthGearOnAcceptableRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedFourthGear(75);
        transmission.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, state.getTransmissionGear());
    }

    @Test
    public void setFourthGearOnMaxRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedFourthGear(90);
        transmission.setGear(Gear.FOURTH);
        assertEquals(Gear.FOURTH, state.getTransmissionGear());
    }

    @Test
    public void fourthGearThrowsOnBelowRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedFourthGear(39));
    }

    @Test
    public void fourthGearThrowsOnAboveRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedFourthGear(91));
    }

    // FIFTH

    @Test
    public void setFifthGearOnMinRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedThirdGear(50);
        transmission.setGear(Gear.FIFTH);
        assertEquals(Gear.FIFTH, state.getTransmissionGear());
    }

    @Test
    public void setFifthGearOnAcceptableRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedFifthGear(100);
        transmission.setGear(Gear.FIFTH);
        assertEquals(Gear.FIFTH, state.getTransmissionGear());
    }

    @Test
    public void setFifthGearOnMaxRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedFifthGear(150);
        transmission.setGear(Gear.FIFTH);
        assertEquals(Gear.FIFTH, state.getTransmissionGear());
    }

    @Test
    public void fifthGearThrowsOnBelowRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedFifthGear(49));
    }

    @Test
    public void fifthGearThrowsOnAboveRangeSpeed() throws EngineIsOnException {
        turnOnEngine();
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedFifthGear(151));
    }

    // REVERSE

    @Test
    public void setReverseGearOnEngineOnAndIdle() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedReverseGear(0);
        transmission.setGear(Gear.REVERSE);
        assertEquals(Gear.REVERSE, state.getTransmissionGear());
    }

    @Test
    public void reverseGearThrowsOnBelowRangeSpeed() {
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedReverseGear(-21));
    }

    @Test
    public void reverseGearThrowsOnAboveRangeSpeed() {
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedReverseGear(1));
    }

    @Test
    public void reverseGearThrowsOnUnacceptableSpeed() {
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedReverseGear(20));
    }

    @Test
    public void reverseGearThrowsOnSetAfterAlreadySetOnMinRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedReverseGear(-10);
        setGear(Gear.NEUTRAL);
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedReverseGear(-1));
    }

    @Test
    public void reverseGearThrowsOnSetAfterAlreadySetOnMaxRangeSpeed() throws IllegalStateChangeException, EngineIsOnException, CarStateException {
        turnOnEngine();
        setStateToMaxSpeedReverseGear(-10);
        setGear(Gear.NEUTRAL);
        assertThrows(IllegalStateChangeException.class, () -> setStateToMaxSpeedReverseGear(-20));
    }

    private void turnOnEngine() throws EngineIsOnException {
        engine.on();
    }

    private void setGear(final Gear gear) throws IllegalStateChangeException {
        transmission.setGear(gear);
    }

    private void setSpeed(final double speed) throws IllegalStateChangeException, CarStateException {
        transmission.testConditionsForSpeed(speed);
        state.setCarSpeed(speed);
    }

    private void setStateToMaxSpeedFirstGear(final double speed) throws IllegalStateChangeException, CarStateException {
        setGear(Gear.FIRST);
        setSpeed(speed);
    }

    private void setStateToMaxSpeedSecondGear(final double speed) throws IllegalStateChangeException, CarStateException {
        setGear(Gear.FIRST);
        setSpeed(30);
        setGear(Gear.SECOND);
        setSpeed(speed);
    }

    private void setStateToMaxSpeedThirdGear(final double speed) throws IllegalStateChangeException, CarStateException {
        setGear(Gear.FIRST);
        setSpeed(30);
        setGear(Gear.THIRD);
        setSpeed(speed);
    }

    private void setStateToMaxSpeedFifthGear(final double speed) throws IllegalStateChangeException, CarStateException {
        setGear(Gear.FIRST);
        setSpeed(30);
        setGear(Gear.THIRD);
        setSpeed(60);
        setGear(Gear.FIFTH);
        setSpeed(speed);
    }

    private void setStateToMaxSpeedFourthGear(final double speed) throws IllegalStateChangeException, CarStateException {
        setGear(Gear.FIRST);
        setSpeed(30);
        setGear(Gear.THIRD);
        setSpeed(60);
        setGear(Gear.FOURTH);
        setSpeed(speed);
    }

    private void setStateToMaxSpeedReverseGear(final double speed) throws IllegalStateChangeException, CarStateException {
        setGear(Gear.REVERSE);
        setSpeed(speed);
    }
}
