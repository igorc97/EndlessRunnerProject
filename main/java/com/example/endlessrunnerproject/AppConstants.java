package com.example.endlessrunnerproject;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {
    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int gapBetweenTopAndBottomPipes;
    static int numberOfPipes;
    static int pipeVelocity;
    static int minPipeOffsetY;
    static int maxPipeOffsetY;
    static int distanceBetweenPipes;
    static SoundBank soundBank;
    static Context gameActivityContext;

    public static void initialization(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine(context);
        soundBank= new SoundBank(context);

    }

    public static SoundBank getSoundBank(){
        return soundBank;
    }

    public static void setGameConstants(){
        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = -40;
        gapBetweenTopAndBottomPipes = 600;
        AppConstants.numberOfPipes = 2;
        AppConstants.pipeVelocity = 12;
        AppConstants.minPipeOffsetY = (int) (AppConstants.gapBetweenTopAndBottomPipes / 2.0);  // minimum y of the bottom edge of the top pipe
        AppConstants.maxPipeOffsetY = (int) (AppConstants.SCREEN_HEIGHT - AppConstants.minPipeOffsetY - AppConstants.gapBetweenTopAndBottomPipes); //maximum y of the bottom edge of the top pipe
        AppConstants.distanceBetweenPipes = AppConstants.SCREEN_WIDTH * 3 / 4;
    }

    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }

}
