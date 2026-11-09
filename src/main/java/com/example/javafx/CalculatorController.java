package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class CalculatorController {

    @FXML
    private TextField display;
    @FXML
    private VBox root;

    private BigDecimal value1;
    private BigDecimal value2;
    private String operation;
    private boolean isDisplayBlocked = false;

    private  Calculator calculator = new Calculator();

    @FXML
    public void handleDigit(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        appendDigit(button.getText());
    }

    @FXML
    public void handleOperation(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        processOperation(button.getText());
    }

    @FXML
    public void handleEquals() {
        if (isDisplayBlocked || display.getText().isEmpty()) return;
        if (value1 == null || operation == null) {
            return;
        }
            value2 = new BigDecimal(display.getText());
            try {
                value1 = calculator.calculate(value1, value2, operation);
                display.setText(value1.stripTrailingZeros().toPlainString());
                setDisplaySize(display);
            } catch (ArithmeticException e) {
                display.setText("ERROR");
                isDisplayBlocked = true;
            }
    }

    @FXML
    public void handleClear() {
        isDisplayBlocked = false;
        display.clear();
        operation = null;
        value1 = null;
        value2 = null;
    }

    @FXML
    public void handlePlusMinusSign(ActionEvent actionEvent) {
        if(isDisplayBlocked) return;
        value1 = new BigDecimal(display.getText());
        value1 = calculator.getPlusMinusValue(value1);
        display.setText(value1.stripTrailingZeros().toPlainString());
    }

    @FXML
    public void handleKeyPressed(KeyEvent event){
        String key = event.getText();

        if(key.matches("[0-9.]")){
            appendDigit(key);
            event.consume();
        }

        if(key.matches("[+*/-]")){
            processOperation(key);
            event.consume();
        }

        System.out.println("KeyCode: " + event.getCode());
        System.out.println("Text: " + event.getText());

        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("call equals from key");
            handleEquals();
            event.consume();
        }

        if (event.getCode() == KeyCode.ESCAPE) {
            handleClear();
            event.consume();
        }
    }



    private void appendDigit(String digit){
        if(isDisplayBlocked) return;

        if(calculator.isDuplicateDecimalPoint(display.getText(), digit)) {
            return;
        }
        setDisplaySize(display);


        display.appendText(digit);
    }

    private void setDisplaySize(TextField display) {
        if (display.getText().length() >= 9) {
            display.setStyle("-fx-font-size: 24px;");
        } else {
            display.setStyle("-fx-font-size: 38px;");
        }
    }

    private void processOperation(String newOperation){
        if(isDisplayBlocked || display.getText().isEmpty()) return;
        value1 = new BigDecimal(display.getText());
        operation = newOperation;
        display.clear();
    }

    @FXML
    public void handleMinimize() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void handleMaximize() {
        Stage stage = (Stage) root.getScene().getWindow();
        boolean maximized = !stage.isMaximized();
        stage.setMaximized(maximized);

        if (maximized) {
            root.setStyle("-fx-background-radius: 0;");
        } else {
            root.setStyle("-fx-background-radius: 28;");
        }
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}