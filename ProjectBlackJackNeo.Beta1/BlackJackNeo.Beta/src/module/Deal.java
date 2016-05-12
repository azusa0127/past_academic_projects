package module;

import java.util.ArrayList;

/**
 * Created by Phoenix on 11/13/2014.
 * Deal class, this is originally designed as a group of chips that offered as a bet or bounty in a game. Not fully implemented in alpha version.
 *
 * Field:
 * (double) value: Represent the valuable amount of the deal.
 *
 * Constructor:
 * Default empty constructor not allowed, instances must be created with a initial value.
 * Deal(double value): Create instance with an initial value;
 * Deal(Chip chip): Create instance with an initial value equal to the chip in argument.
 * Deal(ArrayList<Chip> chips): Create instance with an initial value equal to the total value of the chips in argument.
 *
 * Public method:
 * (void) setValue(double value): set field value as in parameter;
 * (double) getValue(): return the field value.
 * (void) clear(): set field value to 0;
 * (ArrayList<Chip>) getArrayListOfChips(): Return an arraylist of chips equal to the field value;
 */
public class Deal {
    private double value;

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Deal(double value){
        this.value = value;
    }

    public Deal(Chip chip){
        this.value = chip.getValue();
    }

    public Deal(ArrayList<Chip> chips){
        this.value = Chip.GetChipsValueTotal(chips);
    }

    public ArrayList<Chip> getArrayListOfChips(){
        return Chip.BuildChipsEqualTo(value);
    }

    public void clear(){
        value = 0;
    }
}
