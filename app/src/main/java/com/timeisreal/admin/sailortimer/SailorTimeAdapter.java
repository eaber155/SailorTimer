package com.timeisreal.admin.sailortimer;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SailorTimeAdapter extends ArrayAdapter<SailorTime> {
    //declare private variables
    private long startTime = 0L;

    //declare time variables
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    long timeSwapBuff = 0L;

    Context context;
    int layoutResourceId;
    ArrayList<SailorTime> data = new ArrayList<SailorTime>();

    public SailorTimeAdapter(Context context, int layoutResourceId, ArrayList<SailorTime> data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public class UpdateTimerThread implements Runnable {
        private TextView textView;
        String tag;
        private Handler customHandler;

        public UpdateTimerThread(TextView textView,String tag){
            this.textView = textView;
            this.tag = tag;
            customHandler = new Handler();
            //this.customHandler = customHandler;
        }

        /**private TextView GetTextView(){
            HashMap<String, TextView> textViewMap = new HashMap<String, TextView>();
            Iterator myTextViewIterator = textViewMap.keySet().iterator();
            while(myTextViewIterator.hasNext()){
                String key = (String)myTextViewIterator.next();
                TextView value = (TextView)textViewMap.get(key);

            }
            //textViewMap.put()
            return;
        }**/

        public void run(){
            SailorTime sailorTime;

            if (textView.getTag().toString().equals(tag)){
                SailorTimeAdapter sailorTimeAdapter = new SailorTimeAdapter(context, layoutResourceId, data);
                timeInMilliseconds = SystemClock.elapsedRealtime()-startTime;
                updatedTime = timeSwapBuff + timeInMilliseconds;

                int secs = (int)(updatedTime/1000);
                int mins = secs/60;
                secs = secs%60;
                int milliseconds = (int)(updatedTime%1000);
                String string = String.format("%02d", mins) + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
                //if(textView.getTag())

                /**final HashMap<String, TextView> textViewMap = new HashMap<String, TextView>();
                for (int i=0; i<data.size(); i++)
                {
                    textViewMap.put(tag, textView);
                }

                for (TextView textView:textViewMap.values()
                     ) {
                    if(textView.getTag().equals(data)) {
                        textViewMap.get(tag).setText(string);
                    }else{
                        textViewMap.get(tag).setText(string);}
                }**/
                //textView.setText(string);

                sailorTimeAdapter.notifyDataSetChanged();
                customHandler.postDelayed(this,0);
            }else {
                customHandler.removeCallbacks(this);
            }
        }
    }

    public View getView(final int position, View convertView, final ViewGroup parent){
        View row = convertView;
        //RecordHolder holder = null;

        if(row ==null){
            LayoutInflater inflater = ((AppCompatActivity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);


            //holder = new RecordHolder();
            //final Handler customHandler = new Handler();
            //SailorTime sailorTime = data.get(position);
            final TextView timeValue = (TextView)row.findViewById(R.id.timeValue);
            timeValue.setTag(position);

            final HashMap<String, TextView> textViewMap = new HashMap<String, TextView>();
            for(int i=0; i<data.size(); i++){
                textViewMap.put(timeValue.getTag().toString(), timeValue);
            }
            //timeValue.setId(R.id.timeValue +1000 + position);

            final Button startButton = (Button)row.findViewById(R.id.startButton);
            final Button pauseButton = (Button)row.findViewById(R.id.pauseButton);

            for (TextView textView:textViewMap.values()
                 ) {
                final UpdateTimerThread updateTimerThread = new UpdateTimerThread(textView, timeValue.getTag().toString());
                startButton.setTag(position);
                //if(timeValue.getTag().toString().equals(startButton.getTag().toString())){}

                startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //timeValue = (TextView)row.findViewById(R)
                        //((GridView) parent).performItemClick(v, position, 0);
                        startTime = SystemClock.elapsedRealtime();
                        updateTimerThread.customHandler.postDelayed(updateTimerThread, 0);
                        //Toast.makeText(getContext(), startButton.getTag().toString(), Toast.LENGTH_LONG).show();
                        startButton.setVisibility(View.GONE);
                        pauseButton.setVisibility(View.VISIBLE);
                        /**Iterator myTextViewIterator = textViewMap.keySet().iterator();
                             while(myTextViewIterator.hasNext()) {
                             String key = (String) myTextViewIterator.next();
                             TextView value = (TextView) textViewMap.get(key);

                         }**/
                        Toast.makeText(getContext(), "key"+textViewMap.get(timeValue.getTag().toString()), Toast.LENGTH_LONG).show();
                        }
                });

                pauseButton.setTag(position);
                //if(timeValue.getTag().toString().equals(pauseButton.getTag().toString())){

                //}
                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //((GridView)parent).performItemClick(v,position,0);
                        timeSwapBuff += timeInMilliseconds;
                        updateTimerThread.customHandler.removeCallbacks(updateTimerThread);
                        startButton.setVisibility(View.VISIBLE);
                        pauseButton.setVisibility(View.GONE);
                        //Toast.makeText(getContext(), pauseButton.getTag().toString(), Toast.LENGTH_LONG).show();
                    }
                });

                Button resetButton= (Button)row.findViewById(R.id.resetButton);
                resetButton.setTag(position);
                //if (timeValue.getTag().toString().equals(resetButton.getTag().toString()){
                resetButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), pauseButton.getTag().toString(), Toast.LENGTH_LONG).show();
                        if (startButton.getVisibility() == View.GONE) {
                            startTime = SystemClock.elapsedRealtime();
                            timeSwapBuff = 0L;
                        } else if (pauseButton.getVisibility() == View.GONE) {
                            updatedTime = 0L;
                            timeSwapBuff = 0L;
                            int secs = (int) (updatedTime / 1000);
                            int mins = secs / 60;
                            secs = secs % 60;
                            int milliseconds = (int) (updatedTime % 1000);
                            String string = "" + String.format("%02d", mins) + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
                            timeValue.setText(string);
                            //customHandler.removeCallbacks(updateTimerThread,0);
                        }
                        /**((GridView)parent).performItemClick(v, position, 0);
                         updatedTime = 0L;
                         timeSwapBuff = 0L;
                         int secs = (int)(updatedTime/1000);
                         int mins = secs/60;
                         secs = secs%60;
                         int milliseconds = (int)(updatedTime%1000);
                         String string = "" + String.format("%02d", mins)+ ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
                         //timeValue.setText(string);
                         //updateTimerThread.customHandler.removeCallbacks(updateTimerThread);**/
                    }
                });
            }

            //row.setTag(holder);
        //}else {
            //row.setTag(null);
            /**LayoutInflater inflater = ((AppCompatActivity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();

            holder.timeValue = (TextView)row.findViewById(R.id.timeValue);

            holder.start = (Button)row.findViewById(R.id.startButton);
            holder.start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GridView)parent).performItemClick(v, position, 0);
                }
            });

            holder.reset = (Button)row.findViewById(R.id.resetButton);
            holder.reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GridView)parent).performItemClick(v, position, 0);
                }
            });

            holder.pause = (Button)row.findViewById(R.id.pauseButton);
            holder.pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GridView)parent).performItemClick(v,position,0);
                }
            });

            row.setTag(holder);**/
            row.getTag(); //holder = (RecordHolder)
        }

        /**SailorTime sailorTime = data.get(position);
        timeValue = sailorTime.getTime();
        start = sailorTime.getStartButton();
        reset = sailorTime.getResetButton();
        pause = sailorTime.getPauseButton();**/

    return row;
    }

    /***static class RecordHolder{
        TextView timeValue;
        Button start, reset, pause;
    }**/
}
