package com.example.javalib;

import android.content.Context;
import android.widget.Toast;

public class ToastMessageJava {
    public static void show(Context c, String message) {
        Toast.makeText(c, message, Toast.LENGTH_LONG).show();
    }
}
