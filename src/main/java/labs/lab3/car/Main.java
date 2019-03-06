package labs.lab3.car;

import labs.lab3.car.car.Car;
import labs.lab3.car.car.CarStateException;
import labs.lab3.car.engine.EngineIsOnException;
import labs.lab3.car.transmission.Gear;
import labs.lab3.car.transmission.IllegalStateChangeException;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final var car = new Car();
        final var scanner = new Scanner(System.in);
        final Map<String, Command> commands = getCommandsMap();
        boolean running = true;
        while (running) {
            final String input = scanner.next().toLowerCase();
            if (!commands.containsKey(input)) {
                continue;
            }
            try {
                running = performCommand(commands.get(input), car, scanner);
            } catch (InputMismatchException e) {
                System.err.println("Incorrect input!");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private static Map<String, Command> getCommandsMap() {
        final Map<String, Command> commands = new HashMap<>();
        commands.put("info", Command.INFO);
        commands.put("engineon", Command.ENGINE_ON);
        commands.put("engineoff", Command.ENGINE_OFF);
        commands.put("setgear", Command.SET_GEAR);
        commands.put("setspeed", Command.SET_SPEED);
        commands.put("exit", Command.EXIT);
        return commands;
    }

    private static boolean performCommand(final Command command, final Car car, final Scanner scanner) throws EngineIsOnException, CarStateException, IOException, IllegalStateChangeException {
        boolean running = true;
        switch (command) {
            case INFO:
                printCarInfo(car);
                break;
            case ENGINE_ON:
                car.turnOnEngine();
                break;
            case ENGINE_OFF:
                car.turnOffEngine();
                break;
            case SET_GEAR:
                setCarGear(car, scanner);
                break;
            case SET_SPEED:
                setCarSpeed(car, scanner);
                break;
            case EXIT:
                running = false;
                break;
            default:
                break;
        }
        return running;
    }

    private static void printCarInfo(final Car car) {
        System.out.println("Engine is: " + car.getEngineState().name());
        if (car.getSpeed() != 0) {
            System.out.println("Moving: " + (car.getSpeed() > 0 ? "FORWARD" : "BACKWARD"));
        }
        System.out.println("Speed: " + car.getSpeed());
        System.out.println("Gear: " + car.getGear().name());
    }

    private static void setCarGear(final Car car, final Scanner scanner) throws IOException, IllegalStateChangeException {
        final int gear = scanner.nextInt() + 1;
        if (gear < Gear.REVERSE.ordinal() || gear > Gear.FIFTH.ordinal()) {
            throw new IOException("Unsupported gear value!");
        }
        car.setGear(Gear.values()[gear]);
    }

    private static void setCarSpeed(final Car car, final Scanner scanner) throws CarStateException {
        final double speed = scanner.nextDouble();
        car.setSpeed(speed);
    }
}
