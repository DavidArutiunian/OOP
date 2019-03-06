package labs.lab3.car.car;

class IllegalSpeedChangeException extends CarStateException {
    IllegalSpeedChangeException(String message) {
        super(message);
    }
}
