package com.example.secondlib

import android.content.Context
import android.widget.Toast
import com.example.secondlib.config.Config

class ToastMessageSecond {
    companion object {
        fun show(c: Context, message: String) {
            Toast.makeText(c, message + ":" + Config.FLAVOR_NAME, Toast.LENGTH_LONG).show()
        }
    }
}