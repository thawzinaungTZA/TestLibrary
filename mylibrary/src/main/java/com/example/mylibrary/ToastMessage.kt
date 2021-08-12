package com.example.mylibrary

import android.content.Context
import android.widget.Toast

class ToastMessage {
    companion object {
        fun show(c: Context, message: String) {
            if (BuildConfig.DEBUG) {
                Toast.makeText(c, message + "DEBUG", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(c, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}