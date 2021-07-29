package com.example.secondlib

import android.content.Context
import android.widget.Toast

class ToastMessageSecond {
    companion object {
        fun show(c: Context, message: String) {
            Toast.makeText(c, message, Toast.LENGTH_LONG).show()
        }
    }
}