package controller.validate;

import module.Hand;

/**
 * Created by Phoenix on 2014-11-23.
 */
public class CheckStrategyOption {

    public static boolean IsDoubleAvailable(Hand h){
        return (h.getCards().size() < 3) && (h.getHandValue() >= 9) && (h.getHandValue() <= 11);
    }

    public static boolean IsSplitAvailable(Hand h, int zoneIndex){
        if (zoneIndex != 0)
            return false;
        if (h.getCards().get(0) == h.getCards().peek())
            return false;
        if (h.getCards().get(0).getCardValue() != h.getCards().get(1).getCardValue())
            return false;
        if (h.getCards().get(1) != h.getCards().peek())
            return false;
        return true;
    }
}
