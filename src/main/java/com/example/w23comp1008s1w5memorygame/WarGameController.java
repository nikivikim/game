package com.example.w23comp1008s1w5memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ResourceBundle;
/**
 * Карточная игра Волна
 * @author Никитина Виктория
 * @version 1.0
 */
/**
 * Класс WarGameController отвечает за управление логикой игры и пользовательским интерфейсом игры "Волна".
 * Он управляет именами игроков, раздачей карт, сравнением карт и объявлением победителя.
 */
public class WarGameController implements Initializable {

    @FXML
    Label p1CardCountLabel;
    @FXML
    Label name1;
    @FXML
    Label name2;
    @FXML
    TextField nameText1;
    @FXML
    TextField nameText2;
    @FXML
    ImageView p1ImageView;
    @FXML
    Label p2CardCountLabel;
    @FXML
    Label winnerLabel;
    @FXML
    ImageView p2ImageView;
    @FXML
    Button playButton;
    @FXML
    Label pullCardCountLabel;
    @FXML
    Button playAgainButton;

    Deque<Card> p1Hand;
    Deque<Card> p2Hand;
    Deque<Card> cardPile;

    /**
     * Обрабатывает событие нажатия кнопки inputName.
     * Устанавливает имена игроков и скрывает текстовые поля ввода.
     * @param actionEvent событие ActionEvent, связанное с нажатием кнопки.
     */
    public void inputName(ActionEvent actionEvent) {
        String text = nameText1.getText();
        name1.setText(text);

        String text1 = nameText2.getText();
        name2.setText(text1);
        nameText1.setVisible(false);
        nameText2.setVisible(false);
    }

    /**
     * инициализирует игру, устанавливая начальное состояние игры и перемешивая колоду карт.
     */
    @FXML
    void newGame() {
        playAgainButton.setDisable(true);
        playButton.setDisable(false);
        winnerLabel.setVisible(false);
        p1ImageView.setVisible(false);
        p2ImageView.setVisible(false);

        p1Hand = new LinkedList<>();
        p2Hand = new LinkedList<>();
        cardPile = new LinkedList<>();

        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        while (deck.getNumOfCardsInDeck() >= 2) {
            p1Hand.add(deck.dealTopCard());
            p2Hand.add(deck.dealTopCard());
        }

        updateLabels();
    }
    /**
     * Обрабатывает событие нажатия кнопки playButton.
     * начинает розыгрыш игры, сравнивая верхние карты игроков и определяя победителя.
     */

    @FXML
    void playHand() {
        playButton.setText("Выложить");
        p1ImageView.setVisible(true);
        p2ImageView.setVisible(true);

        if (p1Hand.size() == 0)
            declareWinner(name2.getText());
        else if (p2Hand.size() == 0)
            declareWinner(name1.getText());
        else {
            Card p1Card = p1Hand.pollFirst();
            cardPile.addLast(p1Card);
            p1ImageView.setImage(p1Card.getImage());

            Card p2Card = p2Hand.pollFirst();
            cardPile.addLast(p2Card);
            p2ImageView.setImage(p2Card.getImage());

            if (cardPile.size() >= 0) {
                pullCardCountLabel.setText(cardPile.size() + " Карт");
                updateLabels();
            }

            if (p1Card.getCardValue() == p2Card.getCardValue()) {
                updateLabels();
                playWarHand();
            } else if (p1Card.getCardValue() > p2Card.getCardValue()) {
                p1Hand.addAll(cardPile);
                updateLabels();
                cardPile.clear();
            } else if (p1Card.getCardValue() < p2Card.getCardValue()) {
                p2Hand.addAll(cardPile);
                updateLabels();
                cardPile.clear();
            }

            updateLabels();
        }
    }

/**
 * Начало "волны", когда у обоих игроков карты имеют одинаковое значение.
 * Три дополнительные карты играются рубашкой вниз, а две карты играются рубашкой вверх для определения победителя.
  */


void playWarHand() {
        if (p1Hand.size() < 4)
            declareWinner(name2.getText());
        else if (p2Hand.size() < 4)
            declareWinner(name1.getText());
        else {
            for (int i = 1; i <= 3; i++) {
                cardPile.addLast(p1Hand.pollFirst());
                cardPile.addLast(p2Hand.pollFirst());
            }
        }

        playHand();
    }

    /**
     * Объявляет победителя игры и обновляет пользовательский интерфейс соответствующим образом.
     * @param winnerName имя победителя.
     */
    void declareWinner(String winnerName) {
        playButton.setDisable(true);
        playAgainButton.setDisable(false);
        p1ImageView.setVisible(false);
        p2ImageView.setVisible(false);
        winnerLabel.setVisible(true);
        winnerLabel.setText(winnerName + " Победил!!");
        updateLabels();
    }
/**
 * инициализирует игру, вызывая метод newGame
 * @param location расположение, используемое для разрешения относительных путей для корневого объекта или null, если местоположение неизвестно.
 * @param resources ресурсы, используемые для локализации корневого объекта, или null, если корневой объект не локализован.
 */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGame();
    }

    /**
     * Обновляет метки, отображающие количество карт для каждого игрока.
     * @return
     */
    Object updateLabels() {
        if (p1Hand.size() == 1)
            p1CardCountLabel.setText(p1Hand.size() + " Карт");
        else
            p1CardCountLabel.setText(p1Hand.size() + " Карт");

        p2CardCountLabel.setText(String.format("%d %s", p2Hand.size(),
                p2Hand.size() == 1 ? "Карт" : "Карт"));
        return null;
    }
}