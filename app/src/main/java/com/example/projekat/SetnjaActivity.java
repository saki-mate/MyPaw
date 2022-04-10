package com.example.projekat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class SetnjaActivity extends AppCompatActivity implements SensorEventListener {
    private TextView tvDeterktorKoraka;
    private SensorManager sensorManager;
    private Sensor mDetektorKoraka;
    private boolean imateDetektorKoraka;
    int brojKoraka = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setnja);


        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        tvDeterktorKoraka = findViewById(R.id.detektorKoraka);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null){
            mDetektorKoraka = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            imateDetektorKoraka = true;
        }else{
            tvDeterktorKoraka.setText("Nemate senzor za detektovanje koraka :(");
            imateDetektorKoraka = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == mDetektorKoraka){
            brojKoraka = (int) (brojKoraka + event.values[0]);
            tvDeterktorKoraka.setText(String.valueOf(brojKoraka));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null)
            sensorManager.registerListener(this, mDetektorKoraka, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null)
            sensorManager.unregisterListener(this, mDetektorKoraka);
        }

    }

