package view.gui.MainFrame;

import module.Card;

import java.awt.*;

/**
 * Created by Phoenix on 2014-11-18.
 *
 * HandPanel Class:
 * A Bordered JPanel container for storing CardPanels and show.
 *
 * Constructor:
 * Default: A size fixed panel.
 * HandPanel(int index): same as default, just has the index labeled on border.
 *
 * Method:
 * (void) addAFaceDownCard(): add A Face Down Card as a new CardPanel to this.
 * (void) addCard(Card c): add a new CardPanel representing the Card c in argument.
 * (void) removeCards(): remove all components in the panel.
 *
 */
public class HandPanel extends BorderedPanelWithALabel {
    public HandPanel(int index){
        super("Hand #" + index, 380, 100);
        super.remove(textLabel);
        super.setLayout(new FlowLayout());
        this.updateUI();
    }

    public HandPanel(){
        super("Hand", 380,100);
        super.remove(textLabel);
        super.setLayout(new FlowLayout());
        this.updateUI();
    }

    public void addAFaceDownCard(){
        this.add(new CardPanel("B"));
        this.updateUI();
    }

    public void addCard(Card c){
        this.add(new CardPanel(c));
        this.updateUI();
    }

    public void removeCards(){
        this.removeAll();
        this.updateUI();
    }

}
