package com.example.wantedtestassigment.NumbersTask;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.example.wantedtestassigment.NumbersTask.enums.NumberWords.*;

// Написать функцию, возвращающую прописное написание стоимости (до тысяч 99 999.99).
// Входной параметр переменная типа bigDecimal
public class NumberToWords {

    /**
     * Метод перевода стоимости в прописную форму
     * Переводит числовое представление стоимости в прописной формат
     *
     * @param number стоимость
     * @return прописная форма стоимости
     */
    public static String numberToWords(BigDecimal number) {
        BigInteger integerPart = number.toBigInteger();
        int fractionalPart = number.remainder(BigDecimal.ONE).movePointRight(2).intValue();

        if (integerPart.compareTo(BigInteger.ZERO) == 0) {
            return "ноль рублей " + fractionalPartToString(fractionalPart);
        }

        StringBuilder words = new StringBuilder();

        if (integerPart.compareTo(new BigInteger("1000")) >= 0) {
            int thousandsPart = integerPart.divide(new BigInteger("1000")).intValue();
            words.append(thousandsToWords(thousandsPart)).append(" ");
            integerPart = integerPart.remainder(new BigInteger("1000"));
        }

        if (integerPart.compareTo(BigInteger.ZERO) > 0) {
            words.append(hundredsToWords(integerPart.intValue())).append(" ");
        }

        words.append("рублей ");

        words.append(fractionalPartToString(fractionalPart));

        return words.toString().trim();
    }

    /**
     * Перевод тысяч в надписную форму
     *
     * @param number число
     * @return надписная форма числа
     */
    private static String thousandsToWords(int number) {
        if (number < 10) {
            return THOUSANDS.getWords()[number];
        } else if (number < 20) {
            return UNITS.getWords()[number - 1] + " тысяч";
        } else {
            return TENS.getWords()[number / 10] +
                    (number % 10 != 0 ? " " + THOUSANDS.getWords()[number % 10] : " тысяч");
        }
    }

    /**
     * Перевод сотен в надписную форму
     *
     * @param number число
     * @return надписная форма числа
     */
    private static String hundredsToWords(int number) {
        if (number < 10) {
            return UNITS.getWords()[number];
        } else if (number < 20) {
            return TENS.getWords()[number / 10];
        } else if (number < 100) {
            return TENS.getWords()[number / 10] +
                    (number % 10 != 0 ? " " + UNITS.getWords()[number % 10] : "");
        } else {
            return HUNDREDS.getWords()[number / 100] +
                    (number % 100 != 0 ? " " + hundredsToWords(number % 100) : "");
        }
    }

    /**
     * Перевод копеек в надписную форму
     *
     * @param number число
     * @return надписная форма числа
     */
    private static String fractionalPartToString(int number) {
        if (number == 0) {
            return "00 копеек";
        } else if (number < 10) {
            return "0" + number + " копеек";
        } else {
            return number + " копеек";
        }
    }
}
