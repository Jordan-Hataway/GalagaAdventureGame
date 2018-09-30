package com.galagaadventuregame.model.levels;

import com.galagaadventuregame.model.entities.AbstractEnemyShip;
import com.galagaadventuregame.model.entities.Bullet;
import java.util.ArrayList;

/**
 * // -------------------------------------------------------------------------
/**
/**
 *  Abstract class for all levels
 *
 * @author Jordan Hataway (jhat2014)
 */
public abstract class AbstractLevel
    implements Level
{
    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
    private int lives = 3;
    private int score;
    private int windowWidth;
    private int windowHeight;
    private boolean lost;
    /**
     * The list of enemy ships
     */
    protected ArrayList<ArrayList<AbstractEnemyShip>> ships;
    /**
     * The speed of the enemies
     */
    protected int speed;
    /**
     * The list of all spawned bullets
     */
    public ArrayList<Bullet> bullets;
    /**
     * The list of all spawned enemy bullets
     */
    public ArrayList<Bullet> enemyBullets;


    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Creats a new Abstract Level
     */
    public AbstractLevel() {
        bullets = new ArrayList<Bullet>();
        enemyBullets = new ArrayList<Bullet>();
        ships = new ArrayList<ArrayList<AbstractEnemyShip>>();
        lost = false;

    }


    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------
    /**
     * Not used
     */
    public void attack()
    {
        // TODO Auto-generated method stub

    }

    /**
     * @return Returns a boolean value, true if all enemy ships have been
     * destroyed, false otherwise
     */
    public boolean hasWon() {
        for (int i = 0; i < ships.size(); i++)
        {
            for (int j = 0; j < ships.get(i).size(); j++)
            {
                if (ships.get(i).get(j) != null) {
                    return false;
                }
            }
        }
        return true;
    }



    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------
    /**
     * The list of enemies implemented in the level
     * @return ships Ships that are present on level
     */
    public ArrayList<ArrayList<AbstractEnemyShip>> enemies() {
        return ships;
    }
    /**
     * Sets the bounds of the screen on the level
     * @param width Bounds in the x direction
     * @param height Bounds in the y direction
     */
    public void setBounds(int width, int height) {
        this.windowHeight = height;
        this.windowWidth = width;
    }
    /**
     * Determines if the user still has remaining lives to continue
     * @return true if the user's lives have hit zero
     */
    public boolean loseLife() {
        lives--;
        if (lives == 0) {
            return true;
        }
        return false;
    }
    /**
     * Place a description of your method here.
     */
    public void addLife() {
        lives++;
    }
    /**
     * Get the current Score
     * @return score Returns the current score
     */
    public int getScore()
    {
        return score;
    }
    /**
     * Determines the current window width
     * @return windowWidth Returns the current window width
     */
    public int windowWidth() {
        return windowWidth;
    }
    /**
     * Determines the current window height
     * @return windowHeight Returns the current window height
     */
    public int windowHeight() {
        return windowHeight;
    }
    /**
     * Get the speed
     * @return speed Returns the speed of the moving object
     */
    public int speed() {
        return speed;
    }
    /**
     * Determines whether or not the user has lost
     * @return lost True if lost, false if the user still has lives
     */
    public boolean lost() {
        return lost;
    }
    /**
     * Sets the current condition if lost
     * @param lost  Whether or not the user has lost or not
     */
    public void setLost(boolean lost) {
        this.lost = lost;
    }



}
