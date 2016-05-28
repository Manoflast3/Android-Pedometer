package lab1_201_03.uwaterloo.ca.lab1_201_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by manof on 2016-05-21.
 */
public class MagneticFieldEventListener implements SensorEventListener{
    TextView output;
    TextView maximum;
    List<Double> maxValues = new ArrayList<>(Arrays.asList(0.0,0.0,0.0));

    String xyz = "";
    String maxxyz = "";
    double x, y, z;

    public MagneticFieldEventListener(TextView outputView){
        output = outputView;
    }

    public void onAccuracyChanged(Sensor s, int i){

    }

    public void onSensorChanged(SensorEvent se){
        if (se.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            x = se.values[0];
            y = se.values[1];
            z = se.values[2];
            if (Math.abs(x)>maxValues.get(0)){
                maxValues.set(0, Math.abs(x));
            }
            if (Math.abs(y)>maxValues.get(1)){
                maxValues.set(1, Math.abs(y));
            }
            if (Math.abs(z)>maxValues.get(2)){
                maxValues.set(2, Math.abs(z));
            }
            xyz = String.format("(%f, %f, %f) \n" +
                    "Max Value: " + "\n" +
                    "(%f, %f, %f)", x, y, z, maxValues.get(0), maxValues.get(1), maxValues.get(2));
            output.setText(xyz);
            MainActivity.graph.addPoint(se.values);
        }
    }
}
