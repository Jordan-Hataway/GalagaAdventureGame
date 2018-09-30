package com.galagaadventuregame.model.entities;

import java.util.Random;
/**
 *  The class for all boss enemy ships
 *
 * @author Jordan Hataway (jhat2014)
 */
public class BossEnemyShip
    extends AbstractEnemyShip
    implements Entity
{
    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
    private int health;
    private static final int shootProbability = 25;

    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Constructor
     * Sets the parameters of the boss enemy ship
     */
    public BossEnemyShip()
    {
        health = 50;
        imageWidth = 175;
        imageHeight = 125;
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
