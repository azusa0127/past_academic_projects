package controller.actionListeners;

import controller.game.MainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Phoenix on 2014-11-26.
 *
 * NewGameListener Class:
 * This ActionListener class is for implementing New game button's function,
 * actions sent to this listener will trigger the method startNewRound() from the
 * associated MainGame instance.
 *
 * Constructor:
 * Empty constructor not allowed.
 * NewGameListener(MainGame game): Create an instance that associated with the
 * MainGame instance in the argument.
 *
 * Action:
 * Call the method startNewRound() from the associated MainGame instance.
 *
 */
public class NewGameListener implements ActionListener {
    private MainGame game;

    public NewGameListener(MainGame game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            game.startNewRound();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
