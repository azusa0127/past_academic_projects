package view.gui.MainFrame;

import module.Card;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Phoenix on 2014-11-18.
 *
 * CardPanel Class:
 * This JPanel class is provided for displaying a card in a hand panel.
 * It offers no methods but two constructors.
 *
 * Constructor:
 * No empty constructor allowed.
 * CardPanel(Card c): Instantiate a CardPanel representing the Card c in argument.
 * CardPanel(String b): Instantiate a CardPanel for a face down card.
 *
 */
public class CardPanel extends JPanel{
    private final int PANELWIDTH = 45, PANELHEIGHT = 70;

    public CardPanel(Card c){
        String color = getColor(c.getColor());
        String num = getNum(c.getNumber());
        setSize(PANELWIDTH,PANELHEIGHT);
        setPreferredSize(new Dimension(PANELWIDTH,PANELHEIGHT));
        setBackground(Color.white);
        setBorder(BorderFactory.createTitledBorder(color));
        add(new JLabel(num));
    }

    public CardPanel(String b){
        String color = Character.toString('\u2111');
        setSize(PANELWIDTH,PANELHEIGHT);
        setPreferredSize(new Dimension(PANELWIDTH,PANELHEIGHT));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createTitledBorder(color));
    }


    private String getColor(char c){
        switch (c) {
            case 'C':
                return Character.toString('\u2663');
            case 'S':
                return Character.toString('\u2660');
            case 'H':
                return Character.toString('\u2665');
            case 'D':
                return Character.toString('\u2666');
        }
        return "";
    }

    private String getNum(int n){
        switch (n){
            case 14:
                return "A";
            case 13:
                return "K";
            case 12:
                return "Q";
            case 11:
                return "J";
            default:
                return Integer.toString(n);
        }
    }
}
