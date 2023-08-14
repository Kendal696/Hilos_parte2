package com.example.hilosyhandlersparte2recursividad;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio23 extends AppCompatActivity {

    private String inputString = "Hello, World!";
    private String reversedString = "";

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

        startRecursiveStringReversal();
    }

    private void startRecursiveStringReversal() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reverseString(inputString);
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "Reversed string: " + reversedString;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void reverseString(String str) {
        if (str.length() == 0) {
            return;
        } else {
            reversedString += str.charAt(str.length() - 1);
            reverseString(str.substring(0, str.length() - 1));
        }
    }
}

