package controller.actionListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Phoenix on 2014-11-27.
 *
 * HelpListener Class:
 * This ActionListener class is for implementing the help button's function.
 *
 * Action:
 * any action sent to this listener will open an JOptionPane that displays
 * the helping text for the blackjack program.
 */
public class HelpListener implements ActionListener {
    private void displayHelpPanel(){
        JTextArea textArea = new JTextArea("------------------------\n" +
                "Introduction:\n" +
                "------------------------\n" +
                "This game has applied standard BlackJack rules in competition between player(the user) and dealer(computer).\n" +
                "\n" +
                "------------------------\n" +
                "Default setting:\n" +
                "------------------------\n" +
                "Player starts at a balance of $2000.00.\n" +
                "Game will use a Double-Deck with out jokers by default.\n" +
                "\n" +
                "------------------------\n" +
                "Buttons Function:\n" +
                "------------------------\n" +
                "- Reset Game: Reset player balance and deck preference to default, shuffle the deck, and clean GUI.\n" +
                "- Options: Show option window for player balance and deck preference.\n" +
                "- Start New Round: Start a new game round. \n" +
                "- Help: Show this window.\n" +
                "- Quit: Close the program.\n" +
                "\n" +
                "------------------------\n" +
                "Standard BlackJack Rules\n" +
                "------------------------\n" +
                "- Game objective: Draw cards that is close to 21pts but not exceeds 21pts.\n" +
                "- Initial bet limit: $5 - $500\n" +
                "- Dealer must draw to 16pts and stay on 17pts or greater.\n" +
                "\n" +
                "Winning conditions:\n" +
                "- Card value(pts): Card 2-9 has same points as the number, card 10/J/Q/K has value 10(pts), Ace card can be either 1pt or 11pts.\n" +
                "- Busted: If a hand's value exceeds 21, it is busted. Treated as 0 value thus automatically lose.\n" +
                "- Push: If player's hand has the same value as the dealer's, associated bet is returned.\n" +
                "- Player win: If player's hand has greater value than the dealer's, and not bursted, the associated bet is returned in double.\n" +
                "- BlackJack: A 21pt hand with 2 cards only called BlackJack, if a player win with a blackjack hand, the associated bet is returned in double and a half.\n" +
                "\n" +
                "Insurance:\n" +
                "- Insurance: If Dealer got an Ace in the hand, Player will be asked to take insurance in case dealer got a BlackJack. Insurance takes an extra half of the original bet.\n" +
                "The insurance will be either 1) returned in triple when the dealer actually got BlackJack. or 2) lost if the dealer does not have the blackjack.\n" +
                "- EvenMoney: A special kind of insurance that offered when the player has a BlackJack in hand, take it will return players bet in double instead of standard BlackJack payment, and end the round instantly regardless of what hand the dealer has.\n" +
                "\n" +
                "\n" +
                "Player Strategy:\n" +
                "- Hit: Draw a card.\n" +
                "- Stay: Stop drawing and stand on current cards and wait to see dealer's points.\n" +
                "- Double: Available only if player got 9-11 pts from initial cards, pay and rise the initial bet in double and draw ONE more card then stay.\n" +
                "- Split: Available only if player got both initial cards in same points, divide them into two hands, and have to pay the same amount of bet for the new hand as the initial one. Split can only happen for once in a round.\n" +
                "- Surrender: Fold current hand and get hlaf of the bet back instantly, then ends the game.\n" +
                "\n" +
                "------------------------\n" +
                "Ultimate Challenge\n" +
                "------------------------\n" +
                "Game too easy?\n" +
                "Try a combination of starting balance of \"$5\" and deck option \"9-Decks\", and try get $50000.\n" +
                "\n" +
                "You will definately be able to beat any casino over the world if you made it! (Unless they cheat, and cheat very, very hard!XD)");
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial",Font.PLAIN,14));
        scrollPane.setPreferredSize( new Dimension( 500, 600 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Help - BlackJack in Java by Phoenix, Beta 1",
                JOptionPane.OK_OPTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayHelpPanel();
    }
}
