package com.timeisreal.admin.sailortimer;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Admin on 30-Jan-17.
 */
public class SailorTime {
    /**String time;

    long startTime = 0L;
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    long timeSwapBuff = 0L;

    private Handler customHandler = new Handler();

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.elapsedRealtime() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int)(updatedTime/1000);
            int mins = secs/60;
            secs = secs%60;
            int milliseconds = (int)(updatedTime%1000);
            String time = "" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
            customHandler.postDelayed(this, 0);
        }
    };

    public String StartTimer(){
        startTime = SystemClock.elapsedRealtime();
        time =
        return time;
    }**/

    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    private TextView timeValue;
    private String text;

    public SailorTime(TextView timeValue, Button startButton, Button resetButton, Button pauseButton){
        super();
        this.timeValue = timeValue;
        this.startButton = startButton;
        this.resetButton = resetButton;
        this.pauseButton = pauseButton;
    }

    public TextView getTime(){
        setTimer(timeValue);
        return timeValue;
    }

    public void setTimer(TextView timeValue){
        this.timeValue = timeValue;
    }

    public String getText(){
        return text;
    }

    public void setStartButton(Button startButton){
        this.startButton = startButton;
    }

    public Button getStartButton(){
        setStartButton(startButton);
        return startButton;
    }

    public void setResetButton(Button resetButton){
        this.resetButton = resetButton;
    }

    public Button getResetButton(){
        setResetButton(resetButton);
        return resetButton;
    }

    public void setPauseButton(Button pauseButton){
        this.pauseButton = pauseButton;
    }

    public Button getPauseButton(){
        setPauseButton(pauseButton);
        return pauseButton;
    }
}
