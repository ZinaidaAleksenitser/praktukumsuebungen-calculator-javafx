package com.example.javafx;


import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @ParameterizedTest
    @CsvSource({
            "12., 5, false",
            "12., ., true",
            "0.15, 5, false",
            "101.594588, ., true",
            "'', ., false"
    })
    public void testIsDuplicateDecimalPoint(String value, String newDigit, boolean expected){
        boolean actual = calculator.isDuplicateDecimalPoint(value, newDigit);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            // Addition
            "1, 1, +, 2",
            "2.5, 3.5, +, 6",
            "-5, 3, +, -2",

            // Subtraction
            "10, 5, -, 5",
            "3, 8, -, -5",
            "5.5, 2.5, -, 3",

            // Multiplication
            "4, 5, *, 20",
            "-3, 4, *, -12",
            "0, 100, *, 0",

            // Division
            "10, 2, /, 5",
            "7.5, 2.5, /, 3",
            "9, 2, /, 4.5",
            "-10, 4, /, -2.5"
    })
    public void testCalculate(BigDecimal value1, BigDecimal value2, String operation, BigDecimal expected){
        BigDecimal result = calculator.calculate(value1, value2, operation);
        assertEquals(expected.stripTrailingZeros(), result.stripTrailingZeros());
    }

    @ParameterizedTest
    @CsvSource({
        "4, 0",
        "0, 0",
        "-2.465, 0",
        "4694, 0",
        "0.000002, 0",
    })
    public void testCalculateDivideByZero(BigDecimal value1, BigDecimal value2){
        assertThrows(ArithmeticException.class, () -> calculator.calculate(value1, value2, "/"));
    }

    @ParameterizedTest
    @CsvSource({
            "1, -1",
            "6, -6",
            "0, 0",
            "-45684654, 45684654",
    })
    public void testGetPlusMinusValue( BigDecimal value, BigDecimal expected){
        BigDecimal result = calculator.getPlusMinusValue(value);
        assertEquals(expected.stripTrailingZeros(), result.stripTrailingZeros());
    }
}
