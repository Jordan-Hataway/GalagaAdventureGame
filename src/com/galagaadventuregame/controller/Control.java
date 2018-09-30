package com.galagaadventuregame.controller;

import com.galagaadventuregame.model.entities.Enemy;
import com.galagaadventuregame.model.entities.AbstractShip;
/**
 * // -------------------------------------------------------------------------
/**
 *  Control class for passing data between classes
 *
 * @author Jordan Hataway (jhat2014)
 */
public class Control
{
    /**
     * Represents whether the activity is on it's first execution
     */
    public static boolean started;
    /**
     * The ship to be passed to activity via control
     */
    public static AbstractShip ship;
    /**
     * The enemy to be passed to activity via control
     */
    public static Enemy enemy;

    /**
     * Whether or not the level was beat
     */
    public static boolean won;
}
