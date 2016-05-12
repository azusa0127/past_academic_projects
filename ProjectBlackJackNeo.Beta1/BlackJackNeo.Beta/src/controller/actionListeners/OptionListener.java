package controller.actionListeners;

import controller.game.MainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by eiphi_000 on 2014-11-29.
 */
public class OptionListener implements ActionListener{
    MainGame game;

    public OptionListener(MainGame game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            game.changeOptions();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
