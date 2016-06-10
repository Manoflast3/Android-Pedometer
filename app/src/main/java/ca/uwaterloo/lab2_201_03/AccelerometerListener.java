package ca.uwaterloo.lab2_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Tony Wang on 5/28/2016.
 */ //Rotation vector listener.

class AccelerometerListener implements SensorEventListener {
    //2 textviews; both current and max are passed into the listener at the same time.
    protected TextView[] output = new TextView[2];
    private float[] accelerometerCur = new float[3];
    private float[] accelerometerMax = {0,0,0};
    protected float x,y,z,s;

    BufferedWriter out;
    //put in 2 textviews, first is current and second is max.
    public AccelerometerListener(TextView[] outputView) {
        output = outputView;
    }

    public void onAccuracyChanged(Sensor s, int i) {
    }

    public void onSensorChanged(SensorEvent se) {
        if(se.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            //go through all 3 values
            for (int i = 0; i < 3 ; i++) {
                accelerometerCur[i] = se.values[i];
                //compare the max to the current value
                if(Math.abs(accelerometerMax[i])<=Math.abs(se.values[i])){
                    accelerometerMax[i]=se.values[i];
                }
            }
            x = accelerometerCur[0];
            y = accelerometerCur[1];
            z = accelerometerCur[2];
            String current = String.format("(%.2f, %.2f, %.2f)",x, y, z);
            x = accelerometerMax[0];
            y = accelerometerMax[1];
            z = accelerometerMax[2];
            String max = String.format("(%.2f, %.2f, %.2f)",x, y, z);
            //set the textviews.
            output[0].setText(current);
            output[1].setText(max);
            try {
                out = new BufferedWriter(Lab2_201_03.printWriter);
                out.write(current + "\n");
            } catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null)
                        out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

