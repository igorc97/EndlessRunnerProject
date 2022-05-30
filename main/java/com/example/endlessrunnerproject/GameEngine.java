package com.example.endlessrunnerproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowMetrics;

import androidx.core.content.res.ResourcesCompat;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    static int gameState;
    ArrayList<Pipe> pipes;
    Random random;
    int score;  //
    int scoringTube; //tracking scoring
    Paint scorePaint;
    int level = 1;
    boolean pipeVelocityFlag = false;


    private String getString(int app_scoreGame) {
        return String.valueOf(R.string.app_scoreGame);
    }


    public GameEngine(Context context) {

        backgroundImage = new BackgroundImage();
        bird = new Bird();

        // 0 - not started
        // 1 - playing
        // 2 - game over
        gameState = 0;
        pipes = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < AppConstants.numberOfPipes; i++) {
            int pipeX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanceBetweenPipes;
            //get toppipeoffsety
            int topPipeOffsetY = AppConstants.minPipeOffsetY + random.nextInt(AppConstants.maxPipeOffsetY - AppConstants.minPipeOffsetY + 1);

            //pipe object create
            Pipe pipe = new Pipe(pipeX, topPipeOffsetY);
            pipes.add(pipe);
        }

        //File font = new File(Environment.getExternalStorageDirectory(), "first_font.ttf");

        //Typeface two = ResourcesCompat.getFont(context, R.font.first_font);
        //final Typeface one = Typeface.createFromFile("fonts/first_font.ttf");


        score = 0;
        scoringTube = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.CENTER);

        Typeface two = Typeface.createFromAsset(context.getAssets(), "fonts/first_font.ttf");
        scorePaint.setTypeface(two);


    }


    public void updateAndDrawBackgroundImage(Canvas canvas) {

        if (score > 25){
            level = 2;
        }else if (score > 50){
            level = 3;
        }else if (score > 75){
            level = 4;
        }

        if (level == 1){
            if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
                backgroundImage.setX(0);
            }
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
            }
        }else if (level == 2){
            if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
                backgroundImage.setX(0);
            }
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground2(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth2() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground2(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth2(), backgroundImage.getY(), null);
            }
        }else if (level == 3){
            if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
                backgroundImage.setX(0);
            }
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground3(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth3() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground3(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth3(), backgroundImage.getY(), null);
            }
        }else if (level == 4){
            if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
                backgroundImage.setX(0);
            }
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground4(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth4() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground4(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth4(), backgroundImage.getY(), null);
            }
        }
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());




    }

    public void updateAndDrawBird(Canvas canvas) {

        if (gameState == 1) {

            if (bird.getY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight()) || bird.getVelocity() < 0) {
                bird.setVelocity(bird.getVelocity() + AppConstants.gravity);
                bird.setY(bird.getY() + bird.getVelocity());
            }

        }


        int currentFrame = bird.getCurrentFrame();
        Bitmap.createScaledBitmap(AppConstants.getBitmapBank().getBird(currentFrame), 700, 700, false); // scalling to lower resolution
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getX(), bird.getY(), null);
        currentFrame++;

        if (currentFrame > bird.maxFrame) {
            currentFrame = 0;
        }

        bird.setCurrentFrame(currentFrame);

    }

    public void updateAndDrawPipes(Canvas canvas) {


        if (level == 2 && pipeVelocityFlag == false){
            AppConstants.pipeVelocity = 25;
            pipeVelocityFlag = true;
        }else if (level == 3 && pipeVelocityFlag == true){
            AppConstants.pipeVelocity = 18;
            pipeVelocityFlag = false;
        }else if (level == 4 && pipeVelocityFlag == false){
            AppConstants.pipeVelocity = 30;
            pipeVelocityFlag = true;
        }

        if (gameState == 1) {
            //collision detection
            if ((pipes.get(scoringTube).getPipeX() < bird.getX() + AppConstants.getBitmapBank().getBirdWidth()) &&
                    (pipes.get(scoringTube).getTopPipeOffsetY() > bird.getY()
                            || pipes.get(scoringTube).getBottomPipeY() < (bird.getY() + AppConstants.getBitmapBank().getBirdHeight()))) {
                gameState = 2;
                Log.d("Game", "Over");
                AppConstants.getSoundBank().playHit();
                Context context = AppConstants.gameActivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();

            } else if (pipes.get(scoringTube).getPipeX() < bird.getX() - AppConstants.getBitmapBank().getPipeWidth()) {
                score++;
                scoringTube++;
                if (scoringTube > AppConstants.numberOfPipes - 1) {
                    scoringTube = 0;
                }
                AppConstants.getSoundBank().playPoint();
            }
            //random pipe spawner
            for (int i = 0; i < AppConstants.numberOfPipes; i++) {
                if (pipes.get(i).getPipeX() < -AppConstants.getBitmapBank().getPipeWidth()) {
                    pipes.get(i).setPipeX(pipes.get(i).getPipeX() + AppConstants.numberOfPipes * AppConstants.distanceBetweenPipes);
                    int topPipeOffsetY = AppConstants.minPipeOffsetY + random.nextInt(AppConstants.maxPipeOffsetY - AppConstants.minPipeOffsetY + 1);
                    pipes.get(i).setTopPipeOffsetY(topPipeOffsetY);
                    pipes.get(i).setPipeObstacle();
                }


                pipes.get(i).setPipeX(pipes.get(i).getPipeX() - AppConstants.pipeVelocity);
                // 3 types of obstacles, changing by random
                if (pipes.get(i).getPipeObstacle() == 0) {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTopPipe(), pipes.get(i).getPipeX(), pipes.get(i).getTopPipeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getBottomPipe(), pipes.get(i).getPipeX(), pipes.get(i).getBottomPipeY(), null);
                } else if (pipes.get(i).getPipeObstacle() == 1) {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getBottleTop(), pipes.get(i).getPipeX(), pipes.get(i).getTopPipeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getBottleBottom(), pipes.get(i).getPipeX(), pipes.get(i).getBottomPipeY(), null);
                } else {
                   canvas.drawBitmap(AppConstants.getBitmapBank().getThornTop(), pipes.get(i).getPipeX(), pipes.get(i).getTopPipeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getThornBottom(), pipes.get(i).getPipeX(), pipes.get(i).getBottomPipeY(), null);
                }

            }

            //Score text
            canvas.drawText("Score: "+ score, 550, 110, scorePaint);
        }

    }
}

