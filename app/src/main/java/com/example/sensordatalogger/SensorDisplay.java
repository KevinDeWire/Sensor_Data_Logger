package com.example.sensordatalogger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

class SensorDisplay extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mSensor;

    TextView textData = findViewById(R.id.textData);
    TextView textLabel = findViewById(R.id.textLabel);

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_display);

        Intent i = getIntent();
        String sensorType = i.getStringExtra("sensorType");


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        switch (sensorType){
            case "accel" :
                mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                textLabel.setText("X-axis");
                break;

            default:
                break;

        }



    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        textData.setText(Float.toString(x));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
