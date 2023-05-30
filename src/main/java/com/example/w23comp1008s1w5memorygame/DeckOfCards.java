package com.example.w23comp1008s1w5memorygame;

import java.util.*;
/**
 * Карточная игра Волна
 * @author Никитина Виктория
 * @version 1.0
 */
/**
 *Класс DeckOfCards представляет колоду игральных карт для игры в Волну.
 *Он предоставляет функциональность для создания колоды, перемешивания карт, раздачи карты и получения количества карт в колоде.
  */
public class DeckOfCards {
    private LinkedList<Card> deck;

    /**
     *Конструктор класса DeckOfCards.
     *Колода инициализируется стандартным набором из 52 карт.
      */
    public DeckOfCards() {
        deck = new LinkedList<>();
        List<String> suits = Card.getValidSuits();
        List<String> faceNames = Card.getValidFaceNames();

        for (int i = 0; i < suits.size(); i++) {
            for (String faceName : faceNames)
                deck.addLast(new Card(faceName, suits.get(i)));
        }
    }

     /**
      *Раздача верхней карты из колоды.
      *@return верхняя карта из колоды, или null, если колода пуста.
     */
    public Card dealTopCard() {
        if (deck.size() > 0)
            return deck.removeFirst();
        else
            return null;
    }

     /**
      *Перемешивание карт в колоде.
      */
    public void shuffle() {
        Card[] cards = deck.toArray(new Card[deck.size()]);
        Collections.shuffle(Arrays.asList(cards));
        deck.clear();
        deck.addAll(Arrays.asList(cards));
    }

  /**
   *@return количество оставшихся карт в колоде.
   */
     public int getNumOfCardsInDeck() {
        return deck.size();
    }
}