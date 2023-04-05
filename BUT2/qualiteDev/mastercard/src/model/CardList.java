package model;

import java.util.ArrayList;
import java.util.List;

public class CardList {
    private final ArrayList<Card> listOfCard;

    public CardList() {
        this.listOfCard = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.listOfCard.add(card);
    }

    public void removeCard(Card card) {
        this.listOfCard.remove(card);
    }

    public void removeCard(int i) {
        this.listOfCard.remove(i);
    }

    public void clearCardList() {
        this.listOfCard.clear();
    }

    public List<Card> getCardList() {
        return this.listOfCard;
    }
}
