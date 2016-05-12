package module;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Phoenix on 11/13/2014.
 * Chip Class, this is originally designed for advanced gui display, yet not implemented in current alpha version.
 *
 * Field:
 * (double) value: represent the value of the chip.
 *
 * Public Methods:
 * (double) getValue(): Return field value.
 * (String) getName(): Return appropriate display name.
 * (Color) getColor(): Return the color that reflects the value of the chip.
 *
 * Static methods:
 * (double) GetChipsValueTotal(ArrayList<Chip> chips): Return total value of all the chips contains in the parameter arraylist 'chips'.
 * (ArrayList<Chip>) BuildChipsEqualTo(double value): Return an ArrayList of Standard chips reflects the same value as in the parameter.
 */
public class Chip {

    private double value;

    public double getValue() {
        return value;
    }

    public Chip(double value) {
        this.value = value;
    }

    public String getName(){
        String _name;
        if (value == 500.0) {
            _name = "$500";

        } else if (value == 100.0) {
            _name = "$100";

        } else if (value == 50.0) {
            _name = "$50";

        } else if (value == 25.0) {
            _name = "$25";

        } else if (value == 10.0) {
            _name = "$10";

        } else if (value == 5.0) {
            _name = "$5";

        } else if (value == 2.5) {
            _name = "$2.5";

        } else if (value == 1.0) {
            _name = "$1";

        } else {
            _name = "$" + value;

        }
        return _name;
    }

    public Color getColor(){
        Color _color;
        if (value == 500.0) {
            _color = Color.magenta;

        } else if (value == 100.0) {
            _color = Color.black;

        } else if (value == 50.0) {
            _color = Color.blue;

        } else if (value == 25.0) {
            _color = Color.green;

        } else if (value == 10.0) {
            _color = Color.gray;

        } else if (value == 5.0) {
            _color = Color.red;

        } else if (value == 2.5) {
            _color = Color.orange;

        } else if (value == 1.0) {
            _color = Color.yellow;

        } else {
            _color = Color.white;
        }
        return _color;
    }


    public static double GetChipsValueTotal(ArrayList<Chip> chips){
        double sum = 0;
        for (Chip chip : chips) sum += chip.getValue();
        return sum;
    }

    public static ArrayList<Chip> BuildChipsEqualTo(double value){
        ArrayList<Chip> newChips = new ArrayList<Chip>();
        while (value > 0) {
            if (value >= 500) {
                value -= 500;
                newChips.add(new Chip(500));
            } else if (value >= 100) {
                value -= 100;
                newChips.add(new Chip(100));
            } else if (value >= 50) {
                value -= 50;
                newChips.add(new Chip(50));
            } else if (value >= 25) {
                value -= 25;
                newChips.add(new Chip(25));
            } else if (value >= 10) {
                value -= 10;
                newChips.add(new Chip(10));
            } else if (value >= 5) {
                value -= 5;
                newChips.add(new Chip(5));
            } else if (value >= 2.5) {
                value -= 2.5;
                newChips.add(new Chip(2.5));
            } else if (value >= 1) {
                value -= 1;
                newChips.add(new Chip(1));
            }
        }
        return newChips;
    }
}
