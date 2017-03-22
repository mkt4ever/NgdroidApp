package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {

    private Bitmap spriteSheet, tileset;
    private Rect karesrc, karedst, tilesrc , tiledst;
    private int kareno;
    private int tilew, tileh, tilesw, tilesh;
    private int spritew, spriteh, spritesw, spritesh;
    private int spritex, spritey;
    private int spritedx;
    private int animationFirstKareNo[] , animationLastKareNo[];
    private int animationAdedi = 4;
    private int animationNo;


    public GameCanvas(NgApp ngApp) {

        super(ngApp);
    }

    public void setup() {

        //Log.i(TAG, "setup");

        // initializing our objects:
        spriteSheet = Utils.loadImage(root,"cowboy.png"); // loading the image to the app as a Bitmap
        karesrc = new Rect();
        karedst = new Rect();

        kareno = 0;

        tileset = Utils.loadImage(root,"tilea2.png");
        tilesrc = new Rect();
        tiledst = new Rect();

        tilesw = 64;
        tilesh = 64;
        tilew =  128;
        tileh =  128;

        spritesw = 128;
        spritesh = 128;
        spritew =  256;
        spriteh =  256;

        spritex = 0;
        spritey = 0;

        spritedx = spritew /8;

        animationFirstKareNo = new int[animationAdedi];
        animationFirstKareNo[0] = 0;
        animationFirstKareNo[1] = 1;
        animationFirstKareNo[2] = 9;
        animationFirstKareNo[3] = 12;
        animationLastKareNo =  new int[animationAdedi];
        animationLastKareNo[0] = 0;
        animationLastKareNo[1] = 8;
        animationLastKareNo[2] = 11;
        animationLastKareNo[3] = 14;

        animationNo = 1;
    }



    public void update() {

        //Log.i(TAG, "update");

        kareno ++;
        if (kareno > animationLastKareNo[animationNo]) kareno = animationFirstKareNo[animationNo];

        spritex += spritedx;
        if (spritex > getWidth() - spritew) {
            spritex = getWidth() - spritew;
            animationNo = 0;
        }
    }

    public void draw(Canvas canvas) {
        //canvas.drawBitmap(spriteSheet,0,0,null);    // test drawing the loaded Bitmap image to the screen, zeros are float left and top

        for (int i = 0; i < getWidth(); i+= tilew)
        {
            for (int j = 0; j < getHeight(); j+= tileh)
            {
                tilesrc.set(0, 0, tilesw, tilesh);
                tiledst.set(i, j, i+tilew, j+tileh);
                canvas.drawBitmap(tileset,tilesrc,tiledst,null);    // test drawing the loaded Bitmap image to the screen, zeros are float left and top
            }

        }
        karesrc.set(kareno * spritesw,0,(kareno + 1) * spritesw, spritesh);
        karedst.set(spritex,spritey,spritew + spritex,spriteh + spritey);
        canvas.drawBitmap(spriteSheet,karesrc,karedst,null);    // modified to draw the first carechter which is defined by the karesrc and karedst
        //Log.i(TAG, "draw");
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y) {
    }

    public void touchMove(int x, int y) {
    }

    public void touchUp(int x, int y) {
    }


    public void pause() {

    }


    public void resume() {

    }


    public void reloadTextures() {

    }


    public void showNotify() {
    }

    public void hideNotify() {
    }

}
