package com.galagaadventuregame.model.maps;

import com.galagaadventuregame.model.entities.AbstractShip;
import java.nio.channels.NotYetConnectedException;
import com.galagaadventuregame.model.entities.Entity;

// -------------------------------------------------------------------------
/**
 *  Abstract class for all main maps in this game.  Defines methods common
 *  to all maps
 *
 * @author Jordan Hataway (jhat2014)
 */
public abstract class AbstractMap
    implements Map
{
    private int rows, cols;
    private Entity[][] mapEntities;

    /**
     * Constructor
     * @param rows Number of rows
     * @param cols Number of columns
     */
    public AbstractMap(int cols, int rows) {
        this.rows = rows;
        this.cols = cols;
        mapEntities = new Entity[cols][rows];

        generateEntities();
    }

    /**
     * Moves the entity from the current position (xCell, yCell) to the new
     * position (xCell + deltaX, yCell + deltaY).
     * @param xCell The current x coordinate
     * @param yCell The current y coordinate
     * @param newX The amount to move in the x
     * @param newY The amount to move in the y
     * @throws IllegalArgumentException if deltaX or deltaY are out of bounds
     * or if the specified cell is empty
     */
    public void moveEntity(int xCell, int yCell, int newX, int newY)
    {
        if (mapEntities[xCell][yCell] == null) {
            throw new IllegalArgumentException("The specified cell is empty");
        }
        else if (newX >= cols || newX < 0 || newY >= rows || newY < 0) {
            throw new IllegalArgumentException("The new coordinates are out of bounds!");
        } else {
            mapEntities[newX][newY] = mapEntities[xCell][yCell];
        removeEntity(xCell, yCell);
        }
    }

    /**
     * Gets the entity that is in the specified location.
     * Does not change the map.
     * @param xCell the x coordinate
     * @param yCell the y coordinate
     * @return Returns the entity at the specified location, null if empty
     */
    public Entity entityAt(int xCell, int yCell) {
        return mapEntities[xCell][yCell];
    }

    /**
     * Removes the entity at the target location
     * @param xCell the x coordinate
     * @param yCell the y coordinate
     */
    public void removeEntity(int xCell, int yCell)
    {
        mapEntities[xCell][yCell] = null;
    }

    /**
     * Adds the entity to the specified cell if the cell is currently empty
     * @param entity The entity to be added
     * @param xCell the x position of cell
     * @param yCell the y position of cell
     * @throws IllegalStateException If the cell was not empty
     */
    public void addEntity(Entity entity, int xCell, int yCell)
    {
        if (mapEntities[xCell][yCell] != null) {
            throw new IllegalStateException("Cell is not empty");
        } else {
            mapEntities[xCell][yCell] = entity;
        }
    }

    //HELPER METHODS, GETTERS, SETTERS
    /**
     * Tests whether the specified cell contains an entity
     * @param xCell The x cell to be looked at
     * @param yCell The y cell to be looked at
     * @return Returns boolean, true if there exists an entity at the
     * specified location, false otherwise
     */
    public Boolean containsEntity(int xCell, int yCell) {
        if (mapEntities[xCell][yCell] == null) {
            return false;
        }
        return true;
    }

    /**
     * Gets the info of whatever is at the specified location
     * @param xCell The x coordinate of the cell
     * @param yCell The y coordinate of the cell
     * @return Returns the getInfo() method of whatever object currently
     * occupies the cell, or null if empty
     */
    public String getInfo(int xCell, int yCell)
    {
        if (mapEntities[xCell][yCell] == null) {
            return null;
        } else {
            return mapEntities[xCell][yCell].toString();
        }
    }

    /**
     * Implemented in subclass
     */
    public void generateEntities() {
        throw new NotYetConnectedException(); //Not yet implemented
    }

    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------

    /**
     * @return Returns the main ship
     */
    public AbstractShip ship() {
        throw new NotYetConnectedException(); //not implemented yet
    }
    /**
     * @return Returns the number of rows in this map
     */
    public int getRows()
    {
        return rows;
    }
    /**
     * @return Returns the number of columns in this map
     */
    public int getCols()
    {
        return cols;
    }

}
