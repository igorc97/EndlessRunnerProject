package com.example.endlessrunnerproject;

import java.util.Random;

public class Pipe {
    private int pipeX, topPipeOffsetY;
    private Random random;
    private int pipeObstacle;

    public Pipe(int pipeX, int topPipeOffsetY){
      this.pipeX = pipeX;
      this.topPipeOffsetY = topPipeOffsetY;
      random = new Random();
    }

    public void setPipeObstacle(){                       /// 3 types of obstacles
        pipeObstacle = random.nextInt(3);
    }

    public int getPipeObstacle(){
        return pipeObstacle;
    }

    public int getTopPipeOffsetY(){
        return topPipeOffsetY;
    }

    public int getPipeX(){
        return pipeX;
    }

    public int getTopPipeY(){
        return topPipeOffsetY - AppConstants.getBitmapBank().getPipeHeight();
    }

    public int getBottomPipeY(){
        return topPipeOffsetY + AppConstants.gapBetweenTopAndBottomPipes;
    }

    public void setPipeX(int pipeX){
        this.pipeX = pipeX;
    }

    public void setTopPipeOffsetY(int topPipeOffsetY){
        this.topPipeOffsetY = topPipeOffsetY;
    }

    }

