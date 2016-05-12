package controller.game;

import controller.actionListeners.OptionListener;
import controller.actionListeners.HelpListener;
import controller.actionListeners.NewGameListener;
import controller.actionListeners.ResetGameListener;
import module.Deck;
import module.Player;
import view.gui.MainFrame.*;

import javax.swing.*;

/**
 * Created by eiphi_000 on 2014-11-25.
 */
public class MainGame{
    private Player player;
    private Deck deck;
    private GameRound game;
    private MainFrame mainWindow;

    public MainGame(){
        try {
            resetGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Main game here
                new MainGame();
            }
        });
    }

    public void processAGameRound() throws InterruptedException {
        boolean continueRound;
        //pre phase, setting up
        continueRound = game.prePhase();

        //phase1 bet
        if (continueRound)
            continueRound = game.phase1betting();

        //phase2 card dist
        if (continueRound)
            continueRound = game.phase2CardDistribution();

        //phase3 insurance
        if (continueRound && game.getDealer().getHand().getHandValue() == 11){
            continueRound = game.phase3_1Insurance();
            if (continueRound)
                continueRound = game.phase3_2OpenInsurance();
        }

        //phase4 player strategy
        if (continueRound)
            continueRound = game.phase4PlayerStrategy();


        //phase5 dealer round, phase6 winner determine
        if (continueRound){
            game.phase5DealersRound();
            game.phase6WinnerDetermine();
        }

        //endPhase rewarding.
        game.postPhase();
    }

    public void startNewRound() throws InterruptedException {
        mainWindow.disableBtns();
        processAGameRound();
        mainWindow.enableBtns();
    }

    public void resetGame() throws InterruptedException {
        if (mainWindow != null){
            mainWindow.setVisible(false);
            mainWindow.dispose();
        }
        mainWindow = new MainFrame(new NewGameListener(this),new ResetGameListener(this),new HelpListener(),new OptionListener(this));
        player = new Player();
        deck = new Deck();
        game = new GameRound(player, deck, mainWindow);
        mainWindow.setVisible(true);
        game.du.updateAllComponents();
        mainWindow.refresh();
    }

    public void changeOptions() throws InterruptedException {
        String[] options = new String[]{"Player Balance Option","Deck Option"};
        int n = JOptionPane.showOptionDialog(null,
                "Choose what you want to change",
                "Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);
        if (n>=0){
            if (n == 0)
                changePlayerBalanceOption();
            else
                changeDeckOption();
        }
    }

    public void changePlayerBalanceOption() throws InterruptedException {
        String[] startingBalanceOptions = new String[]{"Back","$5","$500","$2000 (default)","$5000","$10000","$50000"};
        double[] startingBalanceValues = new double[]{-1.0,5.0,500.0,2000.0,5000.0,10000.0,50000.0};

        int n = JOptionPane.showOptionDialog(null,
                "Do you want to change your starting balance? (Change this will RESET the game immediately, thus deck option will be also reset to default)",
                "Change Starting Balance",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                startingBalanceOptions,
                null);
        if (n >= 0){
            resetGame();
            player = new Player(startingBalanceValues[n]);
            game.setPlayer(player);
            game.du.updateAllComponents();
        }
    }

    public void changeDeckOption(){
        String[] deckOptions = new String[]{"Back","Single-Deck","Double-Deck (default)","Triple-Deck","4 Decks","5 Decks","6 Decks","7 Decks","8 Decks","9 Decks"};
        int[] deckValues = new int[]{-1,1,2,3,4,5,6,7,8,9};

        int n = JOptionPane.showOptionDialog(null,
                "How many bags of cards in deck do you want to play with? (Change take in effect immediately, new deck will be shuffled.)",
                "Change Deck",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                deckOptions,
                null);
        if (n >= 0){
            deck = new Deck(deckValues[n]);
            game.setDeck(deck);
            mainWindow.updateDeckPanel(deck);
            mainWindow.repaint();
        }
    }
}
