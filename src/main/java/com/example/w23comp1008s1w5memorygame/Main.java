/**
 * Карточная игра Волна
 * @author Никитина Виктория
 * @version 1.0
 */
package com.example.w23comp1008s1w5memorygame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * запускает приложение с графическим интерфейсом,
 * отображая окно с заголовком « Правила», размещенным на сцене, загруженной
 * из файла rules.fxml.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("rules.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("War Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}