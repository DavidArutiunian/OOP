package lab1.task2;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRASUVWXYZ";
    private List<Integer> number = new ArrayList<Integer>();
    private int radix;
    private boolean negative = false;

    public Converter(final String number, int radix) {
        this.radix = radix;
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch == SpecialChars.MINUS.toChar()) {
                negative = true;
            }
            if (SYMBOLS.indexOf(ch) == -1) {
                continue;
            }
            this.number.add(charToInt(ch));
        }
    }

    private int charToInt(char character) {
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

    private char intToChar(int character) {
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
            temp = temp * this.radix + number.get(i);
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

    public String convert(int nextRadix) {
        List<Integer> buffer = new ArrayList<Integer>();
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
