package com.example.sensordatalogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

        buttonGyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("sensorType", "gyro");
                startActivity(intent);
            }
        });

        buttonMagnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("sensorType", "magnet");
                startActivity(intent);
            }
        });

        buttonLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("sensorType", "light");
                startActivity(intent);
            }
        });

        buttonTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("sensorType", "temp");
                startActivity(intent);
            }
        });

    }
}
