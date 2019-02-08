package lab1.task2;

public class Main {
    public static void main(String[] args) {
        final int sourceNotation = Integer.parseInt(args[0]);
        final int destinationNotation = Integer.parseInt(args[1]);
        final String value = args[2];
        final Converter converter = new Converter(value, sourceNotation);
        System.out.println(converter.convert(destinationNotation));
    }
}
