package com.example.hilosyhandlersparte2recursividad;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio17 extends AppCompatActivity {

    private int[] numbers = {5, 9, 3, 11, 7, 2, 8, 6, 10, 4};
    private int currentIndex = 0;
    private int maxNumber = Integer.MIN_VALUE;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        startRecursiveSearch();
    }

    private void startRecursiveSearch() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                findMaxNumber();
            }
        }).start();
    }

    private void findMaxNumber() {
        if (currentIndex < numbers.length) {
            int current = numbers[currentIndex];
            if (current > maxNumber) {
                maxNumber = current;
            }
            currentIndex++;
            findMaxNumber();
        } else {
            Message message = handler.obtainMessage();
            message.what = 1;
            message.obj = "Max number: " + maxNumber;
            handler.sendMessage(message);
        }
    }
}