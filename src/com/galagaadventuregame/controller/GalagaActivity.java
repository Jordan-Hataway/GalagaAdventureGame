package com.galagaadventuregame.controller;

import com.galagaadventuregame.model.entities.DualShotEnemyShip;
import java.util.Random;
import com.galagaadventuregame.model.entities.HardEnemyShip;
import com.galagaadventuregame.model.entities.BossEnemyShip;
import java.util.ArrayList;
import com.galagaadventuregame.model.entities.AbstractEnemyShip;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.galagaadventuregame.R;
import com.galagaadventuregame.model.entities.Bullet;
import com.galagaadventuregame.model.levels.AbstractLevel;
import com.galagaadventuregame.model.levels.BossLevel;
import com.galagaadventuregame.model.levels.FirstLevel;
import com.galagaadventuregame.model.levels.SecondLevel;
import com.galagaadventuregame.model.levels.ThirdLevel;
import com.galagaadventuregame.view.GalagaView;

/**
 * // -------------------------------------------------------------------------
/**
 *  The main game activity
 *
 * @author Jordan Hataway (jhat2014)
 */
public class GalagaActivity
    extends Activity
{

    // ---------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------
    private GalagaView       galagaView;
    private int              width;
    private int              height;
    private AbstractLevel    level;

// //ROTATION
// private float[] mValuesMagnet = new float[3];
// private float[] mValuesAccel = new float[3];
// private float[] mValuesOrientation = new float[3];
//
// private float[] mRotationMatrix = new float[9];

    // GAME LOOP FIELDS
    private boolean          isRunning;
    // GAME LOOP INIT
    // desired fps
    private final static int MAX_FPS         = 50;
    // maximum number of frames to be skipped
    private final static int MAX_FRAME_SKIPS = 5;
    // the frame period
    private final static int FRAME_PERIOD    = 1000 / MAX_FPS;
    private int loop;
    private int loopIncrement;
    private final static int MOVEMENT_TIME = 60;


    // ---------------------------------------------------------------------
    // CONSTRUCTORS
    // ---------------------------------------------------------------------
    /**
     * Runs when the activity is first created
     * @param savedInstanceState The instance state to load
     */
    @SuppressWarnings("incomplete-switch")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_galaga);

        // FIELD INIT]
        isRunning = false;
        // Get screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        //Set Up Level
        switch (Control.enemy.type()) {
            case ENEMY_EASY:
                level = new FirstLevel();
                level.setBounds(width, height);
                ((FirstLevel)level).generateEnemyShips();
                break;
            case ENEMY_MEDIUM:
                level = new SecondLevel();
                level.setBounds(width, height);
                ((SecondLevel)level).generateEnemyShips();
                break;
            case ENEMY_HARD:
                level = new ThirdLevel();
                level.setBounds(width, height);
                ((ThirdLevel)level).generateEnemyShips();
                break;
            case ENEMY_BOSS:
                level = new BossLevel();
                level.setBounds(width, height);
                ((BossLevel)level).generateEnemyShips();
                break;
        }
        Control.ship.setBounds(width, height);
        Control.ship.setX((getWidth() / 2) - 55);
        Control.ship.setY(getHeight() - 110);

        // VIEW INIT
        galagaView = new GalagaView(this, level);
        galagaView.setBackgroundColor(Color.BLACK);
        setContentView(galagaView);

    }


    // ---------------------------------------------------------------------
    // MAIN GAME LOOP
    // ---------------------------------------------------------------------
    /**
     * The main game loop
     */
    public void mainLoop()
    {
        Log.d("Tag1", "Starting Game Loop");
        loopIncrement = 1;
        loop = 0;
        Thread t = new Thread() {
            @Override
            public void run()
            {
                long beginTime; // the time when the cycle begun
                long timeDiff; // the time it took for the cycle to execute
                int sleepTime; // ms to sleep (<0 if we're behind)
                int framesSkipped; // number of frames being skipped
                sleepTime = 0;
                // Your loop here

                while (isRunning)
                {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0; // resetting the frames skipped
                    // update game state

                    //Updates to UI
                    runOnUiThread(new Runnable() {
                        public void run()
                        {
                            doUpdates();
                        }
                    });

                    // calculate how long did the cycle take
                    timeDiff = System.currentTimeMillis() - beginTime;
                    // calculate sleep time
                    sleepTime = (int)(FRAME_PERIOD - timeDiff);
                    if (sleepTime > 0)
                    {
                        // if sleepTime > 0 we're OK
                        try
                        {
                            // send the thread to sleep for a short period
                            Thread.sleep(sleepTime);
                        }
                        catch (InterruptedException e)
                        { //nothing
                        }
                    }

                    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS)
                    {
                        // we need to catch up
                        // update without rendering
                        doUpdates();
                        // add frame period to check if in next frame
                        sleepTime += FRAME_PERIOD;
                        framesSkipped++;
                    }

                }
            }
        };
        t.start();
    }


    /**
     * Performs all necessary game updates per cycle
     */
    public void doUpdates()
    {
        fireBullets();
        checkCollision();
        moveEntities();
        checkGameOver();
        galagaView.invalidate();
    }

    /**
     * Checks if the game is over
     */
    public void checkGameOver() {
        if (level.hasWon()) {
            Control.won = true;
            isRunning = false;
            finish();
        }
        if (level.lost()) {
            Control.won = false;
            isRunning = false;
            finish();
        }

    }


    /**
     * Fires enemy bullets each update cycle
     */
    public void fireBullets() {
        for (ArrayList<AbstractEnemyShip> row : level.enemies())
        {
            for (AbstractEnemyShip ship : row)
            {
                if (ship instanceof BossEnemyShip) {
                    Random random = new Random();
                    if (ship.fire()) {
                        int randNum = random.nextInt(2);
                        if (randNum == 0) {
                            level.enemyBullets.add(new Bullet(ship.getX() + 52, ship.getCellY() + 248, height));
                            level.enemyBullets.add(new Bullet(ship.getX() + 170, ship.getCellY() + 216, height));
                            level.enemyBullets.add(new Bullet(ship.getX() + 292, ship.getCellY() + 248, height));
                        } else {
                            level.enemyBullets.add(new Bullet(ship.getX() + 137, 248, height));
                            level.enemyBullets.add(new Bullet(ship.getX() + 206, 248, height));
                        }
                    }
                }
                else if (ship instanceof HardEnemyShip) {
                    if (ship.fire()) {
                        level.enemyBullets.add(new Bullet(ship.getX() + 20, ship.getCellY() + 2 * ship.imageHeight(), height));
                        level.enemyBullets.add(new Bullet(ship.getX() + 56, ship.getCellY() + 86, height));
                        level.enemyBullets.add(new Bullet(ship.getX() + 82, ship.getCellY() + 2 * ship.imageHeight(), height));
                    }
                }
                else if (ship instanceof DualShotEnemyShip) {
                    if (ship.fire()) {
                        level.enemyBullets.add(new Bullet(ship.getX() + 30, ship.getCellY() + 2 * ship.imageHeight(), height));
                        level.enemyBullets.add(new Bullet(ship.getX() + 84, ship.getCellY() + 2 * ship.imageHeight(), height));
                    }
                }
                else if (ship.fire())
                {
                    level.enemyBullets.add(new Bullet(ship.getX() + ship.imageWidth(), ship.getCellY() + 2 * ship.imageHeight(), height));
                    Log.d("Tag1", "" + ship.getY());
                }
            }
        }
    }

    /**
     * Checks if there was a collision with a bullet
     */
    public void checkCollision() {
        //CHECK COLLISION WITH SHIP BULLETS
        for (int i = level.bullets.size() - 1; i >= 0; i--)
        {
            for (int j = level.enemies().size() - 1; j >= 0; j--)
            {
                for (int k = level.enemies().get(j).size() - 1; k >= 0; k--)
                {
                    int bulletLeft = level.bullets.get(i).getX();
                    int bulletTop = level.bullets.get(i).getY();
                    int bulletRight = bulletLeft + 5;
                    int bulletBottom = bulletTop + 10;

                    int shipLeft = level.enemies().get(j).get(k).getX();
                    int shipTop = level.enemies().get(j).get(k).getY();
                    int shipRight = shipLeft + 2 * level.enemies().get(j).get(k).imageWidth();
                    int shipBottom = shipTop + 2 * level.enemies().get(j).get(k).imageHeight();

                    if (bulletLeft <= shipRight && bulletRight >= shipLeft && bulletTop <= shipBottom && bulletBottom >= shipTop) {
                        if (level.enemies().get(j).get(k).hit())
                        {
                            level.enemies().get(j).remove(k);
                            level.bullets.remove(i);
                            return;
                        } else {
                            level.bullets.remove(i);
                            return;
                        }
                    }
                }
            }
        }
        //COLLISION WITH ENEMY BULLETS
        for (int i = level.enemyBullets.size() - 1; i >= 0; i--) {
            int bulletLeft = level.enemyBullets.get(i).getX();
            int bulletTop = level.enemyBullets.get(i).getY();
            int bulletRight = bulletLeft + 5;
            int bulletBottom = bulletTop + 10;

            int shipLeft = Control.ship.getX();
            int shipTop = Control.ship.getY();
            int shipRight = shipLeft + 2 * 45;
            int shipBottom = shipTop + 2 * 45;

            if (bulletLeft <= shipRight && bulletRight >= shipLeft && bulletTop <= shipBottom && bulletBottom >= shipTop) {
                if (Control.ship.hit())
                {
                    level.setLost(true);
                    level.enemyBullets.remove(i);
                    return;
                } else {
                    level.enemyBullets.remove(i);
                    return;
                }
            }
        }
    }

    /**
     * Moves the entities on the screen
     */
    public void moveEntities()
    {

        if (loop == MOVEMENT_TIME) {
            loopIncrement = -1;
        } else if (loop == -MOVEMENT_TIME) {
            loopIncrement = 1;
        }
        loop += loopIncrement;

        Control.ship.move(); //move ship
        //move ship's bullets
        for (int i = 0; i < level.bullets.size(); i++)
        {
            if (level.bullets.get(i).move(-1))
            {
                level.bullets.remove(i);
            }
        }
        //move enemy bullets
        for (int i = 0; i < level.enemyBullets.size(); i++)
        {
            if (level.enemyBullets.get(i).move(1))
            {
                level.enemyBullets.remove(i);
            }
        }

        //MOVE ENEMIES
        for (int i = 0; i < level.enemies().size(); i++)
        {
            for (int j = 0; j < level.enemies().get(i).size(); j++)
            {
                level.enemies().get(i).get(j).move(level.speed() * loopIncrement);
            }
        }
    }


    /**
     * Called from the event listener
     */
    public void fireBullet()
    {
        level.bullets.add(new Bullet(Control.ship.getX() + 27, Control.ship
            .getY(), height));
    }

    // ---------------------------------------------------------------------
    // EVENT DETECTION
    // ---------------------------------------------------------------------
    private static final int INVALID_POINTER_ID = -1;
    private int              activePointer      = INVALID_POINTER_ID;
    private int buttonDimensions = 100;

    /**
     * Touch listener
     * @param e The motion event
     */
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        switch (e.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                activePointer = e.getPointerId(0);
                if (!isRunning)
                {
                    isRunning = true;
                    mainLoop();
                }
                else if ((int)e.getX() > width - buttonDimensions
                    && (int)e.getY() > height - buttonDimensions)
                { // RIGHT BUTTON
                    Control.ship.setSpeed(5);
                }
                else if ((int)e.getX() < buttonDimensions && (int)e.getY() > height - buttonDimensions)
                { // LEFT BUTTON
                    Control.ship.setSpeed(-5);
                }
                else
                { // EVERYWHERE ELSE
                    fireBullet();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (activePointer != INVALID_POINTER_ID)
                {
                    activePointer = INVALID_POINTER_ID;
                }
                Control.ship.setSpeed(0);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int pointerIndex = e.getActionIndex();
                int pointerId = e.getPointerId(pointerIndex);
                if (pointerId == activePointer)
                {
                    activePointer = INVALID_POINTER_ID;
                }
                fireBullet();
                break;
        }
        return true;
    }


    /**
     * Dialogue confirm exit on back button
     */
    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing Activity")
            .setMessage(
                "Are you sure you want quit?  All progress will be lost.")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which)
                {
                    isRunning = false;
                    finish();
                }

            }).setNegativeButton("No", null).show();
    }


//
//
// //ROTATION DETECTION
//
//
// private final SensorEventListener mEventListener = new SensorEventListener()
// {
// public void onAccuracyChanged(Sensor sensor, int accuracy) {
// }
//
// public void onSensorChanged(SensorEvent event) {
// // Handle the events for which we registered
// switch (event.sensor.getType()) {
// case Sensor.TYPE_ACCELEROMETER:
// System.arraycopy(event.values, 0, mValuesAccel, 0, 3);
// SensorManager.getRotationMatrix(mRotationMatrix, null, mValuesAccel,
// mValuesMagnet);
// SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
// if (mValuesOrientation[0]) {
//
// }
// break;
// }
// };

    // ---------------------------------------------------------------------
    // HELPER METHODS, GETTERS, SETTERS
    // ---------------------------------------------------------------------
    /**
     * Getter for the window width
     * @return Returns the width
     */
    public int getWidth()
    {
        return width;
    }


    /**
     * Getter for the window height
     * @return returns the height
     */
    public int getHeight()
    {
        return height;
    }
}
