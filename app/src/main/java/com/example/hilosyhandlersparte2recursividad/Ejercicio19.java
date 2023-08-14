package com.example.hilosyhandlersparte2recursividad;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio19 extends AppCompatActivity {

    private int n = 10;
    private int sum = 0;

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

        startRecursiveSumCalculation();
    }

    private void startRecursiveSumCalculation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                calculateSum(1);
            }
        }).start();
    }

    private void calculateSum(int num) {
        if (num <= n) {
            sum += num;
            calculateSum(num + 1);
        } else {
            Message message = handler.obtainMessage();
            message.what = 1;
            message.obj = "Sum of first " + n + " natural numbers: " + sum;
            handler.sendMessage(message);
        }
    }
}
