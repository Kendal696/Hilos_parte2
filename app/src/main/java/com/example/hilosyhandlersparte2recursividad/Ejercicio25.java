package com.example.hilosyhandlersparte2recursividad;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio25 extends AppCompatActivity {

    private int number = 12345;
    private int digitSum = 0;

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
                }
            }
        };

        startRecursiveDigitSumCalculation();
    }

    private void startRecursiveDigitSumCalculation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                calculateDigitSum(number);
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "Sum of digits of " + number + " is " + digitSum;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void calculateDigitSum(int num) {
        if (num == 0) {
            return;
        } else {
            digitSum += num % 10;
            calculateDigitSum(num / 10);
        }
    }
}
