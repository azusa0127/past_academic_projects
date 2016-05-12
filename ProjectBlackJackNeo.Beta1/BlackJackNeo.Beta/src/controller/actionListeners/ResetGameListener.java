package controller.actionListeners;

import controller.game.MainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Phoenix on 2014-11-26.
 *
 * ResetGameListener Class:
 * This ActionListener class is for implementing Reset Game button's function,
 * actions sent to this listener will trigger the method resetGame() from the
 * associated MainGame instance.
 *
 * Constructor:
 * Empty constructor not allowed.
 * ResetGameListener(MainGame game): Create an instance that associated with the
 * MainGame instance in the argument.
 *
 * Action:
 * Call the method resetGame() from the associated MainGame instance.
 */
public class ResetGameListener implements ActionListener {
    private MainGame game;

    public ResetGameListener(MainGame game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            game.resetGame();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
