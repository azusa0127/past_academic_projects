package module;

/**
 * Created by 300178245 on 11/13/2014. Basic Cards Class.
 *
 * Fields:
 * color: char, 'S' for spades, 'D' for diamonds, 'C' for clubs, 'H' for hearts, 'J' for jokers;
 * number: int, range from 2-14 as 2,3,4,5,6,7,8,9,10,J,Q,K,A;  Small Joker = J,15 / Big Joker = J, 16;
 *
 */

public class Card {
    private char color; //'S' for spades, 'D' for diamonds, 'C' for clubs, 'H' for hearts, 'J' for jokers;
    private int number; // range from 2-14 as 2,3,4,5,6,7,8,9,10,J,Q,K,A;  Small Joker = J,15 / Big Joker = J, 16;

    public Card(char color, int number) {
        this.color = color;
        this.number = number;
    }

    public char getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    /**
     * Return BlackJack value of the card; A:11, 10,J,Q,K:10, 2-9:2-9
     * @return int BlackJack Card value.
     */
    public int getCardValue(){
        int value;
        switch (number) {
            case 14:
                value = 11;
                break;
            case 13:
            case 12:
            case 11:
            case 10:
                value = 10;
                break;
            default:
                value = number;
        }
        return value;
    }
}
