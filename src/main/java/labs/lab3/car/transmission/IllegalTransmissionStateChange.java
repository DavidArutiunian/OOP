package labs.lab3.car.transmission;

public class IllegalTransmissionStateChange extends Exception {
    IllegalTransmissionStateChange(String message) {
        super(message);
    }
}
