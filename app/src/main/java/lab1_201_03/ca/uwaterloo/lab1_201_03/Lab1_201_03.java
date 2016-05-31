package lab1_201_03.ca.uwaterloo.lab1_201_03;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.*;


import java.util.Arrays;

import ca.uwaterloo.sensortoy.LineGraphView;

public class Lab1_201_03 extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor lightSensor, accSensor, rotationVectorSensor, magneticFieldSensor;
    private LineGraphView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_201_03);


        // Locks Screen Orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        TextView LightCurrent = (TextView)findViewById(R.id.lightCurrent);
        TextView LightMax = (TextView)findViewById(R.id.lightMax);
        TextView [] LightSensor = {LightCurrent, LightMax};

        TextView AccelerometerCurrent = (TextView)findViewById(R.id.accelerometerCurrent);
        TextView AccelerometerMax = (TextView)findViewById(R.id.accelerometerMax);
        TextView [] AccelerometerSensor = {AccelerometerCurrent, AccelerometerMax};

        TextView MFCurrent = (TextView)findViewById(R.id.mfCurrent);
        TextView MFMax = (TextView)findViewById(R.id.mfMax);
        TextView [] MFSensor = {MFCurrent, MFMax};

        TextView RVCurrent = (TextView)findViewById(R.id.rvCurrent);
        TextView RVMax = (TextView)findViewById(R.id.rvMax);
        TextView [] RVSensor = {RVCurrent, RVMax};

        SensorEventListener rotationVector = new RotationVectorListener(RVSensor);
        sensorManager.registerListener(rotationVector, rotationVectorSensor,SensorManager.SENSOR_DELAY_NORMAL);

        SensorEventListener light = new LightSensorListener(LightSensor);
        sensorManager.registerListener(light, lightSensor,SensorManager.SENSOR_DELAY_NORMAL);

        SensorEventListener accelerometer = new AccelerometerListener(AccelerometerSensor);
        sensorManager.registerListener(accelerometer, accSensor,SensorManager.SENSOR_DELAY_NORMAL);

        SensorEventListener magneticField = new MagneticFieldListener(MFSensor);
        sensorManager.registerListener(magneticField, magneticFieldSensor,SensorManager.SENSOR_DELAY_NORMAL);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        graph = new LineGraphView(getApplicationContext(),
                100,
                Arrays.asList("x", "y", "z"));
        linearLayout.addView(graph);
        graph.setVisibility(View.VISIBLE);


        SensorEventListener lineGraph = new LineGraphListener(graph);
        sensorManager.registerListener(lineGraph, accSensor,SensorManager.SENSOR_DELAY_NORMAL);


    }

}
