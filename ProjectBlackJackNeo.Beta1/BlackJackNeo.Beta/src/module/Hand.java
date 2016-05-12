package module;

import java.util.Stack;

/**
 * Created by 300178245 on 11/13/2014.
 * BlackJack Hand Class, represent a hand of cards in the game.
 *
 * Field:
 * cards: Stack<Card> a stack representing the cards it is holding.
 */

public class Hand{
    private Stack<Card> cards;

    public Hand(){
        cards = new Stack<Card>();
    }

    public Hand(Card card){
        cards = new Stack<Card>();
        cards.push(card);
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void clear(){
        cards.clear();
    }

    public Card addCard(Card card){
        cards.push(card);
        return card;
    }

    public Card useCard() {
        return cards.pop();
    }

    /**
     * A method that returns BlackJack Value Sum of the hand.
     * @return int value sum of a BlackJack hand.
     */
    public int getHandValue() {
        int aces = 0;
        int valueSum = 0;

        for (Card c: cards){
            if (c.getCardValue() == 11){
                valueSum += 1;
                aces++;
            } else {
                valueSum += c.getCardValue();
            }
        }
        while (aces>0 && valueSum< 21) {
            if (valueSum + 10 > 21)
                break;
            valueSum += 10;
            aces--;
        }
        return valueSum;
    }
}
