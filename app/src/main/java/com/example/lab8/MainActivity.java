package com.example.myserviceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText randomCharacterEditText;
    private BroadcastReceiver broadcastReceiver;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomCharacterEditText = findViewById(R.id.editText_randomCharacter);
        broadcastReceiver = new MyBroadcastReceiver();
        serviceIntent = new Intent(getApplicationContext(), RandomCharacterService.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start:
                startService(serviceIntent);
                break;
            case R.id.button_end:
                stopService(serviceIntent);
                randomCharacterEditText.setText("");
                break;
        }
    }

    public void nextActivity(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("my.custom.action.tag.lab6");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            char data = intent.getCharExtra("randomCharacter", '?');
            randomCharacterEditText.setText(String.valueOf(data));
        }
    }
}
