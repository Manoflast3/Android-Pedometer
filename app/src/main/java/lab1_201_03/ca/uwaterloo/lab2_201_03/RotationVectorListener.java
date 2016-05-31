package lab1_201_03.ca.uwaterloo.lab2_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by Tony Wang on 5/28/2016.
 */ //Rotation vector listener.
class RotationVectorListener implements SensorEventListener {
    TextView[] output = new TextView[2];
    private float[] rotationVectorCur = new float[4];
    private float[] rotationVectorMax = {0,0,0,0};
    float x,y,z,s;
    //put in 2 textviews, first is current and second is max.
    public RotationVectorListener(TextView[] outputView) {
        output = outputView;
    }

    public void onAccuracyChanged(Sensor s, int i) {
    }

    public void onSensorChanged(SensorEvent se) {
        if(se.sensor.getType()==Sensor.TYPE_ROTATION_VECTOR){
            for (int i = 0; i < 4 ; i++) {
                rotationVectorCur[i] = se.values[i];
                if(rotationVectorMax[i]<se.values[i]){
                    rotationVectorMax[i]=se.values[i];
                }
            }
            x = rotationVectorCur[0];
            y = rotationVectorCur[1];
            z = rotationVectorCur[2];
            s = rotationVectorCur[3];
            String current = String.format("(%.2f, %.2f, %.2f)",x, y, z);
            x = rotationVectorMax[0];
            y = rotationVectorMax[1];
            z = rotationVectorMax[2];
            s = rotationVectorMax[3];
            String max = String.format("(%.2f, %.2f, %.2f)",x, y, z);
            //set the textviews.
            output[0].setText(current);
            output[1].setText(max);

        }
    }
}
