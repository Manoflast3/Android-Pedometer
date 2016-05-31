package ca.uwaterloo.lab2_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by Tony Wang on 5/28/2016.
 */ //Rotation vector listener.

class AccelerometerListener implements SensorEventListener {
    TextView[] output = new TextView[2];
    private float[] accelerometerCur = new float[3];
    private float[] accelerometerMax = {0,0,0};
    float x,y,z,s;
    //put in 2 textviews, first is current and second is max.
    public AccelerometerListener(TextView[] outputView) {
        output = outputView;
    }

    public void onAccuracyChanged(Sensor s, int i) {
    }

    public void onSensorChanged(SensorEvent se) {
        if(se.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            for (int i = 0; i < 3 ; i++) {
                accelerometerCur[i] = se.values[i];
                if(accelerometerMax[i]<=se.values[i]){
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

        }
    }
}
