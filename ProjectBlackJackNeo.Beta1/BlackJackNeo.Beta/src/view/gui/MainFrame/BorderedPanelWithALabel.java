package view.gui.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Phoenix on 2014-11-20.
 * BorderedPanelWithALabel class:
 * This is a core JPanel class used for almost all components on the MainFrame.
 * Its constructors create a Bordered JPanel with a JLabel in in 1)predefined size or 2)customizable size.
 * The public methods allows an instance to 1)Highlight its border, 2) normalize its border, 3)and update text of the JLabel in it.
 *
 *
 */
public class BorderedPanelWithALabel extends JLabel {
    JLabel textLabel;
    private String titleText;
    private Border redline = BorderFactory.createLineBorder(Color.RED,2);

    public BorderedPanelWithALabel(String borderTitle){
        final int PANELWIDTH = 275, PANELHEIGHT = 85;

        titleText = borderTitle;
        setSize(PANELWIDTH,PANELHEIGHT);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        setBorder(BorderFactory.createTitledBorder(titleText));
        textLabel = new JLabel("");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(textLabel,BorderLayout.CENTER);
        this.updateUI();
    }

    public BorderedPanelWithALabel(String borderTitle, int panelWidth, int panelHeight){
        titleText = borderTitle;
        setSize(panelWidth,panelHeight);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBorder(BorderFactory.createTitledBorder(titleText));
        textLabel = new JLabel("");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(textLabel,BorderLayout.CENTER);
        this.updateUI();
    }

    public void updateText(String newLabelText) {
        textLabel.setText(newLabelText);
        this.updateUI();
    }

    public void highlightBoarder() {
        setBorder(BorderFactory.createTitledBorder(redline,titleText));
    }

    public void normalizeBorder() {
        setBorder(BorderFactory.createTitledBorder(titleText));
    }

}
