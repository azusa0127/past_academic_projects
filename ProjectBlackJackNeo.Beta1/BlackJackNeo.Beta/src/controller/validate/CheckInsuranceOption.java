package controller.validate;

import module.Player;

/**
 * Created by Phoenix on 2014-11-23.
 */
public class CheckInsuranceOption {

    public static boolean IsInsuranceAvailable(Player p){
        return p.getBalance() >= p.getBetZones()[0].getInsuancingAmt();
    }

    public static boolean IsEvenMoneyAvailable(Player p){
        return p.getHands()[0].getHandValue() == 21;
    }

}
