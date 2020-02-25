package com.example.sensordatalogger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

class SensorDisplay extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mSensor;



    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_display);

        Intent i = getIntent();
        String sensorType = i.getStringExtra("sensorType");

        ArrayList<SensorData> sensorDataList = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.sensor_data_template, sensorDataList);
        ListView listView = findViewById(R.id.sensorDataListView);
        listView.setAdapter(adapter);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        switch (sensorType){
            case "accel" :
                mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorDataList.add(new SensorData("0", "X-Axis"));
                sensorDataList.add(new SensorData("0", "Y-Axis"));
                sensorDataList.add(new SensorData("0", "Z-Axis"));
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
