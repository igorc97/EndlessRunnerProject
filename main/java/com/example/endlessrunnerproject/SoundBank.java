package com.example.endlessrunnerproject;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundBank {

    Context context;
    MediaPlayer jump, point, hit, step;

    public SoundBank(Context context){
        this.context = context;

        jump = MediaPlayer.create(context, R.raw.sound1);
        point = MediaPlayer.create(context, R.raw.coin1);
        hit = MediaPlayer.create(context, R.raw.laserhit);
        step = MediaPlayer.create(context, R.raw.steps);
    }

    public void playJump(){
        if (jump != null){
            jump.start();
        }
    }

    public void playPoint(){
        if (point != null){
            point.start();
        }
    }

    public void playHit(){
        if (hit != null){
            hit.start();
        }
    }

    public void playStep(){
        if (step != null){
            step.start();
        }
    }
}
