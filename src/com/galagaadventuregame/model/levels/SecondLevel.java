package com.galagaadventuregame.model.levels;

import com.galagaadventuregame.model.entities.HardEnemyShip;
import com.galagaadventuregame.model.entities.AbstractEnemyShip;
import com.galagaadventuregame.model.entities.BasicEnemyShip;
import com.galagaadventuregame.model.entities.DualShotEnemyShip;
import java.util.ArrayList;
import java.util.Random;
/**
 * Creates a new Second Level
 *
 * @author Jordan Hataway (jhat2014)
 */
public class SecondLevel
    extends AbstractLevel
    implements Level
{
    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
//    private int numCols;
//    private int numRows;
    private static final int basicPercentage = 50;
    private static final int dualPercentage = 80;
//    private static final int hardPercentage = 100;



    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Sets the parameters of the Second Level
     */
    public SecondLevel()
    {
        super();
        speed = 1;
    }

    /**
     * Generates the new wave of enemy ships for the Second Level
     */
    public void generateEnemyShips() {
        //GENERATE 2D LIST OF SHIPS THAT FITS IN WINDOW
        int yDifference = 40;
        int xDifference = 60;
        int xStart = 90;

        Random random = new Random();
        int xPadding = xStart;
        int yPadding = 10;
        int yIndex = -1;
        int xIndex = -1;
        while (yPadding < windowHeight() - 400)
        {
            xPadding = xStart;
            xIndex = -1;
            ships.add(new ArrayList<AbstractEnemyShip>());
            yIndex++;
            int tallestY = 0;

            while (xPadding < windowWidth() - 150)
            {
                int randNum = random.nextInt(100);
                xIndex++;
                if (randNum <= basicPercentage)
                {
                    ships.get(yIndex).add(new BasicEnemyShip());
                }
                else if (randNum <= dualPercentage)
                {
                    ships.get(yIndex).add(new DualShotEnemyShip());
                } else {
                    ships.get(yIndex).add(new HardEnemyShip());
                }

                xPadding += ships.get(yIndex).get(xIndex).imageWidth() + xDifference;
                if (ships.get(yIndex).get(xIndex).imageHeight() > tallestY) {
                    tallestY = ships.get(yIndex).get(xIndex).imageHeight();
                }
            }
            yPadding += tallestY + yDifference;
        }



        //SET SHIP COORDINATES
        int xPos = xStart;
        int yPos = 10;
        for (int i = 0; i < ships.size(); i++) {
            int tallestY = 0;
            xPos = xStart;
            for (int j = 0; j < ships.get(i).size(); j++) {
                ships.get(i).get(j).setX(xPos);
                ships.get(i).get(j).setY(yPos);
                if (ships.get(i).get(j).imageHeight() > tallestY) {
                    tallestY = ships.get(yIndex).get(xIndex).imageHeight();
                }
                xPos += ships.get(i).get(j).imageWidth() + xDifference;
            }

            yPos += tallestY + yDifference;
        }
    }

}
