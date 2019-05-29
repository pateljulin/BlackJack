/**
 * The purpose of this abstract class is in order to help create players and a Dealer class.
 */

import java.util.ArrayList;


public abstract class Person {
    public ArrayList <Card> cards;
    private String name;
    protected int cardTotal;
    private int totalMoneyBet = 0;

    public Person()
    {
        cards = new ArrayList<Card>();
        cardTotal = 0;
    }

    //Should print out face value of cards in hand
    public void getCards()
    {
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.print(cards.get(i).faceValue + " ");
        }
    }

    //return persons name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Returns total count for cards in hand
    public int getCardTotal() {
        return cardTotal;
    }

    //Keep track of total Money
    public void bet(int personBet)
    {
        totalMoneyBet += personBet;
    }

    public int getTotalMoney() {
        return totalMoneyBet;
    }

    //Player decides if they want to hit or stay
    public abstract boolean hitOrNo();

    //Adds card to hand for players and dealer
    public Card recieveCard(Card card)
    {
        cards.add(card);
        cardTotal += card.getFaceValue();
        return card;
    }


}
