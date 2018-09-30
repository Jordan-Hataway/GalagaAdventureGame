package com.galagaadventuregame.model.entities;

// -------------------------------------------------------------------------
/**
 *  Enemy class that represents the different enemies available
 *
 *  @author Jordan Hataway (jhat2014)
 */
public class Enemy
    implements Entity
{
    private entityType type;
    private int xCell;
    private int yCell;

    /**
     * Constructor
     * @param type The type of enemy to be made
     * @param x The x coordinate of the enemy
     * @param y The y coordinate of the enemy
     * @throws IllegalArgumentException if the type is not an enemy
     */
    public Enemy(entityType type, int x, int y)
    {
        if (type == entityType.STAR || type == entityType.STARBASE
            || type == entityType.SHIP) {
            throw new IllegalArgumentException("Not a valid enemy type!");
        } else {
            this.type = type;
        }
        this.xCell = x;
        this.yCell = y;
    }


    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    /**
     * @return Returns the type of this enemy, as outlined in
     * {@link com.galagaadventuregame.model.entities.Entity.entityType}
     */
    public entityType type()
    {
        return type;
    }
    /**
     * @return Returns the X coordinate of this enemy on the map
     */
    public int getCellX()
    {
        return xCell;
    }
    /**
     * @return Returns the Y coordinate of this enemy on the map
     */
    public int getCellY()
    {
        return yCell;
    }
    /**
     * Creates a toString
     * @return null if there isn't a string
     */
    @Override
    public String toString()
    {
        return "" + type;
    }
    /**
     * Sets the type of entity
     * @param type The entity type that is retrieved
     */
    public void setType(entityType type) {
        this.type = type;
    }
}
