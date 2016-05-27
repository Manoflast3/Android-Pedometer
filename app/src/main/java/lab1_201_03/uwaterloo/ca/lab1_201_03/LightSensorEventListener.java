package lab1_201_03.uwaterloo.ca.lab1_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by manof on 2016-05-21.
 */
public class LightSensorEventListener implements SensorEventListener{
    TextView output;

    public LightSensorEventListener(TextView outputView){
        output = outputView;
    }

    public void onAccuracyChanged(Sensor s, int i){

    }

    public void onSensorChanged(SensorEvent se){
        if (se.sensor.getType() == Sensor.TYPE_LIGHT){
            output.setText(String.valueOf(se.values[0]));
        }
    }
}
