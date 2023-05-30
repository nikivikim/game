package com.example.w23comp1008s1w5memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Карточная игра Волна
 * @author Никитина Виктория
 * @version 1.0
 */
/**
 *Класс Rules отвечает за отображение правил игры и позволяет пользователю начать игру.
 *Он загружает правила из текстового файла и предоставляет кнопку для запуска игры.
  */
public class Rules {

    public Button playWar;
    public Label rules;

    /**
     *инициализирует контроллер Rules.
     *Загружает правила из текстового файла и устанавливает их в метке с правилами.
      */
    @FXML
    public void initialize() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/example/w23comp1008s1w5memorygame/rules.txt"));
            String text = "";
            String line = reader.readLine();
            while (line != null) {
                text += line + "n";
                line = reader.readLine();
            }
            rules.setText(text);
            rules.setWrapText(true);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /**
         *Обработчик событий для кнопки playWar.
         *Загружает файл FXML игры War и отображает его в новом окне.
         *@param event событие ActionEvent, связанное с нажатием кнопки.
         *@throws IOException если произошла ошибка при загрузке файла FXML.
           */
    @FXML
    void playWar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("war-game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Get the Stage object from the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("War Game");
        stage.setScene(scene);
        stage.show();
    }
}