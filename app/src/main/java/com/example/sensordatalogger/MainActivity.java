package com.example.sensordatalogger;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSION_REQUEST_CODE = 42;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onStart(){
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,PERMISSIONS_STORAGE, PERMISSION_REQUEST_CODE);
        }
    }

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
