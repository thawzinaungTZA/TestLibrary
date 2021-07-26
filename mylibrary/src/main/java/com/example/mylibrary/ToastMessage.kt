package com.example.mylibrary

import android.content.Context
import android.widget.Toast

class ToastMessage {
    fun toastMessage(c: Context, message: String) {
        Toast.makeText(c, message, Toast.LENGTH_LONG).show()
    }
}