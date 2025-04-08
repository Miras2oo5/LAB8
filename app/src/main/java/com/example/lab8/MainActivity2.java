package com.example.myserviceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        serviceIntent = new Intent(this, MyService.class);
    }

    public void startService(View view) {
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View view) {
        stopService(serviceIntent);
    }
}
