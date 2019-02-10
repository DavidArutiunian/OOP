package lab1.task2;

public class Main {
    public static void main(String[] args) {
        final int from = Integer.parseInt(args[0]);
        final int to = Integer.parseInt(args[1]);
        if (!Converter.isRadixValid(from) || !Converter.isRadixValid(to)) {
            System.err.println("Invalid radix!");
            return;
        }
        final String value = args[2];
        if (!Converter.isValueValid(value, from)) {
            System.err.println("Value do not match source radix!");
            return;
        }
        final Converter converter = new Converter(value, from);
        System.out.println(converter.convert(to));
    }
}
