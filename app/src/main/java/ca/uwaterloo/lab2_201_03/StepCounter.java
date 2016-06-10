package ca.uwaterloo.lab2_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import ca.uwaterloo.sensortoy.LineGraphView;

/**
 * Created by Tony Wang on 5/28/2016.
 */ //Rotation vector listener.

class StepCounter implements SensorEventListener {
    //2 textviews; both current and max are passed into the listener at the same time.
    protected int totalStepCount;
    private TextView stepView;

    //put in 2 textviews, first is current and second is max.
    public StepCounter(TextView stepCount, Button button) {
        stepView = stepCount;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void onAccuracyChanged(Sensor s, int i) {
    }

    public void reset() {
        totalStepCount = 0;
    }

    private boolean state1 = false, state2 = false, state3 = false;
    private long delay = 40;
    private boolean period = false;


    public void enableCounter(){
        period = true;
    }


    public void onSensorChanged(SensorEvent se) {
        if(!state1){
            if(se.values[1]>0.5&&se.values[1]<1.5){
                state1 = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        enableCounter();
                    }
                }, delay);


            }
        }else if(period){
            if(state1&&!state2){
                if(se.values[1]<0&&se.values[1]>-1.5){
                    state2 = true;
                }else if(Math.abs(se.values[1])>1.5){
                    state1 = false;
                    period = false;
                }
            } else if(state1&&state2&&!state3){
                if(se.values[1]<-0.5&&se.values[1]>-1.5){
                    state3 = true;
                    totalStepCount++;
                    state1 = false;
                    state2 = false;
                    state3 = false;
                    period = false;
                }else if(Math.abs(se.values[1])>1.5){
                    state1 = false;
                    state2 = false;
                    period = false;
                }
            }
        }
        stepView.setText(Integer.toString(totalStepCount));
    }








}
//
//    if(!state1){
//        if(se.values[1]>Integer.parseInt(states[0].getText().toString())){
//            state1 = true;
//        }
//    }else if(state1&&!state2){
//        if(se.values[1]<Integer.parseInt(states[1].getText().toString())){
//            state2 = true;
//            totalStepCount ++;
//            state1 = false;
//            state2 = false;
//        }
//    }

