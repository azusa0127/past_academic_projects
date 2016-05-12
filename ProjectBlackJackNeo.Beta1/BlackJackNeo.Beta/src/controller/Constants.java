package controller;

/**
 * Created by Phoenix on 2014-11-23.
 *
 * Constants class:
 * This class holds for static magic int that used in option determination.
 *
 * Constant list:
 * STRATA_HIT: Player strategy - hit.
 * STRATA_Stay: Player strategy - stay.
 * STRATA_SPLIT: Player strategy - split.
 * STRATA_DOUBLE: Player strategy - double down.
 * STRATA_SURRENDER: Player strategy - surrender.
 *
 * INSUR_TAKE: Insurance option - take insurance.
 * INSUR_DECLINE: Insurance option - not to take.
 * INSUR_EVENMONEY: Insurance option - take even money.
 */
public class Constants {
    public final static int STRATA_HIT = 201, STRATA_STAY = 202, STRATA_SPLIT = 203, STRATA_DOUBLE = 204, STRATA_SURRENDER = 205;
    public final static int INSUR_TAKE = 101, INSUR_DECLINE = 102, INSUR_EVENMONEY = 103;
}
