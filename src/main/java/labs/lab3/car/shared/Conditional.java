package labs.lab3.car.shared;

public interface Conditional<T> {
    boolean test(final T valueToCheck);
}
