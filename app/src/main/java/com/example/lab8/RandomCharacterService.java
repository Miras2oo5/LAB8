package com.example.lab8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class RandomCharacterService extends Service {
    private boolean isRandomGeneratorOn;
    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final String TAG = "RandomCharacterService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
        isRandomGeneratorOn = true;

        new Thread(() -> startRandomGenerator()).start();

        return START_STICKY;
    }

    private void startRandomGenerator() {
        while (isRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                int randomIdx = new Random().nextInt(alphabet.length);
                char randomChar = alphabet[randomIdx];

                Intent broadcastIntent = new Intent("my.custom.action.tag.lab6");
                broadcastIntent.putExtra("randomCharacter", randomChar);
                sendBroadcast(broadcastIntent);

                Log.i(TAG, "Random Character: " + randomChar);
            } catch (InterruptedException e) {
                Log.e(TAG, "Thread Interrupted", e);
            }
        }
    }

    private void stopRandomGenerator() {
        isRandomGeneratorOn = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomGenerator();
        Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
