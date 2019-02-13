package lab1.task2;

import io.BaseInputOutput;

public class Main {
    public static void main(String[] args) {
        try {
            BaseInputOutput.validate(args, 3);
            final int from = Integer.parseInt(args[0]);
            final int to = Integer.parseInt(args[1]);
            if (!Converter.isRadixValid(from) || !Converter.isRadixValid(to)) {
                throw new ArithmeticException("Invalid radix!");
            }
            final String value = args[2];
            if (!Converter.isValueValid(value, from)) {
                throw new ArithmeticException("Value do not match source radix!");
            }
            final Converter converter = new Converter(value, from);
            System.out.println(converter.convert(to));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
