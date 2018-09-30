package com.galagaadventuregame.model.maps;

import com.galagaadventuregame.model.entities.AbstractShip;
import com.galagaadventuregame.model.entities.Ship1;
import java.util.Random;
import com.galagaadventuregame.model.entities.Enemy;
import java.util.Iterator;
import com.galagaadventuregame.model.entities.Entity.entityType;
import java.util.HashMap;

//-------------------------------------------------------------------------
/**
* First Map
*
* @author Jordan Hataway (jhat2014)
*/
public class Map1
    extends AbstractMap
    implements Map
{
    //FIELDS
    /**
     * Defines the spawn rates that are specific to this map
     */
    private static final HashMap<entityType, Integer> spawnRates;
    static { //Statically initialize the map to contain constant values
        spawnRates = new HashMap<entityType, Integer>();
        spawnRates.put(entityType.ENEMY_EASY, 4);
        spawnRates.put(entityType.ENEMY_MEDIUM, 3);
        spawnRates.put(entityType.ENEMY_HARD, 2);
        spawnRates.put(entityType.ENEMY_BOSS, 1);
        spawnRates.put(entityType.SHIP, 1);
    }
    private AbstractShip ship;
    // ---------------------------------------------------------------------
    // CONSTRUCTOR
    /**
     * Constructor
     * @param cols The number of columns
     * @param rows The number of rows
     */
    public Map1(int cols, int rows)
    {
        super(cols, rows);
    }

    // ---------------------------------------------------------------------
    //METHODS
    /**
     * Generates the entities for the map, based on the spawn rates above
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void generateEntities()
    {
        Random rand = new Random();
        int randx;
        int randy;
        Iterator it = spawnRates.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pairs = (HashMap.Entry)it.next();
            for (int i = 0; i < (Integer)pairs.getValue(); i++) {
                randx = rand.nextInt(getCols());
                randy = rand.nextInt(getRows());
                while (containsEntity(randx, randy))
                {
                    randx = rand.nextInt(getCols());
                    randy = rand.nextInt(getRows());
                }
                if (((entityType)pairs.getKey()).equals(entityType.SHIP)) {
                    ship = new Ship1(randx, randy);
                    addEntity(ship, randx, randy);
                } else {
                addEntity(new Enemy((entityType)pairs.getKey(),
                    randx, randy), randx, randy);
                }
            }
        }
    }

    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------

    /**
     * @return Returns the main ship
     */
    @Override
    public AbstractShip ship() {
        return ship;
    }

}
