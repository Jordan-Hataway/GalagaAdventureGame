package com.galagaadventuregame.view;

import com.galagaadventuregame.model.entities.BossEnemyShip;
import com.galagaadventuregame.model.entities.HardEnemyShip;
import com.galagaadventuregame.model.entities.DualShotEnemyShip;
import com.galagaadventuregame.model.entities.BasicEnemyShip;
import com.galagaadventuregame.controller.Control;
import com.galagaadventuregame.model.entities.Bullet;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.galagaadventuregame.R;
import com.galagaadventuregame.model.levels.AbstractLevel;
//-------------------------------------------------------------------------
/**
*   View for the galaga activity
*
* @author Jordan Hataway (jhat2014)
*/
public class GalagaView
    extends View
{

    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
    private Bitmap shipImage;
    private Bitmap bulletImage;
    private Bitmap EnemyBulletImage;
    private Bitmap badShipBasicImage;
    private Bitmap badShipDualShotImage;
    private Bitmap badShipHardImage;
    private Bitmap badShipBossImage;
    private Resources res;
    private AbstractLevel level;




    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Constructor for the GalagaView
     * Initializes image resources
     * @param context The context in which to operate
     * @param level The level that performs all logic updates
     */
    public GalagaView(Context context, AbstractLevel level)
    {
        super(context);
        res = getResources();


        shipImage = BitmapFactory.decodeResource(res, R.drawable.small_ship);
        bulletImage = BitmapFactory.decodeResource(res, R.drawable.bullet_one);
        EnemyBulletImage = BitmapFactory.decodeResource(res, R.drawable.bullet_one_flipped);
        badShipBasicImage = BitmapFactory.decodeResource(res, R.drawable.bad_ship_basic);
        badShipDualShotImage = BitmapFactory.decodeResource(res, R.drawable.bad_ship_dual_shot);
        badShipHardImage = BitmapFactory.decodeResource(res, R.drawable.bad_ship_hard);
        badShipBossImage = BitmapFactory.decodeResource(res, R.drawable.bad_ship_boss);
        this.level = level;
    }



    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------
    /**
     * Called when the system first draws view to screen or on
     * invalidate() call
     * @param canvas The canvas to draw on
     */
    @Override
    public void onDraw(Canvas canvas) {
        drawShip(canvas);
        drawEnemies(canvas);
        drawBullets(canvas);
        drawButtons(canvas);
    }
    /**
     * Draws the icons representing the buttons on the left and right
     * @param canvas The canvas to draw on
     */
    public void drawButtons(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(getWidth() - 40f, getHeight() - 40f, 40, paint);
        canvas.drawCircle(40f, getHeight() - 40f, 40, paint);
    }
    /**
     * Draws the ship at the ship's x and y coordinates
     * @param canvas The canvis on which to draw
     */
    public void drawShip(Canvas canvas) {
        canvas.drawBitmap(shipImage, Control.ship.getX(),
            Control.ship.getY(), null);

    }

    /**
     * Draws the enemies on the screen based on their x and y coords
     * @param canvas The canvas on which to draw
     */
    public void drawEnemies(Canvas canvas) {
        for (int i = 0; i < level.enemies().size(); i++)
        {
            for (int j = 0; j < level.enemies().get(i).size(); j++)
            {
                if (level.enemies().get(i).get(j) instanceof BasicEnemyShip) {
                    canvas.drawBitmap(badShipBasicImage, level.enemies().get(i).get(j).getX(), level.enemies().get(i).get(j).getY(), null);
                }
                else if (level.enemies().get(i).get(j) instanceof DualShotEnemyShip) {
                    canvas.drawBitmap(badShipDualShotImage, level.enemies().get(i).get(j).getX(), level.enemies().get(i).get(j).getY(), null);
                }
                else if (level.enemies().get(i).get(j) instanceof HardEnemyShip) {
                    canvas.drawBitmap(badShipHardImage, level.enemies().get(i).get(j).getX(), level.enemies().get(i).get(j).getY(), null);
                }
                else if (level.enemies().get(i).get(j) instanceof BossEnemyShip) {
                    canvas.drawBitmap(badShipBossImage, level.enemies().get(i).get(j).getX(), level.enemies().get(i).get(j).getY(), null);
                }
            }
        }
    }

    /**
     * Draws the bullets on the screen
     * @param canvas The canvas on which to draw
     */
    public void drawBullets(Canvas canvas) {
        for (Bullet bullet : level.bullets) {
            canvas.drawBitmap(bulletImage, bullet.getX(), bullet.getY(), null);
        }
        for (Bullet bullet : level.enemyBullets) {
            canvas.drawBitmap(EnemyBulletImage, bullet.getX(), bullet.getY(), null);
        }
    }


    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------





}
