package ca.uwaterloo.lab2_201_03;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import ca.uwaterloo.sensortoy.LineGraphView;

public class Lab2_201_03 extends AppCompatActivity{
    private SensorManager sensorManager;
    private Sensor accSensor;
    private LineGraphView graph;
    // File Stuff
    private File file;
    private String filename = "footstep_data.txt";
    private String fullPath;
    protected static PrintWriter printWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_201_03);

        // Locks Screen Orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        TextView AccelerometerCurrent = (TextView)findViewById(R.id.accelerometerCurrent);
        TextView AccelerometerMax = (TextView)findViewById(R.id.accelerometerMax);
        TextView [] AccelerometerSensor = {AccelerometerCurrent, AccelerometerMax};

        TextView stepCount = (TextView)findViewById(R.id.stepCount);
        Button button = (Button) findViewById(R.id.start);

        SensorEventListener counter = new StepCounter(stepCount, button);


        sensorManager.registerListener(counter, accSensor,SensorManager.SENSOR_DELAY_FASTEST);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        graph = new LineGraphView(getApplicationContext(),
                100,
                Arrays.asList("x", "y", "z"));
        linearLayout.addView(graph);
        graph.setVisibility(View.VISIBLE);

        SensorEventListener lineGraph = new LineGraphListener(graph, AccelerometerSensor);
        sensorManager.registerListener(lineGraph, accSensor,SensorManager.SENSOR_DELAY_FASTEST);

        //Specifies File path
        try {
            fullPath = Environment.getDataDirectory().getAbsolutePath() + "/AcceloData";
            File dir = new File(fullPath);
            if (!dir.exists()){
                dir.mkdir();
            }
            file = new File(fullPath, filename);
            file.createNewFile();
            printWriter = new PrintWriter(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
