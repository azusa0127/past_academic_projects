package module;

/**
 * Created by eiphi_000 on 2014-11-15.
 */
public class Dealer {
    private Hand hand;
    private Card hole;

    public Dealer(){
        clear();
    }

    public void clear(){
        hand = new Hand();
        hole = null;
    }

    public void setHole(Card hole) {
        this.hole = hole;
    }

    public Hand getHand() {
        return hand;
    }

    public void openHole(){
        hand.addCard(hole);
        hole = null;
    }

}
