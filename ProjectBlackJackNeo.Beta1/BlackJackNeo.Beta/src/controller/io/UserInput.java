package controller.io;

import controller.Constants;
import controller.validate.CheckInsuranceOption;
import controller.validate.CheckStrategyOption;
import module.Deal;
import module.Player;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Phoenix on 2014-11-15.
 *
 * UserInput class:
 * This class offers three different JOptionPanes to get user input on bet/insurance/and strategy options.
 *
 * Public method:
 * (Deal) getBetDeal(Player p): Display a JOptionPane offers available betting amount
 *                              depending on the balance of the player p in the argument.
 *                              Take off the player balance for the amount he choose,
 *                              then return a Deal that has a value of user input.
 *
 * (int) getInsuranceOption(Player p): Display a JOptionPane offers available Insurance options
 *                              depending on the balance and initial hand of the player p in the argument.
 *                              Take off the player balance for the amount he should pay,
 *                              then return a magic int in Constants class that representing the user choice.
 *
 * (int) getStrategyOption(Player p, int zoneIndex): Display a JOptionPane offers available Strategy options
 *                              depending on the balance and hand of the player p, hand #zoneIndex in the argument.
 *                              Take off the player balance for the amount he should pay,
 *                              then return a magic int in Constants class that representing the user choice.
 *
 */

public class UserInput {

    public Deal getBetDeal(Player p){
        Deal d = new Deal(getBet(p.getBalance()));
        if (d.getValue() > 0){
            if (!p.spendMoney(d.getValue())){
                JOptionPane.showMessageDialog(null, "You don't have enough money for this chip");
                return getBetDeal(p);
            }
        }
        return d;
    }

    public int getInsuranceOption(Player p) {
        int i = getInsurance(p);
        if (i == Constants.INSUR_TAKE) {
            if (!p.spendMoney(p.getBetZones()[0].getInsuancingAmt())) {
                JOptionPane.showMessageDialog(null, "You don't have enough money for insurance!");
                return Constants.INSUR_DECLINE;
            }
        }
        return i;
    }

    public int getStrategyOption(Player p, int zoneIndex) {
        int i = getStrata(p, zoneIndex);
        if (i == Constants.STRATA_SPLIT){
            if (!p.spendMoney(p.getBetZones()[0].getBet().getValue())) {
                JOptionPane.showMessageDialog(null, "You don't have enough money for Split!");
                return getStrategyOption(p, zoneIndex);
            }
        }
        else if (i == Constants.STRATA_DOUBLE) {
            if (!p.spendMoney(p.getBetZones()[zoneIndex].getBet().getValue())) {
                JOptionPane.showMessageDialog(null, "You don't have enough money for Double Down!");
                return getStrategyOption(p, zoneIndex);
            }
        }
        return i;
    }


    private double getBet(double playerBalance){
        class optionBuild{
            String[] generateAvailableOptions(double playerBalance){
                ArrayList<String> listStr = new ArrayList<String>();
                listStr.add("Exit");
                if(playerBalance >= 5){
                    listStr.add("$5");
                    if(playerBalance >= 10){
                        listStr.add("$10");
                        if(playerBalance >= 25){
                            listStr.add("$25");
                            if(playerBalance >= 50){
                                listStr.add("$50");
                                if(playerBalance >= 100){
                                    listStr.add("$100");
                                    if(playerBalance >= 300){
                                        listStr.add("$300");
                                        if(playerBalance >= 500){
                                            listStr.add("$500");}}}}}}}

                String[] strArr = new String[listStr.size()];
                listStr.toArray(strArr);

                return strArr;
            }
        }

        String[] betAmtOptions = new optionBuild().generateAvailableOptions(playerBalance);
        double[] betValue = {-1.0, 5.0,10.0,25.0,50.0,100.0,300.0,500.0};
        int n = JOptionPane.showOptionDialog(null,
                "How much do you want to bet this time?",
                "Choose your bet",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                betAmtOptions,
                null);

        if (n == -1)
            return -1.0;

        return betValue[n];
    }

    private int getInsurance(Player p){
        class optionBuild{
            String[] generateAvailableOptions(Player p){
                ArrayList<String> listStr = new ArrayList<String>();
                listStr.add("No");
                if (CheckInsuranceOption.IsEvenMoneyAvailable(p))
                    listStr.add("Take EvenMoney");
                else if (CheckInsuranceOption.IsInsuranceAvailable(p))
                    listStr.add("Take Insurance");
                listStr.add("Surrender");

                String[] strArr = new String[listStr.size()];
                listStr.toArray(strArr);

                return strArr;
            }

            Integer[] generateAvailableReturns(Player p){
                ArrayList<Integer> listInt = new ArrayList<Integer>();
                listInt.add(Constants.INSUR_DECLINE);
                if (CheckInsuranceOption.IsEvenMoneyAvailable(p))
                    listInt.add(Constants.INSUR_EVENMONEY);
                else if (CheckInsuranceOption.IsInsuranceAvailable(p))
                    listInt.add(Constants.INSUR_TAKE);
                listInt.add(Constants.STRATA_SURRENDER);

                Integer[] intArr = new Integer[listInt.size()];
                listInt.toArray(intArr);

                return intArr;
            }
        }

        String[] insuranceOptions = new optionBuild().generateAvailableOptions(p);
        Integer[] insuranceValues = new optionBuild().generateAvailableReturns(p);
        int n = JOptionPane.showOptionDialog(null,
                "Dealer got an Ace, want insurance?",
                "Need insurance?",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                insuranceOptions,
                null);

        if (n == -1)
            return Constants.INSUR_DECLINE;

        return insuranceValues[n];
    }

    private int getStrata(Player p, int zoneIndex){
        class optionBuild{
            String[] generateAvailableOptions(Player p,int zi){
                ArrayList<String> listStr = new ArrayList<String>();
                listStr.add("Hit");
                listStr.add("Stay");
                if (CheckStrategyOption.IsDoubleAvailable(p.getHands()[zi]))
                    listStr.add("Double");
                if (CheckStrategyOption.IsSplitAvailable(p.getHands()[zi], zi))
                    listStr.add("Split");
                listStr.add("Surrender");

                String[] strArr = new String[listStr.size()];
                listStr.toArray(strArr);

                return strArr;
            }

            Integer[] generateAvailableReturns(Player p, int zi){
                ArrayList<Integer> listInt = new ArrayList<Integer>();
                listInt.add(Constants.STRATA_HIT);
                listInt.add(Constants.STRATA_STAY);
                if (CheckStrategyOption.IsDoubleAvailable(p.getHands()[zi]))
                    listInt.add(Constants.STRATA_DOUBLE);
                if (CheckStrategyOption.IsSplitAvailable(p.getHands()[zi], zi))
                    listInt.add(Constants.STRATA_SPLIT);
                listInt.add(Constants.STRATA_SURRENDER);

                Integer[] intArr = new Integer[listInt.size()];
                listInt.toArray(intArr);

                return intArr;
            }
        }

        String[] strataOptions = new optionBuild().generateAvailableOptions(p,zoneIndex);
        Integer[] strataValues = new optionBuild().generateAvailableReturns(p,zoneIndex);
        int n = JOptionPane.showOptionDialog(null,
                "Take Strategy for hand #" + (zoneIndex+1),
                "Hand #"+ (zoneIndex+1) +"Strategy",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                strataOptions,
                null);

        if (n == -1)
            return Constants.INSUR_DECLINE;

        return strataValues[n];
    }


}
