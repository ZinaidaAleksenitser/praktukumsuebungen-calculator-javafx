package com.example.javafx;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    public boolean isDuplicateDecimalPoint(String currentValue, String newDigit){
        return (currentValue.contains(".") && newDigit.equals("."));
    }

    public BigDecimal calculate(BigDecimal value1, BigDecimal value2, String operation){
        BigDecimal result;
        switch (operation){
            case "+":
                result = value1.add(value2);
            break;
            case "-":
                result = value1.subtract(value2);
            break;
            case "*":
                result = value1.multiply(value2);
            break;
            case"/":
                if(value2.compareTo(BigDecimal.ZERO)==0){
                    throw new ArithmeticException("Cannot divide by zero");
                } else {
                    result = value1.divide(value2, 10, RoundingMode.HALF_UP );
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
        return result;
    }

    public BigDecimal getPlusMinusValue(BigDecimal value) {
        return value.negate();
    }
}