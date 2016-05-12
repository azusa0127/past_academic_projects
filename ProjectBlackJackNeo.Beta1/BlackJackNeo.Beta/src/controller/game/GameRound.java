package controller.game;

import controller.Constants;
import controller.io.DisplayUpdate;
import controller.io.UserInput;
import controller.validate.CheckWinCondition;
import module.*;
import view.gui.MainFrame.MainFrame;

/**
 * Created by Phoenix on 2014-11-15.
 *
 * GameRound Class: Core logical class to processing the game round in phases.
 */
public class GameRound extends Thread {
    private Player player;
    private Dealer dealer = new Dealer();
    private Deck deck;
    private Deal rewardZone;
    private String statusMsg;
    private UserInput uin;
    public DisplayUpdate du;
    private double initialSpend;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deal getRewardZone() {
        return rewardZone;
    }

    public double getInitialSpend() {
        return initialSpend;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public GameRound(Player p, Deck d, MainFrame mainFrame) {
        player = p;
        deck = d;
        rewardZone = new Deal(0);
        uin = new UserInput();
        du = new DisplayUpdate(mainFrame, this);
    }

    //Procedure phase block
    public boolean prePhase() throws InterruptedException {
        du.normalizeBorders();
        initialSpend = 0;
        statusMsg = "[Starting new game... ]";
        du.updateStatus();
        //delayForHalfSec();

        player.clear();
        dealer.clear();
        rewardZone.clear();
        du.updateAllComponents();

        //delayForASec();
        return true;
    }

    public boolean phase1betting() throws InterruptedException {
        statusMsg = "[Player betting... ]";
        du.updateStatus();
        du.highlightPlayerZone(0);
        //delayForHalfSec();

        Deal bet = uin.getBetDeal(player);
        if (bet.getValue() > 0)
            initialSpend = bet.getValue();
        statusMsg = "You have bet: " + bet.getValue();
        du.updateStatus();
        if (bet.getValue() < 0){
            statusMsg = "Invalid bet, ending game... ";
            du.updateStatus();
            return false;
        }
        player.getBetZones()[0].setBet(bet);
        du.updateBet(0);
        du.updateAllComponents();

        //delayForASec();
        return true;
    }

    public boolean phase2CardDistribution() throws InterruptedException {
        du.highlightDeck();
        statusMsg = "[Distributing initial cards... ]";
        du.updateStatus();
        //delayForHalfSec();

        player.getHands()[0].addCard(deck.drawACard());
        du.updatePlayerHand(0);
        //delayForHalfSec();
        dealer.getHand().addCard(deck.drawACard());
        du.updateDealerHand();
        //delayForHalfSec();
        player.getHands()[0].addCard(deck.drawACard());
        du.updatePlayerHand(0);
        //delayForHalfSec();
        dealer.setHole(deck.drawACard());
        du.updateDealerHand();

        //delayForASec();
        return true;
    }

    public boolean phase3_1Insurance() throws InterruptedException {
        du.highlightDealer();
        statusMsg = "[Dealer got an \"A\" on table, asking for insurance... ]";
        du.updateStatus();
        //delayForHalfSec();

        int uOption = uin.getInsuranceOption(player);
        if (uOption == Constants.INSUR_EVENMONEY){
            statusMsg = "Player has taken even money, this round is ending soon... ";
            du.updateStatus();
            //delayForHalfSec();

            du.highlightRewardZone();
            rewardZone = player.getBetZones()[0].wonBet();
            player.clear();
            dealer.openHole();
            du.updateBalanceChange();
            du.updateBet(0);
            du.updatePlayerHand(0);
            du.updateDealerHand();
            du.refreshGUI();
            return false;
        } else if (uOption == Constants.INSUR_TAKE){
            initialSpend += player.getBetZones()[0].getInsuancingAmt();
            statusMsg = "Player has taken insurance, processing... ";
            du.updateStatus();
            //delayForHalfSec();

            player.getBetZones()[0].takeInsurance();
            du.updateBet(0);
        } else if (uOption == Constants.STRATA_SURRENDER){
            statusMsg = "Player choose to surrender, this round is ending soon... ";
            du.updateStatus();
            //delayForHalfSec();

            du.highlightRewardZone();
            rewardZone.setValue(rewardZone.getValue() + player.getBetZones()[0].getInsuancingAmt());
            player.clear();
            du.updateBalanceChange();
            du.updateBet(0);
            du.updatePlayerHand(0);
            du.refreshGUI();
            return false;
        }
        du.updateAllComponents();

        //delayForASec();
        return true;
    }

    public boolean phase3_2OpenInsurance() throws InterruptedException {
        du.highlightDealer();
        statusMsg = "[Dealer is checking the hole card... ]";
        du.updateStatus();
        //delayForHalfSec();

        dealer.openHole();
        if (CheckWinCondition.isBlackJack(dealer.getHand())){
            du.updateDealerHand();
            statusMsg = "Dealer got a BlackJack! Insurance is now valid... ";
            du.updateStatus();
            //delayForHalfSec();

            rewardZone = player.getBetZones()[0].wonInsurance();
            du.highlightRewardZone();
            du.updateBalanceChange();
            du.refreshGUI();
            return false;
        } else {
            statusMsg = "Dealer doesn't have a BlackJack, round continues... ";
            du.updateStatus();
            //delayForHalfSec();

            dealer.setHole(dealer.getHand().useCard());
            player.getBetZones()[0].clearInsurance();
            du.updateBet(0);
        }
        du.updateAllComponents();

        //delayForASec();
        return true;
    }

    public boolean phase4PlayerStrategy() throws InterruptedException {
        du.normalizeBorders();
        statusMsg = "[Player's turn... ]";
        du.updateStatus();
        //delayForHalfSec();

        takePlayerStrategy(0);
        if (!player.getBetZones()[1].isEmpty())
            takePlayerStrategy(1);
        du.updateAllComponents();
        return !(player.getBetZones()[0].isEmpty() && player.getBetZones()[1].isEmpty());
    }

    private void takePlayerStrategy(int zoneIndex) throws InterruptedException {
        du.highlightPlayerZone(zoneIndex);
        statusMsg = "Player's now taking strategy for Hand #" + (zoneIndex+1) + " ... ";
        du.updateStatus();
        //delayForHalfSec();

        while ((player.getHands()[zoneIndex].getHandValue()<=21)&&(!player.getBetZones()[zoneIndex].isEmpty())){
            int stgOption = uin.getStrategyOption(player,zoneIndex);
            if (stgOption == Constants.STRATA_HIT) {
                statusMsg = "Hit!... ";
                du.updateStatus();
                //delayForHalfSec();

                player.getHands()[zoneIndex].addCard(deck.drawACard());
                du.updatePlayerHand(zoneIndex);
            } else if (stgOption == Constants.STRATA_STAY){
                statusMsg = "Stay... ";
                du.updateStatus();
                break;
            } else if (stgOption == Constants.STRATA_DOUBLE) {
                initialSpend += player.getBetZones()[zoneIndex].getBet().getValue();
                statusMsg = "Double!!... ";
                du.updateStatus();
                //delayForHalfSec();

                du.updateBet(zoneIndex);
                du.updatePlayerBalance();
                player.getBetZones()[zoneIndex].doubleTheBet();
                du.updatePlayerHand(zoneIndex);
                player.getHands()[zoneIndex].addCard(deck.drawACard());
                du.updatePlayerHand(zoneIndex);
                break;
            } else if (stgOption == Constants.STRATA_SPLIT){
                if (zoneIndex==0) {
                    initialSpend += player.getBetZones()[0].getBet().getValue();
                    statusMsg = "Split~... ";
                    du.updateStatus();
                    //delayForHalfSec();

                    du.updateBet(0);
                    du.updateBet(1);
                    du.updatePlayerBalance();
                    player.getBetZones()[1].setBet(new Deal(player.getBetZones()[0].getBet().getValue()));
                    du.updateBet(1);
                    player.getHands()[1] = new Hand(player.getHands()[0].useCard());
                    du.updatePlayerHand(0);
                    du.updatePlayerHand(1);
                }
            } else if (stgOption == Constants.STRATA_SURRENDER){
                statusMsg = "Player has folded hand #" + (zoneIndex+1);
                du.updateStatus();
                //delayForHalfSec();

                du.highlightRewardZone();
                rewardZone.setValue(rewardZone.getValue() + player.getBetZones()[zoneIndex].getInsuancingAmt());
                player.getBetZones()[zoneIndex].clearBet();
                player.getHands()[zoneIndex].clear();
                du.updateBalanceChange();
                du.updateBet(zoneIndex);
                du.updatePlayerHand(zoneIndex);
                du.refreshGUI();
            }
        }
        if (player.getHands()[zoneIndex].getHandValue()>21){
            statusMsg = "Ops... Hand #" + (zoneIndex+1) + " has been busted! ... ";
            du.updateStatus();
            //delayForHalfSec();

            player.getBetZones()[zoneIndex].clearBet();
            du.updateBet(zoneIndex);
        }
    }

    public void phase5DealersRound() throws InterruptedException {
        du.highlightDealer();
        statusMsg = "[Dealer's turn... ]";
        du.updateStatus();
        //delayForHalfSec();

        dealer.openHole();
        du.updateDealerHand();
        while (dealer.getHand().getHandValue() < 17){
            statusMsg = "Dealer draws a card... ";
            du.updateStatus();
            dealer.getHand().addCard(deck.drawACard());
            du.updateDealerHand();
            //delayForHalfSec();
        }
        du.updateAllComponents();

        //delayForASec();
    }

    public void phase6WinnerDetermine() throws InterruptedException {
        du.normalizeBorders();
        statusMsg = "[Determining winner... ]";
        du.updateStatus();
        //delayForHalfSec();

        du.highlightRewardZone();
        if (dealer.getHand().getHandValue() > 21) {
            statusMsg = "Dealer has been busted, all player's hands win!";
            du.updateStatus();
            //delayForHalfSec();

            for (BetZone b : player.getBetZones())
                if (!b.isEmpty()) {
                    rewardZone.setValue(rewardZone.getValue() + b.wonBet().getValue());
                    du.updateBalanceChange();
                }
        } else {
            for (int i = 0; i < 2; i++) {
                if (!player.getBetZones()[i].isEmpty()){
                    if (player.getHands()[i].getHandValue() > dealer.getHand().getHandValue()) {
                        if (CheckWinCondition.isBlackJack(player.getHands()[i])){
                            statusMsg = "Player's hand #"+ (i+1) +" won BlackJack!! +$" + (player.getBetZones()[i].getBet().getValue()*1.5);
                            du.updateStatus();
                            rewardZone.setValue(rewardZone.getValue() + player.getBetZones()[i].wonBlackJack().getValue());
                            du.updateBalanceChange();
                            du.updateBet(i);
                        } else {
                            statusMsg = "Player's hand #" + (i + 1) + " won +$" + player.getBetZones()[i].getBet().getValue() + "!";
                            du.updateStatus();
                            rewardZone.setValue(rewardZone.getValue() + player.getBetZones()[i].wonBet().getValue());
                            du.updateBalanceChange();
                            du.updateBet(i);
                        }
                    } else if (player.getHands()[i].getHandValue() == dealer.getHand().getHandValue()) {
                        statusMsg = "Player's hand #"+ (i+1) +" got pushed back for +$"+player.getBetZones()[i].getBet().getValue()+"!";
                        du.updateStatus();
                        rewardZone.setValue(rewardZone.getValue() + player.getBetZones()[i].getPushedBack().getValue());
                    } else{
                        statusMsg = "Player's hand #"+ (i+1) +" lost -$" + player.getBetZones()[i].getBet().getValue() +"!";
                        du.updateStatus();
                        player.getBetZones()[i].clearBet();
                    }
                    du.updateBalanceChange();
                    du.updateBet(i);
                    //delayForHalfSec();
                }
            }
        }
        du.updateAllComponents();

        //delayForASec();
    }

    public void postPhase() throws InterruptedException {
        du.normalizeBorders();
        if (rewardZone.getValue() >= initialSpend)
            statusMsg = "[Round ended, you WON $" + (rewardZone.getValue()-initialSpend) + " in this round!]";
        else
            statusMsg = "[Round ended, you LOST $" + (initialSpend - rewardZone.getValue()) + " in this round!]";
        du.updateStatus();
        //delayForHalfSec();

        player.addMoney(rewardZone.getValue());
        du.updateAllComponents();
        statusMsg = "=============================================================================";
        du.updateStatus();
    }

    public void delayForASec(){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delayForHalfSec(){
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //End of procedure phase block
}
