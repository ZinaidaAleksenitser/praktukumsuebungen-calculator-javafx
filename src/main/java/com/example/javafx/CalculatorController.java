package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorController {

    @FXML
    private TextField display;

    private BigDecimal bufferValue;
    private BigDecimal value;
    private String operation;
    private boolean isDisplayBlocked = false;

    @FXML
       public void handleDigit(ActionEvent actionEvent) {
        if(isDisplayBlocked) return;
        Button button = (Button) actionEvent.getSource();
        if(display.getText().contains(".") && button.getText().equals(".")) {
            return;
        }
        display.appendText(button.getText());
    }

    @FXML
    public void handleOperation(ActionEvent actionEvent) {
        if(isDisplayBlocked) return;
        Button button = (Button) actionEvent.getSource();
        operation = button.getText();
        bufferValue = new BigDecimal(display.getText());
        display.clear();
    }

    @FXML
    public void handleEquals() {
        if(isDisplayBlocked) return;
        //TODO if the user enters the = right away
        if(bufferValue.compareTo(null))
        value = new BigDecimal(display.getText());
        switch (operation){
            case "+":
                display.setText(bufferValue.add(value).stripTrailingZeros().toPlainString());
                break;
            case "-":
                display.setText(bufferValue.subtract(value).stripTrailingZeros().toPlainString());
                break;
            case "*":
                display.setText(bufferValue.multiply(value).stripTrailingZeros().toPlainString());
                break;
            case"/":
                if(value.equals(BigDecimal.ZERO)){
                    display.setText("ERROR");
                    isDisplayBlocked = true;
                }else {
                    display.setText(bufferValue.divide(value, 10, RoundingMode.HALF_UP ).stripTrailingZeros().toPlainString());
                }
                break;
        }
    }

    @FXML
    public void handleClear() {
        isDisplayBlocked = false;
        display.clear();
        operation = null;
        bufferValue = null;
        value = null;
    }
}
