package lab1_201_03.uwaterloo.ca.lab1_201_03;

import android.hardware.*;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

import ca.uwaterloo.sensortoy.LineGraphView;

public class MainActivity extends AppCompatActivity {
    private Sensor lightSensor;
    private Sensor magneticSensor;
    private SensorManager sensorManager;
    private TextView lightView;
    private TextView magneticView;
    protected static LineGraphView graph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SensorManager initialization
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Layout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);

        //Light
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightView = (TextView) findViewById(R.id.lightCurrent);
        SensorEventListener l = new LightSensorEventListener(lightView);
        sensorManager.registerListener(l, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        //Magnetic
        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        magneticView = (TextView) findViewById(R.id.mfCurrent);
        SensorEventListener m = new MagneticFieldEventListener(magneticView);
        sensorManager.registerListener(m, magneticSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        //Graph
        graph = new LineGraphView(getApplicationContext(),
                100,
                Arrays.asList("x", "y", "z"));
        linearLayout.addView(graph);
        graph.setVisibility(View.VISIBLE);


        linearLayout.setOrientation(LinearLayout.VERTICAL);
    }
}