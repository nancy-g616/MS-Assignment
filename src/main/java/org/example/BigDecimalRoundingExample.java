package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalRoundingExample {

    public static void main(String[] args) {
        String input = "12345.123456";
        String expected = "12345.12346";

        int precision = 25;
        int scale = 5;

        BigDecimal result = roundToPrecisionAndScale(input, precision, scale);

        System.out.println("Rounded result: " + result.toPlainString());

        boolean isEqual = result.compareTo(new BigDecimal(expected)) == 0;
        System.out.println("Matches expected: " + isEqual);
    }

    private static BigDecimal roundToPrecisionAndScale(String value, int precision, int scale) {
        BigDecimal bd = new BigDecimal(value);

        bd = bd.setScale(scale, RoundingMode.HALF_UP);

        if (bd.precision() > precision) {
            throw new ArithmeticException("Value exceeds column precision after rounding: " + bd);
        }

        return bd;
    }
}

