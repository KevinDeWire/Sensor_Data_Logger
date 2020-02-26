package com.example.sensordatalogger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SensorDisplay extends Activity {
    //private static Sensor mSensor;
    //private static SensorManager sensorManager;
    private MySensorEventListener mySensorEventListener = new MySensorEventListener();
    private static ArrayList<SensorData> sensorDataList = new ArrayList<>();
    private static ListViewAdapter adapter;

    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_display);

        Intent i = getIntent();
        String sensorType = i.getStringExtra("sensorType");

        TextView sensorName = findViewById(R.id.textSensorName);

        adapter = new ListViewAdapter(this, sensorDataList);
        ListView listView = findViewById(R.id.sensorDataListView);
        listView.setAdapter(adapter);

        final SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor;

        Button backButton = findViewById(R.id.buttonBack);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sensorDataList.clear();
                assert sensorManager != null;
                sensorManager.unregisterListener(mySensorEventListener);
                finish();
            }
        });

        assert sensorType != null;
        assert sensorManager != null;
        switch (sensorType){
            case "accel" :
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.accel);
                XYZ_Label();
                break;

            case "gyro":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.gyro);
                XYZ_Label();
                break;

            case "magnet":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.magnet);
                XYZ_Label();
                break;

            case "light":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.light);
                AddData(0, "Lux");
                break;

            case "temp":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.temp);
                AddData(0, "degrees C");
                break;

            default:
                break;

        }

    }

    private void XYZ_Label(){
        AddData(0, "X-Axis");
        AddData(1, "Y-Axis");
        AddData(2, "Z-Axis");
    }

    private void AddData(int position, String label){
        //SensorData sensorData = new SensorData("", label);
        SensorData sensorData = new SensorData();
        sensorDataList.add(sensorData);
        sensorDataList.get(position).setLabel(label);
    }

    private void XYZ_Value(float x, float y, float z){
        sensorDataList.get(0).setValue(String.valueOf(x));
        sensorDataList.get(1).setValue(String.valueOf(y));
        sensorDataList.get(2).setValue(String.valueOf(z));
        adapter.notifyDataSetChanged();
    }

    private void Single_Value(float single) {
        sensorDataList.get(0).setValue(String.valueOf(single));
    }

    private class MySensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            //float x = event.values[0];
            //float y = event.values[1];
            //float z = event.values[2];
            if (sensorDataList.size() > 1){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                XYZ_Value(x, y, z);
            }
            else {
                float value = event.values[0];
                Single_Value(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

}
