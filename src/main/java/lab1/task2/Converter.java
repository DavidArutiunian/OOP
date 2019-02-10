package lab1.task2;

import java.util.ArrayList;
import java.util.List;

class Converter {
    private static final int MIN_RADIX = 2;
    private static final int MAX_RADIX = 36;
    private static final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRASUVWXYZ";
    private List<Integer> number = new ArrayList<>();
    private int radix;
    private boolean negative = false;

    Converter(final String number, int radix) {
        this.radix = radix;
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch == SpecialChars.MINUS.toChar()) {
                negative = true;
            }
            if (SYMBOLS.indexOf(ch) == -1) {
                continue;
            }
            this.number.add(Converter.charToInt(ch, radix));
        }
    }

    static boolean isRadixValid(final int radix) {
        return radix >= MIN_RADIX && radix <= MAX_RADIX;
    }

    static boolean isValueValid(final String value, final int radix) {
        for (int i = 0; i < value.length(); i++) {
            final int ch = charToInt(value.charAt(i), radix);
            if (ch > radix - 1) {
                return false;
            }
        }
        return true;
    }

    private static int charToInt(final char character, final int radix) {
        final boolean isMoreEqThenZero = character >= SpecialChars.ZERO.toChar();
        final boolean isLessEqThenNine = character <= SpecialChars.NINE.toChar();
        final boolean isLessThenRadix = (character - SpecialChars.ZERO.toChar()) < radix;
        if (isMoreEqThenZero && isLessEqThenNine && isLessThenRadix) {
            return character - SpecialChars.ZERO.toChar();
        } else {
            final boolean isMoreEqThenA = character >= SpecialChars.A_LETTER.toChar();
            final boolean isLessEqThenZ = character <= SpecialChars.Z_LETTER.toChar();
            final boolean isALessThenRadix = (character - SpecialChars.A_LETTER.toChar()) < radix;
            if (isMoreEqThenA && isLessEqThenZ && isALessThenRadix) {
                return character - SpecialChars.A_LETTER.toChar() + SpecialNumbers.BASE.toNumber();
            } else {
                return -1;
            }
        }
    }

    private static char intToChar(int character) {
        final boolean isMoreThenZero = character >= 0;
        final boolean isLessThenNine = character <= 9;
        if (isMoreThenZero && isLessThenNine) {
            return (char) (character + SpecialChars.ZERO.toChar());
        } else {
            return (char) (character + SpecialChars.A_LETTER.toChar() - SpecialNumbers.BASE.toNumber());
        }
    }

    private int getNextNumber(int nextRadix) {
        int temp = 0;
        for (int i = 0; i < number.size(); i++) {
            temp = temp * radix + number.get(i);
            number.set(i, temp / nextRadix);
            temp = temp % nextRadix;
        }
        return temp;
    }

    private boolean hasOnlyZeroes() {
        for (Integer integer : number) {
            if (integer != 0) {
                return false;
            }
        }
        return true;
    }

    String convert(int nextRadix) {
        List<Integer> buffer = new ArrayList<>();
        do {
            buffer.add(getNextNumber(nextRadix));
        } while (!hasOnlyZeroes());
        StringBuilder temp = new StringBuilder();
        for (int i = buffer.size() - 1; i >= 0; i--) {
            temp.append(intToChar(buffer.get(i)));
        }
        if (negative) {
            temp.insert(0, SpecialChars.MINUS.toChar());
        }
        return temp.toString();
    }

    private enum SpecialChars {
        ZERO('0'),
        NINE('9'),
        A_LETTER('A'),
        Z_LETTER('Z'),
        MINUS('-');

        private final char character;

        SpecialChars(char text) {
            this.character = text;
        }

        public char toChar() {
            return character;
        }
    }

    private enum SpecialNumbers {
        BASE(10);

        private final int number;

        SpecialNumbers(int i) {
            number = i;
        }

        public int toNumber() {
            return number;
        }
    }
}
