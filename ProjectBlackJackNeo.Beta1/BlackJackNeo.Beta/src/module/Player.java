package module;

import module.basis.CharacterWithBalance;

/**
 * Created by Phoenix on 2014-11-15.
 *
 * Player Class:
 *
 */
public class Player extends CharacterWithBalance {
    private BetZone[] betZones;
    private Hand[] hands;

    public Player(){
        clear();
    }

    public Player(double startingBalance){
        super(startingBalance);
        clear();
    }

    public BetZone[] getBetZones() {
        return betZones;
    }

    public Hand[] getHands() {
        return hands;
    }


    public void clear(){
        betZones = new BetZone[]{new BetZone(), new BetZone()};
        hands = new Hand[]{new Hand(), new Hand()};
    }

}
