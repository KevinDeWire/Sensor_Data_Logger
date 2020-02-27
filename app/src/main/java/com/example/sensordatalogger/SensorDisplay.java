package com.example.sensordatalogger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SensorDisplay extends Activity {
    //private static Sensor mSensor;
    //private static SensorManager sensorManager;
    private MySensorEventListener mySensorEventListener = new MySensorEventListener();
    private static ArrayList<SensorData> sensorDataList = new ArrayList<>();
    private static ListViewAdapter adapter;
    Boolean recording;
    File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "sensorData.csv");


    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_display);

        Intent i = getIntent();
        String sensorType = i.getStringExtra("sensorType");
        TextView sensorName = findViewById(R.id.textSensorName);
        Button startButton = findViewById(R.id.buttonStart);
        Button stopButton = findViewById(R.id.buttonStop);
        Button backButton = findViewById(R.id.buttonBack);
        final TextView recordingText = findViewById(R.id.textRecording);

        recording = false;

        adapter = new ListViewAdapter(this, sensorDataList);
        ListView listView = findViewById(R.id.sensorDataListView);
        listView.setAdapter(adapter);

        final SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor;

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordingText.setVisibility(View.VISIBLE);
                recording = true;
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordingText.setVisibility(View.INVISIBLE);
                recording = false;
            }
        });

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
                if (sensorDataList.isEmpty()){
                    XYZ_Label();
                }
                break;

            case "gyro":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.gyro);
                if (sensorDataList.isEmpty()){
                    XYZ_Label();
                }
                break;

            case "magnet":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.magnet);
                if (sensorDataList.isEmpty()){
                    XYZ_Label();
                }
                break;

            case "light":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.light);
                if (sensorDataList.isEmpty()){
                    AddData(0, "Lux");
                }
                break;

            case "temp":
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                sensorManager.registerListener(mySensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorName.setText(R.string.temp);
                if (sensorDataList.isEmpty()){
                    AddData(0, "degrees C");
                }
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
        SensorData sensorData = new SensorData();
        sensorDataList.add(sensorData);
        sensorDataList.get(position).setLabel(label);
    }

    private void XYZ_Value(float x, float y, float z){
        sensorDataList.get(0).setValue(String.valueOf(x));
        sensorDataList.get(1).setValue(String.valueOf(y));
        sensorDataList.get(2).setValue(String.valueOf(z));
        adapter.notifyDataSetChanged();
        if (recording){
            XYZ_Output(x, y, z);
        }
    }

    private void XYZ_Output(float x, float y, float z) {
        Date timestamp = Calendar.getInstance().getTime();
        TextView sensorName = findViewById(R.id.textSensorName);
        StringBuilder output = new StringBuilder();
        output.append(timestamp.toString()).append(", ").append(sensorName.getText()).append(", ").append(x).append(", ").append(y).append(", ").append(z).append("\n");
        OutputFile(output.toString());
    }

    private void Single_Value(float single) {
        sensorDataList.get(0).setValue(String.valueOf(single));
        adapter.notifyDataSetChanged();
        if (recording){
            Single_Output(single);
        }
    }

    private void Single_Output(float single) {
        Date timestamp = Calendar.getInstance().getTime();
        TextView sensorName = findViewById(R.id.textSensorName);
        StringBuilder output = new StringBuilder();
        output.append(timestamp.toString()).append(", ").append(sensorName.getText()).append(", ").append(single).append("\n");
        OutputFile(output.toString());
    }

    private void OutputFile (String outputString){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath(), true));
                out.write(outputString);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
