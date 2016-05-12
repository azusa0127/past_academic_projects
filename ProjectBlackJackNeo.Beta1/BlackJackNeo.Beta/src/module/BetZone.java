package module;

/**
 * Created by Phoenix on 11/13/2014.
 * BetZone is a place to put offered deal as bet/insurance etc, dealer deals the chips in this zone only.
 */
public class BetZone {
    private Deal bet;
    private Deal insurance;

    public BetZone(){
        bet = new Deal(0);
        insurance = new Deal(0);
    }

    public BetZone(Deal bet){
        this.bet = bet;
        insurance = new Deal(0);
    }

    public Deal getInsurance() {
        return insurance;
    }

    public Deal getBet() {
        return bet;
    }

    public void setBet(Deal bet) {
        this.bet = bet;
    }

    public void clearBet(){
        bet = new Deal(0);
    }

    public void clearInsurance(){
        insurance = new Deal(0);
    }

    public double getInsuancingAmt(){
            return bet.getValue()/2.0;
    }

    public void takeInsurance() {
        insurance = new Deal(getInsuancingAmt());
    }

    public void doubleTheBet(){
        bet = new Deal(bet.getValue()*2);
    }

    public Deal wonInsurance(){
        double value = insurance.getValue()*3;
        clearBet();
        clearInsurance();
        return new Deal(value);
    }

    public Deal wonBet(){
        double value = bet.getValue()*2;
        clearBet();
        return new Deal(value);
    }

    public Deal wonBlackJack(){
        double value = bet.getValue()*5/2;
        clearBet();
        return new Deal(value);
    }

    public Deal getPushedBack(){
        double value = bet.getValue();
        clearBet();
        return new Deal(value);
    }

    public boolean isEmpty(){
        return bet.getValue() == 0;
    }
}
