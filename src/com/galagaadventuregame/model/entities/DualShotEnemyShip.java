package com.galagaadventuregame.model.entities;

import java.util.Random;
/**
 * // -------------------------------------------------------------------------
/**
 *  The class for medium difficulty ships
 *
 * @author Jordan Hataway (jhat2014)
 */
public class DualShotEnemyShip
    extends AbstractEnemyShip
    implements Entity
{
    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
    private int health;
    private static final int shootProbability = 7;

    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Creates the width, height, and the health of the dual shot ship
     */
    public DualShotEnemyShip()
    {
        health = 1;
        imageWidth = 60;
        imageHeight = 38;
    }

    /**
     * @return Returns a boolean, true if the ship was destroyed by the
     * hit, false otherwise
     */
    @Override
    public boolean hit() {
        health --;
        if (health <= 0) {
            return true;
        }
        return false;
    }

    /**
     * @return Returns true if fires this update, false otherwise
     */
    @Override
    public boolean fire() {
        Random random = new Random();
        int randNum = random.nextInt(1000);
        if (randNum <= shootProbability) {
            return true;
        }
        return false;
    }
}
