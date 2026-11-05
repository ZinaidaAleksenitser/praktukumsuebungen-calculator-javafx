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

    private Calculator calculator = new Calculator();

    @FXML
       public void handleDigit(ActionEvent actionEvent) {
        if(isDisplayBlocked) return;
        Button button = (Button) actionEvent.getSource();
        if (calculator.canAppendDecemalComma(display.getText(), button.getText()))  return;
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
        if (bufferValue == null || operation == null) {
            return;
        }
        value = new BigDecimal(display.getText());
        if(value.compareTo(BigDecimal.ZERO)==0 && operation.equals("/")){
            display.setText("ERROR");
            isDisplayBlocked = true;
            return;
        }
        display.setText(calculator.calculate(bufferValue,value, operation).stripTrailingZeros().toPlainString());

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
