package labs.lab3.car.engine;

import labs.lab3.car.car.CarStateException;

public class EngineIsOffException extends CarStateException {
    public EngineIsOffException(String message) {
        super(message);
    }
}
