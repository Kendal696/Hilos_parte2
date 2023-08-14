package com.example.hilosyhandlersparte2recursividad;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio21 extends AppCompatActivity {

    private int number1 = 48;
    private int number2 = 36;
    private int mcd = 0;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    String result = (String) msg.obj;
                    // Display the result in the UI or perform other actions
                }
            }
        };

        startRecursiveMCDCalculation();
    }

    private void startRecursiveMCDCalculation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mcd = calculateMCD(number1, number2);
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "MCD of " + number1 + " and " + number2 + " is " + mcd;
                handler.sendMessage(message);
            }
        }).start();
    }

    private int calculateMCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return calculateMCD(b, a % b);
        }
    }
}
