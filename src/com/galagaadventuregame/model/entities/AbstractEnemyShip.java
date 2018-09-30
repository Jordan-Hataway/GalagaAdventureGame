package com.galagaadventuregame.model.entities;

import java.nio.channels.NotYetConnectedException;

/**
 * // -------------------------------------------------------------------------
/**
 *  The abstract class for all enemy ships
 *
 * @author Jordan Hataway (jhat2014)
 */
public abstract class AbstractEnemyShip
    implements Entity
{
    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
    private int x;
    private int y;
    /**
     * The width of the image used for this ship
     */
    protected int imageWidth;
    /**
     * The height of the image used for this ship
     */
    protected int imageHeight;


    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------
    /**
     * Indicates the movement of the enemy ships in the horizontal/x-direction
     * @param speed The speed at which the ship is moving based on the number of
     *              pixels its passing through
     */
    public void move(int speed) {
        setX(x + speed);
    }

    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------

    /**
     * Gets the enemy ship's location in the x direction
     * @return  x The ship's location in the x direction
     */
    public int getX() {
        return x;
    }
    /**
     * Gets the enemy ship's location in the y direction.
     * @return y The ship's location in the y direction.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the ship's x coordinate
     * @param x The new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Sets the ship's x coordinate
     * @param y The new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Gets the image width of the ship
     * @return image Returns the image width of the enemy ship
     */
    public int imageWidth() {
        return imageWidth;
    }
    /**
     * Gets the image height of the ship
     * @return Returns the image height of the enemy ship
     */
    public int imageHeight() {
        return imageHeight;
    }
    /**
     * implemented in subclass
     * Determines the firing of the enemy ships
     * @return false Throws a NotYetConnectedException
     */
    public boolean fire() {
        throw new NotYetConnectedException(); //not yet implemented
    }
    /**
     * implemented in subclass
     * Determines if the enemy ship was hit
     * @return false Throws a NotYetConnected Exception
     */
    public boolean hit() {
        throw new NotYetConnectedException(); //not yet implemented
    }
























    // ---------------------------------------------------------------------
    // NOT USED
    // ---------------------------------------------------------------------
    /**
     * Not used
     */
    public AbstractEnemyShip()
    {
        // TODO Auto-generated constructor stub
    }
    /**
     * Not used
     */
    public entityType type()
    {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * Not used
     */
    public int getCellX()
    {
        // TODO Auto-generated method stub
        return 0;
    }
    /**
     * Not used
     */
    public int getCellY()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
