package controller.io;

import controller.game.GameRound;
import view.gui.MainFrame.MainFrame;

/**
 * Created by Phoenix on 2014-11-23.
 */
public class DisplayUpdate {
    private GameRound game;
    private MainFrame mainWindow;


    public DisplayUpdate(MainFrame mf, GameRound g){
        mainWindow = mf;
        game = g;
    }

    //Border methods
    public void normalizeBorders(){
        mainWindow.normalizeAllBorders();
    }

    public void highlightDealer(){
        mainWindow.borderFocusOnDealer();
    }

    public void highlightDeck(){
        mainWindow.borderFocusOnDeck();
    }

    public void highlightPlayerZone(int zoneIndex){
        if (zoneIndex == 1)
            mainWindow.borderFocusOnPlayerZone2();
        else
            mainWindow.borderFocusOnPlayerZone1();
    }

    public void highlightRewardZone(){
        mainWindow.borderFocusOnBalanceChangePanel();
    }
    //End of Border methods


    //GUI synchronize methods
    public void updateDealerHand() {
        updateDeck();
        mainWindow.updateDealerHand(game.getDealer());
    }

    public void updateDeck(){
        mainWindow.updateDeckPanel(game.getDeck());
    }

    public void updatePlayerHand(int zoneIndex){
        updateDeck();
        mainWindow.updatePlayerHand(game.getPlayer(),zoneIndex);
    }

    public void updateBet(int zoneIndex){
        mainWindow.updatePlayerBet(game.getPlayer(), zoneIndex);
    }

    public void updatePlayerBalance(){
        mainWindow.updatePlayerBalance(game.getPlayer());
        updateBalanceChange();
    }

    public void updateBalanceChange(){
        mainWindow.updateBalanceChange(game.getRewardZone().getValue(),game.getInitialSpend());
    }

    public void updateStatus() throws InterruptedException {
        mainWindow.updateStatus(game.getStatusMsg());
    }

    public void updateAllComponents(){
//      updateStatus();
        updateDeck();
        updatePlayerBalance();
        updateDealerHand();
        updateBet(0);
        updatePlayerHand(0);
        updateBet(1);
        updatePlayerHand(1);
        refreshGUI();
    }

    public void refreshGUI(){
        mainWindow.refresh();
    }
    //End of GUI synchronize methods
}
