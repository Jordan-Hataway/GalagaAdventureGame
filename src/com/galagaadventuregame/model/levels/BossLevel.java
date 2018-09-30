package com.galagaadventuregame.model.levels;

import com.galagaadventuregame.model.entities.BossEnemyShip;
import com.galagaadventuregame.model.entities.AbstractEnemyShip;
import java.util.ArrayList;
/**
 * Creates a new Boss Level
 *
 * @author Jordan Hataway (jhat2014)
 */
public class BossLevel
    extends AbstractLevel
    implements Level
{
    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
//    private static final int basicPercentage = 10;
//    private static final int dualPercentage = 60;
//    private static final int hardPercentage = 40;



    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Sets the parameters of the boss level
     */
    public BossLevel()
    {
        super();
        speed = 1;
    }

    /**
     * Sets the parameters of the enemy ship
     */
    public void generateEnemyShips() {
//        int width = windowWidth() - 90;
//        int height = windowHeight() - 400;
        ships.add(new ArrayList<AbstractEnemyShip>());
        ships.get(0).add(new BossEnemyShip());
        ships.get(0).get(0).setX(windowWidth() / 2 - ships.get(0).get(0).imageWidth());
        ships.get(0).get(0).setY(10);



//        //GENERATE 2D LIST OF SHIPS THAT FITS IN WINDOW
//        int yDifference = 40;
//        int xDifference = 60;
//        int xStart = 90;
//
//        Random random = new Random();
//        int xPadding = xStart;
//        int yPadding = 10;
//        int yIndex = -1;
//        int xIndex = -1;
//        while (yPadding < windowHeight() - 400)
//        {
//            xPadding = xStart;
//            xIndex = -1;
//            ships.add(new ArrayList<AbstractEnemyShip>());
//            yIndex++;
//            int tallestY = 0;
//
//            while (xPadding < windowWidth() - 150)
//            {
//                int randNum = random.nextInt(100);
//                xIndex++;
//                if (randNum <= basicPercentage)
//                {
//                    ships.get(yIndex).add(new BasicEnemyShip());
//                }
//                else if (randNum <= dualPercentage)
//                {
//                    ships.get(yIndex).add(new DualShotEnemyShip());
//                } else {
//                    ships.get(yIndex).add(new HardEnemyShip());
//                }
//
//                xPadding += ships.get(yIndex).get(xIndex).imageWidth() + xDifference;
//                if (ships.get(yIndex).get(xIndex).imageHeight() > tallestY) {
//                    tallestY = ships.get(yIndex).get(xIndex).imageHeight();
//                }
//            }
//            yPadding += tallestY + yDifference;
//        }



//        //SET SHIP COORDINATES
//        int xPos = xStart;
//        int yPos = 10;
//        for (int i = 0; i < ships.size(); i++) {
//            int tallestY = 0;
//            xPos = xStart;
//            for (int j = 0; j < ships.get(i).size(); j++) {
//                ships.get(i).get(j).setX(xPos);
//                ships.get(i).get(j).setY(yPos);
//                if (ships.get(i).get(j).imageHeight() > tallestY) {
//                    tallestY = ships.get(yIndex).get(xIndex).imageHeight();
//                }
//                xPos += ships.get(i).get(j).imageWidth() + xDifference;
//            }
//
//            yPos += tallestY + yDifference;
//        }
    }

}
