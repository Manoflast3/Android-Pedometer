package lab1_201_03.ca.uwaterloo.lab2_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by Tony Wang on 5/28/2016.
 */ //Rotation vector listener.
    
class MagneticFieldListener implements SensorEventListener {
    TextView[] output = new TextView[2];
    private float[] magneticFieldCur = new float[3];
    private float[] magneticFieldMax = {0,0,0};
    float x,y,z,s;
    //put in 2 textviews, first is current and second is max.
    public MagneticFieldListener(TextView[] outputView) {
        output = outputView;
    }

    public void onAccuracyChanged(Sensor s, int i) {
    }

    public void onSensorChanged(SensorEvent se) {
        if(se.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            for (int i = 0; i < 3 ; i++) {
                magneticFieldCur[i] = se.values[i];
                if(magneticFieldMax[i]<=se.values[i]){
                    magneticFieldMax[i]=se.values[i];
                }
            }
            x = magneticFieldCur[0];
            y = magneticFieldCur[1];
            z = magneticFieldCur[2];
            String current = String.format("(%.2f, %.2f, %.2f)",x, y, z);
            x = magneticFieldMax[0];
            y = magneticFieldMax[1];
            z = magneticFieldMax[2];
            String max = String.format("(%.2f, %.2f, %.2f)",x, y, z);
            //set the textviews.
            output[0].setText(current);
            output[1].setText(max);

        }
    }
}
