package com.cardGame;

public class Card extends CardNull {

    static int CardsNum = 0;
    public int ID;

    public Card(String question, String answer) {
        super(question, answer);
        ID = CardsNum;
        CardsNum++;
    }
}
