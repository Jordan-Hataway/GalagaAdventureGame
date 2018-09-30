package com.galagaadventuregame.model.entities;

import java.nio.channels.NotYetConnectedException;
/**
 *  The class for all bullets
 *
 * @author Jordan Hataway (jhat2014)
 */
public class Bullet
    implements Entity
{
    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------

    private entityType type;
    private int x;
    private int y;
    private static final int SPEED = 8;
    private int windowHeight;

    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Creates the bullet
     * @param x Sets the current x value
     * @param y Sets the current y value
     * @param height Sets the height of the bullet on the
     *               screen at which it starts from
     */
    public Bullet(int x, int y, int height)
    {
        this.x = x;
        this.y = y;
        this.type = entityType.BULLET;
        this.windowHeight = height;
    }

    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------
    /**
     * Moves the bullet up by SPEED
     * @param direction Negative for up, Positive for down
     * @return Returns a boolean so that the activity can remove bullet when
     * it goes out of bounds. True if out of bounds, else false
     */
    public boolean move(int direction) {
        if (direction < 0)
        {
            if (y - SPEED < 0)
            {
                setY(y - SPEED);
                return true;
            }
            setY(y - SPEED);
            return false;
        } else {
            if (y + SPEED > windowHeight)
            {
                setY(y + SPEED);
                return true;
            }
            setY(y + SPEED);
            return false;
        }
    }

    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------
    /**
     * @return type Returns the entity type
     */
    public entityType type()
    {
        return type;
    }
    /**
     * Gets the x value of the bullet
     * @return x Returns the x value
     */
    public int getX() {
        return x;
    }
    /**
     * Gets the y value of the bullet
     * @return y Returns the y value
     */
    public int getY() {
        return y;
    }
    /**
     * Sets the x value of the bullet
     * @param x Sets the current x value
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Sets the y value of the bullet
     * @param y Sets the current y value
     */
    public void setY(int y) {
        this.y = y;
    }


















    // ---------------------------------------------------------------------
    // NOT USED
    // ---------------------------------------------------------------------

    public int getCellX()
    {
        throw new NotYetConnectedException(); //Not yet implemented
    }
    public int getCellY()
    {
        throw new NotYetConnectedException(); //Not yet implemented;
    }
}
