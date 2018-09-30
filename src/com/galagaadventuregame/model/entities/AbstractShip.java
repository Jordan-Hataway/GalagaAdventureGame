package com.galagaadventuregame.model.entities;
/**
 *  The abstract class for all ships
 *
 * @author Jordan Hataway (jhat2014)
 */
public class AbstractShip
    implements Entity
{
    //FIELDS
    private entityType type;
    private int xCell;
    private int yCell;
    private int x;
    private int y;
    private int speed;
    private int windowWidth;
    private int windowHeight;
    private static final int MAX_SPEED = 20;
    /**
     *  Indcation of ship's health
     */
    protected int health;

    // ---------------------------------------------------------------------
    //CONSTRUCTOR
    // ---------------------------------------------------------------------
    /**
     * Creates a new AbstractShip object.
     * @param x The x position of the player's ship
     * @param y The y position of the player's ship
     */
    public AbstractShip(int x, int y) {
        type = entityType.SHIP;
        this.xCell = x;
        this.yCell = y;
        speed = 0;
        health = 3;
    }
    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------
    /**
     * Sets the speed of the player's ship when called to move and
     * when its called to stop
     */
    public void accelerate() {
        if (speed > 0) {
            setSpeed(speed + 1);
        } else if (speed < 0) {
            setSpeed(speed - 1);
        }
    }

    /**
     * @return Returns a boolean, true if the ship is now dead, false otherwise
     */
    public boolean hit() {
        health--;
        if (health <= 0) {
            return true;
        }
        return false;
    }
    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------
    /**
     * Gets the entity type
     * @return type Returns the entity type
     */
    public entityType type()
    {
        return type;
    }
    /**
     * @return Returns the xCell of the ship
     */
    public int getCellX()
    {
        return xCell;
    }
    /**
     * @return Returns the yCell of the ship
     */
    public int getCellY()
    {
        return yCell;
    }

    // ---------------------------------------------------------------------
    //GALAGAVIEW
    // ---------------------------------------------------------------------
    /**
     * Sets the ship's x coordinate
     * @param x The new x coordinate
     */
    public void setXCell(int x) {
        xCell = x;
    }
    /**
     * Sets the ship's y coordinate
     * @param y The new y coordinate
     */
    public void setYCell(int y) {
        yCell = y;
    }

    /**
     * @return the x coordinate of the ship
     */
    public int getX()
    {
        return x;
    }
    /**
     * @return Returns the y coordinate of the ship
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the ship's x coordinate
     * @param x The new x coordinate
     */
    public void setX(int x) {
        if (x <= 0) {
            this.x = 0;
        } else if(x >= windowWidth - 110) {
            this.x = windowWidth - 110;
        }
        else {
            this.x = x;
        }
    }
    /**
     * Sets the ship's y coordinate
     * @param y The new y coordinate
     */
    public void setY(int y) {
        if (y >= 0 && y <= windowHeight - 55)
        {
            this.y = y;
        }
    }
    /**
     * Creates the speed of the ship in the x direction. This is determined by
     * how long the user holds down the movement buttons
     */
    public void move() {
        setX(x + speed);
        accelerate();
    }

    /**
     * Sets the ships speed to the specified integer
     * @param speed The speed of the ship
     */
    public void setSpeed(int speed) {
        if (speed <= MAX_SPEED && speed >= -MAX_SPEED) {
            this.speed = speed;
        } else if (speed < 0) {
            this.speed = -MAX_SPEED;
        } else {
            this.speed = MAX_SPEED;
        }
    }
    /**
     * Gets the speed of the ship
     * @return speed Returns the speed of the ship
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * Sets the bounds of the ship within the screen's parameters
     * @param width The length at which the ship can travel in the x direction
     * @param height The height at which teh ship can travel in the y direction
     */
    public void setBounds(int width, int height) {
        windowHeight = height;
        windowWidth = width;
    }
    /**
     * Sets the health of the ship
     * @param health The given health of a ship
     */
    public void setHealth(int health) {
        this.health = health;
    }

}
