package ca.uwaterloo.lab2_201_03;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.*;

import org.w3c.dom.Text;


//Rotation vector listener.
class RVListener implements SensorEventListener {
    TextView[] output = new TextView[2];
    private float lightCur;
    private float lightMax = 0;
    private float[] rotationVectorCur = new float[4];
    private float[] rotationVectorMax = {0,0,0,0};
    float x,y,z,s;
    //put in 2 textviews, first is current and second is max.
    public RVListener(TextView[] outputView) {
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


public class Lab1_201_03 extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private Sensor lightSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_201_03);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        TextView RVCurrent = (TextView)findViewById(R.id.rvCurrent) ;
        TextView RVMax = (TextView)findViewById(R.id.rvMax) ;
        TextView [] RVSensor = {RVCurrent, RVMax};

        SensorEventListener rotationVector = new RVListener(RVSensor);
        sensorManager.registerListener(rotationVector, rotationVectorSensor,SensorManager.SENSOR_DELAY_NORMAL);





//        String a = String.format("(%f, %f, %f)", x, y, z);
//
//        TextView tv2 =(TextView)findViewById(R.id.tv2);
//        tv2.setText("yayyayy");
//
//        LinearLayout ll = (LinearLayout)findViewById(R.id.ll1);
//        assert ll != null;
//
//        TextView tv1 = new TextView(getApplicationContext());
//        ll.setOrientation(LinearLayout.VERTICAL);
//        //TextView tv1 = new TextView(getApplicationContext());
//        double x=0.00300,y=0,z=9134134.13;
//        tv1.setText(s);
//        tv1.setTextColor(Color.BLACK);
//        ll.addView(tv1);


    }

}
