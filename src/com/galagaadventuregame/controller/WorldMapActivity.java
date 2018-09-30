package com.galagaadventuregame.controller;


import com.galagaadventuregame.model.entities.Enemy;
import android.content.Intent;
import com.galagaadventuregame.model.entities.Entity.entityType;
import android.view.MotionEvent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.graphics.Color;
import com.galagaadventuregame.view.MapView;
import com.galagaadventuregame.model.maps.AbstractMap;
import com.galagaadventuregame.model.maps.Map1;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.view.Window;
import android.os.Bundle;
import android.app.Activity;

// -------------------------------------------------------------------------
/**
 *  The controller for the Map which shows where the user is in the game
 *  and where to go
 *
 * @author Jordan Hataway (jhat2014)
 */
public class WorldMapActivity
    extends Activity
{

    //FIELDS
    private AbstractMap map;
    private int width;
    private int height;
    private MapView mapView;


    // ---------------------------------------------------------------------

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Tag1", "WorldMapActivity is creating");
        //set fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Get screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        map = new Map1(7, 7);

        mapView = new MapView(this, map);
        mapView.setBackgroundColor(Color.BLACK);
        setContentView(mapView);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing Activity")
            .setMessage("Are you sure you want quit?  All progress will be lost.")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        })
        .setNegativeButton("No", null)
        .show();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Pixels
        int x = (int)event.getX();
        int y = (int)event.getY();
        //Cell coordinates (xCell = column, yCell = row)
        int xCell = x / (width / map.getCols());
        int yCell = y / (height / map.getRows());

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            if (mapView.shipIsSelected()) { //Ship selected
                if (map.containsEntity(xCell, yCell)
                    && map.entityAt(xCell, yCell).type() == entityType.SHIP) {
                    mapView.toggleShipSelected();
                    mapView.invalidate();
                }
                else if(map.ship().getCellX() - xCell == 0
                    && map.ship().getCellY() - yCell == 0) { //
                    mapView.toggleShipSelected();
                    mapView.invalidate();
                }
                else if (Math.abs(map.ship().getCellX() - xCell) <= 1
                    && Math.abs(map.ship().getCellY() - yCell) <= 1) {

                    if (!map.containsEntity(xCell, yCell)) { //Empty cell

                        map.moveEntity(map.ship().getCellX(),
                            map.ship().getCellY(), xCell, yCell);
                        map.ship().setXCell(xCell);
                        map.ship().setYCell(yCell);
                        mapView.toggleShipSelected();
                        mapView.invalidate();
                    }
                    else if (map.containsEntity(xCell, yCell)) {
                            attack(map.ship().getCellX(), map.ship().getCellY(), xCell, yCell);
                    }
                }
            }
            else if (map.containsEntity(xCell, yCell)) {
                if (map.entityAt(xCell, yCell).type() == entityType.SHIP) {
                    mapView.toggleShipSelected();
                    mapView.invalidate();
                }
            }
        }
    return true;
    }

    /**
     * Handles the case when a
     * @param xShip The x coordinate of the ship
     * @param yShip The y coordinate of the ship
     * @param xOther The x coordinate of the second entity
     * @param yOther The y coordinate of the second entity
     */
    public void attack(int xShip, int yShip, int xOther, int yOther) {
        if (map.entityAt(xOther, yOther) instanceof Enemy) {
            Control.ship = map.ship();
            Control.started = true;
            Control.enemy = (Enemy)map.entityAt(xOther, yOther);
            Intent myIntent = new Intent(WorldMapActivity.this, GalagaActivity.class);
            startActivityForResult(myIntent, 0);
        }

    }

    public void onResume() {
        super.onResume();
        if (Control.started)
        {
            if (Control.won) {
                map.removeEntity(Control.enemy.getCellX(), Control.enemy.getCellY());
                mapView.toggleShipSelected();
                Control.won = false;
                map.ship().setHealth(3);
                mapView.invalidate();
            } else {
                map.ship().setHealth(3);
                mapView.toggleShipSelected();
                mapView.invalidate();
            }
        }
    }












    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    //HELPER METHODS, GETTERS, SETTERS
    /**
     * @return The width of the screen in pixels
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return The height of the screen in pixels
     */
    public int getHeight() {
        return height;
    }

}
