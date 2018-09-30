package com.galagaadventuregame.model.entities;


// -------------------------------------------------------------------------
/**
 *  Interface for all Entities.
 *
 * @author Jordan Hataway (jhat2014)
 */
public interface Entity
{
    /**
     *  Enum for all available entities. Includes:
     *  ENEMY_EASY, ENEMY_MEDIUM, ENEMY_HARD, ENEMY_BOSS, SHIP, STARBASE, STAR
     */
    public enum entityType {
        /**
         * Easy Level Enemy
         */
        ENEMY_EASY,
        /**
         * Medium Level Enemy
         */
        ENEMY_MEDIUM,
        /**
         * Hard Level Enemy
         */
        ENEMY_HARD,
        /**
         * Enemy boss
         */
        ENEMY_BOSS,
        /**
         * User's ship
         */
        SHIP,
        /**
         * Not used
         */
        STARBASE,
        /**
         * Not used
         */
        STAR,
        /**
         * Bullet that is being fired
         */
        BULLET
        }

    // ----------------------------------------------------------
    /**
     * Gets the entity type
     * @return  type The type of the entity
     */
    @Override
    String toString();
    /**
     * Gets the entity type
     * @return  type The type of the entity
     */
    entityType type();
    /**
     * Gets the cell in the x direction
     * @return x cell
     */
    int getCellX();
    /**
     * Gets the cell in the y direction
     * @return y cell
     */
    int getCellY();

}
