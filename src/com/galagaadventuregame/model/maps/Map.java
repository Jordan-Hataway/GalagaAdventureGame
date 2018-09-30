package com.galagaadventuregame.model.maps;

import com.galagaadventuregame.model.entities.Entity;


/**
 * // -------------------------------------------------------------------------
/**
 *  This interface defines the required behavior for all maps
 *
 * @author Jordan Hataway (jhat2014)
 */
public interface Map
{
    // -------------------------------------------------------------------------
    /**
     *  Represents the difficulty of the map
     *  Currently not used
     */
    public enum difficulty {
        /**
         * Difficulty Easy
         */
        EASY,
        /**
         * Difficulty Medium
         */
        MEDIUM,
        /**
         * Difficulty Hard
         */
        HARD
    }
    /**
     * Generate entities
     */
    void generateEntities();
    /**
     * Allow the entity to move
     * @param xCell Item coordinates in the x-direction
     * @param yCell Item coordinates in the y-direction
     * @param deltaX Movement coordinates in the x-direction
     * @param deltaY Movement coordinates in the y-direction
     */
    void moveEntity(int xCell, int yCell, int deltaX, int deltaY);
    /**
     * Get Information from the x and y cells
     * @param xCell Info from x cell
     * @param yCell Info from y cell
     * @return Doesn't do anything
     */
    String getInfo(int xCell, int yCell);
    // ----------------------------------------------------------
    /**
     * Get Rows
     * @return rows Rows in the y-direction
     */
    int getRows();
    // ----------------------------------------------------------
    /**
     * Gets Columns
     * @return cols Columns in the x-direction
     */
    int getCols();
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param xCell
     * @param yCell
     */
    void removeEntity(int xCell, int yCell);
    // ----------------------------------------------------------
    /**
     * Remove Entity in the indicated cell
     * @param entity Entitiy in cell
     * @param xCell Cell coordinate in the x direction
     * @param yCell Cell coordinate in the y direction
     */
    void addEntity(Entity entity, int xCell, int yCell);
    // ----------------------------------------------------------
    /**
     * Determines if the cell determines a specific entity
     * @param xCell Cell coordinate in the x direction
     * @param yCell Cell coordinate in the y direction
     * @return boolean, true if contains entity
     */
    Boolean containsEntity(int xCell, int yCell);
    /**
     * Places gets the location of the entity
     * @param xCell Cell coordinate in the x direction
     * @param yCell Cell coordinate in the y direction
     * @return returns entity at specified coords
     */
    Entity entityAt(int xCell, int yCell);

}
