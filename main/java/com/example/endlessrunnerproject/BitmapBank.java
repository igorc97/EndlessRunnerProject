package com.example.endlessrunnerproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background;
    Bitmap[] bird;
    ////////////
    Bitmap topPipe;
    Bitmap bottomPipe;
    Bitmap gameOver;
    Bitmap thornTop, thornBottom;
    Bitmap bottleTop, bottleBottom;

    Bitmap background2, background3, background4;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.bgfinal);
        background = scaleImage(background);
        bird = new Bitmap[5];
        bird[0] = BitmapFactory.decodeResource(res, R.drawable.hero1);
        bird[1] = BitmapFactory.decodeResource(res, R.drawable.hero2);
        bird[2] = BitmapFactory.decodeResource(res, R.drawable.hero3);
        bird[3] = BitmapFactory.decodeResource(res, R.drawable.hero4);
        bird[4] = BitmapFactory.decodeResource(res, R.drawable.hero5);

        //new level backgrounds

        background2 = BitmapFactory.decodeResource(res, R.drawable.backgroundforest);
        background2 = scaleImage(background2);

        background3 = BitmapFactory.decodeResource(res, R.drawable.backgroundindustrial);
        background3 = scaleImage(background3);

        background4 = BitmapFactory.decodeResource(res, R.drawable.backgroundcyberpunk);
        background4 = scaleImage(background4);

        //tubes
        topPipe = BitmapFactory.decodeResource(res, R.drawable.toptube);
        bottomPipe = BitmapFactory.decodeResource(res, R.drawable.bottomtube);

        //thorns
        thornTop = BitmapFactory.decodeResource(res, R.drawable.thorntop);
        thornBottom = BitmapFactory.decodeResource(res, R.drawable.thornbottom);

        //bottles

        bottleTop = BitmapFactory.decodeResource(res, R.drawable.bottletop);
        bottleBottom = BitmapFactory.decodeResource(res, R.drawable.bottlebottom);

        //gameover
        gameOver = BitmapFactory.decodeResource(res, R.drawable.gameover);
        gameOver = scaleImage(gameOver);

    }

    public Bitmap getBird(int frame){
        return bird[frame];
    }

    public int getBirdWidth(){
        return bird[0].getWidth();
    }

    public int getBirdHeight(){
        return bird[0].getHeight();
    }


    ///////////////////
    public Bitmap getTopPipe() { return topPipe; }

    public Bitmap getBottomPipe() { return bottomPipe; }

    public int getPipeWidth() { return topPipe.getWidth(); }

    public int getPipeHeight() { return topPipe.getHeight(); }

    //////////////////////


    ///throns

    public Bitmap getThornTop(){
        return thornTop;
    }

    public Bitmap getThornBottom(){
        return thornBottom;
    }

    //bottles

    public Bitmap getBottleTop(){
        return bottleTop;
    }

    public Bitmap getBottleBottom(){
        return bottleBottom;
    }

     //Return background bitmap
    public Bitmap getBackground(){
        return background;
    }
    // Return background width
    public int getBackgroundWidth(){
        return background.getWidth();
    }

    //Return background height
    public int getBackgroundHeight(){
        return background.getHeight();
    }


    //Return background bitmap
    public Bitmap getBackground2(){
        return background2;
    }
    // Return background width
    public int getBackgroundWidth2(){
        return background2.getWidth();
    }

    //Return background height
    public int getBackgroundHeight2(){
        return background2.getHeight();
    }



    //Return background bitmap
    public Bitmap getBackground3(){
        return background3;
    }
    // Return background width
    public int getBackgroundWidth3(){
        return background3.getWidth();
    }

    //Return background height
    public int getBackgroundHeight3(){
        return background3.getHeight();
    }



    //Return background bitmap
    public Bitmap getBackground4(){
        return background4;
    }
    // Return background width
    public int getBackgroundWidth4(){
        return background4.getWidth();
    }

    //Return background height
    public int getBackgroundHeight4(){
        return background4.getHeight();
    }




    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        int backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
