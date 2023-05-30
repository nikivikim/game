package com.example.w23comp1008s1w5memorygame;

import javafx.scene.image.Image;

import java.util.*;
/**
 * Карточная игра Волна
 * @author Никитина Виктория
 * @version 1.0
 */
/**
 * Класс Card представляет игральную карту в игре в память.
 * Каждая карта имеет имя значения и масть, а также изображение.
  */

public class Card {

    private String faceName;
    private String suit;

    // Deque implementation using LinkedList
    private LinkedList<Card> deque = new LinkedList<>();

    /**
     * Конструктор класса Card с указанным именем значения и мастью.
     *
     * @param faceName имя значения карты.
     * @param suit масть карты.
     * @throws IllegalArgumentException если имя значения или масть недопустимы.
     */
    public Card(String faceName, String suit) throws IllegalArgumentException {
        setFaceName(faceName);
        setSuit(suit);
    }


     /**
         * @return имя значения карты.
      **/

    public String getFaceName() {
        return faceName;
    }

  /**
   * Устанавливает имя значения карты.
   *@param faceName устанавливаемое имя значения.
   *@throws IllegalArgumentException если имя значения недопустимо.
     */
    public void setFaceName(String faceName) throws IllegalArgumentException {
        faceName = faceName.toLowerCase();

        if (getValidFaceNames().contains(faceName))
            this.faceName = faceName;
        else
            throw new IllegalArgumentException(faceName + " must be in the list of " + getValidFaceNames());
    }
    /**
     * @return масть карты.
     */
    public String getSuit() {
        return suit;
    }

  /**
   *@param suit устанавливаемая масть.
   *@throws IllegalArgumentException если масть недопустима.
   */

    public void setSuit(String suit) throws IllegalArgumentException {
        suit = suit.toLowerCase();

        if (suit.charAt(suit.length() - 1) != 's')
            suit = suit + "s";

        if (getValidSuits().contains(suit))
            this.suit = suit;
        else
            throw new IllegalArgumentException(suit + " is not a valid suit, use one of " + getValidSuits());
    }



    /**
     * @return список допустимых имен значений.
      */
    public static List<String> getValidFaceNames() {
        return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
    }

    /**
     * @return список допустимых мастей.
      */
    public static List<String> getValidSuits() {
        return Arrays.asList("hearts", "diamonds", "spades", "clubs");
    }

    /**
     * @return числовое значение карты на основе ее имени значения.
     */
    public int getCardValue() {
        List<String> faceNames = getValidFaceNames();
        int indexOfCard = faceNames.indexOf(faceName);
        return indexOfCard + 2;
    }

    /**
     * @return изображение, связанное с картой.
    */
    public Image getImage() {
        String fileName = "images/" + faceName + "_of_" + suit + ".png";
        return new Image(Card.class.getResourceAsStream(fileName));
    }

}