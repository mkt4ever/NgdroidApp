package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.core.AppManager;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {

    private Bitmap spritesheet, tileset;
    private Rect karesrc, karedst, tilesrc, tiledst;
    private int kareno;
    private int tilew, tileh, tilesw, tilesh;
    private int spritew, spriteh, spritesw, spritesh;
    private int spritex, spritey, spritedx,spritedy;
    private int spriteix, spriteiy;
    private int[] animasyonIlkKareNo;
    private int[] animasyonSonKareNo;
    private int animasyonNo;
    private int animasyonAdedi = 4;
    private int touchDownX, touchDownY;
    private int yon;
    private boolean atesEdildi;

    private Bitmap mermi;
    private int mermiw, mermih, mermisx, mermisy, mermix,mermiy;
    private Rect mermisrc, mermidst;
    private int mermidx, mermidy, mermiix, mermiiy;

    private Bitmap hedefObje;
    private Rect hedefsrc, hedefdst;
    private int hedefw, hedefh,hedefsw, hedefsh, hedefx, hedefy;

    private boolean hedefvar;

    private Bitmap patlama;
    private Rect patlamasrc, patlamadst;
    private int patlamaw, patlamah,patlamasw, patlamash, patlamax, patlamay;
    private int patlamaKareNo;

    private boolean patlamaoluyor;

    private int oyunekrani;
    private static int EKRAN_NORMALOYUN = 0, EKRAN_MENU = 1;

    private Bitmap menutuslari;
    private int tusadedi = 2;
    private Rect tussrc[] , tusdst[];
    private int tusx[] , tusy[] , tusw, tush, tussx[] , tussy[];
    private int tusaBasildi[];
    private static int MENUTUSU_REPLAY = 0, MENUTUSU_EXIT = 1;


    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        //Log.i(TAG, "setup");
        spritesheet = Utils.loadImage(root,"cowboy.png");
        karesrc = new Rect();
        karedst = new Rect();
        kareno = 0;
        tileset = Utils.loadImage(root, "tilea2.png");
        tilesrc = new Rect();
        tiledst = new Rect();
        tilesw =64;
        tilesh = 64;
        tilew = 128;
        tileh = 128;
        spritew = 128;
        spritesw = 128;
        spriteh = 128;
        spritesh = 128;
        spritex = 0;
        spritey = 0;
        spritedx = spritew / 8;
        spritedy = spriteh / 8;
        spriteix = 1;
        spriteiy = 0;
        animasyonIlkKareNo = new int[animasyonAdedi];
        animasyonIlkKareNo[0]  = 0;
        animasyonIlkKareNo[1]  = 1;
        animasyonIlkKareNo[2]  = 9;
        animasyonIlkKareNo[3]  = 12;
        animasyonSonKareNo = new int[animasyonAdedi];
        animasyonSonKareNo[0] = 0;
        animasyonSonKareNo[1] = 8;
        animasyonSonKareNo[2] = 11;
        animasyonSonKareNo[3] = 13;
        animasyonNo = 1;
        yon = 3;
        atesEdildi = false;

        mermi = Utils.loadImage(root,"ssb.png");
        mermiw = 102;
        mermih = 102;
        mermisx = 330;
        mermisy = 45;
        mermisrc = new Rect();
        mermidst = new Rect();

        mermidx = spritedx * 2;
        mermidy = spritedy * 2;
        mermiix = 0;
        mermiiy = 0;

        hedefObje = Utils.loadImage(root,"walker3.png");
        hedefsrc = new Rect();
        hedefdst = new Rect();
        hedefw = 128;
        hedefh = 128;
        hedefsw = 128;
        hedefsh = 128;
        hedefx = (getWidth() - hedefw)/2;
        hedefy = (getHeight() - hedefh)/2;

        hedefvar = true;


        patlama = Utils.loadImage(root,"explosion2.png");
        patlamasrc = new Rect();
        patlamadst = new Rect();
        patlamaw = 256;
        patlamah = 256;
        patlamasw = 128;
        patlamash = 128;
        patlamax = 0;
        patlamay = 0;

        patlamaoluyor = false;
        patlamaKareNo = -1;

        oyunekrani = EKRAN_NORMALOYUN;

        menutuslari = Utils.loadImage(root,"gameend_buttons2.png");
        tussrc = new Rect[tusadedi];
        tusdst = new Rect[tusadedi];
        tusx = new int[tusadedi];
        tusy = new int[tusadedi];
        tussx = new int[tusadedi];
        tussy = new int[tusadedi];
        tusaBasildi = new int[tusadedi];
        tusw = 300;
        tush = 300;
        int tusaraligi = (getWidth() - (2*tusw))/3;

        for (int i=0; i<tusadedi;i++){
            tussrc[i] = new Rect();
            tusdst[i] = new Rect();
            tussx[i] = 0;
            tussy[i] = i * tush;
            tusy[i] = (getHeight() - tush)/2;
            tusx[i] = tusaraligi + (i*(tusw + tusaraligi));
            tusaBasildi[i]=0;

        }
    }



    public void update() {
        //Log.i(TAG, "update");

        spritex += spritedx * spriteix;
        spritey += spritedy * spriteiy;

        if (spritex > getWidth() - spritew) {
            spritex = getWidth() - spritew;
            animasyonNo = 0;
        } else if (spritex < 0) {
            spritex = 0;
            animasyonNo = 0;
        } else if (spritey > getHeight() - spriteh) {
            spritey = getHeight() - spriteh;
            animasyonNo = 0;
        } else if (spritey < 0) {
            spritey = 0;
            animasyonNo = 0;
        }



        kareno++;
        if(kareno > animasyonSonKareNo[animasyonNo])
            kareno = animasyonIlkKareNo[animasyonNo];

        if (patlamaoluyor){
            patlamaKareNo ++;
            if (patlamaKareNo >= 16){
                patlamaoluyor = false;
                oyunekrani = EKRAN_MENU;
            }
        }

        if (atesEdildi && hedefvar) {
            patlamaoluyor = cakismaKontrolEt();

            if (patlamaoluyor){

                atesEdildi = false;
                patlamax = hedefx + ((hedefw - patlamaw)/2);
                patlamay = hedefy + ((hedefh - patlamah)/2);
                patlamaKareNo = 0;
                hedefvar = false;


            }
        }




    }



    public void draw(Canvas canvas) {
        //Log.i(TAG, "draw");
        for(int i = 0; i < getWidth(); i+= tilew){
            for(int j = 0; j < getHeight(); j+= tileh){
                tilesrc.set(0, 0, tilesw, tilesh);
                tiledst.set(i, j, i + tilew, j + tileh);
                canvas.drawBitmap(tileset, tilesrc, tiledst, null);
            }
        }
        karesrc.set(kareno * spritesw, yon * spritesh, (kareno + 1) * spritesw, (yon + 1) *  spritesh);
        karedst.set(spritex, spritey, spritex + spritew, spritey + spriteh);
        canvas.drawBitmap(spritesheet, karesrc, karedst, null);

        if (atesEdildi){
            mermisrc.set(mermisx, mermisy , mermisx + mermiw , mermisy + mermih);

            mermix += mermidx * mermiix;
            mermiy += mermidy * mermiiy;

            if (mermix > getWidth() || mermix < -mermiw || mermiy > getHeight() || mermiy < -mermih )
            {
                atesEdildi = false;
                mermiix = 0;
                mermiiy = 0;
            }

            mermidst.set(mermix, mermiy , mermix + mermiw , mermiy + mermih);
            canvas.drawBitmap(mermi, mermisrc, mermidst, null);
        }

        if (hedefvar){
            hedefsrc.set(0, 0 , hedefsw, hedefsh);
            hedefdst.set(hedefx, hedefy , hedefx + hedefw, hedefy + hedefh);
            canvas.drawBitmap(hedefObje, hedefsrc, hedefdst, null);
        }

        if (patlamaoluyor){

            int patlamasx = (patlamaKareNo % (4)) * patlamash;
            int patlamasy = (patlamaKareNo / 4) * patlamash;
            patlamasrc.set(patlamasx ,patlamasy , patlamasx + patlamasw , patlamasy +  patlamash);
            patlamadst.set(patlamax , patlamay , patlamax + patlamaw , patlamay + patlamah);
            canvas.drawBitmap(patlama, patlamasrc, patlamadst, null);
        }
        if (oyunekrani == EKRAN_MENU){
            for (int i = 0; i<tusadedi;i++){
                tussrc[i].set(tussx[i],tussy[i], tussx[i] + tusw, tussy[i] + tush);
                tusdst[i].set(tusx[i],tusy[i], tusx[i] + tusw, tusy[i] + tush);
                canvas.drawBitmap(menutuslari, tussrc[i], tusdst[i], null);
            }
        }
    }

    private boolean cakismaKontrolEt(){
        boolean cakismavar = false;
        if (mermix >= hedefx - mermiw && mermiy >= hedefy - mermih && mermix < hedefx + hedefw && mermiy < hedefy + hedefh){
            cakismavar = true;
        }
        return cakismavar;
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

        // Log.e(TAG,"touchDown x:"+x+" , y: "+y);

        touchDownX = x;
        touchDownY = y;
    }

    public void touchMove(int x, int y) {
        //Log.e(TAG,"touchMove x:"+x+" , y: "+y);
    }

    public void touchUp(int x, int y) {
        //   Log.e(TAG,"touchUp x:"+x+" , y: "+y);
        if (oyunekrani == EKRAN_NORMALOYUN)
        {



            animasyonNo = 1;
            int xfarki = x - touchDownX;
            int yfarki = y - touchDownY;

            boolean doubleClick = false;

            if (Math.abs(xfarki) < 20 && Math.abs(yfarki) < 20)
                doubleClick = true;

            if (doubleClick)
            {
                if (atesEdildi) return;

                mermix = spritex + ((spritew - mermiw) / 2);
                mermiy = spritey + ((spriteh - mermih) / 2);

                mermiix = spriteix;
                mermiiy = spriteiy;

                atesEdildi = true;

            } else {
                if (Math.abs(xfarki) >= Math.abs(yfarki))
                {
                    spriteix = 1;
                    yon = 3;
                    if (xfarki < 0 ) {
                        spriteix = -1;
                        yon = 7;
                    }
                    //spriteix = xfarki / Math.abs(xfarki);
                    spriteiy = 0;

                } else {
                    spriteiy = 1;
                    yon = 9;
                    if (yfarki < 0 ) {
                        spriteiy = -1;
                        yon = 5;
                    }
                    // spriteiy = yfarki / Math.abs(yfarki);
                    spriteix = 0;

                }
            }

        } else if (oyunekrani == EKRAN_MENU){
            int basilantus = -1;

            for (int i =0;i<tusadedi;i++){
                if (x>=tusx[i] && x< tusx[i]+tusw && y>=tusy[i] && y< tusy[i]+tush){
                    basilantus = i;
                    break;
                }
            }
            if (basilantus == MENUTUSU_REPLAY){

                GameCanvas gc = new GameCanvas(root);
                root.canvasManager.setCurrentCanvas(gc);

            }else if (basilantus == MENUTUSU_EXIT){
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
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