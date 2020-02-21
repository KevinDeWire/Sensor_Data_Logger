package com.example.sensordatalogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAccel = findViewById(R.id.buttonAccelerometer);
        Button buttonGyro = findViewById(R.id.buttonGyroscope);
        Button buttonMagnet = findViewById(R.id.buttonMagnetometer);
        Button buttonLight = findViewById(R.id.buttonLightSensor);
        Button buttonTemp = findViewById(R.id.buttonTempurature);

        final Intent intent = new Intent(this, SensorDisplay.class);

        buttonAccel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("sensorType", "accel");
                startActivity(intent);
            }
        });

    }
}
