package com.example.javafx;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    //method to check

    public boolean canAppendDecemalComma(String currentValue, String newDigit){

        return (currentValue.contains(".") && newDigit.equals("."));
    }

    //calculate

    public BigDecimal calculate(BigDecimal value1, BigDecimal value2, String operation){
        switch (operation){
            case "+":
                return value1.add(value2);
                break;
            case "-":
                return value1.subtract(value2);
                break;
            case "*":
                return value1.multiply(value2);
                break;
            case"/":
                if(value1.(BigDecimal.ZERO)){

                }else {
                    display.setText(bufferValue.divide(value, 10, RoundingMode.HALF_UP ).stripTrailingZeros().toPlainString());
                }
                break;
        }
    }



}
