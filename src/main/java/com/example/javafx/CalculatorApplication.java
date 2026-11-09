package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("hello-view.fxml"));

        Parent root = fxmlLoader.load();

        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 250, 430);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("calculator.css")).toExternalForm());

        stage.setScene(scene);

        stage.show();

        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
