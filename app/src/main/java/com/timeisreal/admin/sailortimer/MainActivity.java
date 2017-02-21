package com.timeisreal.admin.sailortimer;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private TextView timeValue;

    //initialize gridview, adapter and arraylist
    GridView gridView;
    ArrayList<SailorTime> gridArray = new ArrayList<SailorTime>();
    SailorTimeAdapter sailorTimeAdapter;


    /**declare private variables
    private long startTime = 0L;
    private Handler customHandler = new Handler();

    //declare time variables
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    long timeSwapBuff = 0L;


    //declare runnable
    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.elapsedRealtime()-startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int)(updatedTime/1000);
            int mins = secs/60;
            secs = secs%60;
            int milliseconds = (int)(updatedTime%1000);
            String string = "" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
            timeValue.setText(string);
            customHandler.postDelayed(this,0);
        }
    };**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.timeValue);
        Button startButton = (Button)findViewById(R.id.startButton);
        Button resetButton = (Button)findViewById(R.id.resetButton);
        Button pauseButton = (Button)findViewById(R.id.pauseButton);

        for (int number = 0; number<=3; number++){
            gridArray.add(number, new SailorTime(textView, startButton, resetButton, pauseButton));
        }

        gridView = (GridView)findViewById(R.id.gridView);
        sailorTimeAdapter = new SailorTimeAdapter(this, R.layout.activity_sailor_time_adapter, gridArray);
        gridView.setAdapter(sailorTimeAdapter);

        /***gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long viewId = view.getId();

                if(viewId == R.id.startButton){
                    timeValue = (TextView)gridView.findViewById(R.id.timeValue);
                    startTime = SystemClock.elapsedRealtime();
                    customHandler.postDelayed(updateTimerThread, 0);
                    Toast.makeText(MainActivity.this, "Button Clicked: "+position, Toast.LENGTH_LONG).show();
                }else if (viewId == R.id.resetButton){
                    TextView textView = (TextView)gridView.findViewById(R.id.timeValue);
                    updatedTime = 0L;
                    timeSwapBuff = 0L;
                    int secs = (int)(updatedTime/1000);
                    int mins = secs/60;
                    secs = secs%60;
                    int milliseconds = (int)(updatedTime%1000);
                    String string = "" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
                    textView.setText(string);
                    customHandler.removeCallbacks(updateTimerThread);
                    Toast.makeText(MainActivity.this, "Button Clicked: "+position, Toast.LENGTH_LONG).show();
                }else if (viewId == R.id.pauseButton){
                    timeSwapBuff += timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                    Toast.makeText(MainActivity.this, "Button Clicked: "+position, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Sailor Timer Clicked: "+id, Toast.LENGTH_SHORT).show();
                }
            }
        });**/
    }
}
