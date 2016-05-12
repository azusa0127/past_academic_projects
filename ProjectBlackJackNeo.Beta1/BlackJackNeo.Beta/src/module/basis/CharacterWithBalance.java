package module.basis;

/**
 * Created by Phoenix on 2014-11-15.
 *
 * CharacterWithBalance class:
 * This base class for the player has extract the financial features.
 * It holds player balance in field balance, and offers methods to check/spend/add money one has.
 */
public class CharacterWithBalance {
    private double balance;

    public CharacterWithBalance(){
        balance = 2000.0;
    }

    public CharacterWithBalance(double balance){
        if (balance>0)
            this.balance = balance;
        else
            this.balance = 2000.0;
    }

    public double getBalance() {
        return balance;
    }

    public void addMoney(double addingAmt){
        balance += addingAmt;
    }

    public boolean spendMoney(double spendingAmt){
        if (spendingAmt > balance)
            return false;
        balance -= spendingAmt;
        return true;
    }
}
