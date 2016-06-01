package ca.uwaterloo.lab2_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by Tony Wang on 5/28/2016.
 */ //Rotation vector listener.
class LightSensorListener implements SensorEventListener {
    TextView[] output = new TextView[2];
    private float lightCurrent = 0;
    private float lightMax = 0;
    float x,y,z,s;
    //put in 2 textviews, first is lightCurrent and second is lightMax.
    public LightSensorListener(TextView[] outputView) {
        output = outputView;
    }

    public void onAccuracyChanged(Sensor s, int i) {
    }

    public void onSensorChanged(SensorEvent se) {
        if(se.sensor.getType()==Sensor.TYPE_LIGHT){
            lightCurrent = se.values[0];
            if(lightCurrent>lightMax){
                lightMax = lightCurrent;
            }
            String current = String.format("%.2f",lightCurrent);

            String max = String.format("%.2f",lightMax);
            //set the textviews.
            output[0].setText(current);
            output[1].setText(max);

        }
    }
}
