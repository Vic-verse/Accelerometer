package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final Object TAG = "MainActivity";
    public SensorManager sensorManager;
    Sensor accclerometer;
    SmsManager manager;
    TextView xValue, yValue, zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        zValue = findViewById(R.id.zValue);

        Log.d((String) TAG, "onCreate:initializing Sensor Services");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        manager = SmsManager.getDefault();
        accclerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accclerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d((String) TAG, "onCreate: Register accclerometer listener");


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d((String) TAG, "onSensorChanged: X:" + event.values[0] + "Y:" + event.values[1] + "Z:" + event.values[2]);

        if (event.values[0] > 67) {
            manager.sendTextMessage(" Put your number here ", "", "Your Friend is in Danger", null,
                    null);
            String number = "Put your Second number here";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        }
        else {
            xValue.setText("xValue:" +event.values[0]);
            yValue.setText("yValue:" +event.values[1]);
            zValue.setText("zValue:" +event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
