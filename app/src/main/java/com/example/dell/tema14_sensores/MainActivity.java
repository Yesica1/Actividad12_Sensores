package com.example.dell.tema14_sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener

{

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSenors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        String s = "";

        if (deviceSenors != null)
        {
            for (int i = 0; i < deviceSenors.size(); i++)
            {
                Log.wtf("Listando sensores", "Nombres: " + deviceSenors.get(i).getName());
                s+= deviceSenors.get(i).getName()+" -- ";
                textview.setText(s);
            }
        }


        if (mSensor == null)
        {
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!= null)
            {
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            else
            {
                textview.setText("No tengo el acelerometro");
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.wtf("onSensorChange"," " + sensorEvent.values[0] );
        textview.setText("X: "+sensorEvent.values[0]+ "     Y: "+sensorEvent.values[1] + "   Z: " + sensorEvent.values[2]);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
