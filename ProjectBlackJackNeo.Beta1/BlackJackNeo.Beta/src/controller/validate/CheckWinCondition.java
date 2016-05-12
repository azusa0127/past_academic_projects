package controller.validate;

import module.Hand;

/**
 * Created by eiphi_000 on 2014-11-27.
 */
public class CheckWinCondition {
    public static boolean isBlackJack(Hand hand){
        return (hand.getHandValue() == 21) && (hand.getCards().size() == 2);
    }
}
