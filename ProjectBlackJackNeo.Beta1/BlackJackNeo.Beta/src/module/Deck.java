package module;

import java.util.Random;

/**
 * Created by 300178245 on 11/13/2014.
 * Deck Class that represent deck
 *
 * Fields:
 * cards: Card[] Array of Card contained in the Deck
 * index: int The position of current DrawPlace.
 *
 */

public class Deck {
    private Card[] cards;
    private int index;

    public Card[] getCards() {
        return cards;
    }

    public int getIndex() {
        return index;
    }

    public Deck(){
        cards = BlackJackDeckBuilder();
        shuffle();
        resetIndex();
    }

    public Deck(int bagsOfCards){
        cards = BlackJackDeckBuilder(bagsOfCards);
        shuffle();
        resetIndex();
    }

    private void resetIndex() {
        index = cards.length;
    }

    private void shuffle() {
        resetIndex();

        Random rand = new Random();
        int r;
        Card tmp;
        for (int count = 0; count <cards.length; count++)
        {
            r = rand.nextInt(cards.length - count)+count;
            if (count != r)
            {
                tmp = cards[count];
                cards[count] = cards[r];
                cards[r] = tmp;
            }
        }
    }

    public Card drawACard(){
        if (index <= 0)
            resetTheDeck();
        index--;
        return cards[index];
    }

    public void resetTheDeck(){
        shuffle();
        resetIndex();
    }

    private static Card[] BlackJackDeckBuilder(){
        Card[] tempDeck = new Card[52*2];
        for (int i = 0; i < 13; i++) {
            int k = i + 2;
            tempDeck[i] = new Card('D',k);
            tempDeck[i+52] = new Card('D',k);

            tempDeck[i+13] = new Card('S',k);
            tempDeck[i+13+52] = new Card('S',k);

            tempDeck[i+26] = new Card('H',k);
            tempDeck[i+26+52] = new Card('H',k);

            tempDeck[i+39] = new Card('C',k);
            tempDeck[i+39+52] = new Card('C',k);
        }
        return tempDeck;
    }

    private static Card[] BlackJackDeckBuilder(int cardBagsNumber) {
        if ((cardBagsNumber > 0) && (cardBagsNumber < 10)) {
            Card[] tempDeck = new Card[52 * cardBagsNumber];
            for (int i = 0; i < 13; i++) {
                int k = i + 2;
                for (int j = 0; j < cardBagsNumber; j++) {
                    tempDeck[i + j * 52] = new Card('D', k);
                    tempDeck[i + 13 + j * 52] = new Card('S', k);
                    tempDeck[i + 26 + j * 52] = new Card('H', k);
                    tempDeck[i + 39 + j * 52] = new Card('C', k);
                }
            }
            return tempDeck;
        } else {
            return BlackJackDeckBuilder();
        }
    }
}
