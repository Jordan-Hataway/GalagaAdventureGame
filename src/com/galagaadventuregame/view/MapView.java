package com.galagaadventuregame.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.galagaadventuregame.R;
import android.content.res.Resources;
import com.galagaadventuregame.model.maps.AbstractMap;
import android.content.Context;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//-------------------------------------------------------------------------
/**
*   View for the map activity
*
* @author Jordan Hataway (jhat2014)
*/
public class MapView
    extends View
{
    //FIELDS
    private Paint paint = new Paint();
    private AbstractMap map;
    private Resources res;
    private Bitmap shipImage;
    private Bitmap enemyEasy;
    private Bitmap enemyMedium;
    private Bitmap enemyHard;
    private Bitmap enemyBoss;
    private Boolean shipSelected;




    //CONSTRUCTOR
    // ----------------------------------------------------------
    /**
     * Create a new MapView object.
     * @param context The context in which to create the view
     * @param map A map to draw
     */
    public MapView(Context context, AbstractMap map)
    {
        super(context);
        this.map = map;
        res = getResources();
        shipSelected = false;

        shipImage = BitmapFactory.decodeResource(res, R.drawable.ship);
        enemyEasy = BitmapFactory.decodeResource(res, R.drawable.enemy_easy);
        enemyMedium = BitmapFactory.decodeResource(res, R.drawable.enemy_medium);
        enemyHard = BitmapFactory.decodeResource(res, R.drawable.enemy_hard);
        enemyBoss = BitmapFactory.decodeResource(res, R.drawable.enemy_boss);

    }

    //METHODS
    /**
     * Called when the system first draws to the screen,
     * or on a call to invalidate()
     */
    @Override
    public void onDraw(Canvas canvas) {
        drawGrid(canvas);
        drawEntities(canvas);

    }

    /**
     * Draw the grid on the screen
     * @param canvas The canvas on which to draw
     */
    private void drawGrid(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < map.getRows() ; i++)
        {
            for (int j = 0; j < map.getCols(); j++)
            {
                canvas.drawRect(j * (getWidth() / map.getCols()), // Left
                    i * (getHeight() / map.getRows()), //Top
                    (j + 1) * (getWidth() / map.getCols()), //Right
                    (i + 1) * (getHeight() / map.getRows()), //Bottom
                    paint);
            }
        }
    }

    /**
     * Draw the entities on the grid
     * @param canvas The canvas on which to draw
     */
    @SuppressWarnings("incomplete-switch")
    public void drawEntities(Canvas canvas) {
        int cellWidth = getWidth() / map.getCols();
        int cellHeight = getHeight() / map.getRows();
        for (int i = 0; i < map.getCols(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (map.entityAt(i, j) != null)
                {
                    switch (map.entityAt(i, j).type())
                    {
                        case SHIP:
                            canvas.drawBitmap(shipImage,
                                i * cellWidth + ((cellWidth - shipImage.getWidth()) /2),
                                j * cellHeight + ((cellHeight - shipImage.getHeight()) /2), null);
                            break;
                        case ENEMY_EASY:
                            canvas.drawBitmap(enemyEasy,
                                i * cellWidth + 20 + ((cellWidth - enemyEasy.getWidth()) /2),
                                j * cellHeight + 20 + ((cellHeight - enemyEasy.getHeight()) /2), null);
                            break;
                        case ENEMY_MEDIUM:
                            canvas.drawBitmap(enemyMedium,
                                i * cellWidth + 20 + ((cellWidth - enemyMedium.getWidth()) /2),
                                j * cellHeight + 20 +  ((cellHeight - enemyMedium.getHeight()) /2), null);
                            break;
                        case ENEMY_HARD:
                            canvas.drawBitmap(enemyHard,
                                i * cellWidth + 20 + ((cellWidth - enemyHard.getWidth()) /2),
                                j * cellHeight + 20 + ((cellHeight - enemyHard.getHeight()) /2), null);
                            break;
                        case ENEMY_BOSS:
                            canvas.drawBitmap(enemyBoss,
                                i * cellWidth + ((cellWidth - enemyBoss.getWidth()) /2),
                                j * cellHeight + ((cellHeight - enemyBoss.getHeight()) /2), null);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Toggles the icon displayed by the ship from selected to not selected
     */
    public void toggleShipSelected() {
        if (shipIsSelected()) {
            shipImage = BitmapFactory.decodeResource(res, R.drawable.ship);
            shipSelected = false;
        } else {
            shipSelected = true;
            shipImage = BitmapFactory.decodeResource(res, R.drawable.ship_selected);
        }
    }


    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------

    /**
     * Sets the ship selected or not
     * @param selected True or False
     */
    public void setShipSelected(Boolean selected) {
        shipSelected = selected;
    }

    /**
     * @return boolean True if selected
     */
    public boolean shipIsSelected() {
        return shipSelected;
    }



}
